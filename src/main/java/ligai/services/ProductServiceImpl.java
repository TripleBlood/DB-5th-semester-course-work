package ligai.services;

import ligai.enums.IngredientType;
import ligai.enums.Type;
import ligai.forms.ArtefactForm;
import ligai.forms.IngredientForm;
import ligai.forms.PotionForm;
import ligai.models.Ingredient;
import ligai.models.Potion;
import ligai.models.Product;
import ligai.repositories.IngredientRepository;
import ligai.repositories.PotionRepository;
import ligai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    PotionRepository potionRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addPotion(PotionForm potionForm){

        ArrayList<Ingredient> ingredients = new ArrayList<>();
            ingredients.add(ingredientRepository.findFirstByName(potionForm.getCatalystIng()));
            ingredients.add(ingredientRepository.findFirstByName(potionForm.getEssenceIng()));
            ingredients.add(ingredientRepository.findFirstByName(potionForm.getConservatorIng()));

        Potion potion = Potion.potionBuilder()
                .name(potionForm.getName())
                .description(potionForm.getDescription())
                .cost(potionForm.getCost())
                .available(potionForm.getAvailable())
                .volume(potionForm.getVolume())
                .ingredients(ingredients)
                .build();

        potionRepository.save(potion);
    }

    @Override
    public void addIngredient(IngredientForm ingredientForm) {
        Ingredient ingredient = Ingredient.ingredientBuilder()
                .name(ingredientForm.getName())
                .description(ingredientForm.getDescription())
                .cost(ingredientForm.getCost())
                .available(ingredientForm.getAvailable())
                .build();

        if (ingredientForm.getIngredientType().equals("CATALYST")) ingredient.setIngredientType(IngredientType.CATALYST);
        else if (ingredientForm.getIngredientType().equals("ESSENCE")) ingredient.setIngredientType(IngredientType.ESSENCE);
        else ingredient.setIngredientType(IngredientType.CONSERVATOR);

        ingredientRepository.save(ingredient);
    }

    @Override
    public void addArtefact(ArtefactForm artefactForm) {
        Product product = Product.builder()
                .name(artefactForm.getName())
                .description(artefactForm.getDescription())
                .cost(artefactForm.getCost())
                .available(artefactForm.getAvailable())
                .type(Type.ARTEFACT)
                .build();

        productRepository.save(product);

    }

}
