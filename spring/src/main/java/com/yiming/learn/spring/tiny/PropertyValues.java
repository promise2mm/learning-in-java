package com.yiming.learn.spring.tiny;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性键值对列表
 *
 * @author yiming
 * @since 2018-07-31 14:02.
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    /**
     * 这里可以对add操作前做一些操作, 比如去除重复properties 这也是为什么要将<code List<PropertyValue>>封装成一个对象的原因
     */
    public void addPropertyValue(PropertyValue propertyValue) {
        // todo pre-do sth.
        propertyValues.add(propertyValue);
    }
}
