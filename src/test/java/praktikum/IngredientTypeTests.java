package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.*;

@RunWith(Parameterized.class)
public class IngredientTypeTests {
    private final IngredientType type;
    private final String name;

    public IngredientTypeTests(IngredientType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {SAUCE, "SAUCE"},
                {FILLING, "FILLING"}
        };
    }

    @Test
    public void ingredientTypeTest() {
        assertEquals(type.toString(), name);
    }
}
