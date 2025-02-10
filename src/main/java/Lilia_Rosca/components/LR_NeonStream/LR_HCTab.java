package Lilia_Rosca.components.LR_NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;

public class LR_HCTab extends Component {
    // 05.02
    public LR_HCTab(WebElement parent) {
        super(parent);
    }

    public boolean isActive() {
        return this.getAttribute("class").contains("is-active");
    }

}
