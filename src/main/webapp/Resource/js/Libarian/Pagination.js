import TableRequestHold from './TableRequestHold.js';

export default  class Pagination{
    constructor(){
        this.startPage = 1;
        this.objectPhieuMuonList = '';
        this.numberPageHere  = 1;
        this.paginationObject = this.pagination();
        this.limit  = 5;
        this.record = 0;
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
    
    get getStep(){return this.step;}
    set setStep(number){ this.step = number;}
    
    
   
    
    pagination(){
    // --------------------
    //Các thuộc tính của đối tượng 
    // pagination:
    // elementHTML:
    // domLoadPage: Sau mỗi lần request phân trang thì sẽ xuất hiện mục load 
    // add:
    // active: Đánh dấu số trang hiện tại
    // bind:   add sự kiện cho các nút phân trang <li>1,2..</li>
    // extend:  
    // numberPageClick: 
    // nextPageClick:
    // previousPageClick:
    // selectLimitClick:
    // searchTableOnKeypress:
    // last:
    // finish:
    // finish: 
    // create: DOM HTML các nút phân trang <li></li> bao gồm (first,last )
    // callback:
    // start:
    // init: khởi tạo đối tượng pagination  
    // --------------------
        return {
            pagination : window.pagination,
            elementHTML: null,
            domLoadPage:function(){
                $(`${this.loadingElementStr}`).append(`<div class="justify-content-center row">
                                                <div class="spinner-border mx-auto" role="status">
                                                    <span class="sr-only">Loading...</span>
                                                </div>
                                            </div>`);
                $(`${this.tableElementStr} tbody`).empty();       
            },

            // --------------------
            //              Xử lý sự kiện
            // --------------------
            numberPageClick : function (e){
                e.preventDefault(); 
                let page = +$(this).text();
                if(page == window.pagination.numberPageHere) return;
                let pagination = window.pagination;
                pagination.paginationObject.domLoadPage();
                pagination.setNumberPageHere = +page;
                pagination.paginationObject.callback();
                pagination.paginationObject.create();
  
            },
            nextPageClick : function(e){ // this in object jquery
                e.preventDefault(); 
                let numberPage = this.getNumberPageHere+1;
                if(numberPage <= this.getMaxPage)  {
                    window.pagination.paginationObject.domLoadPage();
                    this.setNumberPageHere=numberPage;
                    window.pagination.paginationObject.callback();
                    this.paginationObject.create();
                }
                
            },
            previousPageClick: function(e){ // this in Pagination.class
                e.preventDefault(); 

                let numberPage = this.numberPageHere - 1;
                if(numberPage >= 1){
                    this.paginationObject.domLoadPage();
                    this.setNumberPageHere=numberPage;
                    this.paginationObject.callback();
                    this.paginationObject.create();
                }
            },
            selectLimitClick: function (){
                let limit =+ $(this).val();
                if(isNaN(limit)) throw "NaN";
                
                window.pagination.maxPage = Math.ceil(window.pagination.record / limit);
                window.pagination.limit = limit;
                window.pagination.numberPageHere = 1;
                window.pagination.paginationObject.domLoadPage();
                window.pagination.paginationObject.callback();
                window.pagination.paginationObject.create();  
            },
            searchTableOnKeypress:function(e){
                var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
                let url = `/PhieuMuons/filter?`;
                if(e.which == 13) {
                    let value = $(this).val();
                    if(isNaN(value)) {
                        url=`${url}maPM=${value}`
                    }else if(emailReg.test(value)){
                         url=`${url}email=${value}`
                    }
                } 
                
            },
            // --------------------
            //              Xử lý chức năng
            // --------------------
            add : function(s, f){
                for(let i = s; i<=f; i++){
                    $(`.pagination`).append(`<li class="page-item"><a class="page-link" href="#">${i}</a></li>`);
                }
            },
            active: function(page){
                 $('.page-item').each(function(index,value){
                    let numberElem = +$(this).text();
                    if(numberElem == page){
                        $(this).addClass('active');
                        return false;
                    }
                });
            },
            bind:function(){
                $('.page-item').click(this.numberPageClick);

                $('#previous').click(this.previousPageClick.bind(window.pagination));

                $('#nextPage').click(this.nextPageClick.bind(window.pagination));
                
                $('#selectLimit').unbind();
                $('#selectLimit').change(this.selectLimitClick);

                $('#searchTable').on('keypress',this.searchTableOnKeypress);
            },

            extend: function(data) {
                let pagination = window.pagination;
                const maxPage = Math.ceil(data.record/window.pagination.getLimit);
                
                pagination.setMaxPage = maxPage || 1;
                pagination.setStep = data.step || 3;
                pagination.record = data.record || 0;
                this.callback = data.callback ;
                this.loadingElementStr = data.loadingElementHTML || '#loadingTable';
                this.tableElementStr = data.tableElementHTML || 'tableRequestHold';
            },
            last: function() {
                let pagination = window.pagination;
                $('.pagination').append( `<li><a class="page-link" disabled>...</a></li>
                        <li class="page-item"><a class="page-link" href="#">${pagination.getMaxPage}</a></li>`);
            },

            first: function() {
                $('.pagination').append( `<li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li><a class="page-link" disabled>...</a></li>`);
            },
            finish:function(){
                this.bind();
            },
            create: function() {
                this.elementHTML.empty();
                this.elementHTML.append(`<li class="disabled" id="previous"><a class="page-link"  href="#" tabindex="-1">Previous</a></li>`);
                this.start();
                this.elementHTML.append(`<li id="nextPage"><a class="page-link" href="#">Next</a> </li>`);
                this.finish();
            },
            start: function(){
                let pagination = window.pagination;
                if (pagination.getMaxPage < pagination.getStep * 2 + 6) {
                    this.add(1, pagination.getMaxPage);
                }
                else if (pagination.numberPageHere < pagination.getStep * 2 + 1) {
                    this.add(1, pagination.getStep * 2 + 4);
                    this.last();
                }
                else if (pagination.numberPageHere >    pagination.getMaxPage - pagination.getStep * 2) {
                    this.first();
                    this.add(pagination.getMaxPage - pagination.getStep * 2 - 2, pagination.getMaxPage);
                }
                else {
                    this.first();
                    this.add(pagination.numberPageHere - pagination.getStep, pagination.numberPageHere + pagination.getStep );
                    this.last();
                }
                this.active(window.pagination.numberPageHere);
            },
            init: function(e, data) {
                this.extend(data);
                this.elementHTML = e;
                this.create();
                this.active(1);
                this.callback();
            }
        };
        
    }
    

    getCtpmFromThisObjectPhieuMuonList(maPm){
        return this.objectPhieuMuonList.filter(phieuMuon => phieuMuon.maPm == maPm);
    }
}