package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTests {

    private String bunName = "BunName";
    private float bunPrice = 100500;
    Bun bun = new Bun(bunName, bunPrice);

    @Test
    public void bunGetNameTest() {
        assertEquals(bun.getName(), bunName);
    }

    @Test
    public void bunGetPriceTest() {
        assertEquals(bun.getPrice(), bunPrice, 0.001);
    }
}
