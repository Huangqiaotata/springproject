package com.yc.spring.Test6_conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional({SystemCondition.class})
public class NetConnection {
}