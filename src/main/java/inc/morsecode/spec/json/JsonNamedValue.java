package inc.morsecode.spec.json;

import inc.morsecode.json.JsonValue;

import java.util.ArrayList;

/**
 * Created by morsecode on 7/23/2017.
 */
public interface JsonNamedValue {

    String getName();

    JsonNamedValue setName(String name);

    Object getValue();

    JsonNamedValue setValue(JsonElement value);

    JsonNamedValue setValue(String value);

    JsonNamedValue setValue(Integer value);

    JsonNamedValue setValue(Long value);

    JsonNamedValue setValue(Double value);

    JsonNamedValue setValue(Boolean value);

    JsonNamedValue setValue(ArrayList<JsonValue> value);

    JsonElement getElement();
}
