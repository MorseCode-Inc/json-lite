package inc.morsecode.json;

import inc.morsecode.spec.json.JsonElement;
import inc.morsecode.spec.json.JsonItem;

import java.util.ArrayList;


public class JsonMember implements inc.morsecode.spec.json.JsonItem {
	
	private String name;
	private JsonElement value;

	public JsonMember(String name, String value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Integer value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Long value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Double value) { this(name, new JsonPrimitive(value)); }
	public JsonMember(String name, Boolean value) { this(name, new JsonPrimitive(value)); }

	public JsonMember(String name, JsonArray value) {
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
	public JsonItem setName(String name) { this.name = name; return this; }
	
	@Override
	public Object getValue() {
		if (value == null) { return null; }
		return value.getValue();
	}
	
	@Override
	public JsonItem setValue(JsonElement value) { this.value= value; return this; }
	@Override
	public JsonItem setValue(String value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonItem setValue(Integer value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonItem setValue(Long value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonItem setValue(Double value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonItem setValue(Boolean value) { this.value= new JsonPrimitive(value); return this; }
	@Override
	public JsonItem setValue(ArrayList<JsonValue> value) { this.value= new JsonArray(value); return this; }

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
