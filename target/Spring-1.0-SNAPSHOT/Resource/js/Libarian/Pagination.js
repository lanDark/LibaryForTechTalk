import URI from './URI.js';
import TableRequestHold from './TableRequestHold.js';

export default  class Pagination{
    constructor(){
        this.startPage = 1;
        this.objectPhieuMuonList = '';
        this.numberPageHere  = 1;
        this.paginationObject = this.pagination();
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
            },
            callback: null,
            extend: function(data) {
                let pagination = window.pagination;
                const maxPage = Math.ceil(data.record/data.limit);
                
                pagination.setMaxPage = maxPage || 30;
                pagination.elementHTML = data.element;
                pagination.setStep = data.step || 3;
                pagination.setLimit = data.limit  || 5;
                this.callback = data.callback ;
                this.loadingElementStr = data.loadingElementHTML || '#loadingTable';
                this.tableElementStr = data.tableElementHTML || 'tableRequestHold';
            },
            numberPageClick : function (e){
                e.preventDefault(); 
                let page = +$(this).text();
                let pagination = window.pagination;
                pagination.paginationObject.domLoadPage();
                pagination.setNumberPageHere = +page;
                pagination.paginationObject.callback();
                pagination.paginationObject.create();
                pagination.paginationObject.active(page);
  
            },
            nextPageClick : function(e){ // this is object jquery
                e.preventDefault(); 
                let numberPage = this.getNumberPageHere+1;
                if(numberPage <= this.getMaxPage)  {
                    window.pagination.paginationObject.domLoadPage();
                    this.setNumberPageHere=numberPage;
                    window.pagination.paginationObject.callback();
                    this.paginationObject.create();
                    this.paginationObject.active(numberPage);
                }
                
            },
            previousPageClick: function(e){ // this is Pagination.class
                e.preventDefault(); 

                let numberPage = this.numberPageHere - 1;
                if(numberPage >= 1){
                    this.paginationObject.domLoadPage();
                    this.setNumberPageHere=numberPage;
                    this.paginationObject.callback();
                    this.paginationObject.create();
                    this.paginationObject.active(numberPage);
                }
            },
            // add last page with separator
            last: function() {
                let pagination = window.pagination;
                $('.pagination').append( `<li><a class="page-link" disabled>...</a></li>
                        <li class="page-item"><a class="page-link" href="#">${pagination.getMaxPage}</a></li>`);
            },

            // add first page with separator
            first: function() {
                $('.pagination').append( `<li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li><a class="page-link" disabled>...</a></li>`);
            },
            finish:function(){
                this.bind();
            },
              // create skeleton
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
    
asddd
    getCtpmFromThisObjectPhieuMuonList(maPm){
        return this.objectPhieuMuonList.filter(phieuMuon => phieuMuon.maPm == maPm);
    }
}