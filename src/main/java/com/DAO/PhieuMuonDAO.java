/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.Class.SearchCriteria;
import com.Interface.CRUD;
import com.model.PhieuMuon;
import java.util.List;

/**
 *
 * @author vital
 */
public interface PhieuMuonDAO extends CRUD<PhieuMuon,Integer> {
    public List<PhieuMuon> findByEmailUsingLike(String email);
    public List<PhieuMuon> findIdUsingLike(int maND);
    public List<PhieuMuon> phieuMuonSuggestion(String hql) throws Exception;
    public List<PhieuMuon> searchPhieuMuon(List<SearchCriteria> params); 
}
