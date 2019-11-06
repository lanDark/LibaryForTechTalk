/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceImpl;

import com.DAO.UserDAO;
import com.model.NguoiDung;
import com.model.Rules;
import com.securityImpl.CustomUser;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vital
 */
@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    private final Logger logger=Logger.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserDAO userDAOImpl;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("IN loadUserByUserName");
        NguoiDung nguoiDung=userDAOImpl.login(email);
        
        if(nguoiDung==null)
            throw new UsernameNotFoundException("User not found");
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Rules> rules = new HashSet();
        rules.add(nguoiDung.getRules());// bởi vì một người dùng chỉ có một quyền duy nhất mà hibernate không hổ trợ sử dụng collection cho ManyToOne nên ta tự thiết lập
        for(Rules rule:rules)
        {
            grantedAuthorities.add(new SimpleGrantedAuthority(rule.getNameRules()));
        }
        return new CustomUser(
                nguoiDung.getEmail(), nguoiDung.getMatKhau(), grantedAuthorities,nguoiDung.getHoTen(),nguoiDung.getMaNguoiDung(),nguoiDung.getSdt());
    }
    
}
