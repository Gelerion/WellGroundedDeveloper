package com.denis.design_patterns.mixin_pattern;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public interface ObjectMixin<T extends ObjectMixin<T>> {

    Object[] members();

    Object[] names();

    Comparable<?>[] compareToMembers();

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
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        // Must be an AbstractObjectSupport since the class is the same as this class
        final AbstractObjectSupport thatAbstractObjectSupport = (AbstractObjectSupport) obj;
        return Arrays.equals(members(), thatAbstractObjectSupport.members());
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
}











































