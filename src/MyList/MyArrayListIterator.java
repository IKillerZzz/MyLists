package MyList;

import java.util.Iterator;

/**
 * Итератор для класса MyArrayList, для последовательного обхода элементов списка.
 * @author Illia Paliachynski
 * @param <E> - тип элементов в списке.
 */
public class MyArrayListIterator<E> implements Iterator<E> {
    private int index;
    E[] list;
    int size;

    /**
     * Конструктор итератора, который принимает следующие параметры:
     * @param list - массив элементов, по которому будет осуществляться итерация.
     * @param size - размер итерируемого массива.
     */
    MyArrayListIterator(E[] list, int size) {
        this.list = list;
        this.size = size;
    }

    /**
     * Метод, который проверяет наличие следующего в списке элемента.
     * @return {@code true} - если следующий элемент существует. {@code false} - если в списке больше нет элементов.
     */
    @Override
    public boolean hasNext() {
        return index < size;
    }

    /**
     * Возвращает следующий элемент списка.
     * @return следующий элемент в списке.
     */
    @Override
    public E next() {
        return list[index++];
    }
}
