package MyList;

import java.util.Iterator;

public class MyLinkedListIterator<E> implements Iterator<E> {
    MyLinkedList<E> list;
    int counter = 0;

    public MyLinkedListIterator(MyLinkedList<E> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return counter < list.size();
    }

    @Override
    public E next() {
        return list.get(counter++);
    }
}
