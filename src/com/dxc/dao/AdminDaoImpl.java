package com.dxc.dao;



import java.sql.*;

import java.util.*;

import com.dxc.dao.IAdminDao;
import com.dxc.pojo.BookPojo;
import com.dxc.pojo.UserPojo;

public class AdminDaoImpl implements IAdminDao{

	private static Connection con;
	static	{
	     try {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("class loaded..");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","tiger");
		System.out.println("Data base Connected...");
		
	    } catch (Exception e) {
		
		e.printStackTrace();
	}
		 
	}
	
	
	@Override
	public boolean passwordCheck(String name,String password) {
		boolean b=false;
		try {
		
			PreparedStatement pstmt=con.prepareStatement("select * from admin");
			ResultSet rs=pstmt.executeQuery();
			while(rs.next())
			{
				if(name.equals(rs.getString(2))&&password.equals(rs.getString(3)))
					b=true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public void closeConn()
	{
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addUser(int id, String name, String password, double balance) {
		try {
			PreparedStatement pstmt=con.prepareStatement("insert into user values(?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setDouble(4, balance);
			pstmt.execute();
			System.out.println("One user added Successfully ");
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addBook(int bid, String bname, String author, int quantity) {
		try {
			PreparedStatement pstmt=con.prepareStatement("insert into book values(?,?,?,?)");
			pstmt.setInt(1, bid);
			pstmt.setString(2, bname);
			pstmt.setString(3, author);
			pstmt.setInt(4, quantity);
			pstmt.execute();
			System.out.println("One is book added into the table");
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BookPojo> getUserBookList(int id) {
		int bookId=0;
		List<BookPojo> list=new ArrayList<BookPojo>();
			try 
			{
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from book_details");
				while(rs.next())
				{
					if(id==rs.getInt(2))
					{
						bookId=rs.getInt(1);
						Statement stmt2=con.createStatement();
						ResultSet rs1=stmt2.executeQuery("select * from book");
						while(rs1.next())
						{
							if(bookId==rs1.getInt(1))
							{
								BookPojo bp=new BookPojo(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getInt(4));
							list.add(bp);
							}
						}
						stmt2.close();
					}
				}
				stmt.close();
			
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	@Override
	public void closeConnection() 
	{
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	@Override
	public double getUserBalance(int id) {
		double balance=0;
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			while(rs.next())
			{
				if(id==rs.getInt(1))
				{
					balance=rs.getDouble(4);
				}
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return balance;
	}
	
	

	@Override
	public List<BookPojo> getTotalBookList() {
		
		List<BookPojo> list1=new ArrayList<BookPojo>();
		try {
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from book");
			while(rs.next())
			{
				BookPojo bp=new BookPojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
				list1.add(bp);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list1;
	}

	@Override
	public void deleteUser(int id) {
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement("delete from user where uid=?");
			pstmt.setInt(1, id);
			pstmt.execute();
			pstmt.close();
			System.out.println("\nsuccessfully user deleted from database...\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UserPojo> getUserList() {
		List<UserPojo> list2=new ArrayList<UserPojo>();
		try {
			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from user");
			while(rs.next())
			{
				UserPojo up=new UserPojo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
				list2.add(up);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list2;
	}
}