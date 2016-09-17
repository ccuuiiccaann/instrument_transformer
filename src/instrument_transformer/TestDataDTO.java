package instrument_transformer;

public class TestDataDTO {
	private String value_20;      
	private String value_50;      
	private String value_80;      
	private String value_100;     
	private String value_120;
	public String getValue_20() {
		return value_20;
	}
	public void setValue_20(String value_20) {
		this.value_20 = value_20;
	}
	public String getValue_50() {
		return value_50;
	}
	public void setValue_50(String value_50) {
		this.value_50 = value_50;
	}
	public String getValue_80() {
		return value_80;
	}
	public void setValue_80(String value_80) {
		this.value_80 = value_80;
	}
	public String getValue_100() {
		return value_100;
	}
	public void setValue_100(String value_100) {
		this.value_100 = value_100;
	}
	public String getValue_120() {
		return value_120;
	}
	public void setValue_120(String value_120) {
		this.value_120 = value_120;
	}
	public TestDataDTO(String value_20, String value_50, String value_80,
			String value_100, String value_120) {
		super();
		this.value_20 = value_20;
		this.value_50 = value_50;
		this.value_80 = value_80;
		this.value_100 = value_100;
		this.value_120 = value_120;
	}
	public TestDataDTO() {
		super();
	}     
	
}
