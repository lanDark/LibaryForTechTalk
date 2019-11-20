/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


export default class Cart{
    static  NAMELOCALSTORE (){ return "CART";};
    
    set setCart(cart){this._cart = cart;}
    get getCart(){return this._cart;}
    
    constructor(){
        this._cart = [];
    }
    
    toJson(){
         return{
             cart : this._cart
         };
    }    
    
    // Các hàm xử lý local storage

    checkLocalStoreIsExist(value){
        return localStorage.getItem(value) != null;
    }
    
    createLocalStore(key){
        localStorage.setItem(key,"");
    }
    
    getLocalStore(value){
        return localStorage.getItem(value);
    } 

    addItemToLocalStores(key,value){
        localStorage.setItem(key,JSON.stringify(value));
    }    
    
    // Xử lý cart 
    parseJsonToObject(jsonString){
        return JSON.parse(jsonString);
    }
    
    addObjectToArray(sach){
        this.callCartInJson();
        this._cart.push(sach);
    }
    
    callCartsInJSON(){
        let stringJson  =   this.getLocalStore(Cart.NAMELOCALSTORE());
        this._cart      =   this.parseJsonToObject(stringJson);
        (this._cart == null) ? this._cart = [] : '';
    }
    
    addItemToLocalStore(sach){
        let stringJson;
        let checkLocal = this.checkLocalStoreIsExist(Cart.NAMELOCALSTORE());
        
        this.callCartsInJSON();
        if(checkLocal) {
            if(this.checkItemInLocalStoreIsExists(sach.maSach)){
                let temp = this.getBookInCart(sach.maSach);
                temp.soLuong = temp.soLuong + 1;
                this.updateCart(temp);
            }else{
                this._cart.push(sach);
                this.addItemToLocalStores(Cart.NAMELOCALSTORE(),this._cart);
            }
        }
        else{
            this._cart.push(sach);
            this.addItemToLocalStores(Cart.NAMELOCALSTORE(),this._cart);
        }
    }
    

    
    checkItemInLocalStoreIsExists(maSach){
        let sach        =   this.getBookInCart(maSach);
        if(sach != null)    
            return true;
        else                
            return false;
    }
    

    
    // Hàm xử lý đối tượng cart 
    getBookInCart(maSach){
        
        for(let i = 0; i < this._cart.length;i++){
            
            if(this._cart[i].maSach == maSach) 
                return this._cart[i];
        }
    }
    
    updateCart(sach){
        
        for(let i = 0; i < this._cart.length ; i++){
            if(this._cart[i].maSach == sach.maSach) {
                this._cart[i]=sach;
                break;
            }
        }
        this.addItemToLocalStores(Cart.NAMELOCALSTORE(),this._cart);
    }    
    
    deleteSach(maSach){
        this.callCartsInJSON();
        for(let i = 0; i< this._cart.length;i++){
            if(this._cart[i].maSach.toString() == maSach) {
                this._cart.splice(i,1);
            }
        }
       this.addItemToLocalStores(Cart.NAMELOCALSTORE(),this._cart);
    }
}

