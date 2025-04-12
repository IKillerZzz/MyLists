package MyList;

import java.util.List;

public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] list;

    public MyArrayList() {
        list = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Введено некорректное значение размера MyArrayList: " + capacity + ". Значение должно быть > 0.");
        }
        list = (E[]) new Object[capacity];
    }

    public boolean add(E element) {
        return false;
    }

    public void add(int index, E element) {

    }

    public E get(int index) {
        return list[0];
    }

    public boolean remove(Object object) {
        return false;
    }

    public E remove(int index) {
        return list[0];
    }

    public int size() {
        return 0;
    }

    public E set(int index, E element) {
        return list[0];
    }

    public MyArrayList<E> subList(int fromIndex, int toIndex) {
        return new MyArrayList<>();
    }

}
