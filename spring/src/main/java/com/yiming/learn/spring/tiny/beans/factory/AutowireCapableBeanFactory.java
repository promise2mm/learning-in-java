package com.yiming.learn.spring.tiny.beans.factory;

import com.yiming.learn.spring.tiny.BeanDefinition;
import com.yiming.learn.spring.tiny.BeanReference;
import com.yiming.learn.spring.tiny.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 可自动装配内容的BeanFactory
 *
 * @author yiming
 * @since 2018-07-27 17:43.
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            Object value = propertyValue.getValue();

            if (value instanceof BeanReference) {
                BeanReference reference = (BeanReference) value;
                value = getBean(reference.getName());
            }
            try {
                String methodName = "set" + propertyValue.getName().substring(0, 1).toUpperCase() +
                        propertyValue.getName().substring(1);
                Method method = bean.getClass().getDeclaredMethod(methodName, value.getClass());
                method.setAccessible(true);
                method.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }
}
