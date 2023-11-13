package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// CLASS LEVEL COMMENT: These are my tests for the circuit class that go and test multiple
// differnet possible scenarios with a try catch implemented so no negative reps can be added

public class CircuitTest {
    private Circuit testCircuit;
    private Exercise testExercise;
    private Exercise testExerciseNumber2;
    private Exercise testExerciseNumber3;


    @BeforeEach
    public void runBefore() throws NegativeException {
        testCircuit = new Circuit("Shoulders");
        testExercise = new Exercise("Shoulder Press",3,10);
        testExerciseNumber2 = new Exercise("Lunges",4,12);
        testExerciseNumber3 = new Exercise("Bench Press",3,8);
    }


    @Test
    public void testConstructor() {
        assertEquals("Shoulders", testCircuit.getCircuitName());
        assertEquals(0, testCircuit.getCircuit().size());
    }

    @Test
    public void testAddOne() {
        testCircuit.addExercise(testExercise);
        assertEquals(testExercise, testCircuit.getCircuit().get(0));
        assertEquals(1, testCircuit.getCircuit().size());
    }

    @Test
    public void testAddTwo() {
        testCircuit.addExercise(testExercise);
        testCircuit.addExercise(testExerciseNumber2);
        assertEquals(testExercise, testCircuit.getCircuit().get(0));
        assertEquals(testExerciseNumber2, testCircuit.getCircuit().get(1));
        assertTrue(testCircuit.getCircuit().contains(testExercise));
        assertTrue(testCircuit.getCircuit().contains(testExerciseNumber2));
        assertEquals(2, testCircuit.getCircuit().size());
    }

    @Test
    public void testAddThree() {
        testCircuit.addExercise(testExercise);
        testCircuit.addExercise(testExerciseNumber2);
        testCircuit.addExercise(testExerciseNumber3);
        assertEquals(testExercise, testCircuit.getCircuit().get(0));
        assertEquals(testExerciseNumber2, testCircuit.getCircuit().get(1));
        assertEquals(testExerciseNumber3, testCircuit.getCircuit().get(2));
        assertTrue(testCircuit.getCircuit().contains(testExercise));
        assertTrue(testCircuit.getCircuit().contains(testExerciseNumber2));
        assertTrue(testCircuit.getCircuit().contains(testExerciseNumber3));
        assertEquals(3, testCircuit.getCircuit().size());
    }

    @Test
    public void testDeleteOne(){
        testCircuit.addExercise(testExercise);
        assertEquals(testExercise, testCircuit.getCircuit().get(0));
        testCircuit.deleteExercise(testExercise);
        assertEquals(0,testCircuit.getCircuit().size());
        assertFalse(testCircuit.getCircuit().contains(testExercise));

    }

    @Test
    public void testDeleteTwo(){
        testCircuit.addExercise(testExercise);
        testCircuit.addExercise(testExerciseNumber2);
        assertEquals(testExercise, testCircuit.getCircuit().get(0));
        assertEquals(testExerciseNumber2, testCircuit.getCircuit().get(1));
        testCircuit.deleteExercise(testExercise);
        testCircuit.deleteExercise(testExerciseNumber2);
        assertEquals(0,testCircuit.getCircuit().size());
        assertFalse(testCircuit.getCircuit().contains(testExercise));
        assertFalse(testCircuit.getCircuit().contains(testExerciseNumber2));

    }

    @Test
    public void testDeleteNothing() {
        // Deleting objects with nothing being added
        testCircuit.deleteExercise(testExercise);
        testCircuit.deleteExercise(testExerciseNumber2);
        assertEquals(0, testCircuit.getCircuit().size());
    }


}
