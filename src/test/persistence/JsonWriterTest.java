package persistence;

import model.Circuit;
import model.Exercise;
import model.NegativeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// CLASS LEVEL COMMENT: These are my tests for the writing function which shows that the data is being saved properly
public class JsonWriterTest extends JsonTest {
    private Exercise exercise1;
    private Exercise exercise2;

    @Test
    void testWriterInvalidFile() {
        try {
            Circuit circuit = new Circuit("Workout Title");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCircuit() {
        try {
            Circuit circuit = new Circuit("Circuit name");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCircuit.json");
            writer.open();
            writer.write(circuit);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCircuit.json");
            circuit = reader.read();
            assertEquals("Circuit name", circuit.getCircuitName());
            assertEquals(0, circuit.getCircuit().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (NegativeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testWriterGeneralCircuit() {
        try {
            Circuit circuit = new Circuit("Circuit Name");
            exercise1 = new Exercise("Leg Press",3 ,12);
            exercise2 = new Exercise("Calf Raises",4 ,13);
            circuit.addExercise(exercise1);
            circuit.addExercise(exercise2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCircuit.json");
            writer.open();
            writer.write(circuit);
            writer.close();
            JsonReader reader = new JsonReader("./data/testWriterGeneralCircuit.json");
            circuit = reader.read();
            assertEquals("Circuit Name", circuit.getCircuitName());
            assertEquals(2, circuit.getCircuit().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (NegativeException e) {
            throw new RuntimeException(e);
        }
    }
}
