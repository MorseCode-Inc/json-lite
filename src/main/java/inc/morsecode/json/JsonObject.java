package inc.morsecode.json;


import java.util.Collection;
import java.util.HashMap;

public class JsonObject extends JsonValue {

	HashMap<String, JsonMember> data;
	
	public JsonObject() {
		data= new HashMap<String, JsonMember>();
	}
	
	/*
	public JsonObject(XmlTag xml) {
		this();
		
		if (!xml.getAttributes().isEmpty()) {
			JsonObject attributes= new JsonObject();
			for (String attribute : xml.getAttributes()) {
				String value= xml.getAttribute(attribute);
				attributes.set(attribute, JsonParser.toValue(value));
			}
			set("attributes", attributes);
		}
		
		String value= xml.getValue();
		if (value != null) {
			set("value", JsonParser.toValue(value));
		}
		
		set("name", xml.getName());
		
		if (xml.getChildren().length() > 0) {
			JsonArray children= new JsonArray();
			for (XmlTag tag : xml.getChildren()) {
				JsonObject child= new JsonObject(tag);
				child.set("name", tag.getName());
				children.add(child);
			}
			set("tags", children);
		}
		
	}
	*/
	
	@Override
	public Object getValue() { return this; }

	public JsonObject set(String name, String value) { set(new JsonMember(name, value)); return this; }
	public JsonObject set(String name, Integer value) { set(new JsonMember(name, value)); return this; }
	public JsonObject set(String name, Long value) { set(new JsonMember(name, value)); return this; }
	public JsonObject set(String name, Double value) { set(new JsonMember(name, value)); return this; }
	public JsonObject set(String name, Boolean value) { set(new JsonMember(name, value)); return this; }
	public JsonObject set(String name, Short value) { set(new JsonMember(name, (int)value)); return this; }
	
	public JsonObject set(String name, JsonObject value) { set(new JsonMember(name, value)); return this; }
	public JsonObject set(String name, JsonArray value) { set(new JsonMember(name, value)); return this; }
	public JsonObject set(String name, JsonValue value) { set(new JsonMember(name, value)); return this; }
	
	public JsonObject set(JsonMember member) {
		data.put(member.getName(), member);
		return this;
	}
	
	// getters
	
	/**
	 * Get a String value from this JSON Object by name
	 * @param name - the name of the attribute to get
	 * @return A String representation of the value
	 */
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
	 * @return the numeric value
	 * @throws ClassCastException if the actual value exists, but is not the proper data type
	 */
	public double get(String name, double ifNull) {
		// Double value= null;
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
	
	
	public short get(String name, short ifNull) {
		Integer value= (Integer)get(name, (Object)null);
		
		if (value == null) {
			return ifNull;
		}
		
		return (short)value.intValue();
	}
	
	public String get(String name, String ifNull) {
		Object value= get(name, (Object)null);
		
		if (value == null) { return ifNull; }
		
		return value.toString();
	}
	
	public Object get(String name, Object ifNull) {
		JsonMember member= data.get(name);
		if (member == null) { return ifNull; }
		
		Object value= member.getValue();
		if (value == null) { return ifNull; }
		
		return value;
	}

	public JsonObject getObject(String name) {
		JsonObject value= getObject(name, (JsonObject)null);
		return value;
	}
	
	public JsonObject getObject(String name, JsonObject ifNull) {
		JsonObject value= (JsonObject)get(name, (Object)null);
		
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

	public JsonArray get(String key, JsonArray ifNull) {
		
		JsonMember member= data.get(key);
		
		if (member == null) { 
			return ifNull;
		}
		
		if (member.getJsonValue() instanceof JsonArray) {
			
			return (JsonArray)member.getJsonValue();
		}
		
		return ifNull;
		
	}

	public Collection<String> keys() {
		return data.keySet();
	}

}
