/**
 * @Descripcion: Controlador encargado de gestionar las vista de Ingreso al sistema 
 * @Author: SmartJungle S.A.S
 * @Date: 25-09-2019
 * @Date Modificación: 25-09-2019
 */

angular.module('smartApp').controller('ingresoAlSistemaCtrl',function($scope, smartServices) {

	
	/**
	 * @Descripcion: Carga Inicial de los envios al servidor
	 * @Author: SmartJungle S.A.S
	 * @Date: 25-09-2019
	 */
	$scope.onInit = function(){
		
		$scope.usuario = {
				"scusuario": null,
			   	"dsusuario": null,
			    "dscontrasena": null,
			    "dsemail": null
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
								$scope.ShowForm();
								$scope.formVisible = false;
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
	$scope.formVisible = true;
	$scope.formVisibility = false;
	$scope.ShowForm = function(){		
		$scope.formVisibility = true;
		
	}
	 
});
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
			$("#dsemail1").focus();
			create= false;
			return;
		}
		
		if($scope.usuario.dsemail == null || $scope.usuario.dsemail == ''){
			msg +='Correo electr&oacute;nico ';
			$("#dsemail2").focus();
			create= false;
			return;
		}	   
		
	   if(create){
		    
		   
		    var password = utf8_to_b64($scope.user.confirmPassword);
			$scope.user.password = password;
			$scope.user.confirmPassword = password;
			
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
/**
 * funciones del steps
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