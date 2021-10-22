

public class Pilot extends Employee{
	
	private String licenseId;
	private int expLvl;
	private String droneId;
	
	public Pilot(String account, String fname, String lname, String phoneNum, String taxId, String licenseId, String expLvl) {
		super(account, fname, lname, phoneNum, taxId);
		this.licenseId = licenseId;
		this.expLvl = Integer.parseInt(expLvl);
	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public int getExpLvl() {
		return expLvl;
	}

	public void setExpLvl(int expLvl) {
		this.expLvl = expLvl;
	}

	public String getDroneId() {
		return droneId;
	}

	public void setDroneId(String droneId) {
		this.droneId = droneId;
	}

	public void incrementExp() {
		this.expLvl = this.expLvl + 1;
		
	}

	
}
