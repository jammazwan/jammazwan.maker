package jammazwan.entity;

public class XmplProject {
	private String xyzCode;
	private String name;
	private String desc;
	private String version;
	private String technology;
	private String specialInstructions;
	private String pckg;
	private String include; //such as processor, bean

	public XmplProject() {
		super();
	}

	public XmplProject(String xyzCode, String name, String desc, String version, String technology, String specialInstructions,
			String pckg, String include) {
		super();
		this.setXyzCode(xyzCode);
		this.name = name;
		this.desc = desc;
		this.version = version;
		this.technology = technology;
		this.specialInstructions = specialInstructions;
		this.pckg = pckg;
		this.include = include;
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public String getPckg() {
		return pckg;
	}

	public void setPckg(String pckg) {
		this.pckg = pckg;
	}

	public String getXyzCode() {
		return xyzCode;
	}

	public void setXyzCode(String xyzCode) {
		this.xyzCode = xyzCode;
	}
}
