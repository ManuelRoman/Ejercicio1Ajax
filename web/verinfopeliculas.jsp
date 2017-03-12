<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ejercicio 1 de Ajax</title>
<style type="text/css">
	body{
		text-align: center;
		background-color: #9edee5;
	}
	div{
		position: absolute;
		width: 30%;
		left: 34%;
	}
	#tabla{
		left: 41%;
		top: 25%;
		display: none;
	}
	table, th, td {
    	border: 1px solid black;
    	border-collapse: collapse;
	}
</style>
<script src="js/utilidades.js"></script>
<script>
	var obj;       
    function validar(evento){
        if((evento.keyCode>=48 && evento.keyCode<=57) || (evento.keyCode>=65 && evento.keyCode<=90)){
            obj=new ObjetoAJAX();
            var objForm=document.forms[0]; //el dom tiene un array con los formularios que contiene, tomamos el form completo
            obj.enviar(objForm.action,objForm.method,"procesaResultado",objForm);
            document.getElementById("tabla").style.display="none";          
        }
	}
	function procesaResultado(){
    	if(obj.estado()==200){ //200->OK
        	var resp=obj.respuestaTexto();
        	document.getElementById("titulo2").value=resp;
            var caja=document.getElementById("titulo");
           	//si se ha recibido una palabra de respuesta se introduce en el control y se seleccionan los caracteres aún no tecleados
           	if(resp!=""){                    
            	var inicioSel=caja.value.length;
            	caja.value=resp;
            	caja.selectionStart=inicioSel;
            	caja.selectionEnd=caja.value.length;
           	}
		}
        else{
           	alert(obj.textoEstado());
        }
	}
	function cambiar(){
		var valor = document.getElementById("titulo").value;
		document.getElementById("titulo2").value=valor;
	}
	function mostrarTabla() {
		objAJAX = new ObjetoAJAX();
		var objForm = document.forms[1];
		objAJAX.enviar(objForm.action,objForm.method,"procesaDatosPeliculas",objForm);
	}
	function procesaDatosPeliculas() {
		var listaPeliculas = eval ("("+objAJAX.respuestaTexto()+")");
		var pelicula ="";
		if(listaPeliculas.length>0){
        	for(var elm = 0;elm < listaPeliculas.length;elm++){
        		pelicula+="<tr><td>"+listaPeliculas[elm].titulo+"</td><td>"+listaPeliculas[elm].director+"</td><td>"+listaPeliculas[elm].genero+"</td><td>"+listaPeliculas[elm].fecha+"</td></tr>";
        	}
        } else {
        	pelicula+="<tr><td colspan='4'>No hay resultados</td></tr>";
        }
        document.getElementById("resultado").innerHTML=pelicula;
        document.getElementById("tabla").style.display="inline";
	}
</script>
</head>
<body>
	<div>
	<fieldset>
		<p>Introduzca un caŕacter para buscar</p>
		<form action="/ejercicio1ajax/mostrarinfopeliculas" method="post">
			<label for="titulo">Título: </label><input id="titulo" type="text" name="titulo" required="required" onkeyup="validar(event)" onchange="cambiar()">
		</form>
		<form action="/ejercicio1ajax/mostrarinfopeliculas" method="post">
			<input id="titulo2" type="hidden" name="titulo2">
			<input type="button" onclick="mostrarTabla()" name="ver" value="Ver"/>
		</form>
	</fieldset>
	</div>
	<div id="tabla">
	<table>
		<thead>
  			<tr>
    			<th>Título</th>
    			<th>Director</th>
    			<th>Género</th>
    			<th>Año</th>
  			</tr>
  		</thead>
  		<tbody id="resultado">
    	<tbody>
	</table>
	</div>
</body>
</html>