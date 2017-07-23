package inc.morsecode.json;

import inc.morsecode.spec.json.JsonElement;
import inc.morsecode.spec.json.JsonNamedValue;

import java.util.ArrayList;


public class JsonMember implements JsonNamedValue {
	
	private String name;
	private JsonElement value;

	public JsonMember(String name, String value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Integer value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Long value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Double value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Boolean value) { this(name, new JsonPrimitive(value)); }

	public JsonMember(String name, TypedJsonArray value) {
		this.name= name;
		this.value= value;
	}

	public JsonMember(String name, JsonElement value) {
		this.name= name;
		this.value= value;
	}
	
	@Override
	public String getName() { return name; }
	@Override
	public JsonNamedValue setName(String name) { this.name = name; return this; }
	
	@Override
	public Object getValue() {
		if (value == null) { return null; }
		return value.getValue();
	}
	
	@Override
	public JsonNamedValue setValue(JsonElement value) { this.value= value; return this; }
	@Override
	public JsonNamedValue setValue(String value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonNamedValue setValue(Integer value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonNamedValue setValue(Long value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonNamedValue setValue(Double value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonNamedValue setValue(Boolean value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonNamedValue setValue(ArrayList<JsonValue> value) { this.value= new TypedJsonArray(value); return this; }

	@Override
	public String toString() {
		String str= "\""+ JsonParser.escape(getName()) +"\":"+ getElement();
		return str;
	}

	@Override
	public JsonElement getElement() {
		return value;
	}
}
