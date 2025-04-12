package MyList;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E> {
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
        isInBounds(index);
        if (isFullCapacity()) {
            expandCapacity();
        }
        System.arraycopy(list, 0, list, 0, index);
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = element;
        size++;
    }

    public E get(int index) {
        isInBounds(index);
        return list[index];
    }

    public boolean remove(Object object) {
        E element = (E) object;
        for (int i = 0; i < size; i++) {
            if(list[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public E remove(int index) {
        if (isInBounds(index)) {
            E deletedElement = list[index];
            if (index == size - 1) {
                list[index] = null;
            } else {
                System.arraycopy(list, 0, list, 0, index);
                System.arraycopy(list, index + 1, list, index, size - index);
            }
            size--;
            return deletedElement;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        if (isInBounds(index)) {
            list[index] = element;
        }
        return list[index];
    }

    public MyArrayList<E> subList(int fromIndex, int toIndex) {
        MyArrayList<E> subList;
        if (isInBounds(fromIndex) && isInBounds(toIndex)) {
            int countOfElements = toIndex - fromIndex;
            subList = new MyArrayList<>(countOfElements);
            subList.size = countOfElements;
            System.arraycopy(list, fromIndex, subList.list, 0, toIndex - fromIndex);
            return subList;
        }
        return null;
    }

    private boolean isFullCapacity() {
        return size == list.length;
    }

    private void expandCapacity() {
        E[] tmpList = list;
        list = (E[]) new Object[size * 2];
        System.arraycopy(tmpList, 0, list, 0, size);
    }

    private boolean isInBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Некорректное значение index: " + index + ". Индекс не может быть < 0 и больше чем " + (size - 1));
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>(list, size);
    }
}
