package com.demo.test;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

/**
 * @auther gzhen
 * @date 2023-10-09  11:26
 * @description
 */
@Slf4j
public class BeanCopyTest {

    @Test
    public void beanCopyMap() {
        Map<String, String> source = new HashMap<>();
        source.put("name", "张三");
        source.put("gender", "nan");
        Person person = new Person();
        BeanUtils.copyProperties(source, person);
        System.out.println(person);
    }

    @Test
    public void beanCopyJsonObject() {
        JSONObject source = new JSONObject();
        source.put("name", "张三");
        source.put("gender", "nan");
        Person person = new Person();
        BeanUtils.copyProperties(source, person);
        System.out.println(person);
    }

    @Test
    public void testPD() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Map<String, String> source = new HashMap<>();
        source.put("name", "张三");
        source.put("gender", "nan");
        Person target = getTarget(source, Person.class);
        Person person = new Person();
        person.setName("李四");
        person.setGender("nv");
        Set<String> excludeSet = new HashSet<>();
        excludeSet.add("name");
        List<ComparableResult> comparableResults = compareInstance(person, target, excludeSet);
        System.out.println(comparableResults);
    }

    @Test
    public void test(){
        System.out.println(Boolean.TRUE.equals(true));
    }

    /**
     * 返回所有字段
     *
     * @param target
     * @return
     */
    public static Map<String, Field> getFields(Object target) {
        if (target == null) {
            return Collections.EMPTY_MAP;
        }
        Field[] fields = target.getClass().getDeclaredFields();
        Map<String, Field> rsMap = new HashMap<>(fields.length);
        Stream.of(fields).forEach(field -> {
            field.setAccessible(true);
            rsMap.put(field.getName(), field);
        });
        return rsMap;
    }

    /**
     * 比较两个对象属性值是否相同,如果不同返回修改过的属性信息集合,包括：字段名,原始数据值，新值，更改类型
     *
     * @param source 原始对象
     * @param target 新对象
     * @param excludeSet 排除比较字段
     * @return List<ComparableResult>  变化后的数据集合
     */
    public static List<ComparableResult> compareInstance(Object source, Object target, Set<String> excludeSet) {

        List<ComparableResult> comparableResultList = new ArrayList<>();
        // 获取字段集合
        Map<String, Field> sourceFields = getFields(source);
        Map<String, Field> targetFields = getFields(target);
        targetFields.keySet().stream()
                .filter(key -> excludeSet == null || !excludeSet.contains(key))
                .forEach(key -> {
                    try {
                        ComparableResult comparableResult = null;
                        Field targetField = targetFields.get(key);
                        Field sourceField = sourceFields.get(key);
                        if (!Modifier.isPublic(targetField.getModifiers())) {
                            targetField.setAccessible(true);
                        }
                        if (!Modifier.isPublic(sourceField.getModifiers())) {
                            sourceField.setAccessible(true);
                        }
                        Object targetFieldValue = targetField.get(target);
                        Object sourceFieldValue = sourceField.get(source);
                        if (targetFieldValue != null) {
                            if (sourceFieldValue == null) {
                                comparableResult = new ComparableResult();
                                comparableResult.setFieldName(key);
                                comparableResult.setFieldContent(null);
                                comparableResult.setNewFieldContent(targetFieldValue);
                                comparableResult.setHanderType("DELETE");
                            } else if (!targetFieldValue.equals(sourceFieldValue)) {
                                comparableResult = new ComparableResult();
                                comparableResult.setFieldName(key);
                                comparableResult.setFieldContent(sourceFieldValue);
                                comparableResult.setNewFieldContent(targetFieldValue);
                                comparableResult.setHanderType("UPDATE");
                            }
                        } else {
                            if (sourceFieldValue != null) {
                                comparableResult = new ComparableResult();
                                comparableResult.setFieldName(key);
                                comparableResult.setFieldContent(sourceFieldValue);
                                comparableResult.setNewFieldContent(null);
                                comparableResult.setHanderType("ADD");
                            }
                        }
                        if (comparableResult != null) {
                            comparableResultList.add(comparableResult);
                        }
                    } catch (Exception e) {
                        log.error("对比异常~~~");
                    }
                });
        return comparableResultList;
    }


    public static <T> T getTarget(Map<String, String> source, Class<T> targetClass) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (CollectionUtils.isEmpty(source)) {
            return null;
        }
        T target = targetClass.newInstance();
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(targetClass);
        for (PropertyDescriptor targetPD : propertyDescriptors) {
            String fieldName = targetPD.getName();
            if (source.containsKey(fieldName)) {
                Method writeMethod = targetPD.getWriteMethod();
                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                    writeMethod.setAccessible(true);
                }
                writeMethod.invoke(target, source.get(fieldName));
            }
        }
        return target;
    }
}

@Data
class Person {

    private String name;

    private String gender;

    private Integer age;

    private BigDecimal amount;
}

@Data
class IdCard {

    private String idNo;

    private String name;

}

@Data
class ComparableResult {

    /**
     * 变更字段
     */
    private String fieldName;

    /**
     * 变更前类的内容容
     */
    private Object fieldContent;
    /**
     * 变更后类的内容容
     */
    private Object newFieldContent;
    /**
     * 变更的枚举类型
     */
    private String handerType;

}