

// -------------  Thêm sản phẩm vào giỏ hàng ------------------
                                            // this is the id of the form
$(".addToCart").click(function(e) {

    e.preventDefault(); 
    e.stopPropagation();
    e.stopImmediatePropagation(); 
    var maSach=$(this).children("p").text();
    var urlS='addToCart';
    console.log(maSach);
    $.ajax({
        type: "POST",
        url:urlS,
        data : {
            maSach : maSach
        },
        success: function()
        {
            alert("thanh cong"); // show response from the php script.
        },
        error: function (jqXHR, textStatus, errorThrown) {
                alert(xhr.status);
                alert(thrownError);
        }
    });
});