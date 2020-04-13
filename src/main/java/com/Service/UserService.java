
package com.Service;

import com.Interface.CRUD;
import com.model.NguoiDung;

/**
 *
 * @author vital
 * @param  url  an absolute URL giving the base location of the image
 * @return      the image at the specified URL
 * @see         Image
 */
public interface UserService extends CRUD<NguoiDung,String>{
    public boolean signUp(NguoiDung nguoiDung);
    /**
     *
     * @author vital
     * @param  email Email cần lấy<String>
     * @return      NguoiDung
     * @see CartService#datMuon(java.util.List, java.lang.String) 
     */
    public NguoiDung getUserByEmail(String email);
}
