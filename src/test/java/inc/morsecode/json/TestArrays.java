package inc.morsecode.json;

import org.junit.Test;
import org.junit.Assert;

public class TestArrays {

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

	

}
