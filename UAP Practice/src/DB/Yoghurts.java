package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Yoghurts extends Dessert
{
	private String ProbioticName;

	public Yoghurts(int dessertId, String dessertName, String dessertType, int dessertPrice, String probioticName) {
		super(dessertId, dessertName, dessertType, dessertPrice);
		ProbioticName = probioticName;
	}

	public String getProbioticName() {
		return ProbioticName;
	}

	public void setProbioticName(String probioticName) {
		ProbioticName = probioticName;
	}

	public static ArrayList <Yoghurts> getDessert() 
	{
		// TODO Auto-generated method stub
		ArrayList <Yoghurts> yoghurt = new ArrayList<>();
		Connect connect = new Connect();
		
		ResultSet rs = connect.executeQuery("SELECT * FROM yoghurts");
		
		try {
			while(rs.next())
			{
				int DessertId = rs.getInt("DessertId");
				String DessertName = rs.getString("DessertName");
				String DessertType = rs.getString("DessertType");
				int DessertPrice = rs.getInt("DessertPrice");
				String ProbioticName = rs.getString("ProbioticName");
				
				yoghurt.add(new Yoghurts(DessertId, DessertName, DessertType, DessertPrice, ProbioticName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return yoghurt;
	}
	
	@Override
	public int calculateTotal(int quantity) {
		// TODO Auto-generated method stub
		return DessertPrice * quantity;
	}
}
