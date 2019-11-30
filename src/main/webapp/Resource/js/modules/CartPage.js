import Sach from './Sach.js';
import Cart from './Cart.js';
import {loadMiniCart,setNumberMiniCart} from './Main.js';
let cart = new Cart();
let originURL = `${window.location.origin}/Spring/`;
cart.callCartsInJSON();

// -------------  Tính tổng số lượng sách đặt  ------------------
let setTongSoLuong = () =>{
    let total_sach = cart._cart.reduce((tong,sach)=>{
        return tong+=sach.soLuong;
    },0);
    $('.cart__total__tk > li').html(total_sach);
};
// -------------  End tính tổng số lượng sách đặt  ------------------
{
    let str="";

    for(let i = 0; i < cart._cart.length; i++)      {
        str += `
                <tr>
                    <td class="product-thumbnail"><a href="#"><img src="${cart._cart[i].url}" alt="product img"></a></td>
                    <td class="product-name"><a href="#">${cart._cart[i].tenSach}</a></td>
                    <td class="product-quantity"><input disabled class="soLuong" type="number" value="${cart._cart[i].soLuong}">
                        <input class="maSach" value="${cart._cart[i].maSach}" hidden/>  
                    </td>

                    <td class="product-remove"><a href="#" class="remove" maSach="${cart._cart[i].maSach}">X</a></td>
                </tr>
        `;
    }
    $('#tblParticipantList tbody').html(str);
    setTongSoLuong();
}


// -------------  Xoá item trong Cart List  ------------------
$(document).on("click",'#tblParticipantList tbody .remove',function(e){
        e.preventDefault();
	$(this).parents('tr').fadeOut(200,()=>{
            let maSach = $(this).attr('maSach');
            cart.deleteSach(maSach);
            $(this).parents('tr').remove();
            loadMiniCart();
            setNumberMiniCart();
            setTimeout(setTongSoLuong,0);
        });
});

 //-------------  Xác nhận đặt ------------------
$("#datGiu").click(function(e) {

    e.preventDefault(); 
    e.stopPropagation();
    e.stopImmediatePropagation(); 

    let strJson = JSON.stringify(localStorage.getItem('CART'));
    $.ajax({
        type: "post",
        url:'/Spring/datMuon',
        dataType:"text",
        data: localStorage.getItem('CART'),
        timeout:10000,
        contentType: "application/json; charset=utf-8",
            
        success : function(data, status, settings)
        {
            let obj = JSON.parse(data);
            if(obj.status == 201){
                $('.modal-body').html(obj.success);
                $('#alertAddToCart').modal('show');
                $('tbody > tr > td').remove();
                localStorage.removeItem('CART');
                loadMiniCart();
                $('.product_qun')[0].innerText = 0;
                $('.cart__total__tk > li').html(0);
                $('.modal-body').html("Đặt mượn thành công!");
            }
                
        },
        error: function (xhr, ajaxOptions, error) {
            let obj = JSON.parse(xhr.responseText);
            if(xhr.status == 500){
                $('.modal-body').html(obj.error);
                $('#alertAddToCart').modal('show');
            }
            if(xhr.status == 403){
                let origin =  window.location.origin;
                window.location.href = `${originURL}Login`;
            }
        }
        
    });
});