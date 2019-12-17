import Pagination from './Pagination.js';

{
    let pagination  = new Pagination();
    pagination.pagination(1,5);
    $.ajax({
        type: "get",
        url:`/Spring/Libarian/api/v1/PhieuMuons/request-holds/count`,
        dataType : 'text',
        timeout:10000,
        success : function(data)
        {
           let objDataSucess = JSON.parse(data);
           pagination.buildPageButton(objDataSucess.number);
        }
    });   
}