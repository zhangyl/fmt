package com.tqmall.zeus.controller.tools;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum ClassUtil {
    ;

    private static final Set<Class<?>> primitiveClasses = new HashSet<Class<?>>();
    private static final Map<Class<?>, Class<?>> primitiveClassesMap = new HashMap<>();

    static {
        primitiveClasses.add(boolean.class);
        primitiveClassesMap.put(boolean.class, Boolean.class);

        primitiveClasses.add(byte.class);
        primitiveClassesMap.put(byte.class, Byte.class);

        primitiveClasses.add(short.class);
        primitiveClassesMap.put(short.class, Short.class);

        primitiveClasses.add(int.class);
        primitiveClassesMap.put(int.class, Integer.class);

        primitiveClasses.add(long.class);
        primitiveClassesMap.put(long.class, Long.class);

        primitiveClasses.add(float.class);
        primitiveClassesMap.put(float.class, Float.class);

        primitiveClasses.add(double.class);
        primitiveClassesMap.put(double.class, Double.class);


        primitiveClasses.add(Boolean.class);
        primitiveClasses.add(Byte.class);
        primitiveClasses.add(Short.class);
        primitiveClasses.add(Integer.class);
        primitiveClasses.add(Long.class);
        primitiveClasses.add(Float.class);
        primitiveClasses.add(Double.class);

        primitiveClasses.add(BigInteger.class);
        primitiveClasses.add(BigDecimal.class);
        primitiveClasses.add(String.class);
    }

    /**
     * 判断是否是java 基本类型
     *
     * @param clazz
     * @return
     */
    public static boolean isPrimtiveType(Class<?> clazz) {
        return primitiveClasses.contains(clazz);
    }

    public static Class<?> getPrimtiveTypeWrapperClass(Class<?> clazz) {
        Class<?> targetClass = primitiveClassesMap.get(clazz);
        if (targetClass != null) {
            return targetClass;
        }
        return clazz;
    }
}
