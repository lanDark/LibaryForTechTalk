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
        this.eventClickButtonAcceptCTPM();
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
            let maPm = $(this).attr('maPM');
            $('#showModalDuyetYeuCau').modal();
            $('#buttonAcceptCTPM').attr('maPm',maPm);
            

        });
    }
    eventClickButtonAcceptCTPM(){
        $('#buttonAcceptCTPM').click(function(){
            let maPm = $(this).attr('mapm');
            let clone_phieuMuonObj = window.pagination.getCtpmFromThisObjectPhieuMuonList(maPm)[0];
            
            
            if(clone_phieuMuonObj.maPm == null) 
                throw "Không tìm thấy Phiếu Mượn";
            clone_phieuMuonObj.trangThai = 2 ;
            $('#showModalDuyetYeuCau').modal('toggle');
            console.log(clone_phieuMuonObj);
        });
    }
}