package ligai.services;

import ligai.forms.ArtefactForm;
import ligai.forms.IngredientForm;
import ligai.forms.PotionForm;

public interface ProductService {
    public void addPotion(PotionForm potionForm);
    public void addIngredient(IngredientForm ingredientForm);
    public void addArtefact(ArtefactForm artefactForm);
}
