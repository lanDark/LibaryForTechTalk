
package com.Service;

import com.model.NguoiDung;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author vital
 * @param  url  an absolute URL giving the base location of the image
 * @return      the image at the specified URL
 * @see         Image
 */
public interface UserService {
    public boolean login(HttpServletRequest req,String email,String password);
    public boolean signUp(NguoiDung nguoiDung);
    public boolean logOut();
    public boolean datMuon(HttpServletRequest req) throws Exception;
    /**
     *
     * @author vital
     * @param  email Email cần lấy<String>
     * @return      NguoiDung
     * @see CartService#datMuon(java.util.List, java.lang.String) 
     */
    public NguoiDung getUserByEmail(String email);
}
