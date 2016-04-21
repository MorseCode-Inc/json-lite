package inc.morsecode.json;

public class JsonPrimitive extends JsonValue {

	public JsonPrimitive(String value) { super(value); }
	public JsonPrimitive(Integer value) { super(value); }
	public JsonPrimitive(Long value) { super(value); }
	public JsonPrimitive(Double value) { super(value); }
	public JsonPrimitive(Boolean value) { super(value); }

}
