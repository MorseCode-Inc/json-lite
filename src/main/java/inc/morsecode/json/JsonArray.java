package inc.morsecode.json;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Iterator;

public class JsonArray extends JsonValue implements Iterable<JsonValue> {
	
	public JsonArray() {
		super(new ArrayList<JsonValue>());
	}
	
	public JsonArray(Collection<JsonValue> array) {
		this();
		for (JsonValue value : array) { add(value); }
	}

	public JsonArray(int[] array) {
		this();
		for (int value : array) { add(value); }
	}

	public JsonArray(long[] array) {
		this();
		for (long value : array) { add(value); }
	}

	public JsonArray(double[] array) {
		this();
		for (double value : array) { add(value); }
	}

	public JsonArray(boolean[] array) {
		this();
		for (boolean value : array) { add(value); }
	}

	public JsonArray(String[] array) {
		this();
		for (String value : array) { add(value); }
	}

	public Iterator<JsonValue> iterator() {
		return getArray().iterator();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JsonValue> getArray() {
		return (ArrayList<JsonValue>)getValue();
	}
	
	public void add(JsonValue e) {
		if (e == null) {
			e= new JsonValue() {};
		}
		getArray().add(e);
	}
	
	public JsonValue remove(int idx) {
		if (getValue() == null) {
			setValue(new ArrayList<JsonValue>());
		}
		return getArray().remove(idx);
	}
	
	public int size() {
		if (getValue() == null) {
			setValue(new ArrayList<JsonValue>());
		}
		return getArray().size();
	}
	
	public boolean isEmpty() {
		if (getValue() == null) {
			setValue(new ArrayList<JsonValue>());
		}
		return getArray().isEmpty();
	}
	
	@Override
	public String toString() {
		String str= "";
		String delim= "";
		for (JsonValue value : this) {
			str+= "\n\t"+ delim + value;
			delim= ", ";
		}
		return "["+ str +"\n]";
	}

	public void add(String value) { add(new JsonPrimitive(value)); }
	public void add(Integer value) { add(new JsonPrimitive(value)); }
	public void add(Long value) { add(new JsonPrimitive(value)); }
	public void add(Double value) { add(new JsonPrimitive(value)); }
	public void add(Boolean value) { add(new JsonPrimitive(value)); }

	public JsonValue get(int i) {
		return getArray().get(i);
	}
	
}
