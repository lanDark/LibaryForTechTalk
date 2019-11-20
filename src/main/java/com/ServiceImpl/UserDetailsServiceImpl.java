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
        NguoiDung nguoiDung=userDAOImpl.getUserByEmail(email);
        
        if(nguoiDung==null)
            throw new UsernameNotFoundException("User not found");
        System.out.println(nguoiDung.getMatKhau());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Rules rule:nguoiDung.getRules())
        {
            System.out.println(rule.getRole());
            grantedAuthorities.add(new SimpleGrantedAuthority(rule.getRole()));
        }
        return new CustomUser(
                nguoiDung.getEmail(), nguoiDung.getMatKhau(), grantedAuthorities,nguoiDung.getHoTen(),nguoiDung.getMaNguoiDung(),nguoiDung.getSdt());
    }
    
}
