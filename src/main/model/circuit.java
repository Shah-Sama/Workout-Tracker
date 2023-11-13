package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Class Level Comment: A workout is a class that adds multiple exercises to a workout as a list
// This implements my user story as the workout is a list of excercises and the workout class takes in multiple
// exercises

public class Circuit {
    private ArrayList<Exercise> circuit;
    private String circuitName;

    // EFFECTS: Creates a workout with a given circuitName and makes a new list of exercises to add to
    public Circuit(String circuitName) {
        circuit = new ArrayList<Exercise>();
        this.circuitName = circuitName;
    }

    // MODIFIES: this
    // EFFECTS: Takes an exercise as a parameter and adds it to the circuit
    public void addExercise(Exercise exercise) {
        circuit.add(exercise);
        EventLog.getInstance().logEvent(new Event("Added exercise " + exercise.getExerciseName()));
    }

    // MODIFIES: this
    // EFFECTS: Takes an Exercise and deletes it depending on if it is in the list
    public void deleteExercise(Exercise exercise) {
        circuit.remove(exercise);
        EventLog.getInstance().logEvent(new Event("Deleted exercise " + exercise.getExerciseName()));
    }

    // EFFECTS: returns the array
    public ArrayList<Exercise> getCircuit() {
        return circuit;
    }

    // EFFECTS: returns the name of the circuit
    public String getCircuitName() {
        return circuitName;
    }


    // EFFECTS: Returns the circuit as a JSON object
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("workout title", circuitName);
        jsonObject.put("exercise", allExercises());
        return jsonObject;

    }

    // EFFECTS: Returns all the moves as an array
    public JSONArray allExercises() {
        JSONArray jsonExercise = new JSONArray();

        for (Exercise e : circuit) {
            jsonExercise.put(e.toJson());
        }

        return jsonExercise;
    }


}
