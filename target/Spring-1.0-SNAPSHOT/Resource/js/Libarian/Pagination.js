import URI from './URI.js';
import TableRequestHold from './TableRequestHold.js';

export default  class Pagination{
    constructor(){
        this.limit = 5;
        this.startPage = 1;
    }
    
    get getLimit(){return this.limit;}
    set setLimit(limit){this.limit = limit;}
    
    get getStartPage(){return this.startPage;}
    
    pagination(page) {
        $.ajax({
            type: "get",
            url:`/Spring/Libarian/api/v1/PhieuMuons/request-holds/?page=${page}&limit=${this.limit}`,
            dataType : 'text',
            timeout:10000,
            success : function(data)
            {
               let phieuMuonList = JSON.parse(data);
               new TableRequestHold().buildTable(phieuMuonList);
                $('#loadingTable').empty();
            }
        });   

    }
    buildPageButton(soLuong) {
        let wrapper = $('#phanTrang .pagination');
        
        if(soLuong < 0 )
            throw "Lỗi Input/Output";

        let soTrang = parseInt(soLuong/this.limit);
         
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
            
            pagination(page,this.limit);
        });
       wrapper.append(`<li class="page-item">  <a class="page-link" href="#">Next</a> </li>`);
    
    
    };
}