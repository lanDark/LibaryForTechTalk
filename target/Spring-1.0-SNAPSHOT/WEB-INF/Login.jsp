<%-- 
    Document   : Login
    Created on : Jul 17, 2019, 4:21:56 PM
    Author     : vital
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp"/>
<jsp:include page="layout/search.jsp"/>
<jsp:include page="layout/slide.jsp"/>
		<section class="my_account_area pt--80 pb--55 bg--white">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-12">
						<div class="my__account__wrapper">
							<h3 class="account__title">Login</h3>
                                                        <form method="POST" action="<c:url value='/Login'/>" id="executeLogin">
								<div class="account__form">
									<div class="input__box" path="email">
										<label>Username or email address <span>*</span></label>
										<input type="text" name="email">
									</div>
									<div class="input__box">
										<label>Password<span>*</span></label>
										<input type="password" name='password'/>
									</div>
									<div class="form__btn">
                                                                            <button type="submit" form="executeLogin">Login</button>
										<label class="label-for-checkbox">
											<input id="rememberme" class="input-checkbox" name="rememberme" value="forever" type="checkbox">
											<span>Remember me</span>
										</label>
                                                                            <c:if test="${not empty failed}">
                                                                            <div class='col-md-12'>
                                                                                <p>${failed}</p>
                                                                            </div>
                                                                            </c:if>
									</div>
									<a class="forget_pass" href="#">Lost your password?</a>
								</div>
							</form>
						</div>
					</div>
					<div class="col-lg-6 col-12">
						<div class="my__account__wrapper">
							<h3 class="account__title">Register</h3>
							<form action="#">
								<div class="account__form">
									<div class="input__box">
										<label>Email <span>*</span></label>
										<input type="email">
									</div>
									<div class="input__box">
										<label>Mật khẩu<span>*</span></label>
										<input type="password">
									</div>
									<div class="input__box">
										<label>Nhập lại mật khẩu<span>*</span></label>
										<input type="password">
									<div class="input__box">
										<label>số điện thoại<span>*</span></label>
										<input type="password">
									</div>		
									<div class="input__box">
										<label>Mã chứng minh thư<span>*</span></label>
										<input type="password">
									</div>	
									<div class="input__box">
										<label>Họ tên<span>*</span></label>
										<input type="password">
									</div>		
									<div class="input__box">
										<label>Địa chỉ<span>*</span></label>
										<input type="password">
									</div>		
									</div>									<div class="form__btn">
										<button>Đăng ký</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
<jsp:include page="layout/footer.jsp"/>