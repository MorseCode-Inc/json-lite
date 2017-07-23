package inc.morsecode.json;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import inc.morsecode.json.ex.MalformedJsonException;
import inc.morsecode.spec.json.JsonStructure;

public class JsonParser {
	
	private enum ParserMode { INIT, NVPAIR, VALUE }
	private int lineNo= 1;
	private int charNo= 1;
	private ArrayList<String> tokens;
	
	private String raw;

	private JsonParser(String json) {
		this.tokens= getTokens(json, ",:{}[]\"\\");
	}
	
	public static JsonStructure parse(File file) throws FileNotFoundException, IOException, MalformedJsonException {
		return parse(new FileInputStream(file), file.length());
	}
	
	public static JsonStructure parse(InputStream in, long length) throws IOException, MalformedJsonException {
		
		StringBuffer buffer= new StringBuffer();
		
		byte[] chunk= new byte[1024];
		
		int available= 0;
		long read= 0;
		while ((available= in.available()) > 0 || read < length) {
			int c= in.read(chunk, 0, (available > chunk.length ? chunk.length : available));
			read+= c;
			buffer.append(new String(chunk, 0, c));
		}
		
		return parse(buffer.toString());
	}

	
	public static JsonStructure parse(String data) throws MalformedJsonException {
		
		
		data= data.trim();
		
		if (data.startsWith("{") && data.endsWith("}")) {
			JsonParser parser= new JsonParser(data);
			JsonStructure obj= parser.parseObject();
			return obj;
		} else {
			// System.err.println("Malformed JSON:\n"+ data);
			throw new MalformedJsonException("JSON object must be contained within '{' and '}' ("+ data +")");
		}
		
	}
	
	public static JsonValue toValue(String value) {
		// String value= xml.getAttribute(attribute);
		// attributes.set(attribute, value);
		value= value.trim();
		try {
			Integer i= Integer.parseInt(value);
			return new JsonPrimitive(i);
		} catch (NumberFormatException i) {
			try {
				return new JsonPrimitive(Long.parseLong(value));
			} catch (NumberFormatException l) {
				try {
					return new JsonPrimitive(Double.parseDouble(value));
				} catch (NumberFormatException d) {
					if ("true".equalsIgnoreCase(value)) {
						return new JsonPrimitive(true);
					} else if ("false".equalsIgnoreCase(value)) {
						return new JsonPrimitive(false);
					} else {
						if ("null".equals(value)) {
							return new JsonPrimitive((String)null);
						}
						return new JsonPrimitive(value);
					}
					
				}
				
			}
			
		}
	}
	
	public static String escape(String value) {
		StringBuilder builder= new StringBuilder();
		
		for (int i= 0; i < value.length(); i++) {
			char c= value.charAt(i);
			switch (c) {
			case '\n':
				builder.append("\\n");
				break;
			case '\r':
				builder.append("\\r");
				break;
			case '\b':
				builder.append("\\b");
				break;
			case '\f':
				builder.append("\\f");
				break;
			case '\t':
				builder.append("\\t");
				break;
			case '"':
			case '\\':
				builder.append("\\"+ c);
				break;
			default:
				
				if (c > 128) {
					builder.append("\\u"+ Integer.toHexString(c));
				} else {
					builder.append(c);	
				}
			}
		}
		
		return builder.toString();
		
	}
	
	private JsonStructure parseObject() throws MalformedJsonException {
		ParserMode mode= ParserMode.INIT;
		
		String token= next();
		if (!"{".equals(token)) {
			throw new  MalformedJsonException("Object Parser expecting '{' as declaration of new structure, instead got '"+ token +"'");
		}
		
		JsonObject obj= new JsonObject();
		
		mode= ParserMode.NVPAIR;
			
		int i= 0;
		JsonValue value= null;
		String junk= null;
		while (tokens.size() > 0 && !"}".equals(peek())) { //  && (i++ < 10)) {
			char c= peek();			// peek at first token
			switch (c) {

			case '"':
				String name= nextString();
				try {
					while (peek() != ':') { next(); }
					junk= next();
				} catch (MalformedJsonException x) {
					throw new MalformedJsonException("Unexpected character '"+ c +"' after name '"+ name +"' for object member at line "+ lineNo +","+ charNo +".", x);
				}
				value= nextValue(name);
				
				obj.set(name, value);
					
				break;
			case '{':
			case ':': // begin object
				throw new MalformedJsonException("Unexpected character '"+ c +"' at line "+ lineNo +","+ charNo +".");
			case ' ': // white space
			case '\t': // white space
			case '\r': // white space
			case '\n': // white space
				next();
				break;
			case ',': // next member
				junk= next();
				mode= ParserMode.NVPAIR;
				break;
			case '}':
				next();
				return obj;
			default:
				throw new MalformedJsonException("Missing expected character '{},\" ' near '"+ next() +"' at line "+ lineNo +","+ charNo +".");
				
			}
			
			
		}
		
		
		return obj;
		
	}


	private String next() throws MalformedJsonException {
		try {
			String token= tokens.remove(0);
		
			for (int i= 0; i < token.length(); i++) {
				char c= token.charAt(i);
				switch (c) {
				case '\n':
					lineNo++;
					charNo= 0;
					break;
				}
				charNo++;
			}
			// System.out.println("POP '"+ token +"'");			
			return token;
		} catch (IndexOutOfBoundsException oobx) {
			
			throw new MalformedJsonException("Unexpected EOF at line "+ lineNo +","+ charNo, oobx);
			
		}
	}
	
	private char peek() throws MalformedJsonException {
		try {
			String token= tokens.get(0).trim();
			// while ((c= token.charAt(i++)) == ' ' || c == '\t' || c == '\n' || c == '\r' ) {}
			while (token.length() == 0) {
				next();
				token= tokens.get(0).trim();
			}
		
			return token.charAt(0);
		} catch (IndexOutOfBoundsException oobx) {
			throw new MalformedJsonException("Unexpected EOF at line "+ lineNo +","+ charNo, oobx);
		}
	}


	private String nextString() throws MalformedJsonException {
		String endToken= next();
		
		StringBuilder text= new StringBuilder();
		
		try {
			String token= null;
		
			while (!endToken.equals(token= next())) {
				if ("\\".equals(token)) {
					String next= next();
					char c= next.charAt(0);
					
					if (next.length() > 1) {
						next= next.substring(1);
						tokens.add(0, next);
					}
					
					switch (c) {
					case 'b':
						text.append("\b");
						break;
					case 'n':
						text.append("\n");
						break;
					case 'f':
						text.append("\f");
						break;
					case 'r':
						text.append("\r");
						break;
					case 't':
						text.append("\t");
						break;
					case 'u':
						// unicode character support
						c= readUnicodeChar();
						text.append(c);
						break;
					default:
						text.append(c);
					}
				} else {
					text.append(token);
				}
			}
			
			return text.toString();
		} catch (MalformedJsonException x) {
			throw x;
		}
	}

	private JsonValue nextValue(String name) throws MalformedJsonException {
		char c= peek();
		if (c == '"') { 
			return new JsonPrimitive(nextString());
		} else if (c == '{') { 
			JsonValue value= (JsonValue)parseObject();
			return value;
		} else if (c == '[') { 
			return nextArray();
		} else if (c == ' ' || c == '\t' || c == '\r' || c == '\n') { // white space
		}
		
		return toValue(next());
	}
	
	
	private TypedJsonArray nextArray() throws MalformedJsonException {
		TypedJsonArray array= new TypedJsonArray();
		if (!"[".equals(next())) {
			throw new MalformedJsonException("Expecting array value, missing [");	// pop beginning bracket
		}
		
		char peek = peek();

		while (']' != peek) {
			
			if ('[' == peek) {
				// multi-dimensional array
				TypedJsonArray dimension= nextArray();
				array.add(dimension);
			} else if ('{' == peek) {
				array.add((JsonStructure)parseObject());
			} else if (',' == peek) {
				next(); // pop the comma off
			} else {
				array.add(nextValue("array["+ array.size() +"]"));
			}
			
		}
		
		// pop the last ] 
		if (!"]".equals(next())) {
			throw new MalformedJsonException("Expecting array value, missing terminating ]");
		}
		
		return array;
	}
	


	private ArrayList<String> getTokens(String data, String delimiters) {
		StringTokenizer parser= new StringTokenizer(data, delimiters, true);
		ArrayList<String> tokens= new ArrayList<String>();
		
		// build a queue of tokens to read from
		while (parser.hasMoreTokens()) {
			String token= parser.nextToken();
			tokens.add(token);
			// System.out.println("TOKEN '"+ token +"'");
		}
		
		return tokens;
	}
	
	public char readUnicodeChar() throws MalformedJsonException {
		throw new MalformedJsonException("Unicode encoded characters are unsupported in this version (at line "+ lineNo +","+ charNo +").");
		/*
		String u= next();
		if (u.length() > 4) {
			String back= next.substring(4);
			tokens.add(0, back);
			u= u.substring(0, 4);
		}
		while (u.length() < 4) {
			u= "0"+ u;
		}
		try {
			c= (char) Integer.parseInt(u, 16);
			return c;
		} catch (NumberFormatException nfx) {
			throw new MalformedJsonException("Unsupported unicode value near '"+ token +"' at line "+ lineNo +","+ charNo +" ["+ nfx.getMessage() +"]", nfx);
		}
		*/
	}
	
	
	public static void main(String[] args) throws Exception {
		
		//String json= "{\"name\" : 3, \"msg\": \"hello world\", \"age\":31}";
		
		JsonStructure json= new JsonObject();
		JsonStructure address= new JsonObject();
		TypedJsonArray array= new TypedJsonArray();
		json.set("name", (Integer)null);
		json.set("age", 31);
		address.set("street", "123 Main St.");
		address.set("city", "Springfield");
		address.set("state", "MA");
		address.set("zip", 80504);
		json.set("address", address);
		json.set("array", array);
		
		array.add(new JsonPrimitive(1));
		array.add(new JsonPrimitive("wo\"rd"));
		array.add(new JsonPrimitive(3.34));
		array.add(new JsonPrimitive("bl[]ue"));
		array.add(new JsonPrimitive("f \t \n ish"));
		// array.add(new JsonPrimitive(true));
		
		
		System.out.println("JSON "+ json);
		
		String jsonString= "{\"here\":40,\"id\":\"300\",\"name\":\"Test\",\"title\":\"Test\",\"path\":\"/pages/mypath\",\"description\":\"\",\"content\":\"my \n<p>content</p>\n\",\"test\":\"\"}";
		jsonString= "{\"here\":[\"thing1\",\"thing2\"],\"id\":\"300\",\"name\":\"Test\",\"title\":\"Test\",\"path\":\"/pages/mypath\",\"description\":\"\",\"content\":\"my \n<p>content</p>\n\",\"test\":\"\"}";

		JsonParser p= new JsonParser(jsonString);
		// JsonParser p= new JsonParser("{\"name\" : \"3\"}");
		
		JsonStructure o= p.parseObject();
		
		System.out.println(o);
		
		System.out.println("id = "+ o.get("id", 30));
		System.out.println("content = "+ o.get("content"));
		
		
		String j= "{\"services\":[{\"id\":\"PNWN6R3\",\"name\":\"support.morsecode-inc.com\",\"service_url\":\"/services/PNWN6R3\",\"service_key\":\"bb730aa07f74467c8254e827a0c921c8\",\"auto_resolve_timeout\":14400,\"acknowledgement_timeout\":1800,\"created_at\":\"2015-07-30T23:01:56-06:00\",\"deleted_at\":null,\"status\":\"active\",\"last_incident_timestamp\":null,\"email_incident_creation\":null,\"incident_counts\":{\"triggered\":0,\"resolved\":0,\"acknowledged\":0,\"total\":0},\"email_filter_mode\":\"all-email\",\"type\":\"generic_events_api\",\"description\":\"\"}],\"limit\":25,\"offset\":0,\"total\":1}";
		
		o= JsonParser.parse(j);
		System.out.println(o);

	}
	
}
