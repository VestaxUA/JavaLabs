package org.example;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoderTest {

    @Test
    public void testDecodeMessage() {
        String encodedMessage1 = "uftujoh";
        String expectedDecodedMessage1 = "testing";
        String actualDecodedMessage1 = Decoder.decodeWord(encodedMessage1);
        assertEquals(expectedDecodedMessage1, actualDecodedMessage1);

        String encodedMessage2 = "t2st3ng";
        String expectedDecodedMessage2 = "testing";
        String actualDecodedMessage2 = Decoder.decodeWord(encodedMessage2);
        assertEquals(expectedDecodedMessage2, actualDecodedMessage2);
    }
}
