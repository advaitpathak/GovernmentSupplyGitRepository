package com.al.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Administrator
 *	DBConnection class creates a connection if connection is closed with database
 */
public class DBConnection 
{
	private static Connection connection;
	
	/**
	 * 
	 * @return Returns connection object of DriverManager
	 */
	public static Connection getConnection()
	{
		
		try 
		{
			if(connection == null || connection.isClosed())
			{
				new DBConnection();		
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * DBConnection constructor establishes connection with database using sqlserver's username and password
	 */
	private DBConnection()
	{
		try 
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String username = "user1";
			String password = "user1";
			String url = "jdbc:sqlserver://127.0.0.1\\SQLEXPRESS"+";databaseName=GovernmentSupply";
			connection = DriverManager.getConnection(url,username,password);
			
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
 
 }
