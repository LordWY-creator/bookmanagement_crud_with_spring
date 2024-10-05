package com.spring.model;

import lombok.Data;

@Data
public class CartBean {
	private int id;
	private int quantity;	  
		
	  private BookBean bookid;
	  private int status;
	  private UserBean userid;
	  
}
