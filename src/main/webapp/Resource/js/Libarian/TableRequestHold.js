import TableCTPM from './TableCTPM.js';

export default  class TableRequestHold{
    buildTable(data){
        data.forEach(function(item){
            $('#tableYeuCauDatGiuSach tbody').append(`
                 <tr>
                     <td>${item.maPm}</td>
                     <td>${item.nguoiDungByMaNguoiDung.email}</td>   
                     <td>${item.ngayDat}</td>
                             <td>
                               <a  class='btn  btn-primary btn-sm duyetYeuCauMuon' maPM="${item.maPm}">
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
        this.eventShowChiTietPhieuMuonClick();
        this.eventShowTaleDuyetYeuCauClick();
    }
    eventShowChiTietPhieuMuonClick(){
        $('.showCTPM').click(function(){
            let maPM = $(this).attr('maPM');
            
            $.ajax({
                type: "get",
                url:`/Spring/Libarian/api/v1/PhieuMuons/${maPM}/CtphieuMuons/`, 
                dataType : 'text',
                timeout:10000,
                async: false,
                success : function(data)
                {
                   let ChiTietPhieuMuonList = JSON.parse(data);
                   new TableCTPM().buildTable(ChiTietPhieuMuonList);
                }
            });   
            $('#mymodal').modal();
        });  
        
    }
    eventShowTaleDuyetYeuCauClick(){
        $('.duyetYeuCauMuon').click(function(){
            let maPM = $(this).attr('maPM');
            $('#showModalDuyetYeuCau').modal();
        });
    }
}