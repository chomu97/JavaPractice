package org.comstudy.homework;
class Account{
	private String id;
	private String password;
	public Account(String id) {
		this(id, "1234");
	}
	public Account(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
}

class Manager extends Account{
	private final String key = "manager"; // 회원가입시 필요한 Key. 바뀌면 안됨
	public Manager(String id, String password){
		super(id, password);
	}
	
	public boolean validateKey(String key) {
		return this.key.equals(key);
	}
	
}

class User extends Account{
	private String name;
	private String phoneNumber;
	private String manager;
	private boolean isApproved;
	private boolean yn;
	
	public User(String id, String password) {
		super(id, password);
		yn = true;
	}
	
	public String getName() {
		return this.name;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public String getManager() {
		return this.manager;
	}
	
	public String getYN() {
		if (yn) return "허용";
		else return "차단";
	}
	
	public boolean getIsApproved() {
		return this.isApproved;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setYN(boolean yn) {
		this.yn = yn;
	}
	public void setManager(String managerName) {
		manager = managerName;
	}
	
	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
}