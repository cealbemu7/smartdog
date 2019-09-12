/**
 * Script utilizado para funciones que permiten formatear fechas
 * SmartJungle S.A
 * 25 de enero de 2019
 */

var formatFechaAAAAMMDD = {
	 separador: "/", // separador "/"
	 formatear:function (fecha){
		 var splitStr = fecha.split(this.separador);
		 return splitStr[2] + splitStr[1] + splitStr[0];
	 },
	 retornar:function(fecha){
		 return this.formatear(fecha);
	 }
}

var validarFecha = {
		 validacion:function (fechainicio, fechafin){
			 var result = true;
			 var fechainiformat = formatFechaAAAAMMDD.formatear(fechainicio);
			 var fechafinformat = formatFechaAAAAMMDD.formatear(fechafin);
			 if(fechainiformat>=fechafinformat){
				 result = true;
			 }else{
				 result = false;
			 }
			 return result;
		 },
		 resultado:function(fechainicio, fechafin){
			 return this.validacion(fechainicio, fechafin);
		 }
	}