package persistence;

import org.json.JSONObject;


// CLASS LEVEL COMMENT: The interface that the json writer and reader will implement
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
