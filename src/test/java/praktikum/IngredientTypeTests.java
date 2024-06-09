package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTests {

    @Test
    public void isSauceSauceTest() {
        assertEquals(IngredientType.SAUCE.toString(), "SAUCE");
    }

    @Test
    public void isFillingFillingTest() {
        assertEquals(IngredientType.FILLING.toString(), "FILLING");
    }
}
