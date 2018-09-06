package com.hm.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteConfig;

public class SqliteDB
{

	private Connection c = null;
	private String dbPath;
	Logger logger;

	public SqliteDB(String dbPath, Logger logger)
	{
		this.dbPath = dbPath;
	}

	public void createConnection()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			SQLiteConfig config = new SQLiteConfig(); //I add this configuration 
			config.enforceForeignKeys(true);
			c = DriverManager.getConnection("jdbc:sqlite:" + dbPath, config.toProperties());
			c.setAutoCommit(false);

		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public void closeConnection()
	{
		try
		{
			c.close();
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public Connection getConnection()
	{
		return c;
	}

}
