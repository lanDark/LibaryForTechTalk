    let tableHoldRequestSetting = {
        limit : 5,
        startPage: 1
    };
    
    const URL_HOST =window.location.host;
    const URL_PROTOCOL = window.location.protocol;
    // Xây dựng button cho phân trang
    let buildPageButton = (soLuong) =>{
        let wrapper = $('#phanTrang .pagination');
        
        if(soLuong < 0 )
            throw "Lỗi Input/Output";

        let soTrang = parseInt(soLuong/tableHoldRequestSetting.limit);
         
        wrapper.append(` <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">Previous</a></li>`);
        if(soTrang == 1)
        {
             wrapper.append(`<li class="page-item"><a class="page-link" href="#">${soTrang}</a></li>`);
        }
        else{
            for(let i=0;i<=soTrang;i++){
                  wrapper.append(`<li class="page-item"><a class="page-link" href="#">${i+1}</a></li>`);
            }
        }
        $('.page-item').click(function(){
            let page = $(this).text();
            $('#tableYeuCauDatGiuSach tbody').empty();
            $('#loadingTable').append(`<div class="justify-content-center row">
                                        <div class="spinner-border mx-auto" role="status">
                                          <span class="sr-only">Loading...</span>
                                        </div>
                                      </div>`);
            
            pagination(page,tableHoldRequestSetting.limit);
        });
       wrapper.append(`<li class="page-item">  <a class="page-link" href="#">Next</a> </li>`);
    
    
    };
    //Thằng này đảm nhận trả kết quả mỗi trang cho buildTable
    // page tức là số trang hiện tại
    // limit tức là số lượng kết quả muốn hiển thị trên 1 trang
    let pagination = (page,limit) =>{
        $.ajax({
            type: "get",
            url:`/Spring/Libarian/api/v1/PhieuMuons/request-holds/?page=${page}&limit=${limit}`,
            dataType : 'text',
            timeout:10000,
            success : function(data)
            {
               let phieuMuonList = JSON.parse(data);
               buildTableRequestHold(phieuMuonList);
                $('#loadingTable').empty();

            }
        });   

    };
    //Đảm nhận chức năng xây dựng bảng yêu cầu đặt sách
    let buildTableRequestHold = (data) => {

        data.forEach(function(item,index){
            $('#tableYeuCauDatGiuSach tbody').append(`
                 <tr>
                     <td>${item.maPm}</td>
                     <td>${item.nguoiDungByMaNguoiDung.email}</td>   
                     <td>${item.ngayDat}</td>
                             <td>
                               <a  class='btn  btn-primary btn-sm' data-toggle="modal" data-target="#mymodal">
                                   <i class='fas fa-edit d-block'></i>
                                       Duyệt yêu cầu 
                               </a>
                                 <a class ='btn btn-primary btn-sm showCTPM'  maPM="${item.maPm}">
                                     <i class='fas fa-eye d-block'></i>
                                     Xem sách đặt
                                 </a>  
                             </td>
                 </tr>
             `);                  
        });
        // Hiển thị chi tiết phiếu mượn(Sách được đặt)
        $('.showCTPM').click(function(){
            let maPM = $(this).attr('maPM');
            
          
            function buildTableModalThongTinCTPM(ChiTietPhieuMuonList){
                $('#tableModalRequestHold tbody').empty();
                ChiTietPhieuMuonList.forEach((item)=>{
                    let sach = item.sach;
                    $('#tableModalRequestHold tbody').append(
                        `
                            <tr>
                                <td><div><img style="height:100px ;width:100px"src="${URL_PROTOCOL}//${URL_HOST}/Spring/Resource/images/books/${sach.hinhAnhs[0].src}" /> </div> </td>
                                <td>${sach.tenSach}</td>   
                                <td>${item.soLuong}</td>
                            </tr>
                        `    
                        );
                });
            }
            $.ajax({
                type: "get",
                url:`/Spring/Libarian/api/v1/PhieuMuons/${maPM}/CtphieuMuons/`, 
                dataType : 'text',
                timeout:10000,
                async: false,
                success : function(data)
                {
                   let ChiTietPhieuMuonList = JSON.parse(data);
                   buildTableModalThongTinCTPM(ChiTietPhieuMuonList);
                }
            });   
            $('#mymodal').modal();
        });
        
    };

//    {
//        pagination(tableHoldRequestSetting.startPage,tableHoldRequestSetting.limit);
//        
//        $.ajax({
//            type: "get",
//            url:`/Spring/Libarian/api/v1/PhieuMuons/request-holds/count`,
//            dataType : 'text',
//            timeout:10000,
//            success : function(data)
//            {
//               let objDataSucess = JSON.parse(data);
//               buildPageButton(objDataSucess.number);
//            }
//        });   
//    }
