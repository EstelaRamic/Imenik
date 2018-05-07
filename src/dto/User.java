package dto;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	

	public User(){
		
	}
	
	public User( String firstName, String lastName, String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String newLastName) {
		lastName = newLastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newPassword) {
		password = newPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String newUsername) {
		username = newUsername;
	}


	@Override
	public String toString() {
		return "User [first name=" + firstName + ", last name=" + lastName + ", username=" + username + ", password="
				+ password + "]"; 
	}
}
