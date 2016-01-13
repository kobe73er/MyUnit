package org.junit.runners.parameterized;

import org.junit.runner.Runner;
import org.junit.runners.model.exception.InitializationError;

/**
 * A {@link ParametersRunnerFactory} that creates
 * {@link BlockJUnit4ClassRunnerWithParameters}.
 * 
 * @since 4.12
 */
public class BlockJUnit4ClassRunnerWithParametersFactory implements
        ParametersRunnerFactory {
    public Runner createRunnerForTestWithParameters(TestWithParameters test)
            throws InitializationError {
        return new BlockJUnit4ClassRunnerWithParameters(test);
    }
}
