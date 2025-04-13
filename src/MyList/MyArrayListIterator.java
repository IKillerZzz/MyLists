package MyList;

import java.util.Iterator;

public class MyArrayListIterator<E> implements Iterator<E> {
    private int index;
    E[] list;
    int size;

    MyArrayListIterator(E[] list, int size) {
        this.list = list;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public E next() {
        return list[index++];
    }
}
