package inc.morsecode.json;

import inc.morsecode.spec.json.JsonElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JsonArray<T extends JsonElement> implements JsonElement, Iterable<T>, List<T> {

    private ArrayList<T> array= new ArrayList<T>();

    // default constructor
    public JsonArray() { }

    // construct from existing Collection of things
	public JsonArray(Collection<T> array) {
		for (T value : array) { add(value); }
	}

	public JsonArray(T[] array) {
		for (T value : array) { add(value); }
	}

	public Iterator<T> iterator() {
		return array.iterator();
	}

	@Override
	public Object getValue() {
		return array;
	}

	@Override
	public boolean add(T e) {
		return array.add(e);
	}

	@Override
	public T remove(int idx) {
		return array.remove(idx);
	}

	@Override
	public int size() {
		return array.size();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}
	
	@Override
	public String toString() {
		String str= "";
		String delim= "";
		for (T value : this) {
			str+= "\n\t"+ delim + value;
			delim= ", ";
		}
		return "["+ str +"\n]";
	}

	@Override
	public T get(int i) {
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
	public boolean addAll(Collection<? extends T> c) {
	    return array.addAll((c));
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
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
	public T set(int index, T element) {
		return array.set(index, element);
	}

	@Override
	public void add(int index, T element) {
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
	public ListIterator<T> listIterator() {
	    return array.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
	    return array.listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
	    return array.subList(fromIndex, toIndex);
	}

}
