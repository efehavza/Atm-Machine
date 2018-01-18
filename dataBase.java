package siparisTakipSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.ResultSetMetaData;

public class main {
	
	private static Connection connection = null;
	private static Statement query = null;
	private static ResultSet result = null;
	
	public static void main(String[] args) throws Exception {
		
		Scanner in = new Scanner(System.in);
		Class.forName("com.mysql.jdbc.Driver");
		connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/Sirket?user=root&password=root");

		int choice = 0;
		System.out.println("Menu:\n 1: Data Input\n 2: Product Quantity\n 3: Purchased Products Name by Name\n 4: Best Selling Products\n 5: Purchased Products on Last Day");
		choice = in.nextInt();
		switch(choice) {
		
		case 1:
			dataInput();
			break;
		
		case 2:
			whichProductQuantity();
			break;
			
		case 3:
			whichNumberOfCustomers();
			break;
			
		case 4:
			bestSellingProduct();
			break;
			
		case 5:
			soldProductsOnLastDayCategory();
			break;
			
		default:
			break;
		
		}
		
		
	}

	public static void dataInput() throws Exception {
		int tip = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Data Input Type:\n 1: Client\n 2: Order\n 3: Product\n 4: Product Category");
		tip = in.nextInt();
		switch(tip) {
		
		case 1:
			
			System.out.println("Please write your input as follows CustomerID, Name, LastName, E-mail, Address");
			String customer = "Insert into musteriler (musteriID, ad, soyad, email, adres) values (?, ?, ?, ?, ?)";
			PreparedStatement query = (PreparedStatement) connection.prepareStatement(customer);
			
			query.setInt(1,  in.nextInt());
			query.setString(2,  in.next());
			query.setString(3,  in.next());
			query.setString(4,  in.next());
			query.setString(5,  in.next());
			query.execute();
			query.close();
			
			control();
			
			break;
		
		case 2:
			
			System.out.println("Please write you input as follows OrderID, CustomerID, ProductID, OrderQuantity, Date of Order");
			String order = "Insert into siparisler (siparisID, musteriID, urunID, siparisAdedi, siparisTarihi) values (?, ?, ?, ?, ?)";
			PreparedStatement query2 = (PreparedStatement) connection.prepareStatement(order);
			
			query2.setInt(1,  in.nextInt());
			query2.setInt(2,  in.nextInt());
			query2.setInt(3,  in.nextInt());
			query2.setInt(4,  in.nextInt());
			query2.setInt(5,  in.nextInt());
			query2.execute();
			query2.close();
			
			
			break;
			
		case 3:
			
			System.out.println("Please write your imput as follows Product ID, Product Name, Category ID, Price, Brand, Stock Value");
			String product = "Insert into urunler (urunID, urunAdi, kategoriID, urunFiyati, urunMarkasi, urunStok) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement query3 = (PreparedStatement) connection.prepareStatement(product);
			
			query3.setInt(1,  in.nextInt());
			query3.setString(2,  in.next());
			query3.setInt(3,  in.nextInt());
			query3.setInt(4,  in.nextInt());
			query3.setString(5,  in.next());
			query3.setInt(6,  in.nextInt());
			query3.execute();
			query3.close();
			
			
			break;
			
		case 4:
			
			System.out.println("Please write your input as follows Category ID, Category Name, Category Description");
			String productCategory = "Insert into urunKategori (kategoriID, kategoriAdi, kategoriAciklama) values (?, ?, ?)";
			PreparedStatement query4 = (PreparedStatement) connection.prepareStatement(productCategory);
			
			query4.setInt(1,  in.nextInt());
			query4.setString(2,  in.next());
			query4.setString(3,  in.next());
			query4.execute();
			query4.close();
			
			
			break;
			
		default:
			break;
		
		}
		
		
		connection.close();
		
		
	}

	public static void soldProductsOnLastDayCategory() throws Exception {
		
		query = connection.createStatement();
		result = query.executeQuery("SELECT siparisler.siparisTarihi, urunKategori.kategoriAdi "
							      + "FROM siparisler, urunKategori, urunler "
							      + "WHERE urunler.kategoriID = urunKategori.kategoriID "
							      + "ORDER BY siparisler.siparisTarihi");
		
		print();
		
		result.close();
		query.close();
		connection.close();
		
	}
	
	public static void bestSellingProduct() throws Exception {
		
		query = connection.createStatement();
		result = query.executeQuery("SELECT siparisler.siparisAdedi, urunler.urunID, urunler.urunAdi "
								 + "FROM siparisler, urunler "
								 + "WHERE urunler.urunID = siparisler.urunID "
								 + "ORDER BY siparisler.siparisAdedi desc");
		
		print();
			
		result.close();
		query.close();
		connection.close();
		
		
	}
	
	public static void whichNumberOfCustomers() throws Exception {
		
		query = connection.createStatement();
		result = query.executeQuery("SELECT musteriler.ad, musteriler.soyad, siparisler.siparisAdedi, urunler.urunID, urunler.urunAdi "
						          + "FROM siparisler, musteriler, urunler "
						          + "WHERE urunler.urunID = siparisler.urunID "
						          + "ORDER BY musteriler.ad");
		
		print();
		
		result.close();
		query.close();
		connection.close();
		
		
	}
	
	public static void whichProductQuantity() throws Exception{
		
		query = connection.createStatement();
		result = query.executeQuery("SELECT urunler.urunAdi, urunler.urunID, siparisler.siparisAdedi "
								  + "FROM urunler, siparisler "
								  + "WHERE siparisler.urunID = urunler.urunID ORDER BY urunler.urunID");
		
		print();
		
		result.close();
		query.close();
		connection.close();
		
	}
	
	public static void print() throws Exception{
		
		ResultSetMetaData metadata = (ResultSetMetaData) result.getMetaData();
		int columnNumber = metadata.getColumnCount();
		
		for (int i = 1 ; i<=columnNumber;i++){
			String columnName = metadata.getColumnName(i);
			System.out.print(columnName + "\t ");
			}
			System.out.println("\n-------------------------------- ");

			while(result.next()){

				for (int i = 1 ; i <= columnNumber;i++){

					System.out.print(result.getString(i)+"\t ");

				}
				System.out.println("\n-------------------------------- ");
			}
		
		
	}
	
//	public static void control() throws Exception{
//		
//		
//		int kontrol = query.execute();
//		if (kontrol ==1) {
//			System.out.println("Ogrenci basariyla kaydedildi");
//		}
//		else {
//			System.out.println("Ogrenci kaydedilemedi");
//		}
//		query.close();
//		connection.close();
//		
//		
//		
//	}
	

}
