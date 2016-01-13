package org.junit.tests.junit3compatibility;

import static common.Assert.assertEquals;
import static common.Assert.assertThat;
import static common.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

public class AllTestsTest {

    private static boolean run;

    public static class OneTest extends TestCase {
        public void testSomething() {
            run = true;
        }
    }

    @RunWith(AllTests.class)
    public static class All {
        static public junit.framework.Test suite() {
            TestSuite suite = new TestSuite();
            suite.addTestSuite(OneTest.class);
            return suite;
        }
    }

    @org.junit.annotation.Test
    public void ensureTestIsRun() {
        JUnitCore runner = new JUnitCore();
        run = false; // Have to explicitly set run here because the runner might independently run OneTest above
        runner.run(All.class);
        assertTrue(run);
    }

    @org.junit.annotation.Test
    public void correctTestCount() throws Throwable {
        AllTests tests = new AllTests(All.class);
        assertEquals(1, tests.testCount());
    }

    @org.junit.annotation.Test
    public void someUsefulDescription() throws Throwable {
        AllTests tests = new AllTests(All.class);
        assertThat(tests.getDescription().toString(), containsString("OneTest"));
    }

    public static class JUnit4Test {
        @org.junit.annotation.Test
        public void testSomething() {
            run = true;
        }
    }

    @RunWith(AllTests.class)
    public static class AllJUnit4 {
        static public junit.framework.Test suite() {
            TestSuite suite = new TestSuite();
            suite.addTest(new JUnit4TestAdapter(JUnit4Test.class));
            return suite;
        }
    }

    @org.junit.annotation.Test
    public void correctTestCountAdapted() throws Throwable {
        AllTests tests = new AllTests(AllJUnit4.class);
        assertEquals(1, tests.testCount());
    }

    @RunWith(AllTests.class)
    public static class BadSuiteMethod {
        public static junit.framework.Test suite() {
            throw new RuntimeException("can't construct");
        }
    }

    @org.junit.annotation.Test(expected = RuntimeException.class)
    public void exceptionThrownWhenSuiteIsBad() throws Throwable {
        new AllTests(BadSuiteMethod.class);
    }
}
