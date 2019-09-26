/**
 * @Descripcion: Controlador encargado de gestionar las vista de Ingreso al sistema 
 * @Author: SmartJungle S.A.S
 * @Date: 25-09-2019
 * @Date Modificación: 25-09-2019
 */

angular.module('smartApp').controller('ingresoAlSistemaCtrl',function($scope, smartServices) {

	
	/**
	 * @Descripcion: Carga Inicial de los envios al servidor
	 * @Author: SmartJungle S.A.S
	 * @Date: 25-09-2019
	 */
	$scope.onInit = function(){
		
		$scope.usuario = {
				"scusuario": null,
			   	"dsusuario": null,
			    "dscontrasena": null,
			    "dsemail": null
		}
		
	}	
		
	/**
	 * este metodo permite consultar usuarios
	 * 
	 */
	 $scope.consultarUsuario = function() {

		  try {	  
			  

				  var exito = function(response) {
					  	if(response.data != null){
							$scope.usuarios = new Array();
							var usuarios  = angular.fromJson(response.data);
							if(usuarios.length>0){
								$.each(usuarios, function( index , usuario ) {						
									$scope.usuario.dsemail = usuario.dsemail;
									$scope.usuario.dscontrasena = usuario.dscontrasena;
										
									alert("exito usuario con este correo");
								});
							}else{
								alert("No se encontro usuario con este correo");
							}
						}else{
							alert("No se encontro usuario con este correo");
						}
					    
				    }
		 
				    var error = function(response) {
					    var reponsoObject = angular.fromJson(response.data);
				    }
				    
				    var sendObject = {
				    		dsemail : $scope.usuario.dsemail,
				    		dscontrasena : $scope.usuario.dscontrasena,				    		
				    };

				    alert(sendObject.dsemail);
				    alert(sendObject.dscontrasena);
				    alert(angular.toJson(sendObject));
				    
				    smartServices.sendPost(
				      angular.toJson(sendObject),
				      hostSmart+context+methodConsultarUsuario,
				      exito,
				      error);

		   
		  } catch (error) {
			 // $scope.mensaje("Error", "Ha ocurrido un error al momento de consultar usuario", error.message);
		  }
	 }
			
		
});


