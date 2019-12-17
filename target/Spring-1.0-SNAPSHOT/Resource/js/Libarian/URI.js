export default  class URI{

    
    constructor(){
        this.URL_HOST = window.location.host;
        this.URL_PROTOCOL = window.location.protocol;
    }
    get get_URL_HOST(){return this.URL_HOST};
    get get_URL_PROTOCOL(){return this.URL_PROTOCOL};
    
    
}