package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static constants.Constants.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Spy
    Burger burger;

    @Mock
    Bun bunMock;
    @Mock
    Ingredient ingredientMock;
    //IngredientType ingrTypeMock

    @Test
    public void burgerSetBunsTest() {
        burger.setBuns(bunMock);
        Mockito.verify(burger).setBuns(bunMock);
    }

    @Test
    public void burgerAddMoveRemoveIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock);
        burger.moveIngredient(0, 1);
        burger.removeIngredient(0);
        Mockito.verify(burger, times(2)).addIngredient(ingredientMock);
        Mockito.verify(burger).moveIngredient(0, 1);
        Mockito.verify(burger).removeIngredient(0);
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

        // проверка, что методы вызвались и результат актуален
        Mockito.verify(bunMock, times(1)).getPrice();
        Mockito.verify(ingredientMock, times(2)).getPrice();  // цифра = кол-во addIngredients
        Assert.assertEquals(expected, burgerPrice, 0.001);
    }


    @Test
    public void getReceiptTest() {
        // мокирование вызовов всех методов, которые вызываются при запросе рецепта бургера
        Mockito.when(bunMock.getName()).thenReturn(BUN_NAME);
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientMock.getName()).thenReturn(INGR_NAME);
        Mockito.when(bunMock.getPrice()).thenReturn(BUN_PRICE);
        Mockito.when(ingredientMock.getPrice()).thenReturn(INGR_PRICE);

        // сбор бургера
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);  // если этот метод вызывать больше 1 раза, нужно поправить expected

        // расчёт цены бургера, которую мы ожидаем получить
        float burgerPrice = ((BUN_PRICE * 2) + INGR_PRICE);

        // вот такой текст должен получиться
        String expected = String.format("(==== " + BUN_NAME + " ====)%n" +
                "= sauce "+ INGR_NAME +" =%n" +
                "(==== "+ BUN_NAME+" ====)%n" +
                "%n" +
                "Price: "+ burgerPrice +"%n");

        String actual = burger.getReceipt();

        // вывод теста экран, для наглядности, т.к. при ошибке jUnit не хочет показывать ОР и ФР
        System.out.println("Expected result: \n" + expected + "\n");
        System.out.println("Actual result: \n" + actual);

        // проверка, сколько раз вызывался каждый метод
        Mockito.verify(bunMock, times(2)).getName();
        Mockito.verify(ingredientMock, times(1)).getType();
        Mockito.verify(ingredientMock, times(1)).getName();
        Mockito.verify(burger, times(1)).getPrice();

        // сравнение ОР и ФР
        Assert.assertEquals(expected, actual);
    }

//    @Test
//    public void burgerGetReceiptTest() {
//        // мокирование всех вызываемых внутри методов
//        burger.setBuns(bunMock);
//        burger.addIngredient(ingredientMock);
//        burger.addIngredient(ingredientMock);
//        ingredientMock.getType();
//
//        burger.getReceipt();
//        Mockito.verify(bunMock, times(2)).getName();
    // запись строки, которая должна получиться, в переменную


    // проверяем правильный вывод строки
//        String burgerReceipt = burger.getReceipt();
//
//        Assert.assertEquals(mockedBurgerGetReceipt, burgerReceipt);
}
