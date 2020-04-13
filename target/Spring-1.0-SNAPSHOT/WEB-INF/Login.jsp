<%-- 
    Document   : Login
    Created on : Jul 17, 2019, 4:21:56 PM
    Author     : vital
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp"/>
<jsp:include page="layout/search.jsp"/>
<jsp:include page="layout/slide.jsp"/>
<style>
  .error {
    color: red;
  }
</style>
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
                                                                            <c:if test="${not empty param.error}">
                                                                                <div class='col-md-12'>
                                                                                    <p style="color: red">Sai tên đăng nhập hoặc mật khẩu, Vui lòng nhập lại!.</p>
                                                                                </div>
                                                                            </c:if>
                                                                            <button type="submit" form="executeLogin">Login</button>
										<label class="label-for-checkbox">
											<input id="rememberme" class="input-checkbox" name="rememberme"  type="checkbox">
											<span>Remember me</span>
										</label>
                                                        
									</div>
									<a class="forget_pass" href="#">Lost your password?</a>
								</div>
							</form>
						</div>
					</div>
					<div class="col-lg-6 col-12">
						<div class="my__account__wrapper">
							<h3 class="account__title">Register</h3>
							<form:form action="/Spring/Register"  method="POST" modelAttribute="registerForm">
								<div class="account__form">
									<div class="input__box">
										<label>Email <span>*</span></label>
                                                                                <form:input path="email"/>
                                                                                <form:errors path="email" cssClass="error"/> 
									</div>
									<div class="input__box">
										<label>Mật khẩu<span>*</span></label>
                                                                                <form:password path="matKhau" />
                                                                                <form:errors path="matKhau" cssClass="error"/> 
									</div>
									<div class="input__box">
										<label>Nhập lại mật khẩu<span>*</span></label>
										<form:password path="matKhau2" />
                                                                                 <form:errors path="matKhau2" cssClass="error"/> 
                                                                        </div>        
									<div class="input__box">
										<label>số điện thoại<span>*</span></label>
                                                                                <form:input path="sdt"/>
                                                                                 <form:errors path="sdt" cssClass="error"/> 
									</div>		
									<div class="input__box">
										<label>Mã chứng minh thư<span>*</span></label>
										<form:input path="cmnd"/>
                                                                                 <form:errors path="cmnd" cssClass="error"/> 
									</div>	
									<div class="input__box">
										<label>Họ tên<span>*</span></label>
										<form:input path="hoTen"/>
                                                                                 <form:errors path="hoTen" cssClass="error"/> 
									</div>		
									<div class="input__box">
										<label>Địa chỉ<span>*</span></label>
										<form:input path="diaChi"/>
                                                                                 <form:errors path="diaChi" cssClass="error"/> 
									</div>		
                                                                        <div class="form__btn">
										<button>Đăng ký</button>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</section>
<jsp:include page="layout/footer.jsp"/>