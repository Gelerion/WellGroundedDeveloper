package com.denis.golovach.multhithreading.course.lecture_10;


//non thread safe
//with simple sync we could make it thread safe but it will block
public class TraiberStack<T> {
    private Node<T> tail;

    public void push(T newElem) {
        this.tail = new Node<>(newElem, tail);
    }
    public T pop(){
        T result = tail.value;
        this.tail = tail.next;
        return result;
    }

    private static class Node<E> {
        final E value;
        final Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        TraiberStack<Object> t = new TraiberStack<>();
        t.push(1);
        t.push(2);
        t.push(3);

        System.out.println(t.pop());
        System.out.println(t.pop());
        System.out.println(t.pop());
//        System.out.println(t.pop()); NPE
    }
}
