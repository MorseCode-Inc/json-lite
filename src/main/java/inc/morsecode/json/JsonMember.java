package inc.morsecode.json;

import java.util.ArrayList;


public class JsonMember {
	
	private String name;
	private JsonValue value;

	public JsonMember(String name, String value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Integer value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Long value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Double value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Boolean value) { this(name, new JsonPrimitive(value)); }
	
	public JsonMember(String name, JsonValue value) {
		this.name= name;
		this.value= value;
	}
	
	public String getName() { return name; }
	public JsonMember setName(String name) { this.name = name; return this; }
	
	public Object getValue() {
		if (value == null) { return null; }
		return value.getValue();
	}
	
	public JsonMember setValue(JsonValue value) { this.value= value; return this; }
	public JsonMember setValue(String value) { this.value= new JsonPrimitive(value); return this; }
	public JsonMember setValue(Integer value) { this.value= new JsonPrimitive(value); return this; }
	public JsonMember setValue(Long value) { this.value= new JsonPrimitive(value); return this; }
	public JsonMember setValue(Double value) { this.value= new JsonPrimitive(value); return this; }
	public JsonMember setValue(Boolean value) { this.value= new JsonPrimitive(value); return this; }
	public JsonMember setValue(ArrayList<JsonValue> value) { this.value= new JsonArray(value); return this; }

	@Override
	public String toString() {
		String str= "\""+ JsonParser.escape(getName()) +"\":"+ getJsonValue();
		return str;
	}

	public JsonValue getJsonValue() {
		return value;
	}
}
