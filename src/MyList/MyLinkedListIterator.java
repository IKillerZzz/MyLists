package MyList;

import java.util.Iterator;

/**
 * Итератор для класса MyLinkedList, для последовательного обхода элементов списка.
 * @author Illia Paliachynski
 * @param <E> - тип элементов в списке.
 */
public class MyLinkedListIterator<E> implements Iterator<E> {
    MyLinkedList<E> list;
    int counter = 0;

    /**
     * Конструктор итератора, который принимает следующий параметр:
     * @param list - объект списка элементов, по которому будет осуществляться итерация.
     */
    public MyLinkedListIterator(MyLinkedList<E> list) {
        this.list = list;
    }

    /**
     * Метод, который проверяет наличие следующего в списке элемента.
     * @return {@code true} - если следующий элемент существует. {@code false} - если в списке больше нет элементов.
     */
    @Override
    public boolean hasNext() {
        return counter < list.size();
    }

    /**
     * Возвращает следующий элемент списка.
     * @return следующий элемент в списке.
     */
    @Override
    public E next() {
        return list.get(counter++);
    }
}
