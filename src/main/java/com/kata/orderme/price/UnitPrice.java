package com.kata.orderme.price;

public class UnitPrice implements PricingRule {

    private static Integer ONE = 1;
    private Integer price;
    private Character item;

    public UnitPrice(Character item, Integer price) {
        this.item = item;
        this.price = price;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public Integer getAmount() {
        return ONE;
    }

    @Override
    public Character getItem() {
        return item;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final UnitPrice unitPrice = (UnitPrice) o;

        if (price != null ? !price.equals(unitPrice.price) : unitPrice.price != null) return false;
        return !(item != null ? !item.equals(unitPrice.item) : unitPrice.item != null);
    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UnitPrice{" +
                "price=" + price +
                ", item=" + item +
                '}';
    }
}
