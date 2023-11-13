package persistence;

import model.Exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This is the JSonTest class which the other test classes will extend
public class JsonTest {
    protected void checkExercise(String exerciseName, int sets, int reps, Exercise exercise) {
        assertEquals(exerciseName, exercise.getExerciseName());
        assertEquals(sets, exercise.getSets());
        assertEquals(reps, exercise.getRepetitions());
    }
}
