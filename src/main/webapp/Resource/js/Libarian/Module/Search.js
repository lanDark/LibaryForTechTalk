/*
 *  Viết module cho phần Search Suggest (Đề xuất tìm kiếm/Gợi ý tìm kiếm)
 */

function Search(){
    return{

        ajaxSetting:{ 
            type: "get",
            dataType: 'text',
            timeout: 10000,
            url:'',
            contentType: "application/json; charset=utf-8"
        },  
        
        fetch : function (e){
            let regex = /[0-9a-zA-Z]/;
            let val = $('#searchTable').val();
            let fetch ;
            
            this.ajaxSetting.url =  this.createURI(val);
            if(regex.test(e.key)){
                fetch = $.ajax({...this.ajaxSetting});
            }
            this.activeEventKeyup();    
            this.buildSuggestHTML(fetch) ;
        },
        
        buildSuggestHTML: ()=>{} ,
        
        activeEventKeyup : function(){
            let obj = this;
            $("#searchTable").keypress(function (e) {
                setTimeout(
                    () => { 
                        obj.fetch(e); 
                        
                    } 
                , 500);
            
                $('#searchTable').off('keypress');
               
            });  
        },
        
        extend: function(data){
            this.buildSuggestHTML = data.buildSuggestHTML;
            this.createURI = data.createURI;
        },
        
        init : function(data){
            this.extend(data);
            this.activeEventKeyup();
            return this;
        }
    };
}

export {Search};