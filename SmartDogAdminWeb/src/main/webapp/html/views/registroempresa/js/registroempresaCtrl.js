/**
 * @Descripcion: Controlador encargado de gestionar la vista de Empresa
 * @Author: Andres Brgs.
 * @Date: 25-09-2019
 * @Date Modificación: 26-09-2019
 */

angular.module('smartApp').controller('registroempresaCtrl',function($scope, smartServices) {

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
		$scope.empresa = {
			"scempresa" : null,
			"nitempresa" : null,
			"dsrazonsocial" : null,
			"cousuario" : null,
			"fhingreso" : null,
			"fhmodificacion" : null,
			"fhretiro" : null,
			"ciudad" : $scope.ciudad,
			"dsdireccion" : null,
			"dstelefono" : null,
			"dsemail" : null,
		}
		// $scope.usuario = getUserSession().user;
		$scope.usuario = "andres";
		
		$scope.listarDepartamento();
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
	
	
	
	
	$scope.listarEmpresa= function() {
		try {
			var exito = function(response) {
				if(response.data != null){
					var empresas = response.data;
					if (empresas.length>0){
						
						$scope.empresas = new Array();
						$scope.empresas = angular.fromJson(empresas);
						alert("exito al consultar empresa")
					}else{
						
						alert("Advertencia", "No se han encontrado empresa ingresadas","");
					}						
				}else{
					alert("Advertencia", "No se han encontrado empresa ingresadas","");
				}
			}
			var error = function(response) {
				var reponsoObject = angular.fromJson(response.data);
				alert("Error", "Ha ocurrido un error al momento de listar empresa",reponsoObject.descripcion);
			}			

			var sendEmpresa = $scope.empresa;
			smartServices.sendPost(angular.toJson(sendEmpresa),hostSmart+context+methodConsultarEmpresa,exito,error);
			
		} catch (error) {
			$scope.mensaje("Error", "Ha ocurrido un error al momento de listar empresa", error.message);
		}
	}	
	
	
	
	
	/**
	 * eliminar empresa
	 */
	 $scope.eliminarEmpresa = function(m) {
	 	try {	
	 		var exito = function(response) {
	 			var reponsoObject = angular.fromJson(response.data);
	 			if(reponsoObject!=null){
	 				alert("Exito", " ",reponsoObject.descripcion);	
	 			}else{
	 				alert("Error", "Ha ocurrido un error al momento de eliminar la  empresa","");
	 			}
	 		
	 		var error = function(response) {
	 			var reponsoObject = angular.fromJson(response.data);
	 			alert("Error", "Ha ocurrido un error al momento de eliminar la  empresa",reponsoObject.descripcion);
	 		}			    

	 		};	
	 		var sendEmpresa = $scope.empresa;
	 		smartServices.sendPost(angular.toJson(sendEmpresa),hostSmart+context+methodEliminarEmpresa,exito,error);	

	 	} catch (error) {
	 		alert("Error Ha ocurrido un error al momento de eliminar la  empresa", error.message);
	 	}	
	 }

	/**
	 * Grabar empresa
	 */
	$scope.grabarEmpresa = function() {
		try {
			if ($scope.sw != false) {
				var exito = function(response) {
					var reponsoObject = angular.fromJson(response.data);
					if (reponsoObject != null) {
						$scope.mensaje(" grabado con Exito  ",reponsoObject.descripcion);
					} else {
						alert("Error Ha ocurrido un error al momento de grabar la empresa");
					}
					$scope.listarEmpresa();
				}
				var error = function(response) {
					var reponsoObject = angular.fromJson(response.data);
					alert("Error Ha ocurrido un error al momento de grabar la empresa",reponsoObject.descripcion);
				}
				var sendObjectEmpresa = {
					scempresa : $scope.empresa.scempresa,
					nitempresa : $scope.empresa.nitempresa,
					dsrazonsocial : $scope.empresa.dsrazonsocial,
					cousuario : $scope.empresa.cousuario,
					fhingreso : $scope.empresa.fhingreso,
					fhmodificacion : $scope.empresa.fhmodificacion,
					fhretiro : $scope.empresa.fhretiro,
					ciudad : $scope.ciudad,
					dsdireccion : $scope.empresa.dsdireccion,
					dstelefono : $scope.empresa.dstelefono,
					dsemail : $scope.empresa.dsemail,
					cousuario : $scope.usuario

				};
				
				smartServices.sendPost(angular.toJson(sendObjectEmpresa), hostSmart+ context + methodGrabarEmpresa, exito,error);
			}
		} catch (error) {
			alert("Error Ha ocurrido un error al momento de grabar la empresa",error.message);
		}
	}
});