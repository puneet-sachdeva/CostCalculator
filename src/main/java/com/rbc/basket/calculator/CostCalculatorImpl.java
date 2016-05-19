package com.rbc.basket.calculator;

import com.google.common.base.Preconditions;
import com.rbc.basket.Constants;
import com.rbc.basket.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public class CostCalculatorImpl implements CostCalculator<Item> {
    @Override
    public BigDecimal calculateCost(List<Item> items) {
        Preconditions.checkNotNull(items);

        return BigDecimal.valueOf(items.stream().filter(item -> item != null)
                .mapToDouble(item -> item.getPrice().doubleValue()* item.getQuantity())
                .sum())
                .setScale(Constants.BASKET_COST_SCALE, Constants.COST_ROUNDING_MODE);
    }
}

