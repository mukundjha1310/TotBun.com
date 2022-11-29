package com.totbun.services;

import java.util.List;

import com.totbun.exceptions.LogException;
import com.totbun.exceptions.ProductException;
import com.totbun.exceptions.UserException;
import com.totbun.modules.Cart;
import com.totbun.modules.Product;
import com.totbun.modules.User;

public interface UserServices {
	
	public User registerUser(User user) throws UserException, LogException;
	
	public User updateUser(User user) throws UserException, LogException;
	
	public User deleteUser(Integer userId) throws UserException, LogException;
	
	public User viewUser(Integer userId) throws UserException, LogException;
	
	public Cart addProductToCart(Integer userId, Integer productId) throws LogException, ProductException;
	
}
