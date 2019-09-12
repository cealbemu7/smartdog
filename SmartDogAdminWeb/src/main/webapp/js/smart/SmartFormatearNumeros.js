/**
 * Script utilizado para funciones que permiten formatear números
 * SmartJungle S.A
 * 22 de agosto de 2018
 */

var formatNumber = {
	 separador: ".", // separador para los miles
	 sepDecimal: ',', // separador para los decimales
	 formatear:function (num){
		 num +='';
		 var splitStr = num.split('.');
		 var splitLeft = splitStr[0];
		 var splitRight = splitStr.length > 1 ? this.sepDecimal + splitStr[1] : '';
		 var regx = /(\d+)(\d{3})/;
		 while (regx.test(splitLeft)) {
			 splitLeft = splitLeft.replace(regx, '$1' + this.separador + '$2');
		 }
		 return this.simbol + splitLeft +splitRight;
	 },
	 retornar:function(num, simbol, numdecimales){
		 this.simbol = simbol ||'';
		 var numaux = num.toFixed(numdecimales);
		 return this.formatear(numaux);
	 }
}