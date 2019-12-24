package com.Interface;

import java.util.List;


/**
 *
 * @author vital
 * Interface này tạo ra để dùng chung cho các Service hoặc DAO
 */
public interface CRUD<T,ID>{
    public boolean create(T o)  ;
    public List<T> findAll()          ;
    public T find(ID id)          ;
    public boolean update(T o)  ;
    public boolean delete(ID id)  ;
    
    
}
