package org.junit.tests.running.methods;

import static common.Assert.assertFalse;
import static common.Assert.assertTrue;
import static common.Assert.fail;
import org.junit.annotation.Before;
import org.junit.annotation.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class InheritedTestTest {
    public abstract static class Super {
        @Test
        public void nothing() {
        }
    }

    public static class Sub extends Super {
    }

    @Test
    public void subclassWithOnlyInheritedTestsRuns() {
        Result result = JUnitCore.runClasses(Sub.class);
        assertTrue(result.wasSuccessful());
    }

    public static class SubWithBefore extends Super {
        @Before
        public void gack() {
            fail();
        }
    }

    @Test
    public void subclassWithInheritedTestAndOwnBeforeRunsBefore() {
        assertFalse(JUnitCore.runClasses(SubWithBefore.class).wasSuccessful());
    }
} 
