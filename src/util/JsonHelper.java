package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.User;


public class JsonHelper {
	
	/**
	 * Funcion que serializa los datos de un hashmap de tipo Integer, User
	 * @param path donde se encuentre el archivo
	 * @param users
	 */
	public void serializeToJsonUsers(String path, HashMap<Integer, User> users) {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String userRepresentation = prettyGson.toJson(users);
		System.out.println(userRepresentation);
		
		try (FileWriter writer = new FileWriter(path)) {
			prettyGson.toJson(users, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Funcion que deserializa los datos de un hashmap de tipo Integer, User
	 * @param path donde se encuentre el archivo
	 * @return un hashmap con los datos del archivo
	 */
	public HashMap<Integer, User> deserializeJsonUsers(String path) {
		HashMap<Integer, User> users = new HashMap<Integer, User>();
		try (Reader reader = new FileReader(path)) {
			Gson gson = new Gson();
			Type usersHashType = new TypeToken<HashMap<Integer, User>>(){}.getType();
			users = gson.fromJson(reader, usersHashType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
}
