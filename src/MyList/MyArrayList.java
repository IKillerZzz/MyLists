package MyList;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Этот класс предоставляет реализацию интерфейса List для минимизации усилий,
 * необходимых для реализации этого интерфейса, поддерживаемого хранилищем данных
 * "произвольного доступа" (например, массивом).
 * @author Illia Paliachynski
 * @param <E> - тип элементов в списке.
 */
public class MyArrayList<E> implements MyList<E>  {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] list;

    /**
     * Конструктор по умолчанию. Создает массив размеров в 10 элементов.
     */
    public MyArrayList() {
        list = (E[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор, который принимает целочисленное значение и создает массив указанного размера.
     * @param capacity - целочисленное значение, которое указывает требуемый для инициализации массива размер.
     */
    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Введено некорректное значение размера MyArrayList: " + capacity + ". Значение должно быть > 0.");
        }
        list = (E[]) new Object[capacity];
    }

    /**
     * Метод, который добавляет указанный в параметре элемент в конец списка.
     * @param element - элемент, который будет добавлен в конец списка.
     * @return {@code true} если элемент был успешно добавлен.
     */
    @Override
    public boolean add(E element) {
        if (isFullCapacity()) {
            expandCapacity();
        }
        list[size++] = element;
        return true;
    }

    /**
     * Добавляет указанный в параметрах элемент на указанную в параметрах позицию в списке.
     * @param index - позиция в списке, на которую необходимо добавить элемент.
     * @param element - элемент, который необходимо добавить в список.
     */
    @Override
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

    /**
     * Возвращает элемента из списка, по указанной в параметрах позиции.
     * @param index - позиция, по которой нужно получить элемент из списка.
     * @return - элемент из списка, который находится по указанной в параметрах позиции.
     */
    @Override
    public E get(int index) {
        isInBounds(index);
        return list[index];
    }

    /**
     * Удаляет первое вхождение указанного в параметрах элемента в списке.
     * @param object - элемент, первое вхождение которого необходимо удалить из списка.
     * @return {@code true} - если указанный элемент был успешно удален. {@code false} - если указанного
     * элемента нет в списке.
     */
    @Override
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

    /**
     * Удаляет элемент из списка, по указанному в параметре индексу.
     * @param index - позиция элемента, который необходимо удалить из списка.
     * @return - возвращает удаленный из списка элемент.
     */
    @Override
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

    /**
     * Возвращает текущий размер списка.
     * @return целочисленное значение, которое соответствует актуальному размеру списка.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Задает существующему элементу указанное в параметрах значение element по заданному в параметрах индексу index.
     * @param index - позиция элемента в списке, который требуется изменить.
     * @param element - новое значение, которое будет задано элементу по указанному индексу.
     * @return элемент, который был успешно изменен.
     */
    @Override
    public E set(int index, E element) {
        if (isInBounds(index)) {
            list[index] = element;
        }
        return list[index];
    }

    /**
     * Возвращает новый список, который находится в заданном в параметрах диапазоне текущего списка.
     * @param fromIndex - начальная позиция существующего списка, с которой будут получены элементы в новый список.
     * @param toIndex - позиция конечного элемента, по который будут получены элементы в новый список.
     * @return новый список, который находится в границах от fromIndex по toIndex текущего списка.
     */
    @Override
    public MyArrayList<E> subList(int fromIndex, int toIndex) {
       MyArrayList<E> subList;
        if (isInBounds(fromIndex) && isInBounds(toIndex)) {
            int countOfElements = toIndex - fromIndex;
            subList = new MyArrayList<E>(countOfElements);
            subList.size = countOfElements;
            System.arraycopy(list, fromIndex, subList.list, 0, toIndex - fromIndex);
            return subList;
        }
        return null;
    }

    /**
     * Проверяет заполнен ли массиву на 100% от имеющейся емкости.
     * @return {@code true}, если массив заполнен на 100%. {@code false} если в массиве еще есть свободные элементы.
     */
    private boolean isFullCapacity() {
        return size == list.length;
    }

    /**
     * Увеличивает размер исходного массива в 2 раза, копирую в него все содержимое исходного массива.
     */
    private void expandCapacity() {
        E[] tmpList = list;
        list = (E[]) new Object[size * 2];
        System.arraycopy(tmpList, 0, list, 0, size);
    }

    /**
     * Проверяет, находиться ли указанный в параметрах index в допустимом диапазоне.
     * @param index - значение index, которое проверяется на допустимость.
     * @throws IndexOutOfBoundsException - если значение index выходит за рамки допустимого диапазона.
     * @return {@code true}, если значение index находится в рамках допустимого диапазона.
     */
    private boolean isInBounds(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Некорректное значение index: " + index + ". Индекс не может быть < 0 и больше чем " + (size - 1));
        }
        return true;
    }

    /**
     * Возвращает строковое представление массива элементов.
     * @return строковое представление содержимого массива.
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

    /**
     * Возвращает итератор, для обхода текущего списка, передавай в него массив элементов и размер списка.
     * @return объект итератора для последовательного обхода текущего списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator<>(list, size);
    }
}
