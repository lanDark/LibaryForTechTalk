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
public interface UserService {
    public boolean login(HttpServletRequest req,String email,String password);
    public boolean signIn();
    public boolean logOut();
    public boolean datMuon(HttpServletRequest req) throws Exception;
}
