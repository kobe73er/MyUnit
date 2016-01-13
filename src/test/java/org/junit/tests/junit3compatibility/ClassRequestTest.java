package org.junit.tests.junit3compatibility;

import static common.Assert.assertNull;
import org.junit.annotation.Test;
import org.junit.internal.builders.SuiteMethodBuilder;

public class ClassRequestTest {
    public static class PrivateSuiteMethod {
        static junit.framework.Test suite() {
            return null;
        }
    }

    @Test
    public void noSuiteMethodIfMethodPrivate() throws Throwable {
        assertNull(new SuiteMethodBuilder()
                .runnerForClass(PrivateSuiteMethod.class));
    }
}
