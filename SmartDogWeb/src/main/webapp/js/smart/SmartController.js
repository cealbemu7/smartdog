/**
 * Controlador principal de la aplicacion
 */
angular.module('smartApp')

.controller('smartController',function($scope,smartServices) {})


.controller('indexController',function($scope,smartServices) {
	
	var divMessages  			 = document.querySelector('#messageBox');
	divMessages.className 	     = "alert alert-info alert-dismissable";	       
	$scope.titulo_mensajes 		 = "Información!";
	$scope.descripcion_mensajes  = "En esta vista es un ejemplo";
	
	
	$scope.send	 = function() {
		var argumentos 	= arguments;
		try{
			var exito = function(respuesta) {
				try{
					var mensaje				= (argumentos.length > 3)?argumentos[2]:"Se realizó la consulta con exito";
					var responseObject		= respuesta.data;
					var lista				= angular.fromJson(responseObject);
					$scope.valor_consultado = lista.code;
				}catch(e){
					alert(e.message);
				}
			};

			var error = function(respuesta){
				$scope.titulo_mensajes 		= "Error!";
				$scope.descripcion_mensajes = "Se produjo un error al momento de consultar la información";
				divMessages.className 		= "alert alert-danger alert-dismissable";
				console.log(angular.toJson(respuesta));
			};
		    
			var sendObject	=  { code : $scope.code };
			$scope.valor_consultado = '';
			smartServices.sendPost(
									angular.toJson(sendObject),
									hostSmart+context+'ExampleService/getCode',
									exito,
									error
								  );
		}catch(e){
			$scope.titulo_mensajes 			= "Error!";
			$scope.descripcion_mensajes  	= "Se produjo un error al momento de ejecutar el servicio";
			divMessages.className 			= "alert alert-danger alert-dismissable";
		}
	}
}	
);
