package com.kata.orderme.price;

public class SpecialPrice implements PricingRule {

    private final Integer amount;
    private final Integer price;
    private final Character item;

    public SpecialPrice(Character item, Integer price, Integer amount) {
        this.item = item;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public Integer getPrice() {
        return price;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public Character getItem() {
        return item;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SpecialPrice that = (SpecialPrice) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return !(item != null ? !item.equals(that.item) : that.item != null);

    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpecialPrice{" +
                "amount=" + amount +
                ", price=" + price +
                ", item=" + item +
                '}';
    }
}
