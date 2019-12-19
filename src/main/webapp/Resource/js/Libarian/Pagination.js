import URI from './URI.js';
import TableRequestHold from './TableRequestHold.js';

export default  class Pagination{
    constructor(){
        this.limit = 3;
        this.startPage = 1;
        this.objectPhieuMuonList = '';
        this.numberPageHere  = 1;
        this.maxPage = 0;
    }
    
    get getLimit(){return this.limit;}
    set setLimit(limit){this.limit = limit;}
    
    get getStartPage(){return this.startPage;}
    
    set setObjectPhieuMuonList(list){this.objectPhieuMuonList = list;}
    get getObjectPhieuMuonList(){return this.objectPhieuMuonList;}
    
    set setNumberPageHere(pageNumber){this.numberPageHere = pageNumber;}
    get getNumberPageHere(){return this.numberPageHere;}
    
    get getMaxPage(){return this.maxPage;}
    set setMaxPage(number){ this.maxPage = number;}
    
    pagination(page) {
        $.ajax({
            type: "get",
            url:`/Spring/Libarian/api/v1/PhieuMuons/request-holds/?page=${page}&limit=${this.limit}`,
            dataType : 'text',
            timeout:10000,
            success : function(data)
            {
               let dataList = JSON.parse(data);
               window.pagination.setObjectPhieuMuonList =  dataList;
               new TableRequestHold().buildTable(dataList);
                $('#loadingTable').empty();
            }
        });   

    }
    
    loadPage(){
         function DOMStartLoadPage(){
            $('#loadingTable').append(`<div class="justify-content-center row">
                                            <div class="spinner-border mx-auto" role="status">
                                                <span class="sr-only">Loading...</span>
                                            </div>
                                        </div>`);
            $('#tableYeuCauDatGiuSach tbody').empty();       
        }

        return {
            pagination : window.pagination,
            numberPageClick : function (){
                let page = $(this).text();
                DOMStartLoadPage();
               
                window.pagination.pagination(page);
            },
            nextPageClick : function(){
                let numberPage = this.pagination.getNumberPageHere+1;
                
                DOMStartLoadPage();
                this.pagination.setNumberPageHere=numberPage;
                this.pagination.pagination(numberPage);
            },
            previousPageClick: function(){
               
                let numberPage = this.pagination.getNumberPageHere-1;
                if(numberPage >= 1){
                    DOMStartLoadPage();
                    this.pagination.setNumberPageHere=numberPage;
                }
            }
            
        };
        
    }
    
    buildPageButton(soLuong) {
        let wrapper = $('#phanTrang .pagination');
        
        if(soLuong < 0 )
            throw "Lỗi Input/Output";

        let soTrang = Math.ceil(soLuong/this.limit);
        this.setMaxPage = soTrang;
        wrapper.append(` <li class="disabled"><a class="page-link" id="previous" href="#" tabindex="-1">Previous</a></li>`);
        if(soTrang == 1)
        {
             wrapper.append(`<li class="page-item"><a class="page-link" href="#">${soTrang}</a></li>`);
        }else{
            for(let i=1;i<=soTrang;i++){
                  wrapper.append(`<li class="page-item"><a class="page-link" href="#">${i}</a></li>`);
            }
        }
        $('.page-item').click(window.pagination.loadPage().numberPageClick);

       wrapper.append(`<li class="">  <a class="page-link" id="nextPage" href="#">Next</a> </li>`);
        
        $('#previous').click(window.pagination.loadPage().previousPageClick.bind(window.pagination.loadPage()));
        
        $('#nextPage').click(window.pagination.loadPage().nextPageClick.bind(window.pagination.loadPage()));
    };
    getCtpmFromThisObjectPhieuMuonList(maPm){
        return this.objectPhieuMuonList.filter(phieuMuon => phieuMuon.maPm == maPm);
    }
    
    
    
}