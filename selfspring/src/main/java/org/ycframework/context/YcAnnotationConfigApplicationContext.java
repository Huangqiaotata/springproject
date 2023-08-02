package org.ycframework.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.annotation.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class YcAnnotationConfigApplicationContext implements YcApplicationContext {

    private Logger logger = LoggerFactory.getLogger(YcAnnotationConfigApplicationContext.class);
    //存每个待托管的Bean的定义信息
    private Map<String, YcBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    //存每个实例化后的bean
    private Map<String, Object> beanMap = new ConcurrentHashMap<>();
    //存系统属性 db.properties
    private Properties pros;

    public YcAnnotationConfigApplicationContext(Class... configClasses) {
        try {
            //读取系统的属性  存好
            pros = System.getProperties();
            List<String> toScanPackagePath = new ArrayList<>();

            //TODO 注解的重要代码  解析器
            for (Class cls : configClasses) {
                if (cls.isAnnotationPresent(YcConfiguration.class) == false) {
                    continue;
                }
                String[] basePackages = null;
                //扫描配置类上的注解 @YcComponentScan 读取要扫描的包
                if (cls.isAnnotationPresent(YcComponentScan.class)) {
                    //如果有 则说明此配置类上有 @YcComponentScan 注解 则读取 basePackages
                    YcComponentScan ycComponentScan = (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                    basePackages = ycComponentScan.basePackages();
                    if (basePackages == null || basePackages.length <= 0) {
                        //如果 @YcComponentScan 后没有自定义的路径 则为该类所在的包路径及其子包中
                        basePackages = new String[1];
                        basePackages[0] = cls.getPackage().getName();
                    }
                    logger.info(cls.getName() + "类上有 @YcComponentScan 注解 它要扫描的路径：" + basePackages[0]);
                }
                //开始递归扫描这些 basePackages 包下的bean 并加载包装成 BeanDefinition 对象  存到 beanDefinitionMap （未实例化）
                recursiveLoadBeanDefinition(basePackages);
            }
            //循环 beanDefinitionMap 创建bean（判断是否为懒加载  是否为单例） 存到beanMap （实例化的）
            createBean();
            //循环所有托管的 beanMap 中的bean 看属性和方法上是否有 @Autowired  @Resource  @Value  考虑DI
            doDi();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始扫描这些 basePackages 包下的bean 并加载包装成 BeanDefinition 对象  存到 beanDefinitionMap
     */
    private void doDi() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        //循环的是 beanMap 这是托管bean
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            String beanId = entry.getKey();
            Object beanObj = entry.getValue();
            //情况一：属性上有 @YcResource 注解的情况
            //TODO 情况二： 方法上有
            //TODO 情况三  构造方法上
            Field[] fields = beanObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(YcResource.class)) {
                    YcResource ycResource = field.getAnnotation(YcResource.class);
                    String toDiBeanId = ycResource.name();
                    //从beanMap 中找 是否为singleton 是否为lazy
                    Object obj = getToDiBean(toDiBeanId);
                    //注入
                    field.setAccessible(true); //因为属性是private的 所以要将它 accessible 设为 true
                    field.set(beanObj,obj); //userImpl.userDao = userDaoImpl
                }
            }
        }
    }

    /**
     * 从beanMap (已实例化) 中找 是否为singleton 是否为lazy
     * 加上 @YcLazy 注解后 因为懒 beanMap 中没有那个class的初始化
     * @param toDiBeanId
     * @return
     */
    private Object getToDiBean(String toDiBeanId) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (beanMap.containsKey(toDiBeanId)) {
            return beanMap.get(toDiBeanId);
        }
        //判断beanMap 中没有此bean 是否因为lazy
        if (! beanDefinitionMap.containsKey(toDiBeanId)) {
            throw new RuntimeException("spring 容器中没有加载此class：" + toDiBeanId);
        }
        YcBeanDefinition bd = beanDefinitionMap.get(toDiBeanId);
        if (bd.isLazy()) {
            //是因为懒 所以没有托管
            String classPath = bd.getClassInfo();
            Object beanObj = Class.forName(classPath).newInstance();
            beanMap.put(toDiBeanId, beanObj);
            return beanObj;
        }
        //是否因为prototype
        if (bd.getScope().equalsIgnoreCase("prototype")) {
            //是因为prototype 所以没有托管
            String classPath = bd.getClassInfo();
            Object beanObj = Class.forName(classPath).newInstance();
//            beanMap.put(toDiBeanId, beanObj); //  原型模式下 每次getBean 创建一次bean  所以beanMap 不存
            return beanObj;
        }
        return null;
    }

    /**
     * 循环 beanDefinitionMap 创建bean（判断是否为懒加载  是否为单例） 存到beanMap
     */
    private void createBean() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //对map的循环  Map.Entry
        for (Map.Entry<String, YcBeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanId = entry.getKey();
            YcBeanDefinition ybd = entry.getValue();
            if (!ybd.isLazy() && !ybd.getScope().equalsIgnoreCase("prototype")) {
                String classInfo = ybd.getClassInfo();
                Object obj = Class.forName(classInfo).newInstance();
                beanMap.put(beanId, obj);
                logger.trace("spring容器托管了：" + beanId + " & " + classInfo);
            }
        }
    }

    /**
     * 开始扫描这些 basePackages 包下的bean 并加载包装成 BeanDefinition 对象  存到 beanDefinitionMap
     *
     * @param basePackages
     */
    private void recursiveLoadBeanDefinition(String[] basePackages) {
        for (String basePackage : basePackages) {
            //将包名中的 . 替换成路径中的 /
            String packagePath = basePackage.replaceAll("\\.", "/");
            // Enumeration  集合  URL：每个资源的路径
            Enumeration<URL> files = null;
            try {
                files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
                //循环这个files  看是否是我要加载的资源
                while (files.hasMoreElements()) {
                    URL url = files.nextElement();
                    logger.trace("当前正在递归加载：" + url.getFile());
                    //查找此包下的类          com/yc全路径   com/yc包名
                    findPackageClasses(url.getFile(), basePackage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查找此包下的类
     * @param packagePath
     * @param basePackage
     */
    private void findPackageClasses(String packagePath, String basePackage) {
        //路径异常的处理 将路径前的 / 去掉
        if (packagePath.startsWith("/")) {
            packagePath = packagePath.substring(1);
        }
        //取这个路径下所有的字节码文件 因为目录下可能有其他资源
        File file = new File(packagePath);
        //只取后缀名为 .class 的字节码
        //写法一：接口的匿名内部类写法
        File[] classFiles = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".class") || pathname.isDirectory()) {
                    return true;
                }
                return false;
            }
        });
        logger.trace("为class文件的有：" + classFiles);
        //写法二：lambda写法
//        File[] classFiles = file.listFiles((pathname) -> {
//            if (pathname.getName().endsWith(".class") || pathname.isDirectory()) {
//                return true;
//            }
//            return false;
//        });
        //循环此classFiles
        if (classFiles == null || classFiles.length <= 0) {
            return;
        }
        for (File cf : classFiles) {
            if (cf.isDirectory()) {
                //继续递归
                logger.trace("递归" + cf.getAbsolutePath() + "，它对应的包名应该为" + (basePackage + "." + cf.getName()));
                findPackageClasses(cf.getAbsolutePath(), basePackage + "." + cf.getName());
            } else {
                //是class文件 则取出文件  判断此文件对应的class中是否有 @Component 注解
                URLClassLoader uc = new URLClassLoader(new URL[]{});
                Class cls = null;
                try {
                    cls = uc.loadClass(basePackage + "." + cf.getName().replaceAll(".class", ""));
                    //TODO 可以支持 子注解
                    if (cls.isAnnotationPresent(YcComponent.class) || cls.isAnnotationPresent(YcConfiguration.class)
                            || cls.isAnnotationPresent(YcRepository.class) || cls.isAnnotationPresent(YcService.class)
                            || cls.isAnnotationPresent(YcController.class)) {
                        logger.info("加载到一个待托管的类：" + cls.getName());
                        //TODO 包装成BeanDefinition
                        YcBeanDefinition bd = new YcBeanDefinition();
                        if (cls.isAnnotationPresent(YcLazy.class)) {
                            bd.setLazy(true);
                        }
                        if (cls.isAnnotationPresent(YcScope.class)) {
                            YcScope ycScope = (YcScope) cls.getAnnotation(YcScope.class);
                            String scope = ycScope.value();
                            bd.setScope(scope);
                        }
                        // classInfo 中保存这个待托管的类的包路径  com.yc.dao.UserDaoImpl
                        // cls.newInstance()
                        bd.setClassInfo(basePackage + "." + cf.getName().replaceAll(".class", ""));
                        //存到 beanDefinitionMap 中  ”beaId“ -> BeanDefinition 对象
                        String beanId = getBeanId(cls);
                        this.beanDefinitionMap.put(beanId, bd);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 直接用首字母小写的类名
     * 或者用自定义的beanId
     * 获取一个待托管类的beanId
     * @param cls
     * @return
     */
    private String getBeanId(Class cls) {
        YcComponent ycComponent = (YcComponent) cls.getAnnotation(YcComponent.class);
        YcController ycController = (YcController) cls.getAnnotation(YcController.class);
        YcService ycService = (YcService) cls.getAnnotation(YcService.class);
        YcRepository ycRepository = (YcRepository) cls.getAnnotation(YcRepository.class);
        //用类的全路径做beanId
        YcConfiguration ycConfiguration = (YcConfiguration) cls.getAnnotation(YcConfiguration.class);
        if (ycConfiguration != null) {
            return cls.getSimpleName(); //全路径名
        }
        String beanId = null;
        if (ycComponent != null) {
            beanId = ycComponent.value();
        } else if (ycController != null) {
            beanId = ycController.value();
        } else if (ycService != null) {
            beanId = ycService.value();
        } else if (ycRepository != null) {
            beanId = ycRepository.value();
        }

        // 无自定义的 beanid 时 userBizImpl 为空
        if (beanId == null || "".equalsIgnoreCase(beanId)) {
            String typename = cls.getSimpleName();
            beanId = typename.substring(0,1).toLowerCase()+typename.substring(1);
        }
        logger.info("当前的beanId = " + beanId);
        return beanId;
    }

    @Override
    public Object getBean(String beanid) {
        YcBeanDefinition bd = this.beanDefinitionMap.get(beanid);
        if (bd == null) {
            throw new RuntimeException("容器中没有加载到此bean");
        }
        String scope = bd.getScope();
        if ("prototype".equalsIgnoreCase(scope)) {
            //原型 多例模式 每次getBean时需要重新创建
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
                //这种不需要保存到beanMap中
                return obj;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (this.beanMap.containsKey(beanid)) {
            return this.beanMap.get(beanid);
        }
        if (bd.isLazy()) {
            Object obj = null;
            try {
                obj = Class.forName(bd.getClassInfo()).newInstance();
                //懒加载的bean需要保存
                this.beanMap.put(beanid, obj);
                return obj;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
