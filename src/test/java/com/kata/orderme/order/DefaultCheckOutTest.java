package com.kata.orderme.order;

import com.kata.orderme.price.SimplePriceManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.kata.orderme.order.CheckOutTestHelper.defaultPriceRules;
import static com.kata.orderme.order.CheckOutTestHelper.scanAndTotal;
import static com.kata.orderme.order.CheckOutTestHelper.scanFreshly;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * @author hirsch
 * @version 2015-07-01, 22:34
 */
@RunWith(JUnit4.class)
public class DefaultCheckOutTest {

    private CheckOut checkOut;

    @Before
    public void createFreshCheckOut() {
        checkOut = new DefaultCheckOut(new SimplePriceManager());
        assertNotNull(checkOut);
    }

    @Test
    public void creationAndNoItemsScanned() {
        assertThat(checkOut.getTotal(), is(0));
    }

    @Test
    public void scanItemsOnly() {
        checkOut.scan('A');
        checkOut.scan('B');
        checkOut.scan('C');

        checkOut.scan('A');
        checkOut.scan('A');
        checkOut.scan('A');

        String toString = checkOut.toString();
        assertThat(toString, containsString("A=4"));
        assertThat(toString, containsString("B=1"));
        assertThat(toString, containsString("C=1"));
        // no rules defined yet
        assertThat(checkOut.getTotal(), is(0));
    }

    @Test
    public void testTotals() {
        final DefaultCheckOut checkOut = new DefaultCheckOut(defaultPriceRules(new SimplePriceManager()));
        assertNotNull(checkOut);

        assertThat("" + checkOut, scanFreshly(""), is(0));
        assertThat("" + checkOut, scanFreshly("A"), is(50));
        assertThat("" + checkOut, scanFreshly("AB"), is(80));
        assertThat("" + checkOut, scanFreshly("CDBA"), is(115));

        assertThat("" + checkOut, scanFreshly("AA"), is(100));
        assertThat("" + checkOut, scanFreshly("AAA"), is(130));
        assertThat("" + checkOut, scanFreshly("AAAA"), is(180));
        assertThat("" + checkOut, scanFreshly("AAAAA"), is(230));
        assertThat("" + checkOut, scanFreshly("AAAAAA"), is(260));
        assertThat("" + checkOut, scanFreshly("AAAAAAA"), is(310));

        assertThat("" + checkOut, scanFreshly("AAAB"), is(160));
        assertThat("" + checkOut, scanFreshly("AAABB"), is(175));
        assertThat("" + checkOut, scanFreshly("AAABBD"), is(190));
        assertThat("" + checkOut, scanFreshly("DABABA"), is(190));
    }

    @Test
    public void testIncremental() {
        final DefaultCheckOut checkOut = new DefaultCheckOut(defaultPriceRules(new SimplePriceManager()));
        assertNotNull(checkOut);

        assertThat("" + checkOut, scanAndTotal(checkOut, ""), is(0));
        assertThat("" + checkOut, scanAndTotal(checkOut, "A"), is(50));
        assertThat("" + checkOut, scanAndTotal(checkOut, "B"), is(80));
        assertThat("" + checkOut, scanAndTotal(checkOut, "A"), is(130));
        assertThat("" + checkOut, scanAndTotal(checkOut, "A"), is(160));
        assertThat("" + checkOut, scanAndTotal(checkOut, "B"), is(175));
    }

}
