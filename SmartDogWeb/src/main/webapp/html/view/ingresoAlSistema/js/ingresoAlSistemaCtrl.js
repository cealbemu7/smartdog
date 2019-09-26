/**
 * @Descripcion: Controlador encargado de gestionar las vista de Ingreso al sistema 
 * @Author: SmartJungle S.A.S
 * @Date: 25-09-2019
 * @Date Modificación: 25-09-2019
 */

angular.module('smartApp').controller('ingresoAlSistemaCtrl',function($scope, smartServices, $compile, $timeout) {

	
	/**
	 * @Descripcion: Carga Inicial de los envios al servidor
	 * @Author: SmartJungle S.A.S
	 * @Date: 25-09-2019
	 */
	$scope.onInit = function(){
		alert("listo");
		
		$scope.usuario = {
				"scusuario": null,
			   	"dsusuario": null,
			    "dscontrasena": null,
			    "dsemail": null
		}
		$( "#dsemail" ).focus();		
	}	
		
	/**
	 * este metodo permite consultar usuarios
	 * 
	 */
	 $scope.consultarUsuario = function() {

		  try {	  
			  
			  if ($scope.swcc != false){
				  var exito = function(response) {
					  	if(response.data != null){
							$scope.usuarios = new Array();
							var usuarios  = angular.fromJson(response.data);
							if(usuarios.length>0){
								$.each(usuarios, function( index , usuario ) {						
									$scope.usuario.dsemail = usuario.dsemail;
									$scope.usuario.dscontrasena = usuario.dscontrasena;
																
									$scope.mensaje("Informativo", "Usuario consultado con éxito", "");
								});
							}else{
								$scope.mensaje("Informativo", "No se encontro usuario con este correo", "");
								$( "#dsemail" ).focus();
							}
						}else{
							$scope.mensaje("Informativo", "No se encontro usuario con este correo", "");
							$( "#dsemail" ).focus();
						}
					    
				    }
		 
				    var error = function(response) {
					    var reponsoObject = angular.fromJson(response.data);
					    $scope.mensaje("Error", "Ha ocurrido un error al consultar usuario",reponsoObject);
				    }
		 
				   
				    
				    var sendObject = {
				    		dsemail : $scope.usuario.dsemail,
				    		dscontrasena : $scope.usuario.dscontrasena,				    		
				    };
				    
				    smartServices.sendPost(
				      angular.toJson(sendObject),
				      hostSmart+context+methodConsultarUsuario,
				      exito,
				      error);
			  }
		   
		  } catch (error) {
		   $scope.mensaje("Error", "Ha ocurrido un error al momento de consultar usuario", error.message);
		  }
	 }
			
	// Función en el botón consultar usuario en evento click 
	$( "#btnIniciarSesion" ).click(function() {
		$scope.consultarUsuario();
	});		
		
});


