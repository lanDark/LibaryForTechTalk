<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp"/>
<jsp:include page="layout/search.jsp"/>
<jsp:include page="layout/slide.jsp"/>  
<script type="module" src="<c:url value="Resource/js/modules/CartPage.js" />"></script>
<!--<script type="module" src=""></script>-->
<div class="cart-main-area section-padding--lg bg--white">
            <div class="container">
                
                <div class="row">
                    <div class="col-md-12 col-sm-12 ol-lg-12">
                        <form action="#">               
                            <div class="table-content wnro__table table-responsive">
                                <table id="tblParticipantList">
                                    <thead>
                                        <tr class="title-top">
                                            <th class="product-thumbnail">ẢNH</th>
                                            <th class="product-name">TÊN SÁCH</th>
                                            <th class="product-quantity">SỐ LƯỢNG</th>
                                            <th class="product-remove">XÓA</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                    </tbody>
                                </table>
                            </div>
                        </form> 
                        <div class="cartbox__btn">
                            <ul class="cart__btn__list d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-between">
                                <div class="col-md-9"></div>
                                <li><a href="#" id="datGiu">Đặt giữ</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6 offset-lg-6">
                        <div class="cartbox__total__area">
                            <div class="cartbox-total d-flex justify-content-between">
                                <ul class="cart__total__list">
                                    <li>Tổng số lượng</li>
                                </ul>
                                <ul class="cart__total__tk">
                                    <li>0</li>
                                </ul>
                            </div>
              
                        </div>
                    </div>
                </div>
                            
            </div>  
        </div>
  <!-- Modal -->
  <div class="modal fade" id="alertAddToCart" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
            <h4 class="modal-title">Thông báo</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
        
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

<jsp:include page="layout/footer.jsp"/>