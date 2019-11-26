/**
 * @Descripcion: Controlador encargado de gestionar la vista de propiedad
 * @Author: Andres Brgs.
 * @Date: 25-09-2019
 * @Date Modificación: 26-09-2019
 */

angular.module('smartApp').controller('propiedadCtrl',function($scope, smartServices) {

	/**
	 * @Descripcion: Carga los Servidor
	 * @Author: SmartJungle S.A.S
	 * @Date: 26-09-2019
	 */
	$scope.sw = true;
	$scope.onInit = function() {
		
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

		$scope.propiedad={
				
				"scpropiedad": null,
				"copropiedad": null,
				"dspropiedad": null,
				"dsdireccion": null,
				"fhingreso": null,
				"fhmodificacion": null,
				"fhretiro": null,
				"cousuario": null,
				"ciudad":  $scope.ciudad,
		
			}
	}
	// $scope.usuario = getUserSession().user;
	$scope.usuario = "andres";
	
	$scope.listarDepartamento();

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
					$scope.listarPropiedad();
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
	$scope.consultarCiudad = function(scciudad) {
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
	
	
	
	
	$scope.listarPropiedad= function() {
		try {
			var exito = function(response) {
				if(response.data != null){
					var propiedades = response.data;
					if (propiedades.length>0){
						
						$scope.propiedades = new Array();
						$scope.propiedades = angular.fromJson(propiedades);
						alert("exito al consultar propidad")
					}else{
						
						alert("Advertencia", "No se han encontrado propiedad ingresadas","");
					}						
				}else{
					alert("Advertencia", "No se han encontrado propiedades ingresadas","");
				}
			}
			var error = function(response) {
				var reponsoObject = angular.fromJson(response.data);
				alert("Error", "Ha ocurrido un error al momento de listar propiedades",reponsoObject.descripcion);
			}			

			var sendpropiedad = $scope.propiedad;
			smartServices.sendPost(angular.toJson(sendpropiedad),hostSmart+context+methodConsultarPropiedad,exito,error);
			
		} catch (error) {
			$scope.mensaje("Error", "Ha ocurrido un error al momento de listar propiedad", error.message);
		}
	}	
	
	
	
	
	/**
	 * eliminar propiedad
	 */
	 $scope.eliminarPropiedad = function(m) {
	 	try {	
	 		var exito = function(response) {
	 			var reponsoObject = angular.fromJson(response.data);
	 			if(reponsoObject!=null){
	 				alert("Exito", " ",reponsoObject.descripcion);	
	 			}else{
	 				alert("Error", "Ha ocurrido un error al momento de eliminar la  propiedad","");
	 			}
	 		
	 		var error = function(response) {
	 			var reponsoObject = angular.fromJson(response.data);
	 			alert("Error", "Ha ocurrido un error al momento de eliminar la  propiedad",reponsoObject.descripcion);
	 		}			    

	 		};	
	 		var sendpropiedad = $scope.propiedad;
	 		smartServices.sendPost(angular.toJson(sendpropiedad),hostSmart+context+methodEliminarPropiedad,exito,error);	

	 	} catch (error) {
	 		alert("Error Ha ocurrido un error al momento de eliminar la  propiedad", error.message);
	 	}	
	 }

	/**
	 * Grabar propiedad
	 */
	$scope.grabarPropiedad = function() {
		try {
			if ($scope.sw != false) {
				var exito = function(response) {
					var reponsoObject = angular.fromJson(response.data);
					if (reponsoObject != null) {
						$scope.mensaje(" grabado con Exito  ",reponsoObject.descripcion);
					} else {
						alert("Error Ha ocurrido un error al momento de grabar la propiedad");
					}
					$scope.listarPropiedad();
				}
				var error = function(response) {
					var reponsoObject = angular.fromJson(response.data);
					alert("Error Ha ocurrido un error al momento de grabar la propiedad",reponsoObject.descripcion);
				}
				var sendObjectPropiedad = {
	
					scpropiedad : $scope.propiedad.scpropiedad,
					copropiedad : $scope.propiedad.copropiedad,
					dspropiedad : $scope.propiedad.dspropiedad,
					dsdireccion : $scope.propiedad.dsdireccion,
					fhingreso : $scope.propiedad.fhingreso,
					fhmodificacion : $scope.propiedad.fhmodificacion,
					fhretiro : $scope.propiedad.fhretiro,
					ciudad : $scope.ciudad,
					//cousuario : $scope.propiedad.cousuario,
					cousuario : $scope.usuario

				};
				
				smartServices.sendPost(angular.toJson(sendObjectpropiedad), hostSmart+ context + methodGrabarPropiedad, exito,error);
			}
		} catch (error) {
			alert("Error Ha ocurrido un error al momento de grabar la propiedad",error.message);
		}
	}
	
	
});

	