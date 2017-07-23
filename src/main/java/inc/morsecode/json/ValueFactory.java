package inc.morsecode.json;

import inc.morsecode.spec.json.JsonElement;
import inc.morsecode.spec.json.JsonStructure;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by morsecode on 7/20/2017.
 */
public class ValueFactory {

    private Function toObject= new Function<Object, JsonElement>() {
        @Override
        public JsonElement apply(Object value) {
            if (value == null) { return new JsonPrimitive((String)null); }
            if (value instanceof List) {
                // array...
            } else if (value instanceof Map) {
                // object structure...
            }

            // string by default
            return new JsonPrimitive(value.toString());
        }
    };

    public static JsonElement createNull() {
        return new JsonPrimitive((String)null);
    }

    public static JsonElement create(final Object value) {

        if (value == null) { return createNull(); }

        if (value instanceof List) {
            // array...
        } else if (value instanceof Map) {
            // object structure...
            Map<String, Object> map = (Map) value;
            //Function<String, >

            final JsonStructure retVal= new JsonObject();

            map.keySet().stream().forEach(key -> retVal.set(key, create(map.get(key))));

            return retVal;

        }

       // treat everything else as a string for now...
       return new JsonPrimitive(value.toString());

    }
}
