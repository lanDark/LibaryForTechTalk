/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Class;

import com.controller.UserController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vital
 */
public final class ForwardHandling {
    public static void forwardSRC(HttpServletRequest req,HttpServletResponse res,String src){
        try {
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(src);
            dispatcher.forward(req, res);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.INFO, null, ex);
        }
    }
}
