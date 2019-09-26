/**
 * @Descripcion: Controlador encargado de gestionar la creacion de usuarios
 * @Author: SmartJungle S.A.S
 * @Date: 11/07/2019
 * @Date Modificación: 11/07/2019
 */
angular.module('smartApp').controller('createUsersCtrl',function($scope, smartServices, $compile, $timeout) {
	$("#confirmarToken").prop('disabled', true);
	$scope.user = {
			       "user":null,
			       "password":null,
			       "confirmPassword":null,
			       "birthdate":null,
			       "gender":null,
			       "country":null,
			       "email":null,
			       "secureToken":null,
			       "application":application
			      };
	
	$scope.onInit = function(){
		if(getUserSession() != null){
		  $scope.broadcast = getUserSession();
		}
		
	}
	/**
	* Método utilizado para confirmar el código del token
	*/
	$scope.confirmaToken = function(){
		try {
			var exito = function(response){
				try{
					if(response.data != null){
						$scope.limpiar();
					}
				}catch(e){
					alert("Error al tratar de comparar codigo: "+e.message);
				}
			}
			var error = function(response){
				alert("Error comparando codigo");
				console.log(angular.toJson(response));
			}
			var confirma = true;
			var msg = 'Debe ingresar el campo [ ';
			if($scope.user.secureToken == null || $scope.user.secureToken == ''){
				msg +='Confirmar Token - ';
				$("#confirmarToken").focus();
				create= false;
				return;
			}
			
			   
		   if(confirma){
			   
				smartServices.sendPost(
										angular.toJson($scope.user),
										hostSmart+context+methodConfirmCreateUser,
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
	/**
	* Método utilizado para crear el registro
	*/
	$scope.create = function(){
		try {
			var exito = function(response){
				try{
					if(response.data != null){
						var resp = angular.toJson(response.data);
						alert(resp.descripcion+": \nVerifique el codigo que le llego al correo "+$scope.user.email+",\n" +
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
			var msg = 'Debe ingresar los campos [ ';
			if($scope.user.user == null || $scope.user.user == ''){
				msg +='Usuario - ';
				$("#user_name").focus();
				create= false;
				return;
			}
			
			if($scope.user.email == null || $scope.user.email == ''){
				msg +='Correo electr&oacute;nico ';
				$("#email").focus();
				create= false;
				return;
			}
			
			if($scope.user.password == null || $scope.user.password == ''){
				msg +='Clave - ';
				$("#password").focus();
				create= false;
				return;
			}
			
			if($scope.user.confirmPassword == null || $scope.user.confirmPassword == ''){
				msg +='Confirmar Clave - ';
				$("#password_confirmation").focus();
				create= false;
				return;
			}

			if($scope.user.birthdate == null || $scope.user.birthdate == ''){
				msg +='Fecha de nacimiento - ';
				$("#datepicker").focus();
				create= false;
				return;
			}

			if(create && $scope.user.confirmPassword != $scope.user.password){
				msg = 'Las claves ingresadas no son iguales';
				$("#password_confirmation").focus();
				create= false;
			}
		   
			
		   if(create){
			    
			   
			    var password = utf8_to_b64($scope.user.confirmPassword);
				$scope.user.password = password;
				$scope.user.confirmPassword = password;
				
				smartServices.sendPost(
										angular.toJson($scope.user),
										hostSmart+context+methodRequestCreateUser,
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
	/**
	* Método utilizado para limpiar la vista
	*/
	$scope.limpiar = function() {
		$scope.user.username = null;
		$scope.user.email = null;
		$scope.user.password = null;
		$scope.user.confirmPassword = null;
		$scope.user.birthdate = null;
		$("#confirmarToken").prop('disabled', true);
	}
	
	$(document).ready(function() {
		  $('#birthdate').datepicker({
			  dateFormat : 'dd/mm/yyyy',
		        uiLibrary: 'bootstrap',
				dayNamesMin: [ "Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa" ],
				monthNames: [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" ],
				changeMonth: true,
		        changeYear: true
		  });
	});

    
  

	
});
