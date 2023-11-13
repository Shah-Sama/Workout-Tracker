package persistence;

import model.Circuit;
import model.NegativeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// CLASS LEVEL COMMENT: These are my tests for the reading function which tests if the file is being read correctly

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Circuit circuit = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        } catch (NegativeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testReaderEmptyCircuit() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCircuit.json");
        try {
            Circuit circuit = reader.read();
            assertEquals("Circuit name", circuit.getCircuitName());
            assertEquals(0, circuit.getCircuit().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (NegativeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testReaderGeneralCircuit() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCircuit.json");
        try {
            Circuit circuit = reader.read();
            assertEquals("Circuit Name", circuit.getCircuitName());
            assertEquals(2, circuit.getCircuit().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (NegativeException e) {
            throw new RuntimeException(e);
        }
    }
}
