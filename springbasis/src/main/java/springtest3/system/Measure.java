package springtest3.system;

/**
 * 测量窗口
 */
public interface Measure {
    /**
     *
     * @param obj  待测量的对象
     * @return  测量的值
     */
    public double doMeasure(Object obj);
}
