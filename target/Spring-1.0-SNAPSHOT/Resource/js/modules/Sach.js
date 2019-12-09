/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


export default  class Sach{

    constructor(build) {
       this.maSach = build.maSach;
       this.tenSach = build.tenSach;
       this.soLuong = 1;
       this.url = build.url;
    }
    
    static getNameStore () {return Sach.NAMELOCALSTORE;}
    
    get _maSach() {return this.maSach;}
    set _maSach(maSach){this.maSach = maSach;}
    
    get _tenSach() {return this.tenSach;}
    set _tenSach(tenSach){this.tenSach=tenSach;}    
    
    
    get _soLuong() {return this.soLuong;}
    set _soLuong(soLuong){this.soLuong=soLuong;}
    
    get _url() {return this._url;}
    set _url(url){this.url=url;}
    toJson(){
         return{
            maSach : this.maSach,
            tenSach : this.tenSach,
            tacGia : this.tacGia,
            soLuong : this.soLuong,
            url : this.url
         };
    }
    static get Builder() {
        class Builder {
            constructor(maSach) {
                this.maSach = maSach;
             }
            setMaSach(maSach){
                this.maSach = maSach;
                return this;    
            }
            setTenSach(tenSach) {
               this.tenSach = tenSach;
               return this;
            }
            setSoLuong(soLuong){
                this.soLuong = soLuong;
                return this;
            }
            setURL(url){
                this.url = url;
                return this;
            }
            build() {
               return new Sach(this);
            }
        }
       return Builder;
    }

}