package com.yiming.learn.spring.tiny.beans.xml;

import com.yiming.learn.spring.tiny.AbstractBeanDefinitionReader;
import com.yiming.learn.spring.tiny.BeanDefinition;
import com.yiming.learn.spring.tiny.BeanReference;
import com.yiming.learn.spring.tiny.PropertyValue;
import com.yiming.learn.spring.tiny.PropertyValues;
import com.yiming.learn.spring.tiny.beans.io.ResourceLoader;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * ${DESCRIPTION}
 *
 * @author yiming
 * @since 2018-07-31 14:51.
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        // 解析bean
        registerBeanDefinitions(document);
        inputStream.close();
    }

    private void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if (childNodes.item(i) instanceof Element) {
                Element e = (Element) childNodes.item(i);
                processElement(e);
            }
        }
    }

    private void processElement(Element e) {
        String name = e.getAttribute("id");
        String clazz = e.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(clazz);
        processProperties(e, beanDefinition);
        getRegistry().put(name, beanDefinition);
    }

    private void processProperties(Element e, BeanDefinition beanDefinition) {
        NodeList propertyList = e.getElementsByTagName("property");
        for (int i = 0; i < propertyList.getLength(); i++) {
            Node propertyNode = propertyList.item(i);
            if (propertyNode instanceof Element) {
                Element element = (Element) propertyNode;
                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                String name = element.getAttribute("name");
                String value = element.getAttribute("value");
                if (value != null && value.length() > 0) {
                    propertyValues.addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = element.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException(
                                "Configuration error: <property> element for property ["
                                        + name + "] must be specified a ref or value!");
                    }
                    BeanReference reference = new BeanReference();
                    reference.setName(ref);
                    propertyValues.addPropertyValue(new PropertyValue(name, reference));
                }
            }
        }
    }
}
