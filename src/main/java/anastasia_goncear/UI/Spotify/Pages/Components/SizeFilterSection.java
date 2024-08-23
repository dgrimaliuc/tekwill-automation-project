package anastasia_goncear.UI.Spotify.Pages.Components;


import helpers.customElements.Component;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SizeFilterSection extends Component {
        @FindBy(css = "input[value='S']")
        public WebElement S;
        @FindBy(css = "input[value='M']")
        public WebElement M;
        @FindBy(css = "input[value='L']")
        public WebElement L;
        @FindBy(css = "input[value='XL']")
        public WebElement XL;

        public SizeFilterSection(WebElement parent) {
            super(parent);
        }

    }

