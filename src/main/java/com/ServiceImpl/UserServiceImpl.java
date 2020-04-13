package com.ServiceImpl;

import com.DAO.UserDAO;
import com.Service.UserService;
import com.model.NguoiDung;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vital
 */
@Service
@Transactional

public class UserServiceImpl implements UserService{
    @Autowired
    UserDAO userDAOImpl;

    @Override
    public boolean signUp(NguoiDung nguoiDung) {
        return  userDAOImpl.signUp(nguoiDung);
    }

    @Override
    public NguoiDung getUserByEmail(String email) {
       return userDAOImpl.getUserByEmail(email);
    }

    @Override
    public boolean create(NguoiDung object) {
        return userDAOImpl.create(object);
    }

    @Override
    public List<NguoiDung> findAll() {
        return userDAOImpl.findAll();
    }

    @Override
    public NguoiDung find(String id) {
        return userDAOImpl.find(id);
    }

    @Override
    public boolean update(NguoiDung object) {
        NguoiDung nguoiDungDetached = userDAOImpl.find(object.getMaNguoiDung());
        
        nguoiDungDetached.setEmail(object.getEmail());
        nguoiDungDetached.setMatKhau(object.getMatKhau());
        nguoiDungDetached.setDiaChi(object.getDiaChi());
        nguoiDungDetached.setCmnd(object.getCmnd());
        nguoiDungDetached.setSdt(object.getSdt());
        nguoiDungDetached.setHoTen(object.getHoTen());
        
        return nguoiDungDetached.equals(object) ? userDAOImpl.update(nguoiDungDetached): false;
    }

    @Override
    public boolean delete(String id) {
        return userDAOImpl.delete(id);
    }
}
