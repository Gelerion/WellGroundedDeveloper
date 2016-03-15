package com.denis.golovach.multhithreading.course.lecture_10;

import java.util.concurrent.atomic.AtomicReference;

public class TraibrStackThreadSafe<T> {
    private AtomicReference<Node<T>> tail;
    private AtomicReference<BoostNode<T>> boostTail;

    public void push(T newElem) {
        while (true) {
            Node<T> oldTail = this.tail.get();
            //constructor - bad
            Node<T> newTail = new Node<>(newElem, oldTail); //-> here values are finals
            if (tail.compareAndSet(oldTail, newTail)) {
                break;
            }
        }
    }

    //Better
    public void pushBoost(T newElem) {
        BoostNode<T> newTail = new BoostNode<>(newElem, null);
        while (true) {
            BoostNode<T> oldTail = this.boostTail.get();
            newTail.next = oldTail;
            if (boostTail.compareAndSet(oldTail, newTail)) {
                break;
            }
        }
    }


    public T pop() {
        while (true) {
            Node<T> oldTail = tail.get();
            Node<T> newTail = oldTail.next;
            if (tail.compareAndSet(oldTail, newTail)) {
                return oldTail.value;
            }
        }
    }

    private static class Node<E> {
        final E value;
        final Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private static class BoostNode<E> {
        E value;
        BoostNode<E> next;

        public BoostNode(E value, BoostNode<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
