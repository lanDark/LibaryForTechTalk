/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Service;

import com.model.Sach;
import java.util.List;

/**
 *
 * @author vital
 */
public interface CartService {
    public boolean datMuon(List<Sach> saches,String maNguoiDung) throws Exception;
}
