  
package com.dxc.service;

import java.util.*;

import com.dxc.dao.*;
import com.dxc.dao.IAdminDao;
import com.dxc.pojo.BookPojo;
import com.dxc.pojo.UserPojo;
import com.dxc.service.*;

public class AdminServiceImpl implements IAdminService {
	
	IAdminDao aDao=new AdminDaoImpl();
	
	@Override
	public boolean passwordCheck(String name,String password) {
		return aDao.passwordCheck(name,password);
	}

	@Override
	public void addUser(int uid, String uname, String password, double balance) {
		aDao.addUser(uid,uname,password,balance);
	}

	@Override
	public void addBook(int bid, String bname, String author, int quantity) {
		aDao.addBook(bid,bname,author,quantity);
	}

	@Override
	public List<BookPojo> getUserBookList(int id) {
	
		return aDao.getUserBookList(id);
	}

	@Override
	public double getUserBalance(int id) {
		return aDao.getUserBalance(id);
	}

	@Override
	public List<BookPojo> getTotalBookList() {
		return aDao.getTotalBookList();
	}

	@Override
	public void closeConnection() {
		aDao.closeConnection();
	}

	@Override
	public void deleteUser(int id) {
		aDao.deleteUser(id);
	}

	@Override
	public List<UserPojo> getUserList() {
		return aDao.getUserList();
	}

}