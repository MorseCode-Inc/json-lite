package inc.morsecode.json;

import inc.morsecode.spec.json.JsonElement;
import org.junit.Test;
import org.junit.Assert;

public class TestArrays {

	
	@Test
	public void testLazyLoad() {
		VariableTypedJsonArray array= new VariableTypedJsonArray();
		array.add(5);
		Assert.assertTrue((Integer)array.get(0).getValue() == 5);
	}
	
	@Test
	public void testEmpty() {
		VariableTypedJsonArray array= new VariableTypedJsonArray();
		Assert.assertTrue(array.isEmpty());
		
		array.add(System.currentTimeMillis());

		Assert.assertFalse(array.isEmpty());

		array.remove(0);
		
		Assert.assertTrue(array.isEmpty());
	}

	@Test
	public void testArray() {
		VariableTypedJsonArray array= new VariableTypedJsonArray();

		long ts= System.currentTimeMillis();
		
		array.add(true);
		array.add("String");
		array.add(4);
		array.add(56.3);
		array.add(ts);
		
		JsonValue nilValue= null;
		array.add(nilValue);
		
		Assert.assertTrue((Boolean)array.get(0).getValue() == true);
		Assert.assertTrue("String".equals(array.get(1).getValue()));
		Assert.assertTrue((Integer)array.get(2).getValue() == 4);
		Assert.assertTrue((Double)array.get(3).getValue() == 56.3);
		Assert.assertTrue((Long)array.get(4).getValue() == ts);
		Assert.assertTrue(array.get(5).getValue() == null);
	}

	@Test
	public void testIterator() {
		VariableTypedJsonArray array= new VariableTypedJsonArray();

		for (int i= 0; i < 5; i++) {
			array.add(i);
		}
		
		int i= 0;
		for (JsonElement value : array) {
			Assert.assertEquals(i++, ((Integer)value.getValue()).intValue());
		}
	}
	
	@Test
	public void testRemove() {
		VariableTypedJsonArray array= new VariableTypedJsonArray();

		for (int i= 10; i < 15; i++) {
			array.add(i);
		}
		
		JsonElement value= array.remove(3);
		
		Assert.assertEquals(13, ((Integer)value.getValue()).intValue());

	}

	
}
