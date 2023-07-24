package helpers.customElements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Components<T extends Component> extends ArrayList<T> {
    private List<T> components;

    public Components(List<Object> components) {
        setComponents(components);
    }

    public void setComponents(List<Object> components) {
        this.components = new ArrayList<>();
        for (Object component : components) {
            this.components.add((T) component);
        }
    }

    @Override
    public T get(int index) {
        return this.components.get(index);
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
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return this.components.toArray();
    }


    public void addComponent(T component) {
        components.add(component);
    }

    public List<T> getComponents() {
        return components;
    }
}
