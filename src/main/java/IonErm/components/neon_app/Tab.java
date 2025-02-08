package IonErm.components.neon_app;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;

public class Tab extends Component {

    public Tab(WebElement parent) {
        super(parent);
    }

    public boolean isActive() {
        return this.getAttribute("class").contains("is-active");
    }
}
