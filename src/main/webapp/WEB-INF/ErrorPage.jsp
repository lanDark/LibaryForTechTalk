<%-- 
    Document   : ErrorPage
    Created on : Nov 22, 2019, 11:21:04 AM
    Author     : vital
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp"/>
<jsp:include page="layout/search.jsp"/>
			</div>
		</section>
		<!-- End Error Area -->
                <!-- Start Bradcaump area -->
                <div class="ht__bradcaump__area bg-image--5">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="bradcaump__inner text-center">
                                    <h2 class="bradcaump-title"><c:out value="${status}"/></h2>
                                    <nav class="bradcaump-content">
                                      <a class="breadcrumb_item" href="index.html">Home</a>
                                      <span class="brd-separetor">/</span>
                                      <span class="breadcrumb_item active"><c:out value="${status}"/></span>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Bradcaump area -->

		<!-- Start Error Area -->
		<section class="page_error section-padding--lg bg--white">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="error__inner text-center">
							<div class="error__logo">
                                                            <a href="#"><img src="${pageContext.request.contextPath}/Resource/images/others/404.png" alt="error images"></a>
							</div>
							<div class="error__content">
								<h2><c:out value="${messenger}"/></h2>
								<p>It looks like you are lost! Try searching here</p>
								<div class="search_form_wrapper">
									<form action="#">
										<div class="form__box">
											<input type="text" placeholder="Search...">
											<button><i class="fa fa-search"></i></button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End Error Area -->
<jsp:include page="layout/footer.jsp"/>