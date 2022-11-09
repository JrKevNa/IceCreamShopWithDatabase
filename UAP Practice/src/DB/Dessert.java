package DB;

import java.util.ArrayList;

public abstract class Dessert 
{
	protected int DessertId;
	protected String DessertName;
	protected String DessertType;
	protected int DessertPrice;
	
	public Dessert(int dessertId, String dessertName, String dessertType, int dessertPrice) {
		super();
		DessertId = dessertId;
		DessertName = dessertName;
		DessertType = dessertType;
		DessertPrice = dessertPrice;
	}

	public int getDessertId() {
		return DessertId;
	}

	public void setDessertId(int dessertId) {
		DessertId = dessertId;
	}

	public String getDessertName() {
		return DessertName;
	}

	public void setDessertName(String dessertName) {
		DessertName = dessertName;
	}

	public String getDessertType() {
		return DessertType;
	}

	public void setDessertType(String dessertType) {
		DessertType = dessertType;
	}

	public int getDessertPrice() {
		return DessertPrice;
	}

	public void setDessertPrice(int dessertPrice) {
		DessertPrice = dessertPrice;
	}
	
	public abstract int calculateTotal(int quantity);
}
