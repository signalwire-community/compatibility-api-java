package io.github.signalwirecommunity;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    public static SignalWireClient getClient() {
        SignalWireClient client = new SignalWireClient("PROJECT-ID", "API-TOKEN-ID", "SPACE-NAME");
        return client;
    }

}
