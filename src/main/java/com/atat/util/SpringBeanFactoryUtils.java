/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring容器工具类
 * 
 * @author ligw
 * @version $Id SpringBeanFactoryUtils.java, v 0.1 2017-08-09 14:24 ligw Exp $$
 */
public class SpringBeanFactoryUtils {

    private static ApplicationContext applicationContext;

    // 获取applicationContext
    public static ApplicationContext getApplicationContext() {
        if (null == applicationContext) {
            applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        }
        return applicationContext;
    }

    // 通过name获取 Bean
    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    // 通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return getApplicationContext().getBean(beanName, clazz);
    }
}
