

import java.util.Date;

public class Employee extends User{
	
	protected String storeName;
	protected String taxId;
	protected Date startDate;
	protected int currSalary;
	
	public Employee(String account, String fname, String lname, String phoneNum, String taxId) {
		super(account, fname, lname, phoneNum);
		this.taxId = taxId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getCurrSalary() {
		return currSalary;
	}

	public void setCurrSalary(int currSalary) {
		this.currSalary = currSalary;
	}
	
	public int calculateExperiance(){
		Date date = new Date();
		return ((date.getYear() * 12 + date.getMonth()) - (this.startDate.getYear() * 12 + this.startDate.getMonth())) + 1;
		
	}
}
