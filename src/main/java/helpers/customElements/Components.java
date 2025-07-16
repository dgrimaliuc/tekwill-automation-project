package helpers.customElements;

import java.util.*;

public class Components<T extends Component> implements List<T> {
    private final List<T> components;

    @SuppressWarnings("unchecked")
    public Components(List<?> components) {
        this.components = (List<T>) components;
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public boolean isEmpty() {
        return components.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return components.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return components.iterator();
    }

    @Override
    public Object[] toArray() {
        return components.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return components.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return components.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return components.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return components.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return components.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return components.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return components.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return components.retainAll(c);
    }

    @Override
    public void clear() {
        components.clear();
    }

    @Override
    public T get(int index) {
        return components.get(index);
    }

    @Override
    public T set(int index, T element) {
        return components.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        components.add(index, element);
    }

    @Override
    public T remove(int index) {
        return components.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return components.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return components.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
        return components.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return components.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return components.subList(fromIndex, toIndex);
    }
}
