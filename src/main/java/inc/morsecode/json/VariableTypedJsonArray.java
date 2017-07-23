package inc.morsecode.json;

import inc.morsecode.spec.json.JsonElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class VariableTypedJsonArray extends JsonValue implements Iterable<JsonElement> {

	public VariableTypedJsonArray() {
		super(new ArrayList<JsonElement>());
	}

	public VariableTypedJsonArray(Collection<JsonElement> array) {
		this();
		for (JsonElement value : array) { add(value); }
	}

	public VariableTypedJsonArray(int[] array) {
		this();
		for (int value : array) { add(value); }
	}

	public VariableTypedJsonArray(long[] array) {
		this();
		for (long value : array) { add(value); }
	}

	public VariableTypedJsonArray(double[] array) {
		this();
		for (double value : array) { add(value); }
	}

	public VariableTypedJsonArray(boolean[] array) {
		this();
		for (boolean value : array) { add(value); }
	}

	public VariableTypedJsonArray(String[] array) {
		this();
		for (String value : array) { add(value); }
	}

	public Iterator<JsonElement> iterator() {
		return getArray().iterator();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<JsonElement> getArray() {
		return (ArrayList<JsonElement>)getValue();
	}
	
	public void add(JsonElement e) {
		if (e == null) {
			e= new JsonValue() {};
		}
		getArray().add(e);
	}
	
	public JsonElement remove(int idx) {
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
		for (JsonElement value : this) {
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

	public JsonElement get(int i) {
		return getArray().get(i);
	}
	
}
