package praktikum;

import org.junit.Test;

import static praktikum.constants.Constants.*;
import static org.junit.Assert.assertEquals;

// проверка геттеров Bun

public class BunTests {

    private final String bunName = BUN_NAME;
    private final float bunPrice = BUN_PRICE;
    Bun bun = new Bun(bunName, bunPrice);

    @Test
    public void bunGetNameTest() {
        assertEquals(bun.getName(), bunName);
    }

    @Test
    public void bunGetPriceTest() {
        assertEquals(bun.getPrice(), bunPrice, DELTA);
    }
}