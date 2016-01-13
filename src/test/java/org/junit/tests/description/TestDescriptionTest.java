package org.junit.tests.description;

import static common.Assert.assertFalse;
import static common.Assert.assertTrue;
import org.junit.annotation.Test;
import org.junit.runner.Description;

public class TestDescriptionTest {
    @Test
    public void equalsIsFalseForNonTestDescription() {
        assertFalse(Description.createTestDescription(getClass(), "a").equals(new Integer(5)));
    }

    @Test
    public void equalsIsTrueForSameNameAndNoExplicitUniqueId() {
        assertTrue(Description.createSuiteDescription("Hello").equals(Description.createSuiteDescription("Hello")));
    }

    @Test
    public void equalsIsFalseForSameNameAndDifferentUniqueId() {
        assertFalse(Description.createSuiteDescription("Hello", 2).equals(Description.createSuiteDescription("Hello", 3)));
    }
}