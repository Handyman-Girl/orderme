package com.kata.orderme.price;

/**
 * A pricing rule is comprised of:
 * <li>a price</li>
 * <li>a shopping item it relates to</li>
 * <li>an amount of shopping items that refers to the given price</li>.
 *
 * For matters of simplicity an item is encoded as a java.lang.Character. In a real scenario this would be a unique POJO.
 */
public interface PricingRule {
    Integer getPrice();
    Integer getAmount();
    Character getItem();
}