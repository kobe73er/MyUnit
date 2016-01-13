package org.junit.tests.running.classes;

import static common.Assert.assertEquals;
import static common.Assert.fail;
import org.junit.annotation.Ignore;
import org.junit.annotation.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class IgnoreClassTest {
    @Ignore("For a good reason")
    public static class IgnoreMe {
        @Test
        public void iFail() {
            fail();
        }

        @Test
        public void iFailToo() {
            fail();
        }
    }

    @Test
    public void ignoreClass() {
        Result result = JUnitCore.runClasses(IgnoreMe.class);
        assertEquals(0, result.getFailureCount());
        assertEquals(1, result.getIgnoreCount());
    }
}
