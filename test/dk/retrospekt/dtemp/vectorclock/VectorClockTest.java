package dk.retrospekt.dtemp.vectorclock;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import toolset.vectorclock.VectorClock;
/*
 * Needs comment.
 */

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class VectorClockTest {

    private VectorClock testClock;

    @Before
    public void setupTest() {
        // MyClass is tested
        this.testClock = new VectorClock(3);
    }

    @Test
    public void testEmptyClock() {
        assertTrue(this.testClock.toString().equals("<0,0,0>"));
    }

    @Test
    public void testIncrementedClock() {
        testClock.incrementClock(2);

        assertTrue(this.testClock.toString().equals("<0,0,1>"));
    }

    @Test
    public void testIncrementedClock2() {
        testClock.incrementClock(2);
        testClock.incrementClock(2);
        testClock.incrementClock(1);
        testClock.incrementClock(0);
        testClock.incrementClock(0);

        assertTrue(this.testClock.toString().equals("<2,1,2>"));
    }
}
