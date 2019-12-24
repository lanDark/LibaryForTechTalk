
/*
    https://css-tricks.com/example/index.html?s=flexbox

    window.location.protocol = "http:"
    window.location.host = "css-tricks.com"
    window.location.pathname = "example/index.html"
    window.location.search = "?s=flexbox"
 */
function URI(){
    return{
        host : window.location.host,
        protocol : window.location.protocol,
        pathName: window.location.pathname,
        api:`api/v1/`,
        originURL : function(){return `${this.protocol}/${this.host}${this.pathName}`;}
        
    };
}
export {URI};