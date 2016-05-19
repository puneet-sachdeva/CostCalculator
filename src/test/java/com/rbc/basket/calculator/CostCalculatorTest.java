package com.rbc.basket.calculator;

import com.rbc.basket.Constants;
import com.rbc.basket.entity.Fruit;
import com.rbc.basket.entity.Item;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CostCalculatorTest {

    private CostCalculator<Item> calculator;

    private final BigDecimal priceOfApple = BigDecimal.TEN;
    private final BigDecimal priceOfBanana = BigDecimal.ONE;
    private final BigDecimal priceOfPeach = BigDecimal.valueOf(20);
    private final BigDecimal priceOfLemon = BigDecimal.valueOf(15);
    private final BigDecimal priceOfOrange = BigDecimal.valueOf(12);

    @Before
    public void setUp() {
        calculator = new CostCalculatorImpl();
    }

    @Test (expected = NullPointerException.class)
    public void testWhenBasketIsNull() {
        calculator.calculateCost(null);
    }

    @Test
    public void testWhenBasketIsEmptyCostIsZero() {
        final BigDecimal cost = calculator.calculateCost(new ArrayList<>());
        assertEquals(BigDecimal.ZERO.setScale(Constants.BASKET_COST_SCALE), cost);
    }

    @Test
    public void testWhenBasketHasNullItemItIsIgnored() {
        final List<Item> items = new ArrayList<>();
        final int appleQuantity = 1;
        final int orangeQuantity = 1;

        items.add(new Item(Fruit.APPLE, appleQuantity, priceOfApple));
        items.add(new Item(Fruit.ORANGE, orangeQuantity, priceOfOrange));
        items.add(null);

        final BigDecimal expectedCost = priceOfApple.multiply(BigDecimal.valueOf(appleQuantity))
                .add(priceOfOrange.multiply(BigDecimal.valueOf(orangeQuantity)))
                .setScale(Constants.BASKET_COST_SCALE, Constants.COST_ROUNDING_MODE);

        assertEquals(expectedCost, calculator.calculateCost(items));
    }

    @Test
    public void testWhenBasketHasAllItems() {
        List<Item> items = new ArrayList<>();

        final int appleQuantity = 10;
        final int orangeQuantity = 10;
        final int bananaQuantity = 20;
        final int lemonQuantity = 20;
        final int peachQuantity = 20;

        items.add(new Item(Fruit.APPLE, appleQuantity, priceOfApple));
        items.add(new Item(Fruit.BANANA, bananaQuantity, priceOfBanana));
        items.add(new Item(Fruit.ORANGE, orangeQuantity, priceOfOrange));
        items.add(new Item(Fruit.LEMON, lemonQuantity, priceOfLemon));
        items.add(new Item(Fruit.PEACH, peachQuantity, priceOfPeach));

        final BigDecimal expectedCost = priceOfApple.multiply(BigDecimal.valueOf(appleQuantity))
                .add(priceOfOrange.multiply(BigDecimal.valueOf(orangeQuantity)))
                .add(priceOfLemon.multiply(BigDecimal.valueOf(lemonQuantity)))
                .add(priceOfPeach.multiply(BigDecimal.valueOf(peachQuantity)))
                .add(priceOfBanana.multiply(BigDecimal.valueOf(bananaQuantity)))
                .setScale(Constants.BASKET_COST_SCALE, Constants.COST_ROUNDING_MODE);

        assertEquals(expectedCost, calculator.calculateCost(items));
    }

}
