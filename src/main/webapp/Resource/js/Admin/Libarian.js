    let tableHoldRequestSetting = {
        limit : 3,
        startPage: 1
    };
    let buildPageButton = (soLuong) =>{
        let wrapper = $('#phanTrang .pagination');
        
        if(soLuong < 0 )
            throw "Lỗi Input/Output";

        let soTrang = parseInt(soLuong/tableHoldRequestSetting.limit);
         
        wrapper.append(` <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">Previous</a></li>`);
      
        for(let i=0;i<=soTrang;i++){
              wrapper.append(`<li class="page-item"><a class="page-link" href="#">${i+1}</a></li>`);
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
            setTimeout(function(){$('#loadingTable').empty();},600);
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
               buildTable(phieuMuonList);
            }
        });   

    };
    //Đảm nhận chức năng xây dựng bảng
    let buildTable = (data) => {

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
        $('.showCTPM').click(function(){
            let maPM = $(this).attr('maPM');
            
            function buildTableModal(){
                let table = `
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
                    </table>`;
            }
            
            $.ajax({
                type: "get",
                url:`/Spring/Libarian/api/v1/PhieuMuons/${maPM}/CtphieuMuons/`,
                dataType : 'text',
                timeout:10000,
                success : function(data)
                {
                   let objDataSucess = JSON.parse(data);
                   
                }
            });   
            $('#mymodal').modal();
        });
        
    };

    {
        pagination(tableHoldRequestSetting.startPage,tableHoldRequestSetting.limit);
        
        $.ajax({
            type: "get",
            url:`/Spring/Libarian/api/v1/PhieuMuons/request-holds/count`,
            dataType : 'text',
            timeout:10000,
            success : function(data)
            {
               let objDataSucess = JSON.parse(data);
               buildPageButton(objDataSucess.number);
            }
        });   
    }
