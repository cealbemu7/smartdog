/**
 * @Descripcion: Controlador encargado de gestionar las vista de Ingreso al sistema 
 * @Author: SmartJungle S.A.S
 * @Date: 25-09-2019
 * @Date Modificación: 25-09-2019
 */

angular.module('smartApp').controller('ingresoAlSistemaCtrl',function($scope, smartServices) {

	$scope.isRequest = 'S';
	
	
	/**
	 * @Descripcion: Carga Inicial de los envios al servidor
	 * @Author: SmartJungle S.A.S
	 * @Date: 25-09-2019
	 */
	$scope.onInit = function(){
		$scope.isRequest = 'S';
		
		// TODO: Controlar si el usuario hace reloadpage, F5 o recarga la pagina y no termino el formulario de registro completo
		
	}
	
	$scope.usuario = {
		"scusuario": null,
	   	"dsusuario": null,
	    "dscontrasena": null,
	    "dsemail": null
	}
	
	$scope.RutaCitas = function () {
        var url = "http://" + window.location.host + "/SmartDogWeb/html/view/registrocitas/view_registrocitas.html";
        console.log(window.location.host);
        window.open (url,'_self');
    };
		
	/**
	 * este metodo permite consultar usuarios
	 * 
	 */
	 $scope.consultarUsuario = function() {
		  try {	   
				  var exito = function(response) {
					  	if(response.data != null){
					  		userSession = angular.fromJson(response.data);
							$scope.session = userSession;
							setUserSession($scope.session);
							$scope.RutaCitas();
						}else{
							alert("No se encontro usuario con este correo");
						}
				    }
		 
				    var error = function(response) {
					    var reponsoObject = angular.fromJson(response.data);
				    }
				    
				    var userSession = {
				    		dsusuario : null,
				    		dsemail : $scope.usuario.dsemail,
				    		dscontrasena : $scope.usuario.dscontrasena,				    		
				    };				    
				    smartServices.sendPost(angular.toJson(userSession),
				    					   hostSmart+context+methodConsultarUsuario,
				    					   exito,
				    					   error);
		  } catch (error) {
			 alert("Error", "Ha ocurrido un error al momento de consultar usuario", error.message);
		  }
	 }
	
	/**
	* Método utilizado para crear el registro
	*/
	$scope.create = function(){
		try {
			var exito = function(response){
				try{
					if(response.data != null){
						var resp = angular.toJson(response.data);
						alert(resp.descripcion+": \nVerifique el codigo que le llego al correo "+$scope.usuario.dsemail+",\n" +
								" Ingresa el c&oacute;digo en el campo de confirmaci&oacute;n para continuar con el registro.");
						$("#confirmarToken").prop('disabled', false);
					}
				}catch(e){
					alert("Error al tratar de crear el usuario: "+e.message);
				}
				
			}
			var error = function(response){
				var resp = angular.toJson(response.data);
				alert("Error creando el usuario: \n"+resp.descripcion);
				console.log(angular.toJson(response));
			}
			var create = true;
			
			if($scope.usuario.dsemail == null || $scope.usuario.dsemail == ''){
				msg +='Correo electr&oacute;nico ';
				$("#dsemail2").focus();
				create= false;
				return;
			}	   
			
		   if(create){
			    
			   
			    var contrasena = utf8_to_b64($scope.usuario.confirmcontresena);
				$scope.usuario.contresena = contrasena;
				$scope.usuario.confirmcontresena = contrasena;
				/*tienes un error es user o usuario?usuario, entocne sporque estas llenando user*/
				
				smartServices.sendPost(
										angular.toJson($scope.usuario),
										hostSmart+context+methodGrabarUsuario,
										exito,
										error
									  );		
		   }else{
			   alert(msg+' ]');
		   }
		} catch (error) {
	      alert("Error al iniciar sesión: "+error.message);
	      console.log(error.message);
		}
		 
	}

	$scope.solicitarRegistro = function() {

		var request = true;
		let msg = 'Campos obligatorios [';

		try {

			let success = function(response) {
				try{
					if(response.data != null){
						var resp = angular.toJson(response.data);
						$scope.usuario = resp;
						alert(resp.descripcion+": \nVerifique el codigo que le llego al correo "+$scope.usuario.dsemail+",\n" +
								" Ingresa el c&oacute;digo en el siguiente formulario.");
						$scope.isRequest = 'N';
					}
				}catch(e){
					alert("Error al tratar de crear el usuario: "+e.message);
				}
			};

			let error = function(response) {
				var resp = angular.toJson(response.data);
				alert("Error creando el usuario: \n"+resp.descripcion);
				console.log(angular.toJson(response));
			};

			if ($scope.usuario.dsemail == null || $scope.usuario.dsemail == '') {
				msg += 'Correo electr&oacute;nico ';
				$("#dsemail2").focus();
				request = false;
				return;
			}

			if (request) {
				$scope.usuario.dsusuario = $scope.usuario.dsemail;
				const sendObject = $scope.usuario;
				smartServices.sendPost(angular.toJson(sendObject), hostSmart
						+ context + methodSolicitarRegistroUsuario, success, error);
			} else {
				alert(msg + ' ]');
			}

		} catch (error) {
			alert("Error al iniciar sesión: " + error.message);
			console.log(error.message);
		}

	}
	 
});

