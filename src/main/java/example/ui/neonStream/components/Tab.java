package example.ui.neonStream.components;

import helpers.customElements.Component;
import helpers.customElements.factories.ComponentContext;

public class Tab extends Component {

    public Tab(ComponentContext context) {
        super(context);
    }

    public boolean isActive() {
        return this.getAttribute("class").contains("is-active");
    }
}
