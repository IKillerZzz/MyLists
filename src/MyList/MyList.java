package MyList;

public interface MyList<E> extends Iterable<E> {

    boolean add(E element);

    void add(int index, E element);

    E get(int index);

    boolean remove(Object object);

    E remove(int index);

    int size();

    E set(int index, E element);

    MyList<E> subList(int fromIndex, int toIndex);

}
