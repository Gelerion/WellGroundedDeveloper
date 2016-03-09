package com.denis.design_patterns.mixin_pattern.standart;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public interface ReflectionObjectMixin<T extends ReflectionObjectMixin<T>> extends ObjectMixin<T> {

    @Override
    default Object[] members() {
        return new MethodUtil(getClass()) {

            @Override
            protected Object onMethod(final Method method) {
                try {
                    return method.invoke(ReflectionObjectMixin.this, (Object[]) null);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalStateException("Unexpected invocation error", e);
                }
            }
        }.toObjects();
    }

    @Override
    default Object[] names() {

        return new MethodUtil(getClass()) {

            @Override
            protected Object onMethod(final Method method) {
                return method.getName().substring(MethodUtil.INGRESS.length());
            }
        }.toObjects();

    }

    abstract class MethodUtil {
        public static final String INGRESS = "get";
        public static final Set<String> EXCLUDED_METHODS = new HashSet<>(Arrays.asList("getClass"));
        private final Class<?> clazz;

        private MethodUtil(Class<?> clazz) {
            this.clazz = clazz;
        }

        private static List<Method> obtainGetMethods(Class<?> clazz) {
            final List<Method> result = new ArrayList<>();
            final Method[] methods = clazz.getMethods();
            for (final Method method : methods) {
                final String methodName = method.getName();
                if (methodName.startsWith(INGRESS) &&
                        method.getParameterCount() == 0 &&
                        !EXCLUDED_METHODS.contains(methodName)) {
                    result.add(method);
                }
            }
            Collections.sort(result, METHOD_COMPARATOR);
            return result;
        }

        protected abstract Object onMethod(Method method);

        public Object[] toObjects() {
            final List<Object> result = new ArrayList<>();
            for (final Method method : MethodUtil.obtainGetMethods(clazz)) {
                result.add(onMethod(method));
            }
            return result.toArray();
        }

        private static final MethodComparator METHOD_COMPARATOR = new MethodComparator();

        private static class MethodComparator implements Comparator<Method> {

            @Override
            public int compare(Method o1, Method o2) {
                int classCompare = o1.getDeclaringClass().getName().compareTo(o2.getDeclaringClass().getName());

                if (classCompare != 0) {
                    return classCompare;
                }

                return o1.getName().compareTo(o2.getName());
            }
        }
    }
}










































