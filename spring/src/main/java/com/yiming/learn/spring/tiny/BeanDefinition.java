package com.yiming.learn.spring.tiny;

/**
 * bean定义
 *
 * @author yiming
 * @since 2018-07-27 17:09.
 */
public class BeanDefinition {

    /**
     * bean对象
     */
    private Object bean;

    /**
     * bean所属类
     */
    private Class beanClass;

    /**
     * bean 类全名称
     */
    private String beanClassName;

    /**
     * bean的属性列表
     */
    private PropertyValues propertyValues = new PropertyValues();

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
