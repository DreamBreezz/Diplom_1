package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTests {
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTests(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {IngredientType.SAUCE, "Cheese Sauce", 49},
                {IngredientType.FILLING, "Parmesan", 79}
        };
    }

    @Test
    public void ingredientIngredientTypeTest() {
        Ingredient i = new Ingredient(type, name, price);
        assertEquals(i.getType(), type);
        assertEquals(i.getName(), name);
        assertEquals(i.getPrice(), price, 0.001);
    }
}
