package model;

import java.sql.Timestamp;
import java.util.Date;

public class User {
	private int id;
	private String type;
	private String mail;
	private String username;
	private String password;
    private Timestamp creationDate;
    
    public User(int id, String type, String mail, String username, String password) {
        this.id = id;
        this.type = type;
        this.mail = mail;
        this.username = username;
        this.password = password;
        this.creationDate = new Timestamp(new Date().getTime());
    }
    
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
    
    public String toString() {
    	return "Usuario" + id + ", " + type + ", " + username + ", " + password + ", " + creationDate + ")";
    }
    
}
