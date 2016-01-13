package org.junit.tests.experimental.theories.extendingwithstubs;

import static common.Assume.assumeThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(StubbedTheories.class)
public class StubbedTheoriesTest {
    @Theory
    public void ask(@Stub Correspondent correspondent) {
        assumeThat(correspondent.getAnswer("What is five?", "four", "five"),
                is("five"));
    }
}
