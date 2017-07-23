package inc.morsecode.spec.json;

import inc.morsecode.json.JsonValue;

import java.util.ArrayList;

/**
 * Created by morsecode on 7/23/2017.
 */
public interface JsonItem {

    public String getName();

    public JsonItem setName(String name);

    public Object getValue();

    public JsonItem setValue(JsonElement value);

    public JsonItem setValue(String value);

    public JsonItem setValue(Integer value);

    public JsonItem setValue(Long value);

    public JsonItem setValue(Double value);

    public JsonItem setValue(Boolean value);

    public JsonItem setValue(ArrayList<JsonValue> value);

    public JsonElement getElement();
}
