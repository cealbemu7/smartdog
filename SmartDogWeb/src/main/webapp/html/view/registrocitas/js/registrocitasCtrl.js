/**
 * @Descripcion: Controlador encargado de gestionar las vista de Ingreso al sistema 
 * @Author: SmartJungle S.A.S
 * @Date: 30-10-2019
 * @Date Modificación: 30-10-2019
 */

angular.module('smartApp').controller('registroCitasCtrl',function($scope, smartServices) {
	/**
	 * @Descripcion: Carga Inicial de los envios al servidor
	 * @Author: SmartJungle S.A.S
	 * @Date: 25-09-2019
	 */
	$scope.onInit = function(){			
		// TODO: Controlar si el usuario hace reloadpage, F5 o recarga la pagina y no termino el formulario de registro completo
		$scope.cargaInicial();
	}
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
						var tipoinmuebles  = angular.fromJson(response.data.listatipoinmueble);
						$.each(tipoinmuebles, function( index , tipoinmueble ) {
							$scope.tipoinmuebles.push(tipoinmueble);
						});
						
					}else{
						alert("Error al cargar inicial");
					}
					$scope.CargarInmobiliaria();
				}catch(e){
					alert("Error en la cargar inicial");
				}
			}
	
			var error = function(response){
				alert("Error en la carga inicial: ");
				var reponsoObject = console.log(angular.toJson(response));
			}
			$scope.maestro = {"comaestro" : null}
			$scope.maestro.comaestro = SmartMaestroTipoDocumento;
			var sendTipoDocumento = $scope.maestro;
			var sendobjectTipoDocumento = {
				maestro : sendTipoDocumento,
				//cousuario: $scope.cousuario
			};	
			
			$scope.maestro = {"comaestro" : null}
		    $scope.maestro.comaestro = SmartMaestroSexo;
		    var sendMaestroSexo = $scope.maestro;
		    var sendObjectSexo = {
				maestro : sendMaestroSexo,
				//cousuario: $scope.usuario
		    };
		    
			$scope.maestro = {"comaestro" : null}
		    $scope.maestro.comaestro = SmartMaestroTipoInmueble;
		    var sendMaestroTipoInmueble = $scope.maestro;
		    var sendObjectInmueble = {
				maestro : sendMaestroTipoInmueble,
				//cousuario: $scope.usuario
		    };
		    
		   var sendObject = {				   
				   tipodocumento: sendobjectTipoDocumento,
				   sexo: sendObjectSexo,
				   tipoinmueble: sendObjectInmueble,
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
					$scope.empresas = new Array();
					var empresas = angular.fromJson(response.data);
					$.each(empresas, function(index,empresa) {
						$scope.empresas.push(empresa);
					});										
				}else{
					alert("Advertencia", "No se han encontrado inmobiliaria ingresadas","");
				}
			}
			var error = function(response) {
				var reponsoObject = angular.fromJson(response.data);
				alert("Error", "Ha ocurrido un error al momento de listar inmobiliaria",reponsoObject.descripcion);
			}			

			var sendInmobiliaria = $scope.empresa;
			smartServices.sendPost(angular.toJson(sendInmobiliaria),hostSmart+context+methodConsultarEmpresa,exito,error);
			
		} catch (error) {
			alert("Error", "Ha ocurrido un error al momento de listar inmobiliaria", error.message);
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