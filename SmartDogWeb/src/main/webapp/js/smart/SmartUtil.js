
/**
 * Este archivo define variables globales de la aplicación,
 * para que la programación de las opciones sea mas sencilla...
 * 
 * También tiene funciones de utilidad javascript, 
 * 
 * */
/*
 * Nombre de las opciones de la barra de menu de la aplicación...
 * */
var optionsbar     = ['news'];

/**
 * @Descripcion : Definicion de endpoint del consumo de los servios de smart
 */
var app = 'Dog';
var port = '8082';
var protocol = 'http';
var server = 'localhost';
var hostSmart = protocol+'://'+server+':'+port;
var context = '/Smart'+app+'Services/SmartBussServices';

/**
 * @Descripcion: Definicion de los servicios a consumir para el login de la aplicacion y el menu principal.
 * @Author: SmartJungle
 * @Date: 30-06-2018
 * @Date Modifi: 30-06-2018
 */
var serviceNameLogin = '/SmartServiceLogin';
var application =   'Smart'+app+'Web';
var contextLogin = '/'+application+'/SmartWebService';

var methodLogin = serviceNameLogin+'/login';
var methodResource = serviceNameLogin+'/resources';
var methodEntitiesUser = serviceNameLogin+'/entitiesUser';
var methodLogout = serviceNameLogin+'/logout';
var methodInfoSession = serviceNameLogin+'/infoSession';
var methodEmpresaEntity = serviceNameLogin+'/getEmpresaEntidad';



/**
 * @Descripcion: Definicion de los servicios a consumir para la solicitud de usuario al sistema
 * @Author: SmartJungle
 * @Date: 29-07-2019
 * @Date Modifi: 29-07-2019
 */
var serviceNameRequestUsersCreate = '/SmartCreateUsersService';
var methodRequestCreateUser = serviceNameRequestUsersCreate+'/requestCreateUser';
var methodConfirmCreateUser = serviceNameRequestUsersCreate+'/confirmCreateUser';

var userSession = {};
var appProperties = {
    developer: "SmartJungle S.A.S",
    version: "1.0.0",
    environment: "P",
    releaseDate: "2019-07-11",
    printServer: "127.0.0.1",
    printPort: "5000"
}

var prototypePrint = {
	data : utf8_to_b64("dafaultValue"),
	url : utf8_to_b64("dafaultValue"),
	isJasper : false
}

var delete_cookie = function(name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};

function HTTPValidateResponse (response){
	try{
		if(response.data != undefined && !(response.data instanceof Object) && response.data.indexOf('<html>') == 0){
			delete_cookie('JSESSIONID');
			window.location.reload();
		}else{
			if((angular.toJson(response.data) != undefined 
			    && angular.toJson(response.data) != null 
				 && response.data.codigo != null
				  && response.data.codigo != undefined
				   && response.data.codigo === '401') || (angular.toJson(response) != undefined 
						    && angular.toJson(response) != null 
							 && response.codigo != null
							  && response.codigo != undefined
							   && response.codigo === '401') ){
					$.SmartMessageBox({
						title : '<h1>Alerta!</h1>' ,
						content : '<h2> Usuario no autorizado, por favor iniciar sesión nuevamente </h2>',
						buttons : '[Aceptar]'
					}, function(ButtonPressed) {
						if (ButtonPressed === "Aceptar") {
							try {
								var smartServices = angular.element(document.body).injector().get("smartServices")
								var exito = function(response){
									try{
										setUserSession(null);
										delete_cookie('JSESSIONID');
										window.location.reload();
									}catch(e){
										alert("Error al tratar de recargar la pagina: "+e.message);
									}
								}
								var error = function(response){
									alert("Error al tratar de cerrar sesión");
									console.log(angular.toJson(response));
								}
							   
								smartServices.sendGet(
										hostSmart+contextLogin+methodLogout,
										exito,
										error);		
								
							} catch (error) {
					          alert(error.message);
							}
						}
					});
				return false;
			 }
		}
	}catch(error){
		$.SmartMessageBox({
			title : '<h1>Error!</h1>' ,
			content : '<h2> Ocurrio un error recibiendo respuesta del servidor <br>'+
			           'Descripci&oacute;n:<br> nombre: '+error.name+'<br> mensaje: '+error.message
			          + '<br> Desea iniciar sesi&oacute;n nuevamente? </h2>',
			buttons : '[No][Si]'
		}, function(ButtonPressed) {
			if (ButtonPressed === "Si") {
				delete_cookie('JSESSIONID');
				window.location.reload();
			}
		});
		return false;
	}
	return true;
}

/*
 * Este metodo controla que opción queda activa cuando se acceda a la misma...
 * Manipula los objetos HTML a través de DOM, modificando sus estilos...
 * */
	function menu() {
		var lmenu      = arguments[0]; 
		var lsubmenu   = arguments[1];
		var optmenu    = arguments[2];
		var optsubmenu = arguments[3];
		if(lmenu != null && lmenu instanceof Array) {
		   for(var index=0; index < lmenu.length; index++) {
			   if(lmenu[index] == optmenu) {
				  $('#'+lmenu[index]).addClass("active");
			   }
			   else {
				  $('#'+lmenu[index]).removeClass("active");
			   }
		   }	
		}
		if(lsubmenu != null && lsubmenu instanceof Array) {
		   for(var index=0; index < lsubmenu.length; index++) {
			   if(lsubmenu[index] == optsubmenu) {
				  $('#'+lsubmenu[index]).addClass("active");
			   }
			   else {
				  $('#'+lsubmenu[index]).removeClass("active");
			   }
		   }	
		}
	}
	
	this.buscarItem		  	= function() {
		var valor				= arguments[0];
		var lista				= arguments[1];
		var comparator			= arguments[2];
		var posicion			= -1;
		
		if(lista != null && lista.length > 0) {
			for(var index= 0; index < lista.length; index++) {
				if(comparator(valor, lista[index])) {
					posicion 		= index;
					break;
				}
			}
		}
		return posicion;
	};

	/*
	 * Función en javascript para formatear los numeros...
	 * En la implementación hay ejemplos de su uso...
	 * */
	function number_format(number, decimals, dec_point, thousands_sep) {
	  //  discuss at: http://phpjs.org/functions/number_format/
	  // original by: Jonas Raoni Soares Silva (http://www.jsfromhell.com)
	  // improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
	  // improved by: davook
	  // improved by: Brett Zamir (http://brett-zamir.me)
	  // improved by: Brett Zamir (http://brett-zamir.me)
	  // improved by: Theriault
	  // improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
	  // bugfixed by: Michael White (http://getsprink.com)
	  // bugfixed by: Benjamin Lupton
	  // bugfixed by: Allan Jensen (http://www.winternet.no)
	  // bugfixed by: Howard Yeend
	  // bugfixed by: Diogo Resende
	  // bugfixed by: Rival
	  // bugfixed by: Brett Zamir (http://brett-zamir.me)
	  //  revised by: Jonas Raoni Soares Silva (http://www.jsfromhell.com)
	  //  revised by: Luke Smith (http://lucassmith.name)
	  //    input by: Kheang Hok Chin (http://www.distantia.ca/)
	  //    input by: Jay Klehr
	  //    input by: Amir Habibi (http://www.residence-mixte.com/)
	  //    input by: Amirouche
	  //   example 1: number_format(1234.56);
	  //   returns 1: '1,235'
	  //   example 2: number_format(1234.56, 2, ',', ' ');
	  //   returns 2: '1 234,56'
	  //   example 3: number_format(1234.5678, 2, '.', '');
	  //   returns 3: '1234.57'
	  //   example 4: number_format(67, 2, ',', '.');
	  //   returns 4: '67,00'
	  //   example 5: number_format(1000);
	  //   returns 5: '1,000'
	  //   example 6: number_format(67.311, 2);
	  //   returns 6: '67.31'
	  //   example 7: number_format(1000.55, 1);
	  //   returns 7: '1,000.6'
	  //   example 8: number_format(67000, 5, ',', '.');
	  //   returns 8: '67.000,00000'
	  //   example 9: number_format(0.9, 0);
	  //   returns 9: '1'
	  //  example 10: number_format('1.20', 2);
	  //  returns 10: '1.20'
	  //  example 11: number_format('1.20', 4);
	  //  returns 11: '1.2000'
	  //  example 12: number_format('1.2000', 3);
	  //  returns 12: '1.200'
	  //  example 13: number_format('1 000,50', 2, '.', ' ');
	  //  returns 13: '100 050.00'
	  //  example 14: number_format(1e-8, 8, '.', '');
	  //  returns 14: '0.00000001'

	  number = (number + '')
	    .replace(/[^0-9+\-Ee.]/g, '');
	  var n = !isFinite(+number) ? 0 : +number,
	    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
	    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
	    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
	    s = '',
	    toFixedFix = function(n, prec) {
	      var k = Math.pow(10, prec);
	      return '' + (Math.round(n * k) / k)
	        .toFixed(prec);
	    };
	  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
	  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n))
	    .split('.');
	  if (s[0].length > 3) {
	    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
	  }
	  if ((s[1] || '')
	    .length < prec) {
	    s[1] = s[1] || '';
	    s[1] += new Array(prec - s[1].length + 1)
	      .join('0');
	  }
	  return s.join(dec);
	}
	
	function ConvertDateToString(date) {
		var monthStr = (date.getMonth()+1);
			monthStr = (monthStr < 10)?"0"+monthStr:monthStr;
		return date.getDate() + "-" + monthStr + "-" + date.getFullYear();
	}

	/*
	 * Convierte el tamaño de un archivo en unidades como MB, GB...
	 * */
	function ConvertUnitSize() {
	   	 var $sizeFile  = arguments[0];
		 var $unit      = "";
	     var $result    = "";
		 
	      if($sizeFile >= Math.pow(1024, 3))       { 
	         $unit      = "GB"; 
	         $sizeFile  = $sizeFile/(Math.pow(1024,3));
	         $result    = number_format($sizeFile, 2)+" "+$unit; 
	      } 
	      else if ($sizeFile >= Math.pow(1024, 2)) { 
	         $unit      = "MB"; 
	         $sizeFile  = $sizeFile/(Math.pow(1024,2)); 
	         $result    = number_format($sizeFile, 2)+" "+$unit; 
	      }
	      else if ($sizeFile >= Math.pow(1024, 1)) { 
	         $unit      = "KB"; 
	         $sizeFile  = $sizeFile/1024; 
	         $result    = number_format($sizeFile, 2)+" "+$unit; 
	      } 
	      else if ($sizeFile <= 1023)         { 
	         $unit      = "Bytes"; 
	         $sizeFile  = $sizeFile; 
	         $result    = number_format($sizeFile, 2)+" "+$unit; 
	      }
		  
		  return ($result);
	}

	/*
	 * Mappea un objeto para convertirlo en dato que se puede enviar a un CGI...
	 * */
	function Param(obj) {
	      var str = [];
	      for(var key in obj) {
	         if(obj[key] instanceof Array) {
	            for(var idx in obj[key]) {
	                var subObj = obj[key][idx];
	                for(var subKey in subObj) {
	                    str.push(
							encodeURIComponent(key) + "[" + idx + "][" + 
							encodeURIComponent(subKey) + "]=" + 
							encodeURIComponent(subObj[subKey]));
	                }
	            }
	         }
			 else if(obj[key] instanceof Object) {
	                    for(var idx in obj[key]){
	                        var subObj = obj[key][idx];
	                        str.push(
								encodeURIComponent(key) + "[" + idx + "]=" + 
								encodeURIComponent(subObj));
	                    }
	         }
	         else {
	                    str.push(encodeURIComponent(key) + "=" + encodeURIComponent(obj[key]));
	         }
	      }
	      return str.join("&");
	}
	
	/**
	 * Obtiene el contexto de la aplicacion
	 * @returns
	 */
	function getContextPath() {
		   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
	function setUserSession(json){
		sessionStorage.setItem("userSession",utf8_to_b64(angular.toJson(json)));
	}
	function getUserSession(){
		var object = sessionStorage.getItem("userSession");
		if(object != null)return angular.fromJson( b64_to_utf8(object));
		return null;
	}
	

	function utf8_to_b64(str) {
	return window.btoa(unescape(encodeURIComponent(str)));
    }

	function b64_to_utf8(str) {
		return decodeURIComponent(escape(window.atob(str)));
	}
	
	function NumText(str){//solo letras y numeros
	    var out = '';
	    //Se añaden las letras validas
	    var filtro = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890';//Caracteres validos
		
	    for (var i=0; i<str.length; i++)
	       if (filtro.indexOf(str.charAt(i)) != -1) 
		     out += str.charAt(i);
	    return out;
	}
	
	function NumTextEspacio(str){//solo letras, numeros y espacios
	    var out = '';
	    //Se añaden las letras validas
	    var filtro = ' abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890';//Caracteres validos
		
	    for (var i=0; i<str.length; i++)
	       if (filtro.indexOf(str.charAt(i)) != -1) 
		     out += str.charAt(i);
	    return out;
	}
	
	function processResponseModal(json){
		localStorage.setItem("responsemodal",utf8_to_b64(angular.toJson(json)));
	}
	
	function getResponseModal(){
		var object = localStorage.getItem("responsemodal");
		localStorage.removeItem("responsemodal");
		if(object != null)return angular.fromJson( b64_to_utf8(object));
		return null;
	}
	
	
	var specialKeys = [];
	specialKeys.push(8); //Backspace
	specialKeys.push(9); //Tab
	specialKeys.push(36); //Home
	specialKeys.push(35); //End
	specialKeys.push(37); //Left
	specialKeys.push(39); //Right
	specialKeys.push(46); //Supr


	/**
	 * Accept only numbers and '.' on input
	 * @param e, the key press HTML event
	 * @returns {Boolean}
	 */
	function IsNumeric(e){
		if(e.shiftKey) return false;
		var keyCode = e.keyCode == 0 ? e.charCode : e.keyCode;
		return ((specialKeys.indexOf(keyCode)!= -1) || (keyCode >= 48 && keyCode <= 57));
	}
	
	/**
	 * Accept only numbers on input
	 * @param e, the key press HTML event
	 * @returns {Boolean}
	 */
	function isInteger(evt)
	  {
		 var charCode = (evt.which) ? evt.which : event.keyCode
		 if (charCode > 31 && (charCode < 48 || charCode > 57)){
			 return false;
		 }

		 return true;
	  }
	
	/* Función que suma o resta días a una fecha, si el parámetro
	   días es negativo restará los días*/
	function dateAddDay(fecha, dias){
	  fecha.setDate(fecha.getDate() + dias);
	  return fecha;
	}
	
	/*
	 * Date Format 1.2.3
	 * (c) 2007-2009 Steven Levithan <stevenlevithan.com>
	 * MIT license
	 *
	 * Includes enhancements by Scott Trenda <scott.trenda.net>
	 * and Kris Kowal <cixar.com/~kris.kowal/>
	 *
	 * Accepts a date, a mask, or a date and a mask.
	 * Returns a formatted version of the given date.
	 * The date defaults to the current date/time.
	 * The mask defaults to dateFormat.masks.default.
	 */

	var dateFormat = function () {
		var	token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
			timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
			timezoneClip = /[^-+\dA-Z]/g,
			pad = function (val, len) {
				val = String(val);
				len = len || 2;
				while (val.length < len) val = "0" + val;
				return val;
			};

		// Regexes and supporting functions are cached through closure
		return function (date, mask, utc) {
			var dF = dateFormat;

			// You can't provide utc if you skip other args (use the "UTC:" mask prefix)
			if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
				mask = date;
				date = undefined;
			}

			// Passing date through Date applies Date.parse, if necessary
			date = date ? new Date(date) : new Date;
			if (isNaN(date)) throw SyntaxError("invalid date");

			mask = String(dF.masks[mask] || mask || dF.masks["default"]);

			// Allow setting the utc argument via the mask
			if (mask.slice(0, 4) == "UTC:") {
				mask = mask.slice(4);
				utc = true;
			}

			var	_ = utc ? "getUTC" : "get",
				d = date[_ + "Date"](),
				D = date[_ + "Day"](),
				m = date[_ + "Month"](),
				y = date[_ + "FullYear"](),
				H = date[_ + "Hours"](),
				M = date[_ + "Minutes"](),
				s = date[_ + "Seconds"](),
				L = date[_ + "Milliseconds"](),
				o = utc ? 0 : date.getTimezoneOffset(),
				flags = {
					d:    d,
					dd:   pad(d),
					ddd:  dF.i18n.dayNames[D],
					dddd: dF.i18n.dayNames[D + 7],
					m:    m + 1,
					mm:   pad(m + 1),
					mmm:  dF.i18n.monthNames[m],
					mmmm: dF.i18n.monthNames[m + 12],
					yy:   String(y).slice(2),
					yyyy: y,
					h:    H % 12 || 12,
					hh:   pad(H % 12 || 12),
					H:    H,
					HH:   pad(H),
					M:    M,
					MM:   pad(M),
					s:    s,
					ss:   pad(s),
					l:    pad(L, 3),
					L:    pad(L > 99 ? Math.round(L / 10) : L),
					t:    H < 12 ? "a"  : "p",
					tt:   H < 12 ? "am" : "pm",
					T:    H < 12 ? "A"  : "P",
					TT:   H < 12 ? "AM" : "PM",
					Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
					o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
					S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
				};

			return mask.replace(token, function ($0) {
				return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
			});
		};
	}();

	// Some common format strings
	dateFormat.masks = {
		"default":      "ddd mmm dd yyyy HH:MM:ss",
		shortDate:      "m/d/yy",
		mediumDate:     "mmm d, yyyy",
		longDate:       "mmmm d, yyyy",
		fullDate:       "dddd, mmmm d, yyyy",
		shortTime:      "h:MM TT",
		mediumTime:     "h:MM:ss TT",
		longTime:       "h:MM:ss TT Z",
		isoDate:        "yyyy-mm-dd",
		isoTime:        "HH:MM:ss",
		isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
		isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
	};

	// Internationalization strings
	dateFormat.i18n = {
		dayNames: [
			"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
			"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
		],
		monthNames: [
			"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
			"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
		]
	};

	// For convenience...
	Date.prototype.format = function (mask, utc) {
		return dateFormat(this, mask, utc);
	};
	
	function stringToDate(_date,_format,_delimiter)
	{
	            var formatLowerCase=_format.toLowerCase();
	            var formatItems=formatLowerCase.split(_delimiter);
	            var dateItems=_date.split(_delimiter);
	            var monthIndex=formatItems.indexOf("mm");
	            var dayIndex=formatItems.indexOf("dd");
	            var yearIndex=formatItems.indexOf("yyyy");
	            var month=parseInt(dateItems[monthIndex]);
	            month-=1;
	            var formatedDate = new Date(dateItems[yearIndex],month,dateItems[dayIndex]);
	            return formatedDate;
	}

	function formatMoney(value) {
		var num = value.toString().replace(/\./g, '');
		if (!isNaN(num)) {
			num = num.toString().split('').reverse().join('').replace(
					/(?=\d*\.?)(\d{3})/g, '$1.');
			num = num.split('').reverse().join('').replace(/^[\.]/, '');
			value = num;
		} else {
			alert('Solo se permiten numeros');
			value = value.toString().replace(/[^\d\.]*/g, '');
		}
		return num;
    }
	
	/**
	 * Validates if an input text is a valid date
	 * @param txtDate
	 * @returns {Boolean}
	 */
	function isDate(txtDate)
	{
	  var currVal = txtDate;
	  if(currVal == null || currVal == '')
	    return false;
	  
	  //Declare Regex 
	  var rxDatePattern = /^(\d{1,2})(\/)(\d{1,2})(\/)(\d{4})$/;
	  var dtArray = currVal.match(rxDatePattern); // is format OK?
	  if (dtArray == null)
	     return false;
	  //Checks for dd/MM/yyyy format.
	  var dtDay= dtArray[1];
	  var dtMonth = dtArray[3];
	  var dtYear = dtArray[5];
	  if (dtMonth < 1 || dtMonth > 12){
	      return false;
	  }else if (dtDay < 1 || dtDay> 31){
	      return false;
	  }else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31){
	      return false;
	  }else if (dtMonth == 2){
	     var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
	     if (dtDay> 29 || (dtDay ==29 && !isleap))
	          return false;
	  }
	  return true;
	}
	
	/**
	 * Validates if value matches with the size of integer and decimal parts
	 * @param value The value
	 * @param intSize Size of integer part
	 * @param decSize Size of decimal part
	 * @param separator Decimal separator
	 */
	function isValidDecimal(value, intSize, decSize, separator){
		//Value isn't present
		if(value == null || value == ''){
			console.log('::: isValidDecimal :::: The value is ' + value);
			return true;
		}
		
		//Value contains separator?
		var sep = value.indexOf(separator) >= 0;
		if(sep){
			//Get integer part
			var intPart = value.substring(0, value.indexOf(separator));
			var decPart = value.substring(value.indexOf(separator) + 1, value.length);
			return (intPart.length <= intSize) && (decPart.length <= decSize);
		}else{
			//Evaluate all value as integer part
			return value.length <= intSize;
		}
		
	}
	