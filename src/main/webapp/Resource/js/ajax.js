

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