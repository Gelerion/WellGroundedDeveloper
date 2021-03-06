package com.denis.design_patterns.mixin_pattern.standart;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * We still have the nuisance that overridden classes like anonymous classes
 * are not equal to seemingly equal classes. For example, bert and bert2 are not equal
 * even though they have the same bean properties. Remember that only the toString() differs
 * and this should not make them different. By introducing a new method called compareClass()
 * we can use this class instead of the getClass() and compare them. Now we are in charge what
 * class we elect to return and can set a new "watermark" whenever we think that an inherited class
 * shall never be equal to its super class. The neat thing with the solution below is that we will
 * also have a default compareClass() that automatically will determine the highest class that
 * also is an ObjectMixin. So, you get the initial base class compareClass() for free. Note how
 * the defaultBaseCompareObjectMixinClass() is hid in the inner class MethodUtil so it will not
 * be exposed to the implementing class. The  defaultBaseCompareObjectMixinClass() recursively
 * inspects super classes and when a super class does not implement ObjectMixin, it returns.
 */
public interface ObjectMixin<T extends ObjectMixin<T>> {

    Object[] members();

    Object[] names();

    Comparable<?>[] compareToMembers();

    @SuppressWarnings("unchecked")
    default Class<? extends ObjectMixin<T>> compareClass() {
        return MethodUtil.defaultBaseCompareObjectMixinClass((Class<T>) getClass());
    }


    default Object[] mkArray(final Object... members) {
        return members;
    }

    default Comparable<?>[] mkComparableArray(final Comparable<?>... members) {
        return members;
    }

    default Object[] exArray(final Object[] originalMembers, final Object... newMembers) {
        final Object[] result = Arrays.copyOf(originalMembers, originalMembers.length + newMembers.length);
        for (int i = originalMembers.length, n = 0; i < result.length; i++, n++) {
            result[i] = newMembers[n];
        }
        return result;
    }

    default Comparable<?>[] exComparableArray(final Comparable<?>[] originalMembers, final Comparable<?>... newMembers) {

        final Comparable<?>[] result = (Comparable<?>[]) exArray(originalMembers, (Object[]) newMembers);
        return result;
    }

    default int _hashCode() {
        return Objects.hash(members());
    }

    default boolean _equals(final Object obj) {
        if (!(obj instanceof ObjectMixin)) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        final ObjectMixin thatObjectMixin = (ObjectMixin) obj;
        if (this.compareClass() != thatObjectMixin.compareClass()) {
            return false;
        }
        return Arrays.equals(members(), thatObjectMixin.members());
    }

    default String _toString() {
        final String className = getClass().getSimpleName().isEmpty() ? getClass().getName() : getClass().getSimpleName();
        final StringJoiner sj = new StringJoiner(", ", className + "{", "}");
        final Object[] members = members();
        final Object[] names = names();
        final int n = Math.min(members.length, names.length);
        for (int i = 0; i < n; i++) {
            final StringJoiner msj = new StringJoiner("=");
            msj.add(Objects.toString(names[i]));
            msj.add(Objects.toString(members[i]));
            sj.merge(msj);
        }
        return sj.toString();
    }

    default int _compareTo(T obj) {
        @SuppressWarnings("rawtypes")
        final Comparable[] thisComparables = compareToMembers();
        @SuppressWarnings("rawtypes")
        final Comparable[] thatComparables = obj.compareToMembers();

        final int n = Math.min(thisComparables.length, thatComparables.length);
        for (int i = 0; i < n; i++) {
            @SuppressWarnings("unchecked")
            final int result = thisComparables[i].compareTo(thatComparables[i]);
            if (result != 0) {
                return result;
            }
        }
        return 0; // They are equal
    }

    abstract class MethodUtil {

        public static <T extends ObjectMixin> Class<T> defaultBaseCompareObjectMixinClass(Class<T> clazz) {
            final Class<? super T> superClazz = clazz.getSuperclass();
            if (!ObjectMixin.class.isAssignableFrom(superClazz)) {
                return clazz;
            }

            @SuppressWarnings("unchecked")
            final Class<T> objectMixinSuperClazz = (Class<T>) superClazz;
            return defaultBaseCompareObjectMixinClass(objectMixinSuperClazz);
        }
    }


}









































