package com.cybage.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.model.User;

public class MainDaoTest
{

@Autowired
private MainDao maindao;
    
    @Test
    @Transactional
    
	public void testUser()
	{
	User user= new User();
	user.setUserName("cyb");
	user.setUserPassword("cyb123");
	user.setFullName("cybage");
	user.setAddress("pune");
	user.setEmail("cyb@cybage.com");
	user.setPhone(88897797);
	maindao.addUser(user.getUserName(), user.getUserPassword(), user.getFullName(), user.getAddress(), user.getEmail(), user.getPhone());
	 }
	
}
