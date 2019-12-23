/**
 * @Descripcion: Controlador encargado de gestionar el login y la carga inicial de las entidades del usuario en sesion
 * @Author: SmartJungle S.A.S
 * @Date: 08/07/2018
 * @Date Modificación: 08/07/2018
 */
angular.module('smartApp').controller('loginCtrl',function($scope, smartServices, $compile, $timeout) {
	
	$scope.sw = false;
	
	$scope.mensaje = function(titulo, descripcion, descripcionsistema) {
		
		if(titulo==="Exito"){
			$scope.titulo_mensajes = titulo+"!";
			$scope.descripcion_mensajes  = descripcion + " - "+descripcionsistema;
			divMessages.className = "alert alert-success fade in";
			iMessagesImage.className = "fa-fw fa fa-check";
		}
		
		if(titulo==="Informativo"){
			$scope.titulo_mensajes = titulo+"!";
			$scope.descripcion_mensajes = descripcion + " - "+descripcionsistema;
			divMessages.className = "alert alert-info fade in";
			iMessagesImage.className = "fa-fw fa fa-info";
		}
		
		if(titulo==="Advertencia"){
			$scope.titulo_mensajes = titulo;
			$scope.descripcion_mensajes = descripcion + " - "+descripcionsistema;
			divMessages.className = "alert alert-warning fade in";
			iMessagesImage.className = "fa-fw fa fa-warning";
		}
		
		if(titulo==="Error"){
			$scope.titulo_mensajes = titulo+"!";
			$scope.descripcion_mensajes = descripcion + " - "+descripcionsistema;
			divMessages.className = "alert alert-danger fade in";
			iMessagesImage.className = "fa-fw fa fa-times";
			
			iMessagesImage.className = "fa-fw fa fa-times";
		}
	}
	
	showMenssageInicial = function(){
		$scope.mensaje("Informativo", "Esta opción le permite generar el repote de viviendas", "");
	}

	var divMessages  = document.querySelector('#messageBox');
	var iMessagesImage  = document.querySelector('#messageBoxImage');
	
	/**
	 * @Descripcion: Inicializa el index de la aplicacion
	 * @Author: SmartJungle S.A.S
	 * @Date: 10/07/2018
	 */
	
	$scope.onInit = function(){
		$scope.session = getUserSession();
		if($scope.session != null && $scope.session.user != null && $scope.session.user !=''){
			$scope.disableEntity = {'visibility': 'hidden','display':'none'};
			$scope.disableFrame = {'visibility': 'visible','display':'block'};
			$scope.loadResources();
	    }
		if($scope.session == null){
		   $scope.disableEntity = {'visibility': 'visible','display':'block'};
		   $scope.disableFrame = {'visibility': 'hidden','display':'none'};
		   $scope.SiteMenu = [];
		   $scope.getInfoSession();
		}
		$("#agreeCookiesBtn, #agreeCookiesBtn2").click(function(){
			$("#cookiesDiv").hide();
		});

	}
	
	$scope.login = function(){
		try {
			var exito = function(response){
				try{
					if(response.data != null){
						userSession = angular.fromJson(response.data);
						$scope.session = userSession;
						setUserSession($scope.session);
						$scope.loadEntitiesUserSession();
					}
				}catch(e){
					alert("Error al consultar los recursos del usuario: "+e.message);
				}
			}
			var error = function(response){
				alert("Error al consultar los recursos del usuario");
				console.log(angular.toJson(response));
			}
			
		   var userLogin = {
				   user : userSession.user,
				   password : null,
				   confirmPassword : null,
				   application : application,
				   entity : null,
				   roles: userSession.roles,
				   empresa : null,
				   secureToken : userSession.secureToken
		   }; 
			   
		  alert("*** "+angular.toJson(userLogin)+" - "+hostSmart+context+methodLogin);
		  
			smartServices.sendPost(
					angular.toJson(userLogin),
					hostSmart+context+methodLogin,
					exito,
					error);		
			
		} catch (error) {
          alert(error.message);
		}
	}
	
	/**
	 * Método utilizado para limpiar la vista
	 */
	$scope.getInfoSession = function() {
		try {
			var exito = function(response){
				try{
					if(response.data != null){
						userSession = angular.fromJson(response.data);
						$scope.session = userSession;
						$scope.login()
					}
				}catch(e){
					alert("Error al consultar los recursos del usuario: "+e.message);
				}
			}
			var error = function(response){
				alert("Error al consultar los recursos del usuario");
				console.log(angular.toJson(response));
			}
			
			 alert("==> "+hostSmart+contextLogin+methodInfoSession);
		   
			smartServices.sendGet(
					hostSmart+contextLogin+methodInfoSession,
					exito,
					error);		
			
		} catch (error) {
          alert(error.message);
		}
	}
	
	/**
	 * carga las entidades que tiene el usuario en session
	 */
	$scope.loadEntitiesUserSession = function() {
		try {
			var exito = function(response){
				try{
					if(response.data != null){
						$scope.entidades = new Array();
						var entidades  = angular.fromJson(response.data);
						$.each(entidades, function( index , entidad ) {
							$scope.entidades.push(entidad);
							$scope.entidad = entidad; // siempre se asigna la ultima entidad por negocio de parrot
						});	
						
						$scope.loadResources();
					}
				}catch(e){
					alert("Error al consultar las entidades del usuario: "+e.message);
				}
			}
			var error = function(response){
				alert("Error al consultar las entidades del usuario");
				console.log(angular.toJson(response));
			}
		
		   var userLogin = {
				   user : userSession.user,
				   password : null,
				   confirmPassword : null,
				   application : application,
				   entity : null,
				   roles: userSession.roles,
				   empresa : null,
				   secureToken : userSession.secureToken
		   }; 
		   
			smartServices.sendPost(
					angular.toJson(userLogin),
					hostSmart+context+methodEntitiesUser,
					exito,
					error);		
			
		} catch (error) {
          alert(error.message);
		}
	}
	/**
	 * carga los recursos del usuario
	 */
	$scope.loadResources = function() {
		try {
			var exito = function(response){
				try{
					if(response.data != null){
						var result = angular.fromJson(response.data);
						$scope.SiteMenu = result;
						$scope.disableEntity = {'visibility': 'hidden','display':'none'};
						$scope.disableFrame = {'visibility': 'visible','display':'block'};
					}
				}catch(e){
					alert("Error al consultar los recursos del usuario: "+e.message);
				}
			}
			var error = function(response){
				alert("Error al consultar los recursos del usuario");
				console.log(angular.toJson(response));
			}
			var userLogin = {};
			if($scope.session == null || $scope.session.entity == undefined){
			   userLogin = {
								user : userSession.user,
								password : null,
								confirmPassword : null,
								application : application,
								entity : $scope.entidad,
								roles : userSession.roles,
								empresa: null,
								secureToken : userSession.secureToken
							  };
			}else{
				userLogin = $scope.session;
			}
			
			alert(">> "+angular.toJson(userLogin)+ " <-> "+hostSmart + context + methodResource);
			
			smartServices.sendPost(angular.toJson(userLogin), hostSmart + context
					+ methodResource, exito, error);	
			
		} catch (error) {
          alert(error.message);
		}
	}
	/**
	 * funcion que invalida la sesion de usuario
	 */
	$scope.logout = function() { 
		try {
			var exito = function(response){
				try{
					$scope.session = {};
					userSession = {};
					setUserSession(null);
					location.reload(); 
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