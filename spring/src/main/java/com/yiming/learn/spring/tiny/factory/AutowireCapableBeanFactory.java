package com.yiming.learn.spring.tiny.factory;

import com.yiming.learn.spring.tiny.BeanDefinition;
import java.util.Objects;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-27 17:43.
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 初始化bean
     *
     * @param beanDefinition bean定义
     */
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        if (Objects.isNull(beanDefinition)) {
            return null;
        }
        try {
            return beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
