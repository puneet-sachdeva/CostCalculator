package com.rbc.basket.entity;

import java.math.BigDecimal;

public final class Item {
    private Fruit fruit;

    private int quantity;

    private BigDecimal price;

    public Item(final Fruit fruit, final int quantity, final BigDecimal price) {
        this.fruit = fruit;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
