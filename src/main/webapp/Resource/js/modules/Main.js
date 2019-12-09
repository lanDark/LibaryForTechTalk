
import Sach from './Sach.js';
import Cart from './Cart.js';

let cart = new Cart();


let setNumberMiniCart =  ()=> {
    cart.callCartsInJSON();
    let soLuong = cart._cart.length;
    $('.product_qun')[0].innerText = soLuong;
};
 

let addEventClick = ()=> {
    $('.zmdi-delete').click(function(e){
        $(this).parents(".item01").hide("slow",() =>{
            $(this).parents(".item01").remove();
            let maSach=$(this).attr("maSach");
            cart.deleteSach(maSach); 
            setNumberMiniCart();
        });
        
    });
};


$(".addToCart").click(function(e) {

    e.preventDefault(); 
    e.stopPropagation();
    e.stopImmediatePropagation(); 
    
    let maSach = $(this).children("p").text();
    let tacGia = $(this).attr("tacGia");
    let tenSach = $(this).attr("tenSach");
    let url = $(this).attr("url");
    
    let sach = new Sach.Builder(maSach).setTenSach(tenSach).setURL(url).build();
    {
        if(!(cart.checkItemInLocalStoreIsExists(maSach))){
            let str =
            `<div class="item01 d-flex mt--20">

               <div class="thumb">
                       <a href="product-details.html"><img src="${url}" alt="product images"/></a>
               </div>
               <div class="content">
                       <h6><a href="product-details.html">${tenSach}</a></h6>
                       <span class="prize">${tacGia}</span>
                       <div class="product_prize d-flex justify-content-between">
                               <span class="qun"></span>
                               <ul class="d-flex justify-content-end">
                                       <li><a href="#"><i class="zmdi zmdi-settings"></i></a></li>
                                       <li><a href="#"><i class="zmdi zmdi-delete" maSach="${maSach}"></i></a></li>
                               </ul>
                       </div>
               </div>
            </div>`;
            
            let miniproduct = $('.miniproduct').html();
            
            $('.miniproduct').html(miniproduct+str);  
            addEventClick();
        }
    }
    cart.addItemToLocalStore(sach);
    setNumberMiniCart();

});



let loadMiniCart = () => {
    let str="";
    
    cart.callCartsInJSON();
    for(let i = 0; i < cart._cart.length;i++){
        str +=`<div class="item01 d-flex mt--20">

               <div class="thumb">
                       <a href="product-details.html"><img src="${cart._cart[i].url}" alt="product images"/></a>
               </div>
               <div class="content">
                       <h6><a href="product-details.html">${cart._cart[i].tenSach}</a></h6>
                       <span class="prize">${cart._cart[i].tacGia}</span>
                       <div class="product_prize d-flex justify-content-between">
                               <span class="qun"></span>
                               <ul class="d-flex justify-content-end">
                                       <li><a href="#"><i class="zmdi zmdi-settings"></i></a></li>
                                       <li><a href="#"><i class="zmdi zmdi-delete" maSach="${cart._cart[i].maSach}" ></i></a></li>
                               </ul>
                       </div>
               </div>
           </div>`;
    }
    $('.miniproduct').html(str);
    addEventClick();
};

cart.callCartsInJSON();


loadMiniCart();


setNumberMiniCart();

export {loadMiniCart,setNumberMiniCart};