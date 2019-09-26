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
	}
	
	$scope.login = function(){
		try {
			var exito = function(response){
				try{
					if(response.data != null){
						userSession = angular.fromJson(response.data);
						$scope.session = userSession;
						setUserSession($scope.session);
						//$scope.loadEntitiesUserSession();
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
						});	
						$("#selectEntityModal").modal('show');
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
						$scope.obtenerEmpresa();
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
			smartServices.sendPost(angular
					.toJson(userLogin), hostSmart + context
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
	/**
	 * carga la empresa de la entidad
	 */
	$scope.obtenerEmpresa = function() {
		try {
			var exito = function(response){
				try{
					if(response.data != null){
						var result = angular.fromJson(response.data);
						$scope.session.empresa = result;
						$scope.session.entity = $scope.entidad;
						$scope.session.entity.secureToken = $scope.session.secureToken;
						$scope.session.application = application;
						setUserSession($scope.session);
						$scope.doChangeEntityPopover();
					}
				}catch(e){
					alert("Error al consultar la empresa de la entidad: "+e.message);
				}
			}
			var error = function(response){
				alert("Error al consultar la empresa de la entidad");
				console.log(angular.toJson(response));
			}
			if($scope.entidad == undefined){
				$scope.entidad = getUserSession().entity;
				$scope.entidad.secureToken = getUserSession().secureToken;
			}else{
				$scope.entidad.secureToken = $scope.session.secureToken;
			}
			smartServices.sendPost(angular
					.toJson($scope.entidad), hostSmart + context
					+ methodEmpresaEntity, exito, error);	
			
		} catch (error) {
          alert(error.message);
		}
	}
	
	
	$scope.doChangeEntityPopover = function(){
			
		var html = "<form action='' class='smart-form' style='min-width:170px'>Seleccione la entidad y pulse Aceptar <br><div class='col-12'>";
		$.each($scope.entidades, function( index , entidad ) {
			if(entidad.entity == $scope.session.entity.entity){
				html += "<label class='radio'><input type='radio' name='rentidad' value='"+entidad.entity+"' id='rentidad_"+entidad.entity+"' checked> <i></i>"+entidad.dsentity+"</label>";
			}else{
				html += "<label class='radio'><input type='radio' name='rentidad' value='"+entidad.entity+"' id='rentidad_"+entidad.entity+"'> <i></i>"+entidad.dsentity+"</label>";	
			}
			
		});	
		html += "</div><div class='form-actions'><div class='row'><div class='col-md-12' id='change_entity_button_div'></div></div></div></form>";
		
		$('#changeEntityPopover').popover({
			placement: "auto",
			container: "body",
			title: "<i class='fa fa-fw fa-institution'></i> Cambiar entidad",
			content: html,
			html:"true"
		});
		
		$('#changeEntityPopover').on('shown.bs.popover', function () {
			if($("#btn_accept_change_entity").length == 0){
				var html = "<a class='btn btn-primary btn-sm' id='btn_accept_change_entity' ng-click='changeEntity()'>Aceptar</a>";
				var angularElement = angular.element(html);
				var linkFunction = $compile(angularElement);
				var el = linkFunction($scope);
				angular.element('#change_entity_button_div').append(el[0]);
			}
			$("#rentidad_"+$scope.entidad.dsnit).attr("checked", true);
		});
	}
	
	$scope.changeEntity = function(){
		var selectedEntity = $("input[name='rentidad']:checked").val();
		$.each($scope.entidades, function( index , entidad ) {
			if(entidad.entity == selectedEntity){
				$scope.entidad = entidad;
				$scope.session.entity = entidad;
				return false;
			}
		});
		$('#changeEntityPopover').popover('hide');
		$scope.SiteMenu = null;
		var iframe = document.getElementById("SmartParrotAdminWeb");
		setTimeout(function() { 
            if( iframe.document ) {
                document.SmartHawkWeb.document.body.innerHTML = ""; //Chrome, IE
            }else {
            	iframe.contentDocument.body.innerHTML = ""; //FireFox
            }
        }, 1000);
		$scope.loadResources();
	}
	
	$scope.goToApp = function(){
		var selectedEntity = $("input[name='rentidadModal']:checked").val();
		$.each($scope.entidades, function( index , entidad ) {
			if(entidad.entity == selectedEntity){
				$scope.entidad = entidad;
				$scope.session.entity = entidad;
				return false;
			}
		});
		$("#selectEntityModal").modal('hide');
		$scope.loadResources();
	}
	
	$(document).ready(function() {
		$app = appProperties;
		$(document).attr("title", $app.developer+' '+$app.version+' ('+$app.environment+')');
        $("#selectEntityModal").modal({
            backdrop: 'static',
            keyboard: false
        });
	});
		
});