package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Transaction 
{
	private String TransactionId;
	private String UserName;
	private String DessertName;
	private int DessertPrice;
	private int Quantity;

	public Transaction(String transactionId, String userName, String dessertName, int dessertPrice, int quantity) {
		super();
		TransactionId = transactionId;
		UserName = userName;
		DessertName = dessertName;
		DessertPrice = dessertPrice;
		Quantity = quantity;
	}
	
	public String getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getDessertName() {
		return DessertName;
	}

	public void setDessertName(String dessertName) {
		DessertName = dessertName;
	}

	public int getDessertPrice() {
		return DessertPrice;
	}

	public void setDessertPrice(int dessertPrice) {
		DessertPrice = dessertPrice;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	public static ArrayList<Transaction> getTransactionFromDB()
	{
		ArrayList <Transaction> transaction = new ArrayList<>();
		Connect connect = Connect.getConnection();
		ResultSet rs = connect.executeQuery
				("SELECT TransactionId, UserName, DessertName, DessertPrice, Quantity \r\n"
				+ "FROM transactions tr\r\n"
				+ "JOIN users us\r\n"
				+ "ON tr.UserId = us.UserId\r\n"
				+ "JOIN icecreams ic\r\n"
				+ "ON ic.DessertId = tr.DessertId\r\n"
				+ "UNION\r\n"
				+ "SELECT TransactionId, UserName, DessertName, DessertPrice, Quantity \r\n"
				+ "FROM transactions tr\r\n"
				+ "JOIN users us\r\n"
				+ "ON tr.UserId = us.UserId\r\n"
				+ "JOIN yoghurts yg\r\n"
				+ "ON yg.DessertId = tr.DessertId");
		
		try {
			while(rs.next())
			{
				String TransactionId = rs.getString("TransactionId");
				String UserName = rs.getString("UserName");
				String DessertName = rs.getString("DessertName");
				int DessertPrice = rs.getInt("DessertPrice");
				int Quantity = rs.getInt("Quantity");
				
				transaction.add(new Transaction(TransactionId, UserName, DessertName, DessertPrice, Quantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transaction;	
	}

}
