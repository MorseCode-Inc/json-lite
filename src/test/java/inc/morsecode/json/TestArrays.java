package inc.morsecode.json;

import org.junit.Test;
import org.junit.Assert;
import static org.mockito.Mockito.*;

public class TestArrays {

	
	@Test
	public void testLazyLoad() {
		JsonArray<JsonPrimitive> array= new JsonArray();
		array.add(new JsonPrimitive(5));
		Assert.assertTrue((Integer)array.get(0).getValue() == 5);
	}
	
	@Test
	public void testEmpty() {
		JsonArray array= new JsonArray();
		Assert.assertTrue(array.isEmpty());
		
		array.add(System.currentTimeMillis());

		Assert.assertFalse(array.isEmpty());

		array.remove(0);
		
		Assert.assertTrue(array.isEmpty());
	}

	@Test
	public void testArray() {
		JsonArray array= new JsonArray();

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
		JsonArray array= new JsonArray();
		
		for (int i= 0; i < 5; i++) {
			array.add(i);
		}
		
		int i= 0;
		for (JsonValue value : array) {
			Assert.assertEquals(i++, ((Integer)value.getValue()).intValue());
		}
	}
	
	@Test
	public void testRemove() {
		JsonArray array= new JsonArray();
		
		for (int i= 10; i < 15; i++) {
			array.add(i);
		}
		
		JsonValue value= array.remove(3);
		
		Assert.assertEquals(13, ((Integer)value.getValue()).intValue());

	}

	
}
