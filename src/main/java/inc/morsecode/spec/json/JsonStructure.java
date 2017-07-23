package inc.morsecode.spec.json;

import inc.morsecode.json.TypedJsonArray;

import java.util.Collection;
import java.util.Map;

/**
 * Created by morsecode on 7/23/2017.
 */
public interface JsonStructure extends JsonElement, JsonItem {
    JsonStructure set(String name, String value);

    JsonStructure set(String name, Integer value);

    JsonStructure set(String name, Long value);

    JsonStructure set(String name, Double value);

    JsonStructure set(String name, Boolean value);

    JsonStructure set(String name, Short value);

    JsonStructure set(String name, JsonStructure value);

    JsonStructure set(String name, TypedJsonArray value);

    JsonStructure set(String name, JsonElement value);

    JsonStructure set(JsonItem member);

    String get(String name);

    double get(String name, double ifNull);

    boolean get(String name, boolean ifNull);

    long get(String name, long ifNull);

    int get(String name, int ifNull);

    short get(String name, short ifNull);

    String get(String name, String ifNull);

    Object get(String name, Object ifNull);

    JsonStructure getObject(String name);

    JsonStructure getObject(String name, JsonStructure ifNull);

    TypedJsonArray get(String key, TypedJsonArray ifNull);

    Collection<String> keys();

    Map<String, JsonItem> getData();

    JsonStructure set(String key, Map<String, Object> map);

    JsonStructure merge(Map<String, Object> map);

}
