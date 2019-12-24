/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Interface.CRUD;
import com.model.Sach;
import java.util.List;

/**
 *
 * @author vital
 */
public interface SachDAO extends  CRUD<Sach,String>{
    /**
     * Lấy sách mới nhất
     */
    public List getNewProductView();


}
