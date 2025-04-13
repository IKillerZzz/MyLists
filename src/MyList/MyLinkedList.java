package MyList;

import java.util.Arrays;
import java.util.Iterator;

public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public MyLinkedList() {
        head = new Node<E>(null, null, tail);
        tail = new Node<E>(head, null, null);
    }

    @Override
    public boolean add(E element) {
        if (size == 0) {
            Node<E> newNode = new Node<>(head, element, tail);
            head.setNextElement(newNode);
            tail.setPrevElement(newNode);
        } else {
            Node<E> prevNode = tail;
            prevNode.setCurrentElement(element);
            tail = new Node<E>(prevNode, null, null);
            prevNode.setNextElement(tail);
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        isInBounds(index);
        Node<E> targetNode = getNode(index);
        Node<E> prevNode = targetNode.getPrevElement();
        Node<E> newNode = new Node<>(prevNode, element, targetNode);
        prevNode.setNextElement(newNode);
        targetNode.setPrevElement(newNode);
        size++;
    }

    @Override
    public E get(int index) {
        isInBounds(index);
        Node<E> currentNode = head.getNextElement();
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextElement();
        }
        return currentNode.getCurrentElement();
    }

    @Override
    public boolean remove(Object object) {
        E element = (E) object;
        for (int i = 0; i < size; i++) {
            if(get(i).equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (isInBounds(index)) {
            E deletedElement = get(index);
            Node<E> targetNode = getNode(index);
            Node<E> prevNode = targetNode.getPrevElement();
            Node<E> nextNode = targetNode.getNextElement();
            prevNode.setNextElement(nextNode);
            nextNode.setPrevElement(prevNode);
            size--;
            return deletedElement;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E set(int index, E element) {
        Node<E> targetNode = getNode(index);
        targetNode.setCurrentElement(element);
        return targetNode.getCurrentElement();
    }

    @Override
    public MyLinkedList<E> subList(int fromIndex, int toIndex) {
        MyLinkedList<E> subList = new MyLinkedList<E>();
        if (isInBounds(fromIndex) && isInBounds(toIndex)) {
            Node<E> firstNode = getNode(fromIndex);
            Node<E> lastNode = getNode(toIndex);
            subList.head.setNextElement(firstNode);
            subList.tail.setPrevElement(lastNode);
            subList.size = toIndex - fromIndex;
            return subList;
        }
        return null;
    }

    private boolean isInBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Некорректное значение index: " + index + ". Индекс не может быть < 0 и больше чем " + (size - 1));
        }
        return true;
    }

    private Node<E> getNode(int index) {
        isInBounds(index);
        Node<E> currentNode = head.getNextElement();
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNextElement();
        }
        return currentNode;
    }

    @Override
    public String toString() {
        E[] array = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = get(i);
        }
        return Arrays.toString(array);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator<E>(this);
    }

    private static class Node<E> {
        Node<E> prevElement;
        E currentElement;
        Node<E> nextElement;

        public Node(Node<E> prevElement, E currentElement, Node<E> nextElement) {
            this.prevElement = prevElement;
            this.currentElement = currentElement;
            this.nextElement = nextElement;
        }

        public void setPrevElement(Node<E> node) {
            prevElement = node;
        }

        public void setCurrentElement(E element) {
            currentElement = element;
        }

        public void setNextElement(Node<E> node) {
            nextElement = node;
        }

        public Node<E> getPrevElement() {
            return prevElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }
    }
}
