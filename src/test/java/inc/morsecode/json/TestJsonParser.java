package inc.morsecode.json;

import inc.morsecode.json.ex.MalformedJsonException;
import inc.morsecode.spec.json.JsonStructure;
import org.junit.Test;
import org.junit.Assert;

public class TestJsonParser {

	private final static String SIMPLE= "{\"x\": 43, \"y\": 22}";
	private final static String OBJECT= "{\"x\": 43, \"address\": {\"street\": \"123 main\", \"city\": \"Denver\", \"state\": \"CO\"}}";
	private final static String ARRAY= "{\"array\": 43, \"address\": {\"street\": \"123 main\", \"city\": \"Denver\", \"state\": \"CO\"}}";
	private final static String MALFORMED_JSON= "{x: 43}";
	
	@Test
	public void testParse() {
		try {
			JsonStructure json= JsonParser.parse(SIMPLE);
			int x= json.get("x", 0);
			Assert.assertEquals(43, x);
		} catch (MalformedJsonException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(expected= MalformedJsonException.class)
	public void testMalformed() throws MalformedJsonException {
		JsonParser.parse(MALFORMED_JSON);
	}
	
	
	

}
