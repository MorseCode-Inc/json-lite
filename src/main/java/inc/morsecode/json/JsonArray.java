package inc.morsecode.json;

import inc.morsecode.spec.json.JsonElement;
import inc.morsecode.spec.json.JsonStructure;

import java.util.*;
import java.util.stream.Collectors;

public class JsonArray extends JsonValue implements Iterable<JsonElement>, List<JsonElement> {

	private ArrayList<JsonElement> array;

	public JsonArray() {
	    // the parent class (JsonValue) will hold a reference to
		// the array of JsonElements
		super(new ArrayList<JsonElement>());

		// now grab a properly typed reference from
		// our parent class
		this.array= (ArrayList<JsonElement>) this.getValue();
	}

	public JsonArray(Collection<JsonElement> array) {
		this();
		for (JsonElement value : array) { add(value); }
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

	public Iterator<JsonElement> iterator() {
		return array.iterator();
	}
	
	// public ArrayList<JsonElement> getArray() {
	//     return this.array;
	// }

	public boolean add(JsonElement e) {
		if (e == null) {
			e= new JsonValue() {};
		}
		return array.add(e);
	}
	
	public JsonElement remove(int idx) {
		if (getValue() == null) {
			setValue(new ArrayList<JsonValue>());
		}
		return array.remove(idx);
	}
	
	public int size() {
		if (getValue() == null) {
			setValue(new ArrayList<JsonValue>());
		}
		return array.size();
	}
	
	public boolean isEmpty() {
		if (getValue() == null) {
			setValue(new ArrayList<JsonValue>());
		}
		return array.isEmpty();
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


	@Override
	public JsonElement get(int i) {
		return array.get(i);
	}

	@Override
	public boolean contains(Object o) {
		return array.contains(o);
	}

	@Override
	public Object[] toArray() {
		return array.toArray();
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return array.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		return array.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return array.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends JsonElement> c) {
		return array.addAll((c));
	}

	@Override
	public boolean addAll(int index, Collection<? extends JsonElement> c) {
		return array.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return array.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return array.retainAll(c);
	}

	@Override
	public void clear() {
		array.clear();
	}

	@Override
	public JsonElement set(int index, JsonElement element) {
		return array.set(index, element);
	}

	@Override
	public void add(int index, JsonElement element) {
		array.add(index, element);
	}

	@Override
	public int indexOf(Object o) {
		return array.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return array.lastIndexOf(o);
	}

	@Override
	public ListIterator<JsonElement> listIterator() {
		return array.listIterator();
	}

	@Override
	public ListIterator<JsonElement> listIterator(int index) {
		return array.listIterator(index);
	}

	@Override
	public List<JsonElement> subList(int fromIndex, int toIndex) {
		return array.subList(fromIndex, toIndex);
	}

	public Object[] asPrimitive() {
		return (array.stream()
					 .map(e -> {
						if (e instanceof JsonStructure) {
							return ((JsonStructure) e).asMap();
						} else if (e instanceof JsonArray) {
							// todo: need to make the json objects do this...
                            return ((JsonArray) e).asPrimitive();
						}
						return e.getValue();
					})
					.collect(Collectors.toList()).toArray(new Object[]{}));
	}

}
