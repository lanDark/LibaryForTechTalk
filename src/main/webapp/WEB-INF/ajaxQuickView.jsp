<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" %>
                    <c:forEach var="tacGia" items="${sach.tacgias}">
                        <c:set var="tenTacGia" value="${tacGia.hoTen}"/>
                    </c:forEach>   
                    
                    <c:forEach var="hinhAnhs"  items="${sach.hinhAnhs}">
                        <c:if test="${ empty srcHinhAnh1}">
                             <c:set var="srcHinhAnh1" value="${hinhAnhs.src}"/>
                        </c:if>
                    </c:forEach>
		    <!-- Modal -->
		    <div class="modal fade" id="productmodal" tabindex="-1" role="dialog">
		        <div class="modal-dialog modal__container" role="document">
		            <div class="modal-content">
		                <div class="modal-header modal__header">
		                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		                </div>
		                <div class="modal-body">
		                    <div class="modal-product">
		                        <!-- Start product images -->
		                        <div class="product-images">
		                            <div class="main-image images">
                                                <img alt="big images" src='<c:url value="Resource/images/books/${srcHinhAnh1}"/>' >
		                            </div>
		                        </div>
		                        <!-- end product images -->
		                        <div class="product-info">
                                            <h1><c:out value="${sach.tenSach}"/></h1>
		                            <div class="rating__and__review">
		                                <ul class="rating">
		                                    <li><span class="ti-star"></span></li>
		                                    <li><span class="ti-star"></span></li>
		                                    <li><span class="ti-star"></span></li>
		                                    <li><span class="ti-star"></span></li>
		                                    <li><span class="ti-star"></span></li>
		                                </ul>
		                                <div class="review">
		                                    <a href="#">4 customer reviews</a>
		                                </div>
		                            </div>
		                            <div class="price-box-3">
		                                <div class="s-price-box">
		                                    <span class="new-price">Tác giả:</span>
		                                    <span class="old-price">${tenTacGia}</span>
		                                </div>
		                            </div>
		                            <div class="quick-desc">
		                                Designed for simplicity and made from high quality materials. Its sleek geometry and material combinations creates a modern look.
		                            </div>
		                            <div class="select__color">
		                          
		                            </div>
		                            <div class="select__size">
		                               
		                            </div>
		                            <div class="social-sharing">
		                                <div class="widget widget_socialsharing_widget">
		                                    <h3 class="widget-title-modal">Share this product</h3>
		                                    <ul class="social__net social__net--2 d-flex justify-content-start">
		                                        <li class="facebook"><a href="#" class="rss social-icon"><i class="zmdi zmdi-rss"></i></a></li>
		                                        <li class="linkedin"><a href="#" class="linkedin social-icon"><i class="zmdi zmdi-linkedin"></i></a></li>
		                                        <li class="pinterest"><a href="#" class="pinterest social-icon"><i class="zmdi zmdi-pinterest"></i></a></li>
		                                        <li class="tumblr"><a href="#" class="tumblr social-icon"><i class="zmdi zmdi-tumblr"></i></a></li>
		                                    </ul>
		                                </div>
		                            </div>
		                            <div class="addtocart-btn">
                                                <a href="#" class="addToCart"><p hidden>${sach.maSach}</p>Add to cart</a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
                         <script src='<c:url value="Resource/js/ajax.js"/>'>  </script>
                         //hoanglena
		<!-- END QUICKVIEW PRODUCT -->