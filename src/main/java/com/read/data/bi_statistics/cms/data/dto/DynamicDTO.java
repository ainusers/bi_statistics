package com.read.data.bi_statistics.cms.data.dto;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import java.io.Serializable;
import java.util.Map;

/**
 * @author: tianyong
 * @Time: 2019/5/20 20:14
 * @description:动态bean
 */
public class DynamicDTO implements Serializable {
    //SerialversionUID(保证先序列化，再反序列化，该值不变)
    private static final long serialVersionUID = 1905122041950251207L;
    //目标对象
    private Object target;
    //属性集合
    private BeanMap bean;

    //构造函数
    public DynamicDTO(Class superclass, Map<String, Class> propertyMap) {
        this.target = generateBean(superclass, propertyMap);
        this.bean = BeanMap.create(this.target);
    }


    //添加属性和值
    public void setValue(String property, Object value) {
        bean.put(property, value);
    }

    //获取属性和值
    public Object getValue(String property) {
        return bean.get(property);
    }

    //获取对象
    public BeanMap getObject() {
        return this.bean;
    }


    /**
      * @author: tianyong
      * @time: 2019/5/20 20:15
      * @description:根据属性生成对象
      */
    private Object generateBean(Class superclass, Map<String, Class> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        if (null != superclass) {
            generator.setSuperclass(superclass);
        }
        BeanGenerator.addProperties(generator, propertyMap);
        return generator.create();
    }

    @Override
    public String toString() {
        return "DynamicDTO{" +
                "bean=" + bean +
                '}';
    }
}
