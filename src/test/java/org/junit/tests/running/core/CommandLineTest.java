package org.junit.tests.running.core;

import static common.Assert.assertEquals;
import static common.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.annotation.After;
import org.junit.annotation.Before;
import org.junit.annotation.Test;
import org.junit.runner.JUnitCore;

public class CommandLineTest {
    private ByteArrayOutputStream results;
    private PrintStream oldOut;
    private static boolean testWasRun;

    @Before
    public void before() {
        oldOut = System.out;
        results = new ByteArrayOutputStream();
        System.setOut(new PrintStream(results));
    }

    @After
    public void after() {
        System.setOut(oldOut);
    }

    static public class Example {
        @Test
        public void test() {
            testWasRun = true;
        }
    }

    @Test
    public void runATest() {
        testWasRun = false;
        new MainRunner().runWithCheckForSystemExit(new Runnable() {
            public void run() {
                JUnitCore.main("org.junit.tests.running.core.CommandLineTest$Example");
            }
        });
        assertTrue(testWasRun);
    }

    @Test
    public void runAClass() {
        testWasRun = false;
        JUnitCore.runClasses(Example.class);
        assertTrue(testWasRun);
    }

    private static int fCount;

    static public class Count {
        @Test
        public void increment() {
            fCount++;
        }
    }

    @Test
    public void runTwoClassesAsArray() {
        fCount = 0;
        JUnitCore.runClasses(new Class[]{Count.class, Count.class});
        assertEquals(2, fCount);
    }

    @Test
    public void runTwoClasses() {
        fCount = 0;
        JUnitCore.runClasses(Count.class, Count.class);
        assertEquals(2, fCount);
    }
}
