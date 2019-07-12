/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.model.*;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vital
 */
public interface DanhMucDAO {
    public List getDanhMucAll();
    public DanhMuc getDanhMucByURL(String url);
    public boolean updateDanhMuc(DanhMuc obj);
    public boolean deleteDanhMuc(DanhMuc obj);
    public boolean insertDanhMuc(DanhMuc obj);
    public boolean getDanhMucObj(DanhMuc obj);
}
