package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IceCreams extends Dessert
{
	private String CreamName;

	public IceCreams(int dessertId, String dessertName, String dessertType, int dessertPrice, String creamName) {
		super(dessertId, dessertName, dessertType, dessertPrice);
		CreamName = creamName;
	}

	public String getCreamName() {
		return CreamName;
	}

	public void setCreamName(String creamName) {
		CreamName = creamName;
	}
	
	public static ArrayList<IceCreams> getDessert() 
	{
		// TODO Auto-generated method stub
		ArrayList <IceCreams> iceCream = new ArrayList<>();
		Connect connect = new Connect();
		
		ResultSet rs = connect.executeQuery("SELECT * FROM icecreams");
		
		try {
			while(rs.next())
			{
				int DessertId = rs.getInt("DessertId");
				String DessertName = rs.getString("DessertName");
				String DessertType = rs.getString("DessertType");
				int DessertPrice = rs.getInt("DessertPrice");
				String CreamName = rs.getString("CreamName");
				
				iceCream.add(new IceCreams(DessertId, DessertName, DessertType, DessertPrice, CreamName));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return iceCream;
	}

	@Override
	public int calculateTotal(int quantity) {
		// TODO Auto-generated method stub
		return DessertPrice * quantity;
	}
}
