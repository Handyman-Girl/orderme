package com.kata.orderme.order;

import com.kata.orderme.price.PriceManager;
import com.kata.orderme.price.SimplePriceManager;
import com.kata.orderme.price.SpecialPrice;
import com.kata.orderme.price.UnitPrice;

/**
 * Provides helper methods to allow for easier testing and definition of pricing rules to solve the given kata.
 */
public final class CheckOutTestHelper {
    private CheckOutTestHelper() {
        // prevent instantiation
    }

    // setup test data as given from within the kata
    public static PriceManager defaultPriceRules(PriceManager manager) {
         manager.addPricingRule(new UnitPrice('A', 50));
         manager.addPricingRule(new UnitPrice('B', 30));
         manager.addPricingRule(new UnitPrice('C', 20));
         manager.addPricingRule(new UnitPrice('D', 15));
         manager.addPricingRule(new SpecialPrice('A', 130, 3));
         manager.addPricingRule(new SpecialPrice('B', 45, 2));
         return manager;
     }

    // add to the given checkOut and return its accumulated total
    public static Integer scanAndTotal(CheckOut checkout, String items) {
        scan(checkout, items);
        return checkout.getTotal();
    }

    // always generate a new CheckOut
    public static Integer scanFreshly(String items) {
        final CheckOut checkout = new DefaultCheckOut(defaultPriceRules(new SimplePriceManager()));
        scan(checkout, items);
        return checkout.getTotal();
    }

    private static void scan(CheckOut checkOut, final String items) {
        for (final char item : items.toCharArray()) {
            checkOut.scan(item);
        }
    }

}
