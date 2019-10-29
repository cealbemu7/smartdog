/**
 * @Descripcion: Controlador encargado de gestionar las vista de Ingreso al sistema 
 * @Author: SmartJungle S.A.S
 * @Date: 25-09-2019
 * @Date Modificación: 25-09-2019
 */

angular.module('smartApp').controller('ingresoAlSistemaCtrl',function($scope, smartServices) {

	$scope.isRequest = 'S';
	$scope.isUser = 'N';
	
	/**
	 * @Descripcion: Carga Inicial de los envios al servidor
	 * @Author: SmartJungle S.A.S
	 * @Date: 25-09-2019
	 */
	$scope.onInit = function(){
		$scope.isRequest = 'S';
		$scope.isUser = 'N';
		// TODO: Controlar si el usuario hace reloadpage, F5 o recarga la pagina y no termino el formulario de registro completo
		$scope.cargaInicial();
	}
	
	$scope.maestro = {
		"comaestro" : null
	}
	
	
	$scope.tipodocumento = {
		"scdatmaestro" : null,
		"codatmaestro" : null,
		"dsdatmaestro" : null,
		"dsvalor" : null
	}
	
	$scope.usuario = {
		"scusuario": null,
	   	"dsusuario": null,
	    "dscontrasena": null,
	    "dsemail": null
	}
	
	$scope.inmobiliaria = {
		"scempresa" : null,
		"nitempresa" : null,
		"dsrazonsocial" : null,
		"cousuario" : null,			
		"fhretiro" : null,
		"ciudad" : null,
		"dsdireccion" : null,
		"dstelefono" : null,
		"dsemail" : null,
	}
	/**
	* controlador que consulta de los maestros
	*/
	
	$scope.cargaInicial = function() {
		try {			
			var exito = function(response){
				try{
					if(response.data != null){
						$scope.tipodocumentos = new Array();
						var tipodocumentos  = angular.fromJson(response.data.listatipodocumento);						
						$.each(tipodocumentos, function( index , tipodocumento ) {
							$scope.tipodocumentos.push(tipodocumento);
						});						
						
						$scope.CargarInmobiliaria();
					}else{
						alert("Error al cargar inicial: ");
					}
				}catch(e){
					alert("Error en la cargar inicial: ");
				}
			}
	
			var error = function(response){
				alert("Error en la carga inicial: ");
				console.log(angular.toJson(response));
			}
			$scope.maestro = {"comaestro" : null}
			$scope.maestro.comaestro = SmartMaestroTipoDocumento;
			var sendTipoDocumento = $scope.maestro;
			var sendobjectTipoDocumento = {
				maestro : sendTipoDocumento,
				cousuario: $scope.cousuario
			};	   
		
		   var sendObject = {				   
				   tipodocumento : sendobjectTipoDocumento,				  
		   }; 	   
		   smartServices.sendPost(angular.toJson(sendObject),hostSmart+context+methodListaInicial,exito,error);				
			
		} catch (error) {
			alert("Error en cargar inicial: ");
		}
	}
	/***
	 * metodo para cargar inmobiliaria
	 */
	$scope.CargarInmobiliaria = function(){
		try {
			var exito = function(response) {
				if(response.data != null){
					$scope.inmobiliaria = new Array();
					var inmobiliarias = angular.fromJson(response.data);
					$.each(inmobiliarias, function(index,inmobiliaria) {
						$scope.inmobiliarias.push(inmobiliaria);
					});				
				}else{
					alert("Advertencia", "No se han encontrado inmobiliaria ingresadas","");
				}
			}
			var error = function(response) {
				var reponsoObject = angular.fromJson(response.data);
				alert("Error", "Ha ocurrido un error al momento de listar inmobiliaria",reponsoObject.descripcion);
			}			

			var sendInmobiliaria = $scope.inmobiliaria;
			smartServices.sendPost(angular.toJson(sendInmobiliaria),hostSmart+context+methodConsultarEmpresa,exito,error);
			
		} catch (error) {
			alert("Error", "Ha ocurrido un error al momento de listar inmobiliaria", error.message);
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
									
								});
								
								alert("el usuario existe con este correo");
								$scope.isRequest = 'N';
								$scope.isUser = 'S';								
								setUserSession(angular.tojson(respose.data));
								
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
				    smartServices.sendPost(
				      angular.toJson(sendObject),
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


/**
 * funciones del steps
 * 
 * @returns
 */
$(document).ready(function () {

    var navListItems = $('div.setup-panel div a'),
        allWells = $('.setup-content'),
        allNextBtn = $('.nextBtn');

    allWells.hide();

    navListItems.click(function (e) {
        e.preventDefault();
        var $target = $($(this).attr('href')),
            $item = $(this);

        if (!$item.hasClass('disabled')) {
            navListItems.removeClass('btn-success').addClass('btn-default');
            $item.addClass('btn-success');
            allWells.hide();
            $target.show();
            $target.find('input:eq(0)').focus();
        }
    });

    allNextBtn.click(function () {
        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
            curInputs = curStep.find("input[type='text'],input[type='url']"),
            isValid = true;

        $(".form-group").removeClass("has-error");
        for (var i = 0; i < curInputs.length; i++) {
            if (!curInputs[i].validity.valid) {
                isValid = false;
                $(curInputs[i]).closest(".form-group").addClass("has-error");
            }
        }

        if (isValid) nextStepWizard.removeAttr('disabled').trigger('click');
    });

    $('div.setup-panel div a.btn-success').trigger('click');
});