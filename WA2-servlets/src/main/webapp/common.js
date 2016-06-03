/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clears browser history of HTTP BASIC authentification by trying to login
 * non existing user.
  */
/*function logoutBasicAuth(){
    var username = "badUser";
    var password = "badPW";
    var safeLocation = linkPrefix+"destination/";
    logoutInProcess = true;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.status == 200) {
            log("200 - not logged out");
        } else {
            if(logoutInProcess == true){
                printResponse("LOGGED OUT", false);
                logoutInProcess = false;
            } 
        }
    };
    xhttp.open("get", safeLocation, true, username, password);
    xhttp.send();
    log("logout request sent");
}*/

/**
 * http://stackoverflow.com/questions/1787322/htmlspecialchars-equivalent-in-javascript
 * @param {type} text
 * @returns {unresolved}
 */
function escapeHtml(text) {
  var map = {
    '&': '&amp;',
    '<': '&lt;',
    '>': '&gt;',
    '"': '&quot;',
    "'": '&#039;'
  };

  return text.replace(/[&<>"']/g, function(m) { return map[m]; });
}


/**
 * Print text + time.
 * @param {String} text
 */
function log(text){
    var logP = document.getElementById('logP');    
    var date = new Date();
    logP.innerHTML = date.toLocaleTimeString()+": <br/> "+escapeHtml(text)+" <br/>"+logP.innerHTML;
}

/**
 * Prints given text with escaped HTML chars. 
 * @param String text text to print
 * @param boolean itIsBad TRUE - if the response is client-gnerated error information, ELSE otherwise
 */
function printResponse(text, itIsBad){
    var cssclass = "oneResp";
    if(itIsBad === true){
        cssclass = cssclass + " bad";
    }
    var responsesBox = document.getElementById("responsesBox");
    var html = responsesBox.innerHTML;
    responsesBox.innerHTML = "<div class=\""+cssclass+"\">"+escapeHtml(text)+"</div>"+html;
}

/**
 * Returns TRUE if XML format should be used
 * @returns {Element.checked}
 */
function useXML(){
    //return document.getElementById('useXML').checked;
    return false;
}

function getAddress(){
    
    return document.getElementById("requiredAddress").value + "rest/";
}
window.onload = function() {
    document.getElementById('logP').innerHTML = "Page loaded";
    log("Javascript started");
    //document.getElementById("WSaddress").value = WSaddress;
    document.getElementById("requiredAddress").value = document.URL;
};

var lastResponse = 1;
var linkPrefix = document.URL+"rest/";
var logoutInProcess = false;