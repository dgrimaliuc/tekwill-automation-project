package lilia_toma.NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;

public class Tab extends Component {

    public boolean isActive() {
        return this.getAttribute("class").contains("is-active");
    }

    public Tab(WebElement parent) {
        super(parent);
    }
}
