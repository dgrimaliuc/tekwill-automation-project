package helpers.customElements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Components<T extends Component> extends ArrayList<T> {
    private final List<T> components;

    public Components(List<?> components) {
        this.components = (List<T>) components;
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
    public Iterator<T> iterator() {
        return this.components.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.components.toArray();
    }

    public List<T> getComponents() {
        return components;
    }
}
