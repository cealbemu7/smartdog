/**
 * @Descripcion: Controlador encargado de gestionar la vista de Empresa
 * @Author: Andres Brgs.
 * @Date: 25-09-2019
 * @Date Modificación: 26-09-2019
 */

angular
		.module('smartApp')
		.controller(
				'registroempresaCtrl',
				function($scope, smartServices) {

					/**
					 * @Descripcion: Carga Inicial de los envios al servidor
					 * @Author: SmartJungle S.A.S
					 * @Date: 26-09-2019
					 */
					$scope.sw = true;
					$scope.onInit = function() {

						$scope.departamento = {
							"scdepartamento" : null,
							"dsdepartamento" : null,
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

					}
					
					
					
					/**
					 * controlador que consultar departamentos
					 */
					$scope.consultarDepartamentoEmpresa = function() {
						try {
							
							var exito = function(response){
								try{
									if(response.data != null){
										$scope.departamentos = new Array();
										var departamentos  = angular.fromJson(response.data);
										$.each(departamentos, function( index , departamento ) {
											$scope.departamentos.push(departamento);
										});
									}else{
										$scope.mensaje("Informativo", "No se encontraron departamentos", "");
									}
									
								}catch(e){
									$scope.mensaje("Error", "Se produjo un error al momento de consultar los departamentos", e.message);
								}
							}	
							var error = function(response){
								$scope.mensaje("Error", "Se produjo un error al momento de listar los departamentos", response.data);
								console.log(angular.toJson(response));
							}

							$scope.pais = {"copais" : null}
							$scope.pais.copais = SmartMaestroPais;
							   var sendPais = $scope.pais;
							   var sendObject = {
								   pais : sendPais
						   }; 
							   
						   smartServices.sendPost(angular.toJson(sendObject),hostSmart+context+methodListarDepartamento,exito,error);		
							
						} catch (error) {
							$scope.mensaje("Error", "Se produjo un error al momento de listar los departamentos", error.message);
						}
					}
					
					/**
					 * controlador que consulta de las ciudades
					 */
					$scope.consultarCiudadEmpresa= function(scciudad) {
						try {
							if($scope.departamento != null){
								var exito = function(response){
									try{
										if(response.data != null){
											$scope.ciudades = new Array();
											var ciudades  = angular.fromJson(response.data);
											$.each(ciudades, function( index , ciudad ) {
												$scope.ciudades.push(ciudad);
											});
											$.each($scope.ciudades, function( index , ciudad ) {
												if(ciudad.scciudad == scciudad){
													$scope.ciudad = ciudad;
												}
											})
										}else{
											$scope.mensaje("Informativo", "No se encontraron ciudad", "");
										}
									}catch(e){
										$scope.mensaje("Error", "Se produjo un error al momento de consultar las ciudades", e.message);
									}
								}		
								var error = function(response){
									$scope.mensaje("Error", "Se produjo un error al momento de listar las ciudades", response.data);
									console.log(angular.toJson(response));
								}			
							   var sendObject = {
									   departamento : $scope.departamento
							   }; 
							  
						   	  smartServices.sendPost(angular.toJson(sendObject),hostSmart+context+methodListarCiudad,exito,error);		
							}
							
						} catch (error) {
							$scope.mensaje("Error", "Se produjo un error al momento de listar las ciudades", error.message);
						}
					}
					

					/**
					 * este metodo permite consultar empresas
					 * 
					 */
					$scope.consultarEmpresa = function() {

						var exito = function(response) {
							if (response.data != null) {
								$scope.empresa = new Array();
								var empresa = angular.fromJson(response.data);
								if (empresa.length > 0) {
									$
											.each(
													empresa,
													function(index, empresa) {
														$scope.empresa.nitempresa = empresa.nitempresa;
														alert("exito nit correcto");
													});
								} else {
									alert("No se encontro empresa con ese nit");

								}

								var error = function(response) {
									var reponsoObject = angular
											.fromJson(response.data);
								}

								var sendObject = {
									nitempresa : $scope.empresa.nitempresa,
								};

								alert(sendObject.nitempresa);

								alert(angular.toJson(sendObject));

								smartServices.sendPost(angular
										.toJson(sendObject), hostSmart
										+ context + methodConsultarEmpresa,
										exito, error);

							}
						}

					}

					/**
					 * Grabar empresa
					 */
					$scope.grabarEmpresa = function() {
						try {
							$scope.validarcampos();
							if ($scope.sw != false) {
								var exito = function(response) {
									var reponsoObject = angular
											.fromJson(response.data);
									if (reponsoObject != null) {
										$scope.mensaje("Exito", " ",
												reponsoObject.descripcion);
										// $scope.limpiar();
										$scope.consultarEmpresa();
									} else {
										$scope
												.mensaje(
														"Error",
														"Ha ocurrido un error al momento de grabar la empresa",
														"");
									}
								}
								var error = function(response) {
									var reponsoObject = angular
											.fromJson(response.data);
									$scope
											.mensaje(
													"Error",
													"Ha ocurrido un error al momento de grabar la empresa",
													reponsoObject.descripcion);
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

								};
								smartServices.sendPost(angular
										.toJson(sendObjectEmpresa), hostSmart
										+ context + methodGrabarEmpresa, exito,
										error);
							}
						} catch (error) {
							$scope
									.mensaje(
											"Error",
											"Ha ocurrido un error al momento de grabar la empresa",
											error.message);
						}
					}

				});
