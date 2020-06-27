/*presentation*/
package com.dxc.presentation;

import java.util.*;
import java.util.Scanner;

import com.dxc.pojo.BookPojo;
import com.dxc.pojo.UserPojo;
import com.dxc.service.AdminServiceImpl;
import com.dxc.service.IAdminService;

public class Admin {

	public void adminOperation()
	{
		Scanner sc=new Scanner(System.in);
		
		IAdminService adminserv=new AdminServiceImpl();
		
		
		while(true) 
		{
			System.out.println("1.Add user");
			System.out.println("2.Add book");
			System.out.println("3.get list of book issued by particular user");
			System.out.println("4.get balance amount of particular user");
			System.out.println("5.get total book list");
			System.out.println("6.get total user list");
			System.out.println("7.Delete user from library");
			System.out.println("8.exit");
			
			System.out.println("Choose your choice...");
			int choice=sc.nextInt();
			
			switch(choice)
			{
			case 1:
				System.out.println("Enter user id,name & password ");
				double balance=5000.0;
				adminserv.addUser(sc.nextInt(),sc.next(),sc.next(),balance);
				break;
			case 2:
				System.out.println("Enter book id,book name,author name & quantity");
				adminserv.addBook(sc.nextInt(), sc.next(), sc.next(), sc.nextInt());
				break;
			case 3:
				System.out.println("Enter user id");
				List<BookPojo> ls=adminserv.getUserBookList(sc.nextInt());
				System.out.println();
				for(BookPojo bp:ls)
				{
					bp.display();
				}
				System.out.println();
				break;
			case 4:
				System.out.println("Enter user id");
				double balance1=adminserv.getUserBalance(sc.nextInt());
				System.out.println("balance is: "+balance1);
				break;
			case 5:
				List<BookPojo> ls2=adminserv.getTotalBookList();
				System.out.println();
				for(BookPojo bp:ls2)
				{
					bp.display();
				}
				System.out.println();
				break;
			case 6:
				List<UserPojo> userLs=adminserv.getUserList();
				System.out.println();
				for(UserPojo up:userLs)
				{
					up.display();
				}
				System.out.println();
				break;
			case 7:
				System.out.println("Enter user id");
				adminserv.deleteUser(sc.nextInt());
				break;
			case 8:
				adminserv.closeConnection();
				System.exit(0);
				break;
			default:
				System.out.println("Enter your choice from above list...");
				break;
			}
		}
	}

}