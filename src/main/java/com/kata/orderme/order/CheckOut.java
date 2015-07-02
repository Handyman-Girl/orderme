package com.kata.orderme.order;


public interface CheckOut {
    /**
     * Adds the given item to the internal shopping basket.
     * @param item a one-letter coded shopping item.
     */
    void scan(Character item);

    /**
     * @return the accumulated total prices of all scanned items.
     */
    Integer getTotal();
}
