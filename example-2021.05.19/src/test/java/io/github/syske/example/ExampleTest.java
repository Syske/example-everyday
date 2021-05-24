package io.github.syske.example;

import io.github.syske.example20210519.Example;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author sysker
 * @version 1.0
 * @date 2021-05-20 8:22
 */
public class ExampleTest {

    @Test
    public void lengthOfLastWordTest() {
        Assert.assertSame(1, new Example().lengthOfLastWord("a"));

    }
}
