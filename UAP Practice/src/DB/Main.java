package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main 
{
	static Connect connect = new Connect();
	
	ArrayList <Transaction> transaction = new ArrayList<>();
	
	ArrayList <User> user = new ArrayList<>();
	
	ArrayList <Dessert> dessert = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	public Main() 
	{
		// TODO Auto-generated constructor stub
		int opt = 0;
		do
		{
			System.out.println("1. View All Transaction");
			System.out.println("2. Buy Dessert");
			System.out.println("3. Delete Transaction");
			
			do
			{
				System.out.print("Choose: ");
				try {
					opt = sc.nextInt();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sc.nextLine();
			}
			while(opt < 0 || opt > 4);
			
			switch(opt)
			{
			case 1:
				getTransaction();
				printTransaction();
				break;
				
			case 2:
				String userName = null, userEmail = null;
				int userAge = 0;
				
				do
				{
					System.out.print("Input your name [must more than 5 char]: ");
					userName = sc.nextLine();
				}
				while(userName.length() < 5);
				
				do
				{
					System.out.print("Input your email [must contains '@' and ends with '.com']: ");
					userEmail = sc.nextLine();
				}
				while(!(userEmail.contains("@") && userEmail.endsWith(".com")));
				
				do
				{
					System.out.print("Input your age [must be grater than 0]: ");
					try {
						userAge = sc.nextInt();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sc.nextLine();
				}
				while(userAge < 1);
				
				
				user = User.getUser();
				boolean userFound = false;
				for(int i=0; i<user.size(); i++)
				{
					if(userEmail.equals(user.get(i).getUserEmail()))
					{
						userFound = true;
						break;
					}
				}
				
				User currUser = new User(0, null, null, 0);
				if(userFound)
				{
					// do stuff
					currUser = User.getOneUser(userEmail);		
				}
				else if(!userFound)
				{
					addUser(userName, userEmail, userAge);
					currUser = User.getOneUser(userEmail);
				}
				
				//System.out.printf("currUser: %d %s %s %d\n", currUser.getUserId(), currUser.getUserName(), currUser.getUserEmail(), currUser.getUserAge());
				dessert.clear();
				ArrayList <IceCreams> iceCream = new ArrayList<>();
				iceCream = IceCreams.getDessert();	
				for(IceCreams i : iceCream)
				{
					dessert.add(i);
				}
				
				ArrayList <Yoghurts> yoghurt = new ArrayList<>();
				yoghurt = Yoghurts.getDessert();
				for(Yoghurts y : yoghurt)
				{
					dessert.add(y);
				}
				
				printDessert();

				
				int dessertOpt = 0;
				do
				{
					System.out.printf("Choose your dessert [1-%d]: ", dessert.size());
					try {
						dessertOpt = sc.nextInt();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sc.nextLine();
					
					dessertOpt -= 1;
				}
				while(opt < 1 || opt > dessert.size());
				
				int dessertQty = 0;
				do
				{
					System.out.print("Input Quantity [Must be greater than 0]: ");
					try {
						dessertQty = sc.nextInt();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sc.nextLine();
				}
				while(dessertQty < 1);
				
				// print details
				System.out.println("Detail Transaction");
				System.out.println("Name:\t\t" + currUser.getUserName());
				System.out.println("Price:\t\t" + dessert.get(dessertOpt).getDessertPrice());
				System.out.println("Quantity:\t" + dessertQty);
				System.out.println("Total:\t\t" + dessert.get(dessertOpt).calculateTotal(dessertQty));
				
				addTransaction(currUser.getUserId(), dessert.get(dessertOpt).getDessertId(), dessertQty);
				break;
				
			case 3:
				transaction = Transaction.getTransactionFromDB();
				printTransaction();
				
				int transactionIndex = 0;
				do
				{
					System.out.printf("Choose number you want to delete[1-%d]: ", transaction.size());
					try {
						transactionIndex = sc.nextInt();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sc.nextLine();
				}
				while(transactionIndex < 1 || transactionIndex > transaction.size());
				transactionIndex -= 1;
				
				String transactionId = transaction.get(transactionIndex).getTransactionId();
				deleteTransaction(transactionId);
				
				break;
			}
		}
		while(opt != 4);
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new Main();
	}
	
	public void printTransaction()
	{
		int i = 1;
		
		if(transaction.size() > 0)
		{
			System.out.printf("%-3s %-7s %-25s %-25s %-10s %-5s %-10s\n", "No", "ID", "Name", "Dessert", "Price", "Qty", "Total");
			for(Transaction t : transaction)
			{
				System.out.printf("%-3d %-7s %-25s %-25s %-10d %-5d %-10d\n", i, t.getTransactionId(), t.getUserName(), t.getDessertName(), t.getDessertPrice(), t.getQuantity(), t.getDessertPrice() * t.getQuantity());
				i++;
			}
		}
		else
		{
			System.out.println("No Transaction");
		}
	}
	
	public void printDessert()
	{
		System.out.printf("%-3s %-25s %-15s %-10s %-30s\n", "ID", "Name", "Type", "Price", "Ingredients");
		for(Dessert d : dessert)
		{
//			if(d.getDessertType().equals("Ice Cream"))
//			{
//				System.out.printf("%-3d %-25s %-15s %-10d %-30s\n", d.getDessertId(), d.getDessertName(), d.getDessertType(), d.getDessertPrice(), ((IceCreams)d).getCreamName());
//			}	
			
			if(d.getDessertType().equals("Ice Cream"))
			{
				System.out.printf("%-3d %-25s %-15s %-10d %-30s\n", d.getDessertId(), d.getDessertName(), d.getDessertType(), d.getDessertPrice(), (d instanceof IceCreams) ? ((IceCreams)d).getCreamName() : "-");
			}	
			else if(d.getDessertType().equals("Yoghurt"))
			{
				System.out.printf("%-3d %-25s %-15s %-10d %-30s\n", d.getDessertId(), d.getDessertName(), d.getDessertType(), d.getDessertPrice(), (d instanceof Yoghurts) ? ((Yoghurts)d).getProbioticName() : "-");
			}
		}
	}
	
	public ArrayList<Transaction> getTransaction()
	{
		transaction = Transaction.getTransactionFromDB();
		return null;
	}
	
	public void addUser(String UserName, String UserEmail, int UserAge)
	{
		String query = String.format("INSERT INTO users (`UserName`, `UserEmail`, `UserAge`) "
					+ "VALUES ('%s', '%s', '%d')", UserName, UserEmail, UserAge);
		connect.executeUpdate(query);
	}
	
	public void addTransaction(int UserId, int DessertId, int Quantity)
	{
		String TransactionId;
		
		TransactionId = generateRandomId();
		
		String query = String.format
				("INSERT INTO `transactions`(`TransactionId`, `UserId`, `DessertId`, `Quantity`)" + 
				"VALUES ('%s','%d','%d','%d')", TransactionId, UserId, DessertId, Quantity);
		
		connect.executeUpdate(query);
	}
	
	public void deleteTransaction(String transactionIndex)
	{
		String query = String.format
				("DELETE FROM `transactions`" +
				"WHERE TransactionId = '%s'", transactionIndex);
		
		connect.executeUpdate(query);
		System.out.printf("Transaction %s has been deleted\n", transactionIndex);
	}
	
	public String generateRandomId()
	{
		Random rand = new Random();
		return "TR" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
	}
}
