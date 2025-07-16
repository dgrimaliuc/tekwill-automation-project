package example.ui.petstore.components;

import helpers.customElements.Component;
import helpers.customElements.Components;
import helpers.customElements.factories.ComponentContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static example.api.petstore.AdoptionsEndpoint.createAdoption;

public class AdoptionsSection extends Component {

    @FindBy(tagName = "h2")
    public WebElement title;

    @FindBy(css = "[data-t=single-adoption]")
    public Components<Adoption> adoptions;

    public AdoptionsSection(ComponentContext context) {
        super(context);
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
