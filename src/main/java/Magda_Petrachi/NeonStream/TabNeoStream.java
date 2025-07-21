package Magda_Petrachi.NeonStream;

import helpers.customElements.Component;
import org.openqa.selenium.WebElement;

public class TabNeoStream extends Component {

    public TabNeoStream(WebElement parent) {
        super(parent);
    }

    public boolean isActive() {
        return this.getAttribute("class").contains("is-active");
    }


}
