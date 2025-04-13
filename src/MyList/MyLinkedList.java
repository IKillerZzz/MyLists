package MyList;

import java.util.Iterator;

public class MyLinkedList<E> implements MyList<E> {

    @Override
    public boolean add(E element) {
        return false;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public MyLinkedList<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
