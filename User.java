

public class User {
	
	protected String account;
	protected String fname;
	protected String lname;
	protected String phoneNum;
	
	public User(String account, String fname, String lname, String phoneNum) {
		this.account = account;
		this.fname = fname;
		this.lname = lname;
		this.phoneNum = phoneNum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
