package model;

import org.json.JSONObject;

// Class Level Comment: An exercise that has a name with a number of sets and reps that is the main class used
// in the program
public class Exercise {
    private String exerciseName;
    private int sets;
    private int repetitions;

    // EFFECTS: Creates a new exercise with a name as well as a given number of sets and reps
    public Exercise(String name, int sets, int reps) throws NegativeException {
        try {
            this.exerciseName = name;
            this.sets = sets;
            this.repetitions = reps;
            if (sets <= 0 || reps <= 0) {
                throw new NegativeException();
            }
        } catch (NegativeException e) {
            System.out.println("Wrong Number Entered");
        }
    }

    // EFFECTS: Returns the name of the Exercise
    public String getExerciseName() {
        return exerciseName;
    }

    // EFFECTS: Returns the sets of the Exercise
    public int getSets() {
        return sets;
    }

    // EFFECTS: Returns the reps of the Exercise
    public int getRepetitions() {
        return repetitions;
    }


    // EFFECTS: Returns this Exercise as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", exerciseName);
        json.put("sets", sets);
        json.put("reps", repetitions);
        return json;
    }
}
