/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Service;

import java.util.List;

/**
 * @author vital
 */
public interface ProductService {
    /**
     * Lấy sách mới nhất
     */
    public List getNewProductView();
    /**
     * Lấy sách theo danh mục
     */
    public List getProductByCategoryView();
    /**
     * Lấy sách theo loại của danh mục
     */
    public List getProductByKindView();
    public boolean updateProduct();
    public boolean insertProduct();
    public boolean deleteProduct();
}
