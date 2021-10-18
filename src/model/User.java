package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class User {
    private int id;
    private String type;
    private String name;
    private String surname;
    private String mail;
    private String username;
    private String password;
    private Timestamp creationDate;
    
    
    public User(String type, String name, String surname, String mail, String username, String password) {
    	this.generateId();
        this.type = type;
        this.name = name;
        this.surname = surname;
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
    
    public String getName() {
    	return name;
    }
    
    public String getSurname() {
    	return surname;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setSurname(String surname) {
    	this.surname = surname;
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
    
    public void generateId() {
        final String path = "src/database/users/lastid.txt";
        int newId = -1;
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
               newId = Integer.parseInt(line) + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id = newId;
        
        try(FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(String.valueOf(newId));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String toString() {
        return "Usuario" + id + ", " + type + ", " + username + ", " + password + ", " + creationDate + ")";
    }
    
}