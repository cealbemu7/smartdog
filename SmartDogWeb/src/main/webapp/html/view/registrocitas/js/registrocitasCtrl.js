/**
 * @Descripcion: Controlador encargado de gestionar las vista de Ingreso al sistema 
 * @Author: SmartJungle S.A.S
 * @Date: 30-10-2019
 * @Date Modificación: 10-02-2020
 */

angular.module('smartApp').controller('registroCitasCtrl',function($scope, smartServices, messageFactory, messageFactoryDos, messageFactoryTres) {
	
	var validarcamporegistro = true;
	var validarcampocita = true;
	
	$scope.maestro = {
		"comaestro" : null
	}
	
	$scope.sexo = {
		"scdatmaestro" : null,
		"dsdatmaestro" : null,
		"dsvalor" : null
	};	
	
	$scope.tipoinmueble = {
		"scdatmaestro" : null,
		"codatmaestro" : null,
		"dsdatmaestro" : null,
		"dsvalor" : null
	}
	
	$scope.tipodocumento = {
		"scdatmaestro" : null,
		"codatmaestro" : null,
		"dsdatmaestro" : null,
		"dsvalor" : null
	}
	
	$scope.empresa = {
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
	
	$scope.cliente = {
		"sccliente" : null,
		"nombrecompleto": null,
		"dspnombre" : null,
		"dssnombre" : null,
		"dspapellido" : null,
		"dssapellido" : null,
		"tipoidentificacion" : null,
		"dsidentificacion" : null,
		"dsdireccion" : null,
		"dstelefono" : null,
		"dscelular" : null,
		"dsemail" : null,
		"sexo" : null,
		"fhnacimiento" : null,
		"usuario" : null,
		"dsidentificacion" : null
	};
	
	$scope.usuario = {
		"scusuario":null,
		"cousuario" : null,
		"dsusuario":null
	}
	
	$scope.cita = {
	  "sccita": null,
	  "propiedad": null,
	  "cliente": null,
	  "fhhorainicio": null,
	  "usuario": null,
	  "fhingreso": null,
	  "fhhoracita": null,
	  "fhcita": null,
	  "fhhorafin": null,
	  "asesor": null,
	  "estado": null,
	  "empresa": null	      
	}
	
	$scope.propiedad = {
		"scpropiedad": null
	}
	
	$scope.asesor = {
		"scasesor": null
	}
	
	$scope.estado = {
		"scdatmaestro" : 1,
		"dsdatmaestro" : "pendiente",
		"dsvalor" : "pendiente"	
	}
	
	$scope.ciudad = {
		"scciudad": null,
	    "cociudad": null,
	    "dsciudad": null,
	   "cousuario": null	
	}
	
	$scope.propiedad = {
		"scpropiedad": null,
		"copropiedad": null,
		"dspropiedad":null,
		"dsdireccion": null,
		"usuario": null,
		"ciudad": null
	}
	/**
	 * @Descripcion: Carga Inicial de los envios al servidor
	 * @Author: SmartJungle S.A.S
	 * @Date: 25-09-2019
	 */
	$scope.onInit = function(){			
		// TODO: Controlar si el usuario hace reloadpage, F5 o recarga la pagina y no termino el formulario de registro completo
		$scope.cargaInicial();
		messageFactory.showMessage($scope,"Informativo", " ","Ingresa los datos para agendar la cita");
		messageFactoryDos.showMessageDos($scope,"Informativo", " ","Lista de citas solicitadas");
		messageFactoryTres.showMessageTres($scope,"Informativo", " ","Ingresa los datos para completar su registro de usuario");
		$scope.usuario = getUserSession();
		
		$scope.listarCitas();
		$scope.CargarInmobiliaria();
		$scope.CargarPropiedad();
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
						$scope.sexos = new Array();
						var sexos  = angular.fromJson(response.data.listasexo);
						$.each(sexos, function( index , sexo ) {
							$scope.sexos.push(sexo);
						});
						
						$scope.tipoinmuebles = new Array();
						var tipoinmuebles  = angular.fromJson(response.data.listaTipoInmueble);						
						$.each(tipoinmuebles, function( index , tipoinmueble ) {							
							$scope.tipoinmuebles.push(tipoinmueble);
						});
						 		
						$scope.ConsultarCliente();
					}else{
						messageFactory.showMessage($scope,"Error", " ","Se ha producido un error al momento de realizar la carga inicial de datos");
					}
					
				}catch(e){
					messageFactory.showMessage($scope,"Error", " ","Se ha producido un error al momento de realizar la carga inicial de datos");
				}
			}
	
			var error = function(response){
				messageFactory.showMessage($scope,"Error", " ","Se ha producido un error al momento de realizar la carga inicial de datos");
				var reponsoObject = console.log(angular.toJson(response));
			}
			$scope.maestro = {"comaestro" : null}
			$scope.maestro.comaestro = SmartMaestroTipoDocumento;
			var sendTipoDocumento = $scope.maestro;
			var sendobjectTipoDocumento = {
				maestro : sendTipoDocumento
			};	
			
			$scope.maestro = {"comaestro" : null}
		    $scope.maestro.comaestro = SmartMaestroSexo;
		    var sendMaestroSexo = $scope.maestro;
		    var sendObjectSexo = {
				maestro : sendMaestroSexo
		    };
		    
			$scope.maestro = {"comaestro" : null}
		    $scope.maestro.comaestro = SmartMaestroTipoInmueble;
		    var sendMaestroTipoInmueble = $scope.maestro;
		    var sendObjectInmueble = {
				maestro : sendMaestroTipoInmueble
		    };
		    
		   var sendObject = {				   
				   tipodocumento: sendobjectTipoDocumento,
				   sexo: sendObjectSexo,
				   tipoinmueble: sendObjectInmueble,
		   };		
		   smartServices.sendPost(angular.toJson(sendObject),hostSmart+context+methodListaInicial,exito,error);				
			
		} catch (error) {
			messageFactory.showMessage($scope,"Error", " ","Se ha producido un error al momento de realizar la carga inicial de datos");
		}
	}

	/***
	 * metodo para cargar inmobiliaria
	 */
	$scope.CargarInmobiliaria = function(){
		try {
			var exito = function(response) {
				if(response.data != null){
					$scope.empresas = new Array();
					var empresas = angular.fromJson(response.data);
					$.each(empresas, function(index,empresa) {
						$scope.empresas.push(empresa);
					});										
				}else{
					messageFactory.showMessage($scope,"Informativo", " ","No se encontraron inmobiliarias configuradas");
				}
			}
			var error = function(response) {
				var reponsoObject = angular.fromJson(response.data);
				messageFactory.showMessage($scope,"Error", " ","Se ha producido un error al momento de cargar las inmobiliarias");
			}			

			var sendInmobiliaria = $scope.empresa;
			smartServices.sendPost(angular.toJson(sendInmobiliaria),hostSmart+context+methodConsultarEmpresa,exito,error);
			
		} catch (error) {
			messageFactory.showMessage($scope,"Error", " ","Se ha producido un error al momento de cargar las inmobiliarias");
		}
	}
	
	/***
	 * metodo para cargar propiedad
	 */
	$scope.CargarPropiedad = function(){
		try {
			var exito = function(response) {
				if(response.data != null){
					$scope.propiedades = new Array();
					var propiedades = angular.fromJson(response.data);
					$.each(propiedades, function(index,propiedad) {
						$scope.propiedades.push(propiedad);
					});										
				}else{
					messageFactory.showMessage($scope,"Informativo", " ","No se encontraron inmuebles configurados");
				}
			}
			var error = function(response) {
				var reponsoObject = angular.fromJson(response.data);
				messageFactory.showMessage($scope,"Error", " ","Se ha producido un error al momento de cargar los inmuebles");
			}			

			var sendPropiedad = $scope.propiedad;
			smartServices.sendPost(angular.toJson(sendPropiedad),hostSmart+context+methodConsultarPropiedad,exito,error);
			
		} catch (error) {
			messageFactory.showMessage($scope,"Error", " ","Se ha producido un error al momento de cargar los inmuebles");
		}
	}

	/***
	 * metodo para cargar clientes
	 */
	$scope.CargarCliente = function(){
		try {
			var exito = function(response) {
				if(response.data != null){
					$scope.clientes = new Array();
					var clientes = angular.fromJson(response.data);
					$.each(clientes, function(index,cliente) {
						$scope.clientes.push(cliente);
					});										
				}else{
					messageFactoryTres.showMessageTres($scope,"Informativo", " ","El usuario no se encuentra registrado, debe registrarse");
				}
			}
			var error = function(response) {
				var reponsoObject = angular.fromJson(response.data);
				messageFactoryTres.showMessageTres($scope,"Error", " ","Se ha producido un error al momento de cargar el usuario");
			}			

			var sendCliente = $scope.cliente;
			smartServices.sendPost(angular.toJson(sendCliente),hostSmart+context+methodConsultarCliente,exito,error);
			
		} catch (error) {
			messageFactoryTres.showMessageTres($scope,"Error", " ","Se ha producido un error al momento de cargar el usuario");
		}
	}
	/***
	 * metodo utilizado para consultar cliente logeado
	 */
	$scope.ConsultarCliente = function(){
		try{
			var exito = function(response){				
				if(response.data != null){
			  		cliente = angular.fromJson(response.data);
			  		$scope.cliente.sccliente = cliente.sccliente;
			  		$scope.cliente.nombrecompleto = cliente.nombrecompleto;
			  		
			  		$.each($scope.tipodocumentos, function( index , tipodocumento ) {
						if(tipodocumento.scdatmaestro == cliente.tipoidentificacion.scdatmaestro){
							$scope.tipodocumento = tipodocumento;
						}
					});

			  		$scope.cliente.dsidentificacion = cliente.dsidentificacion;
					$scope.cliente.dspnombre = cliente.dspnombre;
					$scope.cliente.dssnombre = cliente.dssnombre;
					$scope.cliente.dspapellido = cliente.dspapellido; 
					$scope.cliente.dssapellido = cliente.dssapellido;
					$scope.cliente.dstelefono = cliente.dstelefono;
					$scope.cliente.dscelular = cliente.dscelular;
					$scope.cliente.dsemail = cliente.dsemail;
					$scope.cliente.fhnacimiento = new Date(cliente.fhnacimiento);
					
					$.each($scope.sexos, function( index , sexo ) {
						if(sexo.scdatmaestro == cliente.sexo.scdatmaestro){
							$scope.sexo = sexo;
						}
					});
								 						
					$scope.cliente.dsdireccion = cliente.dsdireccion;
					 
				}else{
					messageFactoryTres.showMessageTres($scope,"Informativo", " ","El usuario no se encuentra registrado, debe registrarse");
				}
			}
			var error = function(){
				messageFactoryTres.showMessageTres($scope,"Error", " ","Se ha producido un error al momento de cargar el usuario");
			}
			var sendUsuario = {
					scusuario:$scope.usuario.scusuario
			}
			var sendCliente = {
					usuario: sendUsuario
			}			
			smartServices.sendPost(angular.toJson(sendCliente),hostSmart+context+methodConsultarCliente,exito,error);
			  
		}catch (error) {
			messageFactoryTres.showMessageTres($scope,"Error", " ","Se ha producido un error al momento de cargar el usuario");
	  }
	}
	
	/**
	 * metodo utilizado para validar los datos obligatorios en el almacenamiento del registro de usuario
	 */
	$scope.validarDatosRegistro = function(){
		validarcamporegistro = true;
		
		if($scope.tipodocumento == null){
			messageFactoryTres.showMessageTres($scope,"Advertencia", " ","El tipo de identificaci&oacuten es un dato abligatorio");
			$("#tipodocumento").focus();
			validarcamporegistro = false;
			return;
		}
		
		if($scope.cliente.dsidentificacion == null || $scope.cliente.dsidentificacion == ""){
			messageFactoryTres.showMessageTres($scope,"Advertencia", " ","El n&uacute;mero de identificaci&oacuten es un dato abligatorio");
			$("#dsidentificacion").focus();
			validarcamporegistro = false;
			return;
		}
		
		if($scope.cliente.dspnombre == null || $scope.cliente.dspnombre == ""){
			messageFactoryTres.showMessageTres($scope,"Advertencia", " ","El primer nombre es un dato abligatorio");
			$("#dspnombre").focus();
			validarcamporegistro = false;
			return;
		}
		
		if($scope.cliente.dspapellido == null || $scope.cliente.dspapellido == ""){
			messageFactoryTres.showMessageTres($scope,"Advertencia", " ","El primer apellido es un dato abligatorio");
			$("#dspapellido").focus();
			validarcamporegistro = false;
			return;
		}
	}
	
	/**
	 * metodo utilizado para grabar clientes 
	 */	
	 $scope.GrabarCliente = function() {
		  try {				
			  if($scope.validarDatosRegistro()){
					var exito = function(response) {
					    var reponsoObject = angular.fromJson(response.data);
					    if(reponsoObject!=null){
							messageFactoryTres.showMessageTres($scope,"Exito", " ","El usuario se registro con éxito");
					    }else{
							messageFactoryTres.showMessageTres($scope,"Error", " ","Se produjo un error al momento de registrar al usuario");
					    }					    
					}		 
					var error = function(response) {
					    var reponsoObject = angular.fromJson(response.data);
					    messageFactoryTres.showMessageTres($scope,"Error", " ","Se produjo un error al momento de registrar al usuario");
					    }		 
						   	    
					    var sendCliente = {
					    		sccliente : $scope.cliente.sccliente,
					    		tipoidentificacion : $scope.tipodocumento,
					    		dsidentificacion : $scope.cliente.dsidentificacion,
								dspnombre : $scope.cliente.dspnombre,
								dssnombre : $scope.cliente.dssnombre,
								dspapellido : $scope.cliente.dspapellido,
								dssapellido : $scope.cliente.dssapellido,
								dstelefono : $scope.cliente.dstelefono,
								dscelular :  $scope.cliente.dscelular,
								dsemail : $scope.cliente.dsemail,						
								fhnacimiento : $scope.cliente.fhnacimiento,
								sexo : $scope.sexo,						
								usuario : $scope.usuario,
								dsdireccion : $scope.cliente.dsdireccion
					   
					    };	
					    smartServices.sendPost(angular.toJson(sendCliente),hostSmart+context+methodGrabarCliente,exito,error);
			  }
		  }	catch (error) {
			  messageFactoryTres.showMessageTres($scope,"Error", " ","Se produjo un error al momento de registrar al usuario");
		  }
	 }
	 
	 /**
		 * metodo utilizado para validar los datos obligatorios en el almacenamiento de la cita
		 */
		$scope.validarDatosCita = function(){
			validarcampocita = true;
			

			if($scope.empresa == null || angular.toJson($scope.empresa.dsrazonsocial) === 'null'){
				messageFactory.showMessage($scope,"Advertencia", " ","La inmobiliaria es un dato obligatorio");
				$("#empresa").focus();
				validarcampocita = false;
				return;
			}
				
			
			if($scope.tipoinmueble == null || angular.toJson($scope.tipoinmueble.scdatmaestro) == 'null'){
				messageFactory.showMessage($scope,"Advertencia", " ","El tipo de inmueble es un dato obligatorio");
				$("#tipoinmueble").focus();
				validarcampocita = false;
				return;
			}
			
			if($scope.propiedad == null || angular.toJson($scope.propiedad.scpropiedad) == 'null'){
				messageFactory.showMessage($scope,"Advertencia", " ","El inmueble es un dato obligatorio");
				$("#propiedad").focus();
				validarcampocita = false;
				return;
			}
			
			if($scope.fhcita == null){
				messageFactory.showMessage($scope,"Advertencia", " ","La fecha de la cita es un dato obligatorio");
				$("#fecha").focus();
				validarcampocita = false;
				return;
			}
			
			if($scope.fhhoracita == null){
				messageFactory.showMessage($scope,"Advertencia", " ","La hora de la cita es un dato obligatorio");
				$("#hora").focus();
				validarcampocita = false;
				return;
			}
		}

	/**
	 * metodo utilizado para grabar citas 
	 */	
	 $scope.GrabarCita = function() {
		  try {				
			 if ($scope.validarDatosCita()){
				    var exito = function(response) {
					    var reponsoObject = angular.fromJson(response.data);
					    if(reponsoObject!=null){
					    	messageFactory.showMessage($scope,"Exito", " ","La cita ha sido almacenada con éxito, un asesor se comunicará con usted para confirmar la cita");
					    	$scope.listarCitas();
						}else{
							messageFactory.showMessage($scope,"Error", " ","Se produjo un error al momento de grabar la cita");
					    }					    
				    }		 
				    var error = function(response) {
					    var reponsoObject = angular.fromJson(response.data);
					    messageFactory.showMessage($scope,"Error", " ","Se produjo un error al momento de grabar la cita");
				    }			    
				    horafin = $scope.fhhoracita.getHours();			    
				    var minutosfin = parseInt($scope.fhhoracita.getMinutes())+30;
				    
				    if(minutosfin > 60){
				    	minutosfin = minutosfin - 60;
				    	horafin = horafin + 1;
				    }
				    			     
				    fechacita = $scope.fhcita.getDate().toString()+'/'+(($scope.fhcita.getMonth())+1).toString()+'/'+$scope.fhcita.getFullYear().toString()
				    horainicita = $scope.fhhoracita.getHours().toString()+':'+$scope.fhhoracita.getMinutes().toString()+':'+$scope.fhhoracita.getSeconds().toString();
				    horafincita = horafin+':'+minutosfin+':00';  
				    
				    var sendCita = {
			    		sccita : $scope.cita.sccita,
			    		propiedad : $scope.propiedad,
			    		cliente : $scope.cliente,
			    		fhcita : $scope.fhcita,
			    		fhhoracita :  $scope.fhhoracita,
			    		fhhorainicio :fechacita+' '+horainicita,
			    		usuario : $scope.usuario,
			    		fhhorafin : fechacita+' '+horafincita,
			    		asesor  : $scope.asesor,		    	
			    		empresa : $scope.empresa,
			    		estado: $scope.estado
				    };
				    
				    smartServices.sendPost(angular.toJson(sendCita),hostSmart+context+methodGrabarCita,exito,error);
			 }
		  }	catch (error) {
			  messageFactory.showMessage($scope,"Error", " ","Se produjo un error al momento de grabar la cita");
		  }
	 }
	 /**
	  * Listar citas
	  */
	 $scope.listarCitas = function() {
			try {
				var exito = function(response) {
					if(response.data != null){
						var citas = response.data;
						if (citas.length>0){							
							$scope.citas = new Array();
							$scope.citas = angular.fromJson(citas);
						}else{
							messageFactoryDos.showMessageDos($scope,"Informativo", " ","No se encontraron citas agendadas para este usuario");
						}						
					}else{
						messageFactoryDos.showMessageDos($scope,"Error", " ","Se produjo un error al momento de grabar la cita");
					}
				}
				var error = function(response) {
					var reponsoObject = angular.fromJson(response.data);
					messageFactoryDos.showMessageDos($scope,"Error", " ","Se produjo un error al momento de grabar la cita");
				}			

				var sendCita = $scope.cita;
				smartServices.sendPost(angular.toJson(sendCita),hostSmart+context+methodConsultarCita,exito,error);
				
			} catch (error) {
				messageFactoryDos.showMessageDos($scope,"Error", " ","Se produjo un error al momento de grabar la cita");
			}
		}	
	 /**
	 *  funcion eliminar citas
	 */
	 $scope.eliminarCita = function(c) {
	 	try {	
	 		var exito = function(response) {
	 			var reponsoObject = angular.fromJson(response.data);
	 			if(reponsoObject!=null){
	 				$scope.listarCitas();
	 				messageFactoryDos.showMessageDos($scope,"Exito", " ","La cita ha sido eliminada con éxito");
	 			}else{
	 				messageFactoryDos.showMessageDos($scope,"Error", " ","Se produjo un error al momento de eliminar la cita");
	 			}
 			}	 		
	 		var error = function(response) {
	 			var reponsoObject = angular.fromJson(response.data);
	 			messageFactoryDos.showMessageDos($scope,"Error", " ","Se produjo un error al momento de eliminar la cita");
	 		} 
	 		
	 		var sendObjectCita = {		    
 				sccita: c.sccita,
 				usuario: $scope.usuario
		    }			

	 		smartServices.sendPost(angular.toJson(sendObjectCita),hostSmart+context+methodEliminarCita,exito,error);	

	 	} catch (error) {
	 		messageFactoryDos.showMessageDos($scope,"Error", " ","Se produjo un error al momento de eliminar la cita");
	 	}	
	 }	

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
});