package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// CLASS LEVEL COMMENT: these are my tests for the Exercise Class where multiple different scenarios are tested

public class ExerciseTest {
    private Exercise newExercise;

    @Test
    public void testConstructorWithoutException() throws NegativeException {
            Exercise newExercise = new Exercise("Bicep Curls", 3, 12);
            assertEquals("Bicep Curls", newExercise.getExerciseName());
            assertEquals(3, newExercise.getSets());
            assertEquals(12, newExercise.getRepetitions());
    }

    @Test
    public void testConstructorWithNegatives() {
        try {
            Exercise newExercise = new Exercise("Leg Press", 1, -1);
        } catch (NegativeException e) {
            fail("Wrong Number Entered");
        }
    }

    @Test
    public void testConstructorBoundaryConditionZero() {
        try {
            Exercise newExercise = new Exercise("Shoulder Press", 0, 1);
        } catch (NegativeException e) {
            fail("Wrong Number Entered");
        }
    }

    @Test
    public void testConstructorBothNegativeCondition() {
        try {
            Exercise newExercise = new Exercise("Calf Raises", -2, -1);
        } catch (NegativeException e) {
            fail("Wrong Number Entered");
        }
    }

    @Test
    public void testConstructorBothNegativeConditionMore() {
        try {
            Exercise newExercise = new Exercise("Calf Raises", -2000, 112);
        } catch (NegativeException e) {
            fail("Wrong Number Entered");
        }
    }

}
