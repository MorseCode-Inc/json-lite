package inc.morsecode.json;

import org.junit.Test;
import org.junit.Assert;

public class TestPrimitives {

	@Test
	public void testInteger() {
		JsonPrimitive value= new JsonPrimitive(5);
		Assert.assertTrue((Integer)value.getValue() == 5);
	}

	@Test
	public void testDouble() {
		JsonPrimitive value= new JsonPrimitive(4.32);
		Assert.assertTrue((Double)value.getValue() == 4.32);
	}

	@Test
	public void testString() {
		JsonPrimitive value= new JsonPrimitive("Hello World");
		Assert.assertTrue("Hello World".equals(value.getValue()));
	}
	

}
