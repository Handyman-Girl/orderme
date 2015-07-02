package com.kata.orderme.price;

import java.util.List;
import java.util.Map;

/**
 * Interface defines how to calculate prices and handle pricing rules per shopping item.
 */
public interface PriceManager {

    /**
     * @return pricing rules per item and combines implementation-specific pricing rules.
     */
    Map<Character, List<PricingRule>> getPriceRules();

    // add simple rules
    void addPricingRule(PricingRule price);

    /**
     * The underlying pricemanager implementation decides how to calculate the actual prices.
     *
     * @param item single-letter coded shopping item.
     * @param amount how many items are to be bought.
     * @return combined price for the given amount of the item.
     */
    Integer getPrice(Character item, Integer amount);
}
