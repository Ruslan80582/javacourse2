package ru.edu.task2;

import ru.edu.task2.annotations.Cache;
import ru.edu.task2.annotations.Mutator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class CustomInvocationHandler implements InvocationHandler {
    Object proxyObj;
    private final HashMap<Integer, Object> argsToOutput = new HashMap<>();

    public CustomInvocationHandler(Object proxyObj) {
        this.proxyObj = proxyObj;
    }

    public static <T> T createProxy(final T obj) {
        return (T) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new CustomInvocationHandler(obj));
    }

    public int calcHash(Method method, Object[] args) {
        int h = method.hashCode();
        if (args == null) return h;
        for (final Object o : args) {
            h = h * 65599 + (o == null ? 0 : o.hashCode());
        }
        return h;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object inputObj = null;
        if (method.isAnnotationPresent(Mutator.class)) {
            argsToOutput.clear();
            inputObj = method.invoke(proxyObj, args);
        } else if (method.isAnnotationPresent(Cache.class)) {
            Integer hashCode = Integer.valueOf(calcHash(method, args));
            inputObj = argsToOutput.get(hashCode);
            if (inputObj == null) {
                inputObj = method.invoke(proxyObj, args);
                argsToOutput.put(hashCode, inputObj);
            }
        }
        return inputObj;
    }
}
