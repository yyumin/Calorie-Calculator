package persistence;

import org.json.JSONObject;

public interface Writable {
    //CITATION: code from the sample application provided on edX.
    //EFFECTS: returns this as JSON object
    JSONObject toJson();
}
