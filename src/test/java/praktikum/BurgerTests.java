package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static praktikum.constants.Constants.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    Burger burger = new Burger();

    @Mock
    Bun bunMock;
    @Mock
    Ingredient ingredientMock;
    @Mock
    Ingredient ingredientMock2;

    @Test
    public void burgerSetBunsTest() {
        burger.setBuns(bunMock);
        Assert.assertEquals(bunMock, burger.bun);
    }

    @Test
    public void burgerAddIngredientTest() {
        burger.addIngredient(ingredientMock);
        Assert.assertEquals(ingredientMock, burger.ingredients.get(0));
    }

    @Test
    public void burgerMoveIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(2,0);
        Assert.assertEquals(ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    public void burgerRemoveIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        Assert.assertEquals(burger.ingredients.size(), 1);
    }

    @Test
    public void burgerGetPriceTest() {
        // мокирование вызовов методов, которые вызываются при запросе цены бургера
        Mockito.when(bunMock.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(ingredientMock.getPrice()).thenReturn(INGR_PRICE);

        // вызов методов, без которых бургер не соберётся
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock);
        float burgerPrice = burger.getPrice();  // в переменную запишется фактический результат

        // ожидаемый результат посчитать простой формулой
        // булки всегда х2, ингредиентов столько, сколько раз вызывался addIngredient чуть выше
        float expected = (BUN_PRICE * 2) + (INGR_PRICE * 2);

        // проверка, что результат корректен
        Assert.assertEquals(expected, burgerPrice, 0.001);
    }

    private void createMockedBurger() {
        // мокирование вызовов всех методов, которые вызываются при запросе рецепта бургера
        Mockito.when(bunMock.getName()).thenReturn(BUN_NAME);
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientMock.getName()).thenReturn(INGR_NAME);
        Mockito.when(bunMock.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(ingredientMock.getPrice()).thenReturn(INGR_PRICE);

        // сбор бургера
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);  // если этот метод вызывать больше 1 раза, нужно менять тесты
    }

    @Test
    public void getReceiptTextTest() {
        createMockedBurger();
        String actual = burger.getReceipt();

        // вот такой текст должен получиться
        String expected = String.format("(==== " + BUN_NAME + " ====)%n" +
                "= sauce "+ INGR_NAME +" =%n" + // эта строка должна быть столько раз, сколько вызывался addIngredient
                "(==== "+ BUN_NAME+" ====)%n" +
                "%n" +
                "Price: "+ String.format("%f", ((BUN_PRICE * 2) + INGR_PRICE)) +"%n"); // строку обязательно форматировать

        // вывод текста экран, для наглядности, т.к. при ошибке jUnit не хочет показывать о.р. и ф.р.
        System.out.println("Expected result:\n" + expected + "\n\n" + "Actual result: \n" + actual);

        // сравнение о.р. и ф.р.
        Assert.assertEquals(expected, actual);
    }
}