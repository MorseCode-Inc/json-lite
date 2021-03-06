package inc.morsecode.json;


import inc.morsecode.spec.json.JsonNamedValue;
import inc.morsecode.spec.json.JsonElement;
import inc.morsecode.spec.json.JsonStructure;

import java.util.*;
import java.util.stream.Collectors;

public class JsonObject implements JsonElement, JsonStructure {

	private Map<String, JsonNamedValue> data= new HashMap<String, JsonNamedValue>();

	@Override
	public Object getValue() { return this; }

	@Override
	public JsonStructure set(String name, String value) { set(new JsonMember(name, value)); return this; }
	@Override
	public JsonStructure set(String name, Integer value) { set(new JsonMember(name, value)); return this; }
	@Override
	public JsonStructure set(String name, Long value) { set(new JsonMember(name, value)); return this; }
	@Override
	public JsonStructure set(String name, Double value) { set(new JsonMember(name, value)); return this; }
	@Override
	public JsonStructure set(String name, Boolean value) { set(new JsonMember(name, value)); return this; }
	@Override
	public JsonStructure set(String name, Short value) { set(new JsonMember(name, (int)value)); return this; }
	
	@Override
	public JsonStructure set(String name, JsonStructure value) { set(new JsonMember(name, value)); return this; }
	@Override
	public JsonStructure set(String name, TypedJsonArray value) { set(new JsonMember(name, value)); return this; }
	@Override
	public JsonStructure set(String name, JsonElement value) { set(new JsonMember(name, value)); return this; }
	
	@Override
	public JsonStructure set(final JsonNamedValue member) {
		data.put(member.getName(), member);
		return this;
	}

	public JsonStructure setNull(String key) {
		data.put(key, new JsonMember(key, ValueFactory.createNull()));
		return this;
	}
	
	// getters
	
	/**
	 * Get a String value from this JSON Object by name
	 * @param name - the name of the attribute to get
	 * @return A String representation of the value
	 */
	@Override
	public String get(String name) {
		Object value= (Object)get(name, (Object)null);
		if (value == null) { return null; }
		if (value instanceof String) {
			return (String) value;
		}
		return value.toString();
	}
	
	/**
	 * Get a numeric(double) value from this JSON Object by name
	 * @param name - the name of the attribute to get
	 * @param ifNull - value to return if the key does not exist
	 * @return the numeric value
	 * @throws ClassCastException if the actual value exists, but is not the proper data type
	 */
	@Override
	public double get(String name, double ifNull) {
		Object value= get(name, (Object)null);
		if (value == null) { return ifNull; }
		
		if (value instanceof Double) {
			value= (Double)get(name, (Object)null);
		} else if (value instanceof Integer) {
			value= new Double((Integer)value);
		} else if (value instanceof Long) {
			value= new Double((Long)value);
		} else if (value instanceof String) {
			value= Double.parseDouble((String)value);
		}
		
		return (Double)value;
	}
	
	@Override
	public boolean get(String name, boolean ifNull) {
		try {
			Boolean value= (Boolean)get(name, (Object)null);
			if (value == null) { return ifNull; }
			return value;
		} catch (ClassCastException ccx) {
			String value= (String)get(name, "false");
			
			if ("true".equalsIgnoreCase(value)) {
				return true;
			}
			
			return false;
		}
	}
	
	@Override
	public long get(String name, long ifNull) {
		Object o= get(name, (Object)null);
		
		if (o instanceof Integer) {
			Integer value= (Integer)get(name, (Object)null);
			if (value == null) { return ifNull; }
			return new Long(value);
		} 
		
		Long value= (Long)get(name, (Object)null);
		if (value == null) { return ifNull; }
		return value;
		
	}
	
	@Override
	public int get(String name, int ifNull) {
		try {
			Integer value= (Integer)get(name, (Object)null);
		
			if (value == null) {
				return ifNull;
			}
		
			return value;
		} catch (ClassCastException ccx) {
			
			String string= ""+ (Object)get(name, (Object)null);
			
			try {
				return Integer.parseInt(string);
			} catch (NumberFormatException nfx) {
				return ifNull;
			}
		}
	}
	
	
	@Override
	public short get(String name, short ifNull) {
		Integer value= (Integer)get(name, (Object)null);
		
		if (value == null) {
			return ifNull;
		}
		
		return (short)value.intValue();
	}
	
	@Override
	public String get(String name, String ifNull) {
		Object value= get(name, (Object)null);
		
		if (value == null) { return ifNull; }
		
		return value.toString();
	}
	
	@Override
	public Object get(String name, Object ifNull) {
		JsonNamedValue member= data.get(name);
		if (member == null) { return ifNull; }
		
		Object value= member.getValue();
		if (value == null) { return ifNull; }
		
		return value;
	}

	@Override
	public JsonStructure getObject(String name) {
		JsonStructure value= getObject(name, (JsonStructure)null);
		return value;
	}
	
	@Override
	public JsonStructure getObject(String name, JsonStructure ifNull) {
		JsonStructure value= (JsonStructure)get(name, (Object)null);
		
		if (value == null) {
			return ifNull;
		}
		if (value instanceof JsonObject) {
			return value;
		}
		return ifNull;
	}
	

	/*
	@Override
	public String toString() {
		String str= "";
		String nl= "";
		String delim= "";
		for (JsonMember member : data.values()) {
			if (member.getJsonValue() instanceof JsonObject) {
				str+= delim + StrUtils.prefixText("\t", member.toString());
				
			} else if (member.getJsonValue() instanceof JsonArray) {
				str+= nl +"\t"+ delim +"\""+ member.getName() +"\":";
				str+= "\n"+ StrUtils.prefixText("\t\t", member.getJsonValue().toString());
			} else {
				str+= nl +"\t"+ delim + member;
			}
			delim= ", ";
			nl= "\n";
		}
		return "{\n"+ str +"\n}";
	}
	*/

	@Override
	public TypedJsonArray get(String key, TypedJsonArray ifNull) {
		
		JsonNamedValue member= data.get(key);
		
		if (member == null) { 
			return ifNull;
		}
		
		if (member.getElement() instanceof TypedJsonArray) {
			
			return (TypedJsonArray)member.getElement();
		}
		
		return ifNull;
		
	}

	@Override
	public String toString() {
		StringBuffer buff= new StringBuffer("{");

		String comma= "";
	    for (JsonNamedValue value : data.values()) {
	    	buff.append(comma).append(value.toString());
	    	comma= ",";
		}

	    buff.append("}");

	    return buff.toString();
	}

	@Override
	public Collection<String> keys() {
		return data.keySet();
	}

	@Override
	public Map<String, JsonNamedValue> getData() {
		return Collections.unmodifiableMap(data);
	}

	@Override
	public JsonStructure set(String key, Map<String, Object> map) {
	    set(key, ValueFactory.create(map));
	    return this;
	}

	@Override
	public JsonStructure merge(Map<String, Object> map) {
	    map.keySet().forEach(key -> set(key, ValueFactory.create(map.get(key))));
	    return this;
	}


	// no-op methods on a JsonObject, these are inherited, probably
	// means i have a poor design and need to separate this a bit more
	public JsonNamedValue setValue(Long value) { return this; }
	public JsonNamedValue setValue(Double value) { return this; }
	public JsonNamedValue setValue(String value) { return this; }
	public JsonNamedValue setValue(Boolean value) { return this; }
	public JsonNamedValue setValue(Integer value) { return this; }
	public JsonNamedValue setValue(ArrayList<JsonValue> value) { return this; }
	public JsonNamedValue setValue(JsonElement value) { return this; }
	public JsonNamedValue setName(String name) { return this; }
	public String getName() { return null; }

	public JsonElement getElement() { return this; }

	public Map<String, Object> asMap() {

		return data.entrySet()
				.stream().collect(Collectors.toMap(
						o -> o.getKey(),
						t -> {
							if (t.getValue() instanceof JsonStructure) {
								return ((JsonStructure)t.getValue().getValue()).asMap();
							} else if (t.getValue() instanceof JsonArray) {
							    // todo: needs a bit more work to return
								// the right thing in the case of an array
								return ((JsonArray) t.getValue()).asPrimitive();
							}

							return t.getValue().getValue();
						}
				))
				;
	}
}
