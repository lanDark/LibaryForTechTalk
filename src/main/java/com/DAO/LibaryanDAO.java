/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO;

import com.model.CtPhieumuon;
import com.model.PhieuMuon;
import java.util.List;

/**
 *
 * @author vital
 */
public interface LibaryanDAO {
    public List<CtPhieumuon> getChiTietPhieuMuon(int idPhieuMuon);
    public List<PhieuMuon> getYeuCauGiuMoiNhat();
    public List<PhieuMuon> getPaginationRequestHold(int page);
    public Object getNumberRequestHold();
}
