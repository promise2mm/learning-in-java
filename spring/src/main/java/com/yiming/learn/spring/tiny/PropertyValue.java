package com.yiming.learn.spring.tiny;

/**
 * 属性对象封装
 *
 * @author yiming
 * @since 2018-07-31 14:01.
 */
public class PropertyValue {

    private String name;

    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
