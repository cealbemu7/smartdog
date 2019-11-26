/**
 * @Descripcion: Controlador encargado de gestionar la vista de Empresa
 * @Author: Andres Brgs.
 * @Date: 06-11-2019
 * @Date Modificación: 08-11-2019
 */

angular.module('smartApp').controller('asesorCtrl',function($scope, smartServices) {

	/**
	 * @Descripcion: Carga los Servidor
	 * @Author: SmartJungle S.A.S
	 * @Date: 08-11-2019
	 */
		
	

	$scope.sw = true;
	$scope.onInit = function() {
		
		
		$scope.maestro = {
				"comaestro" : null
			};
		$scope.tipoidentificacion = {
				"scdatmaestro" : null,
				"codatmaestro" : null,
				"dsdatmaestro" : null,
				"dsvalor" : null
			};
		
		$scope.sexo = {
				"scdatmaestro" : null,
				"dsdatmaestro" : null,
				"dsvalor" : null
			};
		$scope.pais = {
				"scpais" : null,
				"copais" : null,
				"dspais" : null
			}

		$scope.departamento = {
			"scdepartamento" : null,
			"dsdepartamento" : null,
			"pais" : null
			
		}
		$scope.ciudad = {
			"scciudad" : null,
			"dsciudad" : null,
			"departamento" : $scope.departamento
		}
		
		$scope.asesor = {
			"scasesor" : null,
			"tipoidentificacion" : null,
			"dsidentificacion" : null,
			"dspnombre" : null,
			"dssnombre" : null,
			"dspapellido" : null,
			"dssapellido" : null,
			"fhingreso" : null,
			"fhmodificacion" : null,
			"fhretiro" : null,
			"ciudad" : $scope.ciudad,
			"dscelular" : null,
			"dsdireccion" : null,
			"dstelefono" : null,
			"dsemail" : null,
			"fhnacimineto":null,
			"cousuario":null,
			"sexo":null,
			
			
		}
		// $scope.usuario = getUserSession().user;
		$scope.usuario = "andres";
		
		$scope.listarDepartamento();
	}
	
	$scope.cargaInicial = function() {
		try {			
			var exito = function(response){
				try{
					if(response.data != null){
						
						$scope.tipoidentificacion = new Array();
						var tipoidentificaciones  = angular.fromJson(response.data.listatipoidentificacion);						
						$.each(tipoidentificaciones, function( index , tipoidentificacion ) {
							$scope.tipoidentificaciones.push(tipoidentificacion);
						});
						
						$scope.sexos = new Array();
						var sexos  = angular.fromJson(response.data.listasexo);
						$.each(sexos, function( index , sexo ) {
							$scope.sexos.push(sexo);
						});
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
			
			$scope.maestro = {"comaestro" : null}
		    $scope.maestro.comaestro = SmartMaestroSexo;
		    var sendMaestroSexo = $scope.maestro;
		    var sendObjectSexo = {
				maestro : sendMaestroSexo,
				cousuario: $scope.usuario
		    };
		    
		
		   var sendObject = {				   
				   tipodocumento : sendobjectTipoDocumento,
				   sexo : sendObjectSexo,
				  
		   }; 	   
		   smartServices.sendPost(angular.toJson(sendObject),hostSmart+context+methodListaInicial,exito,error);				
			
		} catch (error) {
			alert("Error en cargar inicial: ");
		}
	}
	

	/**
	 * controlador que consultar departamentos
	 */
	$scope.listarDepartamento = function() {
		try {
			var exito = function(response) {
				try {
					if (response.data != null) {
						$scope.departamentos = new Array();
						var departamentos = angular.fromJson(response.data);
						$.each(departamentos, function(index,departamento) {
							$scope.departamentos.push(departamento);
						});
					} else {
						alert("Informativo No se encontraron departamentos");
					}
					$scope.listarEmpresa();
				} catch (e) {
					alert("Error Se produjo un error al momento de consultar los departamentos...",e.message);
				}
			}
			var error = function(response) {
				alert("Error Se produjo un error al momento de listar los departamentos",response.data);
				console.log(angular.toJson(response));
			}
			
			$scope.pais = {"scpais": null}
			$scope.pais.scpais = SmartMaestroPais;
				var sendPais =$scope.pais;
				var sendObject ={
						pais :sendPais
				
			}
			smartServices.sendPost(angular.toJson(sendObject),hostSmart + context+ methodListarDepartamento, exito,error);
		} catch (error) {
			alert("Error Se produjo un error al momento de listar los departamentos b",error.message);
		}
	}

	/**
	 * controlador que consulta de las ciudades
	 */
	$scope.consultarCiudadEmpresa = function(scciudad) {
		try {
			if ($scope.departamento != null) {
				var exito = function(response) {
					try {
						if (response.data != null) {
							$scope.ciudades = new Array();
							var ciudades = angular.fromJson(response.data);
							$.each(ciudades, function(index,ciudad) {
								$scope.ciudades.push(ciudad);
							});
							$.each($scope.ciudades,function(index,ciudad) {
												if (ciudad.scciudad == scciudad) {
													$scope.ciudad = ciudad;
												}
											})
						} else {
							alert("Informativo No se encontraron ciudad");
						}
					} catch (e) {
						alert("Error Se produjo un error al momento de consultar las ciudades",e.message);
					}
				}
				var error = function(response) {
					alert("Error Se produjo un error al momento de listar las ciudades",response.data);
					console.log(angular.toJson(response));
				}
				var sendObject = {
					departamento : $scope.departamento
				};

				smartServices.sendPost(angular.toJson(sendObject), hostSmart+ context + methodListarCiudad, exito,error);
			}

		} catch (error) {
			alert("Error Se produjo un error al momento de listar las ciudades",error.message);
		}
	}
	/**
	 *  funcion de liastar Asesores
	 */
	$scope.listarAsesor= function() {
		try {
			var exito = function(response) {
				if(response.data != null){
					var asesor = response.data;
					if (asesor.length>0){
						
						$scope.asesores = new Array();
						$scope.asesores = angular.fromJson(asesores);
						alert("exito al consultar asesor")
					}else{
						
						alert("Advertencia", "No se han encontrado asesores ingresados","");
					}						
				}else{
					alert("Advertencia", "No se han encontrado asesores ingresados","");
				}
			}
			var error = function(response) {
				var reponsoObject = angular.fromJson(response.data);
				alert("Error", "Ha ocurrido un error al momento de listar asesores",reponsoObject.descripcion);
			}			

			var sendAsesor = $scope.asesor;
			smartServices.sendPost(angular.toJson(sendAsesor),hostSmart+context+methodConsultarAsesor,exito,error);
			
		} catch (error) {
			$scope.mensaje("Error", "Ha ocurrido un error al momento de listar asesores", error.message);
		}
	}	

	/**
	 * Grabar asesor
	 */
	$scope.grabarAsesor = function() {
		try {
			if ($scope.sw != false) {
				var exito = function(response) {
					var reponsoObject = angular.fromJson(response.data);
					if (reponsoObject != null) {
						$scope.mensaje(" grabado con Exito  ",reponsoObject.descripcion);
					} else {
						alert("Error Ha ocurrido un error al momento de grabar asesor");
					}
					$scope.listarAsesor();
				}
				var error = function(response) {
					var reponsoObject = angular.fromJson(response.data);
					alert("Error Ha ocurrido un error al momento de grabar asesor",reponsoObject.descripcion);
				}
				var sendObjectAsesor = {
						
					scasesor : $scope.asesor.scasesor,
					tipoidentificacion: $cope.asesor.tipoidentificacion,
					dsidentificacion: $cope.asesor.dsidentificacion,
					dspnombre: $scope.asesor.dspnombre,
					dssnombre: $scope.asesor.dssnombre,
					dspapellido: $scope.asesor.dspapellido,
					dssapellido:$scope.asesor.dssapellido,
					cousuario : $scope.asesor.cousuario,
					fhingreso : $scope.asesor.fhingreso,
					fhmodificacion : $scope.asesor.fhmodificacion,
					fhretiro : $scope.asesor.fhretiro,
					ciudad : $scope.ciudad,
					dscelular: $scope.asesor.dscelular,
					dsdireccion : $scope.asesor.dsdireccion,
					dstelefono : $scope.asesor.dstelefono,
					dsemail : $scope.asesor.dsemail,
					scsexo: $scope.asesor.scsexo,
					fhnacimineto: $scope.asesor.fhnacimineto,
					cousuario : $scope.usuario,

				};
				
				smartServices.sendPost(angular.toJson(sendObjectAsesor), hostSmart+ context + methodGrabarAsesor, exito,error);
			}
		} catch (error) {
			alert("Error Ha ocurrido un error al momento de grabar asesor",error.message);
		}
	}
});
