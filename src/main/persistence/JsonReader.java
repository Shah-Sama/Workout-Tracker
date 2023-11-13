package persistence;

import model.Circuit;
import model.Exercise;
import model.NegativeException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// CLASS LEVEL COMMENT: Represents a reader that will read the JSON file and reads the data stored in file.json
// Code used as a guide : https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String file;

    // EFFECTS: Constructs a JSON reader to read a file
    public JsonReader(String file) {
        this.file =  file;
    }

    // EFFECTS: reads Circuit from file and returns it;
    public Circuit read() throws IOException, NegativeException {
        String jsonData = readFile(file);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCircuit(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();

    }

    // EFFECTS: parses a circuit from JSON object and returns it
    private Circuit parseCircuit(JSONObject jsonObject) throws NegativeException {
        String name = jsonObject.getString("workout title");
        Circuit circuit = new Circuit(name);
        addExercises(circuit, jsonObject);
        return circuit;
    }

    // MODIFIES: circuit
    // EFFECTS: parses exercises from JSON object and adds them to circuit
    private void addExercises(Circuit circuit, JSONObject jsonObject) throws NegativeException {
        JSONArray jsonArray = jsonObject.getJSONArray("exercise");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(circuit, nextExercise);
        }
    }

    // MODIFIES: circuit
    // EFFECTS: parses exercise from JSON object and adds it to circuit
    private void addExercise(Circuit circuit, JSONObject jsonObject) throws NegativeException {
        String name = jsonObject.getString("name");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        Exercise ex = new Exercise(name,sets,reps);
        circuit.addExercise(ex);
    }
}
