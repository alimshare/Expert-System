package com.sispak.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

public class Koneksi {

	private String DB_HOST = "jdbc:mysql://localhost/";
	private String DB_USER = "root";
	private String DB_PASSWORD = "";
	private String DB_NAME = "sispak_db";

	public Koneksi() {
		//loadConfigurationFile(); // Comment perintah ini jika ingin menggunakan konfigurasi file yang ada di dalam Class Koneksi
	}

	public Connection openConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DB_HOST + DB_NAME, DB_USER, DB_PASSWORD);
		} catch (Exception e) {
			System.out.println("Error Create Connection : " + e);
		}
		return con;
	}

	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadConfigurationFile() {
		String filename = "application.properties";
		InputStream inputx = null;

		try {
			inputx = new FileInputStream(filename);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}

		if (inputx == null) {
			System.out.println("Sorry, unable to find " + filename);
		}

		Properties property = new Properties();
		try {
			property.load(inputx);
			
			DB_USER = property.getProperty("connection.user");
			DB_PASSWORD = property.getProperty("connection.password");
			DB_HOST = property.getProperty("connection.host");
			DB_NAME = property.getProperty("connection.database.name");
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Koneksi().openConnection();
	}

}
