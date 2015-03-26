package database;

public class Professor {
	private String name, title, department, phone, email, loc1, loc2;
	
	public Professor(String name, String title, String department, String phone, String email, String loc1, String loc2) {
		this.name = name;
		this.title = title;
		this.department = department;
		this.phone = phone;
		this.email = email;
		this.loc1 = loc1;
		this.loc2 = loc2;
	}
	
	public Professor() {
		
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public void setTitle(String s) {
		title = s;
	}
	
	public void setDepartment(String s) {
		department = s;
	}

	public void setPhone(String s) {
		phone = s;
	}
	
	public void setEmail(String s) {
		email = s;
	}
	
	public void setLoc1(String s) {
		loc1 = s;
	}
	
	public void setLoc2(String s) {
		loc2 = s;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDepartment() {
		return department;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getLoc1() {
		return loc1;
	}

	public String getLoc2() {
		return loc2;
	}

}
