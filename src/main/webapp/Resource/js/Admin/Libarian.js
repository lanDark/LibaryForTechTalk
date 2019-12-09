{
    $.ajax({
        type: "get",
        url:'/Spring/Libarian/api/v1/PhieuMuons/request-holds/',
        
        dataType : 'text',
        timeout:10000,
        success : function(data)
        {
           let objDataSuccess = JSON.parse(data);
           let phieuMuonList = objDataSuccess.list;
           setTimeout(chiaSoTrang(objDataSuccess.soLuong),500);
            phieuMuonList.forEach(function(item,index){
                $('#tableYeuCauDatGiuSach tbody').append(`
                     <tr>
                         <td>${index}</td>
                         <td>${item.nguoiDungByMaNguoiDung.email}</td>   
                         <td>${item.ngayDat}</td>
                                 <td>
                                   <a  class='btn  btn-primary btn-sm' data-toggle="modal" data-target="#mymodal">
                                       <i class='fas fa-edit d-block'></i>
                                           Duyệt yêu cầu 
                                   </a>
                                     <a class ='btn btn-primary btn-sm'>
                                         <i class='fas fa-eye d-block'></i>
                                         Xem sách đặt
                                     </a>  
                                 </td>
                     </tr>
                 `);                  
            });
          
            }
    });
}
let chiaSoTrang = (soLuong) =>{
    let soTrang = 0;
    if(soLuong < 0 )
        throw "Lỗi Input/Output";
    
    soTrang = parseInt(soLuong/10)+1;
    
    if(soLuong % 10 > 0 ){
        soTrang=soTrang+1;
    }  

    let front_end = `<nav aria-label="...">
                        <ul class="pagination">

                        </ul>
                      </nav>`;
    $('#phanTrang').html(front_end);
    $('#phanTrang .pagination').append(` <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">Previous</a></li>`);
    for(let i=1;i<=soTrang;i++){
        if(i==1)
            $('#phanTrang .pagination').append(`<li class="page-item active"><a class="page-link" href="#">${i}</a></li>`);
        else  $('#phanTrang .pagination').append(`<li class="page-item"><a class="page-link" href="#">${i}</a></li>`);
    }
    
    $('#phanTrang .pagination').append(`<li class="page-item">  <a class="page-link" href="#">Next</a> </li>`);
    
    
};