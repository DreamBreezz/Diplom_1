package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

//@RunWith(Parameterized.Parameters)
//@RunWith(MockitoJUnitRunner.class)
public class IngredientTests {

//    private static final String ingredientName = "IngredientName";
//    public static final float ingredientPrice = 100500;

//    @Mock
//    IngredientType ingredientType;
//
//    @Mock
//    Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);
//
//    @Test
//    public void ingredientTypeTest() {
////        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);
//        assertEquals(ingredient.getType() = ingredientType);
//    }

    IngredientType ingredientType = IngredientType.SAUCE;
    private final String ingredientName = "IngredientName";
    private final float ingredientPrice = 31415;
    Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

    @Test
    public void ingredientTypeTest() {
        assertEquals(ingredient.getType(), ingredientType);
    }

    @Test
    public void ingredientGetNameTest() {
        assertEquals(ingredient.getName(), ingredientName);
    }

    @Test
    public void ingredientGetPriceTest() {
        assertEquals(ingredient.getPrice(),
                ingredientPrice, 0.001);
    }
}
