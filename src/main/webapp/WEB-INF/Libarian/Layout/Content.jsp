<%-- 
    Document   : Content
    Created on : Dec 6, 2019, 9:36:35 PM
    Author     : vital
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Xem yêu cầu đặt sách</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Dashboard v1</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
    <!-- Main content -->
    <section class="content">
    <div class="container-fluid">
        <div class="card card-primary">
                <div class ="card-body">
                    <table class="table table-bordered" id="tableYeuCauDatGiuSach">
                      <thead>
                          <tr>
                              <th>Row</th>
                              <th>Email người đặt</th>
                              <th>Thời gian đặt</th>
                              <th>Thao tác</th>
                          </tr>
                      </thead>
                      <tbody>
                          
                      </tbody>
                    </table>
                    <div  id="loadingTable">
                        
                    </div>
                    <div class="row pl-2" id="phanTrang">
                        <nav aria-label="...">
                            <ul class="pagination">

                            </ul>
                        </nav>
                    </div>
                </div>

        </div>            
    </div>

    </section>
    <!-- Main content -->
  </div>

  <!-- /.content-wrapper -->
  <div class="modal fade" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
            <h4 class="modal-title" id="exampleModalLabel">New message</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
            <table class="table table-bordered" id="tableModalRequestHold">
                <thead>
                    <tr>
                        <th>Hình ảnh</th>
                        <th>Tên sách</th>
                        <th>Số lượng</th>
                    </tr>
                </thead>
                <tbody>

                </tbody>
        </table>    
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       
      </div>
    </div>
  </div>
</div>
  <div class="modal fade" id="showModalDuyetYeuCau" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
      <div class="modal-body">
          <div><p>Bạn đã chắc chắn ?</p></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
        <button type="button" class="btn btn-primary">Xác nhận</button>
      </div>
    </div>
  </div>
</div>
  <script type ="module" src="${pageContext.request.contextPath}/Resource/js/Libarian/Main.js"></script>
