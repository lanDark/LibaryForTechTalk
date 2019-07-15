<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Footer Area -->
		<footer id="wn__footer" class="footer__area bg__cat--8 brown--color">
			<div class="footer-static-top">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="footer__widget footer__menu">
								<div class="ft__logo">
									<a href="index.html">
                                                                            <img src="<c:url value="Resource/images/logo/3.png"/>" alt="logo">
									</a>
									<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered duskam alteration variations of passages</p>
								</div>
								<div class="footer__content">
									<ul class="social__net social__net--2 d-flex justify-content-center">
										<li><a href="#"><i class="bi bi-facebook"></i></a></li>
										<li><a href="#"><i class="bi bi-google"></i></a></li>
										<li><a href="#"><i class="bi bi-twitter"></i></a></li>
										<li><a href="#"><i class="bi bi-linkedin"></i></a></li>
										<li><a href="#"><i class="bi bi-youtube"></i></a></li>
									</ul>
									<ul class="mainmenu d-flex justify-content-center">
										<li><a href="index.html">Trending</a></li>
										<li><a href="index.html">Best Seller</a></li>
										<li><a href="index.html">All Product</a></li>
										<li><a href="index.html">Wishlist</a></li>
										<li><a href="index.html">Blog</a></li>
										<li><a href="index.html">Contact</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="copyright__wrapper">
				<div class="container">
					<div class="row">
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div class="copyright">
								<div class="copy__right__inner text-left">
									<p>Copyright <i class="fa fa-copyright"></i> <a href="https://freethemescloud.com/">Free themes Cloud.</a> All Rights Reserved</p>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-12">
							<div class="payment text-right">
                                                            <img src="<c:url value="/Resource/images/icons/payment.png"/>" alt="" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</footer>
		<!-- //Footer Area -->
		<!-- QUICKVIEW PRODUCT -->
		<div id="quickview-wrapper">
                    
                </div>
	</div>
	<!-- //Main wrapper -->

	<!-- JS Files -->
        <script src=<c:url value="/Resource/js/vendor/jquery-3.2.1.min.js"/>></script>
	<script src='<c:url value="/Resource/js/popper.min.js"/>'>
        </script>
        <script src='<c:url value="Resource/js/bootstrap.min.js"/>'>
        </script>
	<script src='<c:url value="/Resource/js/plugins.js"/>'>
        </script>
        <script src='<c:url value="Resource/js/active.js"/>'>
        </script>
        <script src='<c:url value="Resource/js/ajax.js"/>'>
        </script>
        <script>
            $(".showProductModal").on('click', function(event){
                event.stopPropagation();
                event.stopImmediatePropagation();
                var maSach=$(this).children("p").text();
                console.log(maSach);
                //(... rest of your JS code)
                $.ajax({
                    type:'post',
                    url:'<c:url value="/quickview"/>',
                    data : {
                        maSach : maSach
                    },
                    dataType : 'html',
                    timeout:10000,
                    success : function(data){
                        $("#quickview-wrapper").html(data);
                        $("#productmodal").modal('show');
                    }
                });
            });
        </script>
</body>
</html>