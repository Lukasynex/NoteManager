package com.lukasyno.useful.sql;

public class SQLCommander {
	private SQLCommander instance = null;
	public final String DATABASE_NAME;
	public final String TABLE_NAME;
	public String CREATE;
	public String DELETE;

	public final String COL1 = "id INT PRIMARY KEY";
	public final String COL2 = "content TEXT";
	public final String COL3 = "tag TEXT";
	public final String COL4 = "date DATE";

	public SQLCommander getInstance(String db_name) {
		if (instance == null)
			instance = new SQLCommander(db_name);
		return instance;
	}

	public SQLCommander getInstance(String db_name, String tb_name) {
		if (instance == null)
			instance = new SQLCommander(db_name, tb_name);
		return instance;
	}

	private SQLCommander(String db_name) {
		this(db_name, "NOTE");
	}

	private SQLCommander(String db_name, String tb_name) {
		DATABASE_NAME = db_name;
		TABLE_NAME = tb_name;
		CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + COL1 + ", " + COL2
				+ ", " + COL3 + ", " + COL4 + ");";
		DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
	}
}