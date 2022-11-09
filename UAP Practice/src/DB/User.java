package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class User 
{
	Scanner sc = new Scanner(System.in);
	
	private int UserId;
	private String UserName;
	private String UserEmail;
	private int UserAge;
	public User(int userId, String userName, String userEmail, int userAge) {
		super();
		UserId = userId;
		UserName = userName;
		UserEmail = userEmail;
		UserAge = userAge;
	}
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public int getUserAge() {
		return UserAge;
	}
	public void setUserAge(int userAge) {
		UserAge = userAge;
	}
	
	public static ArrayList<User> getUser()
	{
		Connect connect = new Connect();
		ResultSet rs = connect.executeQuery("SELECT * FROM users");
		ArrayList<User> user = new ArrayList<>();
		
		try {
			while(rs.next())
			{
				int UserId = rs.getInt("UserId");
				String UserName = rs.getString("UserName");
				String UserEmail = rs.getString("UserEmail");
				int UserAge = rs.getInt("UserAge");
				
				user.add(new User(UserId, UserName, UserEmail, UserAge));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public static User getOneUser(String UserEmail)
	{
		Connect connect = new Connect();
		String query = String.format
				("SELECT * FROM `users` \r\n"
				+ "WHERE UserEmail = '%s'", UserEmail);
		
		ResultSet rs = connect.executeQuery(query);
		
		int UserId = 0, UserAge = 0;
		String userEmail = null, userName = null;
		
		try {
			while(rs.next())
			{
				UserId = rs.getInt("UserId");
				userName = rs.getString("UserName");
				userEmail = rs.getString("UserEmail");
				UserAge = rs.getInt("UserAge");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		User currUser = new User(UserId, userName, userEmail, UserAge);
		return currUser;
	}
}
