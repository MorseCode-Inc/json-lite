package inc.morsecode.json;


public abstract class JsonValue {
	
	public static final String NULL= "null";
	
	private Object value;
	
	protected JsonValue() { this.value= null; }
	protected JsonValue(Object value) { this.value= value; }
	
	public Object getValue() { return value; }
	
	protected void setValue(Object value) { this.value= value; }
	
	@Override
	public String toString() {
		
		Object value= getValue();
		
		if (value == null) { return quoted(NULL); }
		
		if (value instanceof String) { return quoted(JsonParser.escape(value.toString())); }
		
		return value.toString();
	}
	
	private String quoted(String value) {
		return "\""+ value +"\"";
	}
	
}
