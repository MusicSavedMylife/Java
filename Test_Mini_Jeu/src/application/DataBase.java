package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DataBase 
{
	private static DataBase instance = new DataBase();
	private Connection connection;
	
	private DataBase()
	{
		connect();
	}
	
	private void connect() 
	{
		try
		{
			String url = "jdbc:sqlite:game.db";
			connection = DriverManager.getConnection(url);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static DataBase getInstance()
	{
		if(instance==null)
		{
			instance = new DataBase();
		}
		return instance;
	}

	public Statement createStatement() throws Exception
	{
		return connection.createStatement();
	}
	
	public PreparedStatement prepareStatement(String sql) throws Exception
	{
		return connection.prepareStatement(sql);
	}
}
