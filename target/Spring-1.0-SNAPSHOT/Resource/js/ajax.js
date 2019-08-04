

// -------------  Thêm sản phẩm vào giỏ hàng ------------------
                                            // this is the id of the form
$(".addToCart").click(function(e) {

    e.preventDefault(); 
    e.stopPropagation();
    e.stopImmediatePropagation(); 
    var maSach=$(this).children("p").text();
    $.ajax({
        type: "post",
        url:'/Spring/addToCart',
        data : {
            maSach : maSach
        },
        dataType : 'html',
        timeout:10000,
        success : function(data)
        {
           $('.modal-body').html(data);
           $('#alertAddToCart').modal('show');
        }
    });
});

$("#addToCartInQuickView").click(function(e) {

    e.preventDefault(); 
    e.stopPropagation();
    e.stopImmediatePropagation(); 
    var maSach=$(this).children("p").text();
    $.ajax({
        type: "post",
        url:'/Spring/addToCart',
        data : {
            maSach : maSach
        },
        dataType : 'html',
        timeout:10000,
        success : function(data)
        {
           $('.modal-body').html(data);
            $("#productmodal").modal('toggle');
           $('#alertAddToCart').modal('show');
        }
    });
});
// -------------  Xác nhận đặt ------------------
$("#datGiu").click(function(e) {

    e.preventDefault(); 
    e.stopPropagation();
    e.stopImmediatePropagation(); 
    $.ajax({
        type: "GET",
        url:'/Spring/datMuon',
        dataType : 'html',
        timeout:10000,
        success : function(data)
        {
             $('.modal-body').html(data);
             if(data.search(" Xin lỗi") == -1 ){
                $('tbody > tr > td').remove();
             }
             $('#alertAddToCart').modal('show');
        }
        
    });
});
// -------------  Show quickView ------------------
$(".showProductModal").on('click', function(event){
    event.stopPropagation();
    event.stopImmediatePropagation();
    var maSach=$(this).children("p").text();
    console.log(maSach);
    //(... rest of your JS code)
    $.ajax({
        type:'post',
        url:'/Spring/quickview',
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

// -------------  Cart update số lượng  ------------------
$("tbody tr td ").on("change", "input", function(event) {
   
   var maSach=$(this).next(".maSach").val();
   var soLuong=$(this).val();
    $.ajax({
        type:'GET',
        url:'/Spring/updateItemCart',
        data : {
            maSach : maSach,
            soLuong: soLuong
        },
        dataType : 'html',
        timeout:10000,
        success : function(data){
           
        }
    });
});
// -------------  Xoá item trong Cart List  ------------------
$("tbody tr td ").on("click", ".remove", function(event) {
     event.preventDefault();
    var maSach=$(this).next(".maSach").val();
    $(this).parent().parent().remove();
    $.ajax({
        type:'GET',
        url:'/Spring/deleteItemCart',
        data : {
            maSach : maSach
        },
        dataType : 'html',
        timeout:10000,
        success : function(data){
         
        }
    });
});