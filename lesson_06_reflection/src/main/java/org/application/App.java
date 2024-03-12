package org.application;

import org.application.reflection.Init;
import org.application.reflection.LazyService;
import org.application.reflection.Service;
import org.application.reflection.SimpleService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class App {
    private static final Map<String, Object> MAP = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        LazyService lazyService = new LazyService();
        Class<? extends LazyService> aClass = lazyService.getClass();
        Class<? extends LazyService> aClass1 = LazyService.class;
        Class<?> aClass2 = Class.forName("org.application.reflection.LazyService");

//        inspectService(LazyService.class);
//        inspectService(SimpleService.class);
//        inspectService(String.class);

//        findMethod(LazyService.class);

        createService("org.application.reflection.LazyService");
        callInitMethod("org.application.reflection.LazyService");
    }

    public static void inspectService(Class<?> service) {
        if (service.isAnnotationPresent(Service.class)) {
            Service annotation = service.getAnnotation(Service.class);
            System.out.println(annotation.name());
        } else {
            System.out.println("Didn't find annotation" + Service.class);
        }
    }

    public static void findMethod(Class<?> service) {
        Method[] methods = service.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            if (method.isAnnotationPresent(Init.class)) {
                System.out.println("Method with init annotation was found");
            }
        }
    }

    public static void createService(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = Class.forName(className);
        if (aClass.isAnnotationPresent(Service.class)) {
            Constructor<?> constructor = aClass.getConstructor(null);
            Object instance = constructor.newInstance(null);
            MAP.put(className, instance);
            System.out.println(MAP);
        } else {
            System.out.println("Didn't find annotation");
        }
    }

    public static void callInitMethod(String className) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(className);
        Method[] methods = aClass.getMethods();
        Service annotation = aClass.getAnnotation(Service.class);
        if (annotation != null && annotation.lazyLoad()) {
            for (Method method : methods) {
                if (method.isAnnotationPresent(Init.class)) {
                    try {
                        method.invoke(MAP.get(className));
                    } catch (Exception e) {
                        Init annotation1 = method.getAnnotation(Init.class);
                        if (annotation1.isSuppressException()) {
                            System.out.println("Reflection approach " + e.getCause().getMessage());
                        } else {
                            throw new RuntimeException();
                        }
                    }
                }
            }
        } else {
            System.out.println("I am not lazy");
        }
    }
}
