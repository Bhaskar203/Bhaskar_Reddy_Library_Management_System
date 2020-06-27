package com.dxc.presentation;

import java.util.Scanner;

import com.dxc.service.AdminServiceImpl;
import com.dxc.service.UserServiceImpl;
import com.dxc.service.IAdminService;
import com.dxc.service.IUserService;

public class Library {
	
	public static void main(String[] args) {
		
		IAdminService adminserv=new AdminServiceImpl();
		IUserService userserv=new UserServiceImpl();
		User user=new User();
		Admin admin=new Admin();
		
		
		while(true) {
			
			Scanner sc=new Scanner(System.in);
			System.out.println("1.Admin");
			System.out.println("2.User");
			System.out.println("Choose your choice...");
			int choice=sc.nextInt();
			
			switch(choice)
			{
			case 1:
				System.out.println("Enter your name & password");
				String name=sc.next();
				String password=sc.next();
				boolean b1=adminserv.passwordCheck(name, password);
				if(b1==true)
				{
					System.out.println(name+" Loggined  Successfully");
					admin.adminOperation();
				}else
					System.out.println("name & password incorrect");
				break;
				
				
			case 2:
				System.out.println("Enter your name & password");
				String name2=sc.next();
				boolean b2=userserv.PasswordCheck(name2, sc.next());
				if(b2==true)
				{
					System.out.println("Hello"+name2);
					user.userOperation(name2);
				}else
					System.out.println("name & password incorrect");
				break;
				
				
			default:
				System.out.println("Choice any one in the above list...");
			}
		}
	}

}