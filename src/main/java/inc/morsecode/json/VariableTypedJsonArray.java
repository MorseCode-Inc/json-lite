package inc.morsecode.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class VariableTypedJsonArray extends JsonValue implements Iterable<JsonValue> {

	public VariableTypedJsonArray() {
		super(new ArrayList<JsonValue>());
	}

	public VariableTypedJsonArray(Collection<JsonValue> array) {
		this();
		for (JsonValue value : array) { add(value); }
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
