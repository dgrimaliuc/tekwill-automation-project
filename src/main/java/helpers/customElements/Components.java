package helpers.customElements;

import java.util.*;

public class Components<T extends Component> implements List<T> {
    private final List<T> components;

    public Components(List<?> components) {
        this.components = (List<T>) components;
    }


    @Override
    public T get(int index) {
        return this.components.get(index);
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
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

    @Override
    public int size() {
        return this.components.size();
    }

    @Override
    public boolean isEmpty() {
        return this.components.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return components.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return this.components.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.components.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return new ArrayList<>(components).toArray(a);
    }

    @Override
    public boolean add(T t) {
        return components.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return components.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
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

    public List<T> getComponents() {
        return components;
    }
}
