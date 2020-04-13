/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAOImpl;

import com.Class.SearchCriteria;
import com.model.NguoiDung;
import com.model.PhieuMuon;
import java.util.function.Consumer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;




/**
 *
 * @author vital
 * link: https://www.baeldung.com/rest-search-language-spring-jpa-criteria
 */
public class PhieuMuonSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {
 
    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root r;

    public PhieuMuonSearchQueryCriteriaConsumer() {
    }

    public PhieuMuonSearchQueryCriteriaConsumer(Predicate predicate, CriteriaBuilder builder, Root r) {
        this.predicate = predicate;
        this.builder = builder;
        this.r = r;
    }
 
    @Override
    public void accept(SearchCriteria param) {
        if (param.getOperation().equalsIgnoreCase(">")) {
            predicate = builder.and(predicate, builder
              .greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase("<")) {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(
              r.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase(":")) {
            if (r.get(param.getKey()).getJavaType() == String.class) {
                predicate = builder.and(predicate, builder.like(
                  r.get(param.getKey()), "%" + param.getValue() + "%"));
            }else if(r.get(param.getKey()).getJavaType() == NguoiDung.class){
                Join<PhieuMuon,NguoiDung> nguoiDung = r.join("nguoiDungByMaNguoiDung");
                predicate = builder.and(predicate, builder.equal(
                                 nguoiDung.get("maNguoiDung"), param.getValue()));
            } else {
                predicate = builder.and(predicate, builder.equal(
                  r.get(param.getKey()), param.getValue()));
            }
        }
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public CriteriaBuilder getBuilder() {
        return builder;
    }

    public Root getR() {
        return r;
    }
    
}
