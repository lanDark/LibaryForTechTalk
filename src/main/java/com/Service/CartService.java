/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Service;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vital
 */
public interface CartService {
    public boolean addItem(HttpServletRequest request, ProductService productServiceImpl) throws Exception;
    public void deleteItem();
    
}
