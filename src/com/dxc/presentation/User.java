package com.dxc.presentation;

import java.util.*;
import java.util.Scanner;

import com.dxc.pojo.BookPojo;
import com.dxc.service.UserServiceImpl;
import com.dxc.service.IUserService;

public class User {

	
	public void userOperation(String name)
	{
		String uName=name;
		Scanner sc=new Scanner(System.in);
		Library lb=new Library();
		IUserService userserv=new UserServiceImpl();
		int uId=userserv.getUserId(uName);
		while(true)
		{
			System.out.println("1.Issue books from library");
			System.out.println("2.Get book list of library");
			System.out.println("3.Get list of books by particular author");
			System.out.println("4.Check available balance in library account");
			System.out.println("5.Return book");
			System.out.println("6.Issued books");
			System.out.println("7.Exit");
			
			System.out.println("Enter your choice...");
			int choice=sc.nextInt();
			
			switch (choice)
			{
			case 1:	
				
				
				System.out.println("Enter book id");
				int bId=sc.nextInt();
				System.out.println("Enter no of day Book you want");
				int day=sc.nextInt();
				if(day<=15)
				{
					double balance=day*5;
					userserv.issueBook(uId,bId,day,balance);
				}
				else
				{
					System.out.println("Only fifteen days are allowed!!!!...");
				}
				
				break;
				
			case 2:
				List<BookPojo> ls=userserv.getBookList();
				System.out.println();
				for(BookPojo bj:ls)
				{
					bj.display();
				}
				System.out.println();
				break;
				
			case 3:
				System.out.println("Enter Auther name");
				List<BookPojo> ls1=userserv.getBookListOfParticularAuther(sc.next());
				System.out.println();
				for(BookPojo bj:ls1)
				{
					bj.display();
				}
				System.out.println();
				break;
				
			case 4:
				double balance=userserv.getBalance(uId);
				System.out.println("\nbalance: "+balance+"\n");
				break;
			case 5:
				System.out.println("Enter book id");
				userserv.returnBook(uId,sc.nextInt());
				break;
			case 6:
				List<BookPojo> lst=userserv.getIssuedBook(uId);
				for(BookPojo bp1:lst)
				{
					bp1.displayBook();
				}
				break;
			case 7:
				userserv.closeConnection();
				System.exit(0);
				break;
				
			default:
				System.out.println("Wrong!! choose correct choice");
				break;
			}
		}
	}
}