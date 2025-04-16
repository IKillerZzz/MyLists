package MyList;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Этот класс предоставляет реализацию интерфейса List для минимизации усилий,
 * необходимых для реализации этого интерфейса, поддерживаемого хранилищем данных
 * "последовательного доступа".
 * @author Illia Paliachynski
 * @param <E> - тип элементов в списке.
 */
public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    /**
     * Конструктор по умолчанию, который задает голову и хвост списка.
     */
    public MyLinkedList() {
        head = new Node<E>(null, null, tail);
        tail = new Node<E>(head, null, null);
    }

    /**
     * Метод, который добавляет указанный в параметре элемент в конец списка.
     * @param element - элемент, который будет добавлен в конец списка.
     * @return {@code true} если элемент был успешно добавлен.
     */
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

    /**
     * Добавляет указанный в параметрах элемент на указанную в параметрах позицию в списке.
     * @param index - позиция в списке, на которую необходимо добавить элемент.
     * @param element - элемент, который необходимо добавить в список.
     */
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

    /**
     * Возвращает элемента из списка, по указанной в параметрах позиции.
     * @param index - позиция, по которой нужно получить элемент из списка.
     * @return - элемент из списка, который находится по указанной в параметрах позиции.
     */
    @Override
    public E get(int index) {
        isInBounds(index);
        int middleIndex = size / 2;
        Node<E> currentNode;

        if (index < middleIndex) {
            currentNode = head.getNextElement();
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNextElement();
            }
        } else {
            currentNode = tail.getPrevElement();
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.getPrevElement();
            }
        }
        return currentNode.getCurrentElement();
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
            if(get(i).equals(element)) {
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
        Node<E> targetNode = getNode(index);
        targetNode.setCurrentElement(element);
        return targetNode.getCurrentElement();
    }

    /**
     * Возвращает новый список, который находится в заданном в параметрах диапазоне текущего списка.
     * @param fromIndex - начальная позиция существующего списка, с которой будут получены элементы в новый список.
     * @param toIndex - позиция конечного элемента, по который будут получены элементы в новый список.
     * @return новый список, который находится в границах от fromIndex по toIndex текущего списка.
     */
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
     * Возвращает объект Node, который находится по указанному в параметрах индексу.
     * @param index - целочисленное значение, по позиции которому нужно поучить узел из списка.
     * @return объект Node, который находится по заданному индексу.
     */
    private Node<E> getNode(int index) {
        isInBounds(index);
        int middleIndex = size / 2;
        Node<E> currentNode;

        if (index < middleIndex) {
            currentNode = head.getNextElement();
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNextElement();
            }
        } else {
            currentNode = tail.getPrevElement();
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.getPrevElement();
            }
        }
        return currentNode;
    }

    /**
     * Возвращает строковое представление списка элементов.
     * @return строковое представление списка элементов.
     */
    @Override
    public String toString() {
        E[] array = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = get(i);
        }
        return Arrays.toString(array);
    }

    /**
     * Возвращает итератор, для обхода текущего списка, передавая в него ссылку на текущий объект списка.
     * @return объект итератора для последовательного обхода текущего списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator<E>(this);
    }

    /**
     * Внутренний статический класс представление узлов (элементов) списка.
     * Хранит ссылку на узел предыдущего и следующего элемента, а также значение текущего элемента.
     * @param <E> - тип элементов в списке
     */
    private static class Node<E> {
        Node<E> prevElement;
        E currentElement;
        Node<E> nextElement;

        /**
         * Конструктор, который принимает следующие параметры:
         * @param prevElement - ссылка на узел предыдущего элемента списка.
         * @param currentElement - значение элемента текущего узла списка.
         * @param nextElement - ссылка на узел следующего в списке элемента.
         */
        public Node(Node<E> prevElement, E currentElement, Node<E> nextElement) {
            this.prevElement = prevElement;
            this.currentElement = currentElement;
            this.nextElement = nextElement;
        }

        /**
         * Устанавливает текущему элементу ссылку на узел предыдущего элемента списка
         * @param node - ссылка на узел предыдущего элемента
         */
        public void setPrevElement(Node<E> node) {
            prevElement = node;
        }

        /**
         * Устанавливает текущему узлу списка значение переданного в параметрах элемента.
         * @param element - значение для текущего элемента списка.
         */
        public void setCurrentElement(E element) {
            currentElement = element;
        }

        /**
         * Устанавливает текущему элементу ссылку на узел следующего элемента списка
         * @param node - ссылка на узел следующего элемента списка.
         */
        public void setNextElement(Node<E> node) {
            nextElement = node;
        }

        /**
         * Возвращает ссылку на узел предыдущего элемента списка.
         * @return ссылку на узел предыдущего элемента
         */
        public Node<E> getPrevElement() {
            return prevElement;
        }

        /**
         * Возвращает значение элемента для текущего узла списка.
         * @return значение элемента списка для текущего узла.
         */
        public E getCurrentElement() {
            return currentElement;
        }

        /**
         * Возвращает ссылку на узел следующего элемента списка.
         * @return ссылку на узел следующего элемента
         */
        public Node<E> getNextElement() {
            return nextElement;
        }
    }
}
