package com.dxc.service;

import java.util.List;

import com.dxc.pojo.BookPojo;
import com.dxc.pojo.UserPojo;

public interface IAdminService {
	public boolean passwordCheck(String name,String password);

	public void addUser(int id, String name, String password, double balance);
	public void addBook(int bid,String bname,String author,int quantity);

	public List<BookPojo> getUserBookList(int id);

	public double getUserBalance(int id);

	public List<BookPojo> getTotalBookList();

	public void closeConnection();

	public void deleteUser(int id);

	public List<UserPojo> getUserList();

}