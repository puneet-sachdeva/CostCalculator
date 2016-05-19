package com.rbc.basket.calculator;

import java.math.BigDecimal;
import java.util.List;

public interface CostCalculator<T> {
    BigDecimal calculateCost(List<T> items);
}
