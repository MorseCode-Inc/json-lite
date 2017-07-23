package inc.morsecode.spec.json;

import inc.morsecode.json.JsonValue;

import java.util.ArrayList;

/**
 * Created by morsecode on 7/23/2017.
 */
public interface JsonItem {

    String getName();

    JsonItem setName(String name);

    Object getValue();

    JsonItem setValue(JsonElement value);

    JsonItem setValue(String value);

    JsonItem setValue(Integer value);

    JsonItem setValue(Long value);

    JsonItem setValue(Double value);

    JsonItem setValue(Boolean value);

    JsonItem setValue(ArrayList<JsonValue> value);

    JsonElement getElement();
}
