package net.rcelma.a1_30_app1.constant;

/**
 * Created by User on 1/30/2017.
 */

public class ContactQueries {
	public static final String CREATE = "INSERT INTO %1 (%2$s, %3$s} VALUES (%4$s, %5$s)";
	public static final String UPDATE = "UPDATE contact SET %1$s = %2$s WHERE %3$s = %4$f";
	public static final String DELETE = "DELETE FROM contact WHERE %3$s = %4$f";
	public static final String SELECT = "SELECT %1$s FROM contact WHERE %3$s = %4$f";
	public static final String CREATE_TABLE = "CREATE TABLE %1$s (%2$s INTEGER PRIMARY KEY,%3$s TEXT,%4$s TEXT)";
}