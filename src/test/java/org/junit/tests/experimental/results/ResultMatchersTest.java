package org.junit.tests.experimental.results;

import static common.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import org.junit.annotation.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.experimental.theories.Theory;

public class ResultMatchersTest {
    @Test
    public void hasFailuresHasGoodDescription() {
        assertThat(ResultMatchers.failureCountIs(3).toString(),
                is("has 3 failures"));
    }

    @Theory
    public void hasFailuresDescriptionReflectsInput(int i) {
        assertThat(ResultMatchers.failureCountIs(i).toString(),
                containsString("" + i));
    }
}
