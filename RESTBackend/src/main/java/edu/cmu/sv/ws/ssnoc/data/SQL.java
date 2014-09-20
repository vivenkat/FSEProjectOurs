package edu.cmu.sv.ws.ssnoc.data;

/**
 * This class contains all the SQL related code that is used by the project.
 * Note that queries are grouped by their purpose and table associations for
 * easy maintenance.
 * 
 */
public class SQL {
	/*
	 * List the USERS table name, and list all queries related to this table
	 * here.
	 */
	public static final String SSN_USERS = "SSN_USERS";
	public static final String USERS_MESSAGES = "USERS_MESSAGES";

	/**
	 * Query to check if a given table exists in the H2 database.
	 */
	public static final String CHECK_TABLE_EXISTS_IN_DB = "SELECT count(1) as rowCount "
			+ " FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA() "
			+ " AND UPPER(TABLE_NAME) = UPPER(?)";

	// ****************************************************************
	// All queries related to USERS
	// ****************************************************************
	/**
	 * Query to create the USERS table.
	 */
	public static final String CREATE_USERS = "create table IF NOT EXISTS "
			+ SSN_USERS + " ( user_id IDENTITY PRIMARY KEY,"
			+ " first_name VARCHAR(100),"+ " last_name VARCHAR(100)," 
			+ " user_name VARCHAR(100),"+ " password VARCHAR(512),"
			+ " online_status TINYINT(1)"+ " role VARCHAR(100)," 
			+ " location VARCHAR(100)," +" salt VARCHAR(512) )";
	
	public static final String CREATE_MESSAGES = "create table IF NOT EXISTS "
			+ USERS_MESSAGES + " ( user_id int FOREIGN KEY REFERENCES SSN_USERS(user_id), "
			+ " pid IDENTIY PRIMARY KEY," + "message VARCHAR(255),"
			+ " timestamp TIMESTAMP(8)";

	/**
	 * Query to load all users in the system.
	 */
	public static final String FIND_ALL_USERS = "select user_id, user_name, password,"
			+ " salt " + " from " + SSN_USERS + " order by user_name";

	/**
	 * Query to find a user details depending on his name. Note that this query
	 * does a case insensitive search with the user name.
	 */
	public static final String FIND_USER_BY_NAME = "select user_id, user_name, password,"
			+ " salt "
			+ " from "
			+ SSN_USERS
			+ " where UPPER(user_name) = UPPER(?)";

	/**
	 * Query to insert a row into the users table.
	 */
	public static final String INSERT_USER = "insert into " + SSN_USERS
			+ " (user_name, password, salt) values (?, ?, ?)";

}
