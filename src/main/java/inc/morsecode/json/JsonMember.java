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
	
	public String getName() {
		return name;
	}
	
	public Object getValue() {
		return value.getValue();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(String value) { this.value= new JsonPrimitive(value); }
	public void setValue(Integer value) { this.value= new JsonPrimitive(value); }
	public void setValue(Long value) { this.value= new JsonPrimitive(value); }
	public void setValue(Double value) { this.value= new JsonPrimitive(value); }
	public void setValue(Boolean value) { this.value= new JsonPrimitive(value); }
	public void setValue(ArrayList<JsonValue> value) { this.value= new JsonArray(value); }

	@Override
	public String toString() {
		String str= "\""+ JsonParser.escape(getName()) +"\":"+ getJsonValue();
		return str;
	}
	public JsonValue getJsonValue() {
		return value;
	}
}
