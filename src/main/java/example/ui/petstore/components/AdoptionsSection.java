package example.ui.petstore.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static example.api.petstore.endpoints.AdoptionsEndpoint.createAdoption;

public class AdoptionsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t=single-adoption]")
    public Components<Adoption> adoptions;

    public AdoptionsSection(WebElement parent) {
        super(parent);
    }

    public List<Map<String, String>> addAdoptions(String location, List<String> pets, int count) {
        List<Map<String, String>> adoptions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            var adoption = createAdoption(location, pets);
            adoptions.add(adoption.jsonPath().getMap(""));
        }
        return adoptions;
    }
}
