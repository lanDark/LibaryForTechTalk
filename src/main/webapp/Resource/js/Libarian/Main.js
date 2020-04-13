import Pagination from './Pagination.js';
import TableRequestHold from './TableRequestHold.js';
import {URI} from './URI.js';
import {Search} from './Module/Search.js';

window.URI = URI();
window.pagination = new Pagination();

window.Search = Search().init(
    {
        buildSuggestHTML: fetch =>{
            fetch.done(data  =>  {
                let obj = JSON.parse(data);
                let html = ``;
                obj.forEach(item => {
                   html +=` <div class="col-md-12"><a href="#">${item.maPm}</a></div>`;
                });
                $('#test').html(html);
                $('#test').show();
                 
                $('#test').off('click');
                $('#test > div').click(()=>{
                    $('#test').toggle();
                });
            });  
        },
        
        createURI : function(val){
            let emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
            if(isNaN(val)){
                return `${ window.URI.pathName }${ window.URI.api }PhieuMuons/search?email=${val}&trangThai==1`;
            }
            else
                return `${ window.URI.pathName }${ window.URI.api }PhieuMuons/?search=maPm:${val},trangThai:1`;  
        }
    }     
);

$.ajax({
    type: "get",
    url: `/Spring/Libarian/api/v1/PhieuMuons/request-holds/count`,
    dataType: 'text',
    timeout: 10000,
    success: function (data)
    {
        let objDataSucess = JSON.parse(data);
        pagination.paginationObject.init($('.pagination'), {
            record: objDataSucess.number,
            step: 3,
            loadingElementHTML: '#loadingTable',
            tableElementHTML: '#tableYeuCauDatGiuSach',
            callback: function () {
                $.ajax({
                    type: "get",
                    url: `/Spring/Libarian/api/v1/PhieuMuons/request-holds/?page=${window.pagination.numberPageHere}&limit=${window.pagination.limit}`,
                    dataType: 'text',
                    timeout: 10000,
                    contentType: "application/json; charset=utf-8",
                    success: function (data)
                    {
                        let dataList = JSON.parse(data);
                        window.pagination.setObjectPhieuMuonList = dataList;
                        new TableRequestHold().buildTable(dataList);
                        // --------------------
                        // Giữ lại thằng này ở dưới, ở trên không quan trọng tùy cách người build giao diện thế nào
                        // thằng này xử lý về html loading khi ajax request thành công thì sẽ xóa html đó đi
                        // --------------------
                        $(`${window.pagination.paginationObject.loadingElementStr}`).empty();
                    }
                });
            }
        });
    }
});
