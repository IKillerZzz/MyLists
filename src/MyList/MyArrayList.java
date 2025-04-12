package MyList;

import java.util.Arrays;

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
        if (isFullCapacity()) {
            expandCapacity();
        }
        list[size++] = element;
        return true;
    }

    public void add(int index, E element) {
        if (isInBounds(index)) {
            if (isFullCapacity()) {
                expandCapacity();
            }
            System.arraycopy(list, 0, list, 0, index);
            System.arraycopy(list, index, list, index + 1, size - index);
            list[index] = element;
            size++;
        }
    }

    public E get(int index) {
        isInBounds(index);
        return list[index];
    }

    public boolean remove(Object object) {
        return false;
    }

    public E remove(int index) {
        return list[0];
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        return list[0];
    }

    public MyArrayList<E> subList(int fromIndex, int toIndex) {
        return new MyArrayList<>();
    }

    boolean isFullCapacity() {
        return size == list.length;
    }

    public void expandCapacity() {
        E[] tmpList = list;
        list = (E[]) new Object[size * 2];
        System.arraycopy(tmpList, 0, list, 0, size);
    }

    boolean isInBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Некорректное значение index: " + index + ". Индекс не может быть < 0 и больше чем " + (size - 1));
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }
    
}
