import Pagination from './Pagination.js';
import TableRequestHold from './TableRequestHold.js';
window.pagination  = new Pagination();

    
$.ajax({
    type: "get",
    url:`/Spring/Libarian/api/v1/PhieuMuons/request-holds/count`,
    dataType : 'text',
    timeout:10000,
    success : function(data)
    {
        let objDataSucess = JSON.parse(data);
        pagination.paginationObject.init($('.pagination'),{
            limit:3,
            record:objDataSucess.number,
            step:3 ,
            loadingElementHTML: '#loadingTable',
            tableElementHTML : '#tableYeuCauDatGiuSach',
            callback: function(){
               $.ajax({
                    type: "get",
                    url:`/Spring/Libarian/api/v1/PhieuMuons/request-holds/?page=${window.pagination.numberPageHere}&limit=${window.pagination.limit}`,
                    dataType : 'text',
                    timeout:10000,
                    success : function(data)
                    {
                        let dataList = JSON.parse(data);
                        window.pagination.setObjectPhieuMuonList =  dataList;
                        new TableRequestHold().buildTable(dataList);
                        // --------------------
                        // Giữ lại thằng này ở dưới, ở trên không quan trọng tùy cách người build giao diện thế nào
                        // thằng này xử lý về html loading khi ajax request thành công thì sẽ xóa html đó đi
                        // --------------------
                        $(`${window.pagination.paginationObject.loadingElementStr}`).empty();  // Giữ lại thằng này
                    }   
                });
            }
        });
    }
});   