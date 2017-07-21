package inc.morsecode.json;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by morsecode on 7/20/2017.
 */
public class ValueFactory {

    private Function toObject= new Function<Object, JsonValue>() {
        @Override
        public JsonValue apply(Object value) {
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

    public static JsonValue create(final Object value) {

        if (value == null) { return new JsonPrimitive((String)null); }

        if (value instanceof List) {
            // array...
        } else if (value instanceof Map) {
            // object structure...
            Map<String, Object> map = (Map) value;
            //Function<String, >

            final JsonObject retVal= new JsonObject();

            map.keySet().stream().forEach(key -> retVal.set(key, create(map.get(key))));

            return retVal;

        }

       // treat everything else as a string for now...
       return new JsonPrimitive(value.toString());

    }
}
