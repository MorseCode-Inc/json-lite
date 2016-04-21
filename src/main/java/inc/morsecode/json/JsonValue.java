package inc.morsecode.json;


/**
 * 
 * &copy; MorseCode Incorporated 2015<br/>
 * =--------------------------------=<br/><pre>
 * Created: Aug 21, 2015
 * Project: uim-probe-kit
 *
 * Description:
 * JSON Value
 * </pre></br>
 * =--------------------------------=
 */
public abstract class JsonValue {
	
	private Object value;
	
	protected JsonValue() { this.value= null; }
	protected JsonValue(Object value) { this.value= value; }
	
	public Object getValue() { return value; }
	
	protected void setValue(Object value) { this.value= value; }
	
	@Override
	public String toString() {
		
		Object value= getValue();
		
		if (value == null) { return "null"; }
		
		if (value instanceof String) { return "\""+ JsonParser.escape((String)(value.toString())) +"\""; }
		
		return value.toString();
	}
	

}
