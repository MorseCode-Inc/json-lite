package inc.morsecode.json;

import inc.morsecode.json.JsonObject;

import inc.morsecode.json.ex.MalformedJsonException;
import inc.morsecode.kits.JsonParser;
import org.junit.Test;
import org.junit.Assert;

public class TestJsonParser {

	private final static String JSON= "{\"x\": 43, \"address\": {\"street\": \"123 main\", \"city\": \"Denver\", \"state\": \"CO\"}}";
	private final static String MALFORMED_JSON= "{x: 43}";
	
	@Test
	public void testParse() {
		try {
			JsonObject json= JsonParser.parse(JSON);
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
