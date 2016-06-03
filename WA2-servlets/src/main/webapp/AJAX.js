/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getResponse(address, idTextFieldElement) {
    var givenID = document.getElementById(idTextFieldElement).value;
    var requestedPath = address;
    if (givenID != "") {
        requestedPath = requestedPath + "/" + givenID;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            log("ok");
            var toPrint = xhttp.responseText;
            
            var headers = xhttp.getAllResponseHeaders();
            var indexOfHeader = headers.indexOf("X-Count-records");
            if(indexOfHeader > 0){
                toPrint += " Number of possible records for this filter:"+headers.substr(indexOfHeader, 20 );
            }
            
            printResponse(toPrint, false);
        } else if (xhttp.readyState == 4){
            printResponse("xhttp.status=" + xhttp.status, true);
            log("bad response: xhttp.readyState=" + xhttp.readyState + ", xhttp.status=" + xhttp.status);
        }

    };
    var link = getAddress() + requestedPath;
    xhttp.open("GET", link, true);
    //add filtering headers
    var headersAsString = "";
    if(address == "flight"){
        headersAsString += "Headers:";
        var order = getValueOfInputField("sortingBy")+":"+getValueOfInputField("sortingWay");
        xhttp.setRequestHeader("X-Order",order);
        var filter = getValueOfInputField("filterBy")+"From="+getValueOfInputField("filterMin")
                +","+getValueOfInputField("filterBy")+"To="+getValueOfInputField("filterMax");
        xhttp.setRequestHeader("X-Filter", filter);
        var pagB = getValueOfInputField("pagingBase");
        xhttp.setRequestHeader("X-Base",pagB);
        var pagO = getValueOfInputField("pagingOffset");
        xhttp.setRequestHeader("X-Offset",pagO);
        headersAsString += "X-order - "+order+";"
            +"X-Filter - "+filter + "; X-Base - "+pagB+"; X-Offset - "+pagO;
    } else if(address == "reservation"){
        var pw = getValueOfInputField("resPWget");
        xhttp.setRequestHeader("X-Password",pw);
        headersAsString += "X-Password - "+pw+";";
    }
    if(useXML()){
        xhttp.setRequestHeader("Accept", "application/xml");
        headersAsString += " Accept - application/xml;";
    } else {
        xhttp.setRequestHeader("Accept", "application/json");
        headersAsString += " Accept - application/json;";
    }
    log("sent GET - " + link + "<br>"+headersAsString);
    xhttp.send();
}

//<?xml version="1.0" encoding="UTF-8"?><destination><id>0</id><lat>25.0</lat><lon>12.0</lon><name>praga</name><url>http://ofcakpc:8080/AOS-semestralka-1.0-SNAPSHOT/service/destination/0</url></destination>

function postMessage(address, inputsPrefix) {
    var requestedPath = address;
    var newObject;
    //log("a");
    if (inputsPrefix == "newUser") {
        newObject = prepareDestination(inputsPrefix);
    } else if(inputsPrefix == "newFlight"){
        newObject = prepareFlight(inputsPrefix);
    } else if(inputsPrefix == "newReser"){
        newObject = prepareReservation(inputsPrefix);
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            log("ok");
            //document.getElementById("demo").innerHTML = xhttp.responseText;
            printResponse(xhttp.responseText, false);
        } else if (xhttp.readyState == 4){
            printResponse("xhttp.status=" + xhttp.status, true);
            log("bad response: xhttp.readyState=" + xhttp.readyState + ", xhttp.status=" + xhttp.status);
        }

    };
    var link = getAddress() + requestedPath;
    xhttp.open("POST", link, true);
    var message = convertObjectToWantedString(newObject, address);
    log("sent POST - " + link + ", message: "+message);
    log("obj:"+newObject);
    if(useXML()){
        xhttp.setRequestHeader("Content-type", "application/xml");
    } else {
        xhttp.setRequestHeader("Content-type", "application/json");
    }
    xhttp.send(message);
}

function putMessage(address, inputsPrefix) {
    var requestedPath = address;
    var updatedObject;
    var idstring = "";
    if (inputsPrefix == "editUser") {
        updatedObject = prepareDestination(inputsPrefix);
        idstring = "id_user";
    } else if (inputsPrefix == "editFlight") {
        updatedObject = prepareFlight(inputsPrefix);
    } else if(inputsPrefix == "editReser"){
        updatedObject = prepareReservation(inputsPrefix);
    }
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            log("ok");
            //document.getElementById("demo").innerHTML = xhttp.responseText;
            printResponse(xhttp.responseText, false);
        } else if (xhttp.readyState == 4){
            printResponse("xhttp.status=" + xhttp.status, true);
            log("bad response: xhttp.readyState=" + xhttp.readyState + ", xhttp.status=" + xhttp.status);
        }

    };
    var link = getAddress() + requestedPath + "/" + updatedObject[idstring];
    xhttp.open("PUT", link, true);
    var headersAsString = "";
    if(inputsPrefix == "editReser"){
        xhttp.setRequestHeader("X-Password",updatedObject["password"]);
        headersAsString += "X-Password - "+updatedObject["password"]+";";
    }
    
    var message = convertObjectToWantedString(updatedObject, address);
    log("sent PUT - " + link + ", message: "+message + "PW header:"+headersAsString);
    if(useXML()){
        xhttp.setRequestHeader("Content-type", "application/xml");
    } else {
        xhttp.setRequestHeader("Content-type", "application/json");
    }
    xhttp.send(message);
}

/**
 * Transforms given object into wanted String (XML / JSON) selected by checkbox.
 * Only first level objects are transformed into XML. This function does not transform
 * array or object which is inside of given object.
 * @param {type} obj object which is transformed
 * @param {type} objectName object name for name of XML root element
 * @returns {String} objects as XML or JSON
 */
function convertObjectToWantedString(obj, objectName){
    //<reservation><id>0</id><idflight>0</idflight><password>403</password><seats>10</seats><state>new</state><url>http://ofcakpc:8080/AOS-semestralka-1.0-SNAPSHOT/service/reservation/0</url></reservation>
    if(useXML()){
        var xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"";
        xml +="?><"+objectName+">";
        for (var key in obj) {
            xml += "<"+key+">"
                    + obj[key]
                 + "</"+key+">";
        }
        xml += "</"+objectName+">";
        return xml;
    } else {
        return JSON.stringify(obj);
    }
}

function prepareDestination(idTextFieldElement){
    var comp = document.getElementById(idTextFieldElement+"Company").value;
    //var latitude = 0;
    var destName = document.getElementById(idTextFieldElement+"Name").value;
    var destID = document.getElementById(idTextFieldElement+"ID").value;
    var emptyArray = new Array();
    return {id_user:destID, 
        company:comp,
        name:destName,
        positions:emptyArray,
        shifts:emptyArray
    };
}

function prepareFlight(idTextFieldElement){
/*    id - identificator (Long)
name - flight name (unikátní)
dateOfdeparture - date and time of departure (format iso8601 YYYY-MM-DDThh:mm:ssTZD)
distance - distance in km
seats - number of seats in the aircraft
price - proce of the flight
from - from destination - id of the flight is sent when the destination is created only
to - final destination - id of the flight is sent when the destination is created only
url - url of the resource (př. /flight/1)*/
    var flId = getValueOfInputField(idTextFieldElement+"ID");
    var flName = getValueOfInputField(idTextFieldElement+"Name");
    var date = getValueOfInputField(idTextFieldElement+"Date");
    var flDist = getValueOfInputField(idTextFieldElement+"Distance");
    var flSeats = getValueOfInputField(idTextFieldElement+"Seats");
    var flPrice = getValueOfInputField(idTextFieldElement+"Price");
    //TODO I am not suer if FROM and To should be destinations(object) or if the id of destination is enough
    var flFrom = getValueOfInputField(idTextFieldElement+"From");
    var flTo = getValueOfInputField(idTextFieldElement+"To");
    var flUrl = getValueOfInputField(idTextFieldElement+"URL");
    return {id:flId, name:flName, dateOfDeparture:date, distance:flDist, //this missclick makes me 90min debugging filter :x
        seats:flSeats, price:flPrice, from:flFrom, to:flTo, url:flUrl};
}

function prepareReservation(idTextFieldElement){
    /*id - identificator (Long)
flight - flight id
created - date and time of reservation creation
password - randomly generated password
seats - number of the seats of the reservation
state - reservation state (new, canceled, paid)
url - url address of the resource*/
    var reId = getValueOfInputField(idTextFieldElement+"ID");
    //TODO I am not sure if SLIGHT should be Flight(object) or if the id is enough
    var reFl = getValueOfInputField(idTextFieldElement+"Flight");
    var reCreated = getValueOfInputField(idTextFieldElement+"Created");
    var rePW = getValueOfInputField(idTextFieldElement+"PW");
    var reSeats = getValueOfInputField(idTextFieldElement+"Seats");
    var reState = getValueOfInputField(idTextFieldElement+"State");
    var reUrl = getValueOfInputField(idTextFieldElement+"URL");
    return {id:reId, idflight:reFl, created:reCreated, password:rePW,
        seats:reSeats, state:reState, url:reUrl};
}

function getValueOfInputField(inputID){
    return document.getElementById(inputID).value;
}

function deleteMessage(address, idTextFieldElement) {
    var givenID = document.getElementById(idTextFieldElement).value;
    var requestedPath = address;
    if (givenID != "") {
        requestedPath = requestedPath + "/" + givenID;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            log("ok");
            //document.getElementById("demo").innerHTML = xhttp.responseText;
            printResponse(xhttp.responseText, false);
        } else if (xhttp.readyState == 4){
            printResponse("xhttp.status=" + xhttp.status, true);
            log("bad response: xhttp.readyState=" + xhttp.readyState + ", xhttp.status=" + xhttp.status);
        }

    };
    var link = getAddress() + requestedPath;
    xhttp.open("DELETE", link, true);
    log("sent DELETE - " + link + "<br>");
    xhttp.send();
}

function sendMQ(address, message, num) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) {
            log("ok");
            //document.getElementById("demo").innerHTML = xhttp.responseText;
            printResponse(xhttp.responseText, false);
        } else if (xhttp.readyState == 4){
            printResponse("xhttp.status=" + xhttp.status, true);
            log("bad response: xhttp.readyState=" + xhttp.readyState + ", xhttp.status=" + xhttp.status);
        }

    };
    //var addr = document.getElementById(address).value;
    var mess = document.getElementById(message).value;
    var number = document.getElementById(num).value;
    var link = getAddress()+"sendMQ";
    
    xhttp.open("POST", link, true);
    
    var objekt = {note:mess, num:number};
    var zprava = JSON.stringify(objekt);
    //if(useXML()){
     //   xhttp.setRequestHeader("Content-type", "application/xml");
    //} else {
        xhttp.setRequestHeader("Content-type", "application/json");
    //}
    xhttp.send(zprava);
    log("sendMQ link:"+link+", message:"+zprava);
    
    longPolling();
}

function manualMQ() {
    window.open(getAddress()+"Shift",'_blank');
    
}
var pullCounter = 0;
var myPolling;
function longPolling(){
    pullCounter = 0;
    myPolling = window.setInterval(pull, 1000);
    
}

function pull(){
    log("pull"+pullCounter);
    pullCounter = pullCounter +1;
    if(pullCounter>5){
        log("stopped");
        clearTimeout(myPolling);
        manualMQ();
    }
}