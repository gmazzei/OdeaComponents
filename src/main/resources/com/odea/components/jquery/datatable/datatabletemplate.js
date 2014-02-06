var oTable;

defineJQueryFunctions = function() {
	
	$.fn.dataTableExt.oApi.fnReloadAjax = function ( oSettings, sNewSource, fnCallback, bStandingRedraw ) {
        if ( sNewSource !== undefined && sNewSource !== null ) {
            oSettings.sAjaxSource = sNewSource;
        }

        // Server-side processing should just call fnDraw
        if ( oSettings.oFeatures.bServerSide ) {
            this.fnDraw();
            return;
        }

        this.oApi._fnProcessingDisplay( oSettings, true );
        var that = this;
        var iStart = oSettings._iDisplayStart;
        var aData = [];

        this.oApi._fnServerParams( oSettings, aData );

        oSettings.fnServerData.call( oSettings.oInstance, oSettings.sAjaxSource, aData, function(json) {
            /* Clear the old information from the table */
            that.oApi._fnClearTable( oSettings );

            /* Got the data - add it to the table */
            var aData =  (oSettings.sAjaxDataProp !== "") ?
                that.oApi._fnGetObjectDataFn( oSettings.sAjaxDataProp )( json ) : json;


            for ( var i=0 ; i<aData.length ; i++ ) {
                that.oApi._fnAddData( oSettings, aData[i] );
            }

            oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();

            that.fnDraw();

            if ( bStandingRedraw === true ) {
                oSettings._iDisplayStart = iStart;
                that.oApi._fnCalculateEnd( oSettings );
                that.fnDraw( false );
            }

            that.oApi._fnProcessingDisplay( oSettings, false );

            /* Callback user function - for event handlers etc */
            if ( typeof fnCallback == 'function' && fnCallback !== null ) {
                fnCallback( oSettings );
            }
        }, oSettings );
    };

}


toUrlParameter = function(value) {
	return value.replace("%","%25").replace(" ","%20").replace("<","%3C").replace(">","%3E").replace("#","%23").replace("{","%7B").replace("}","%7D").replace("|","%7C").replace("\\","%5C").replace("^","%5E").replace("~","%7E").replace("[","%5B").replace("]","%5D").replace("`","%60").replace(";","%3B").replace("/","%2F").replace("?","%3F").replace(":","%3A").replace("@","%40").replace("=","%3D").replace("&","%26").replace("$","%24");
}


borrar = function(index, elemento, mensaje, requestParameter) {	

	if(confirm(mensaje)) {
		var ep = {requestParameter:""};
		ep[requestParameter] = JSON.stringify(elemento);
		
		Wicket.Ajax.ajax({"u":"${callbackUrl}", "c":"${dataTableId}", "wr":false, "ep":ep});
				
		oTable.fnDeleteRow(index, function (dtSettings, row) {
            console.log('Fila borrada');
        }, true);
	    
		recargarTabla();
	}
}

recargarTabla = function() {
	setTimeout(function(){oTable.fnReloadAjax(null,null,true);}, 500);
}

modificar = function(paginaReceptora, elemento, urlParameterName) {
	var json = JSON.stringify(elemento);
	var jsonUrl = toUrlParameter(json);
	self.location = paginaReceptora + "?" + urlParameterName +"=" + jsonUrl;
}

modificarPopUp = function(index, elemento,requestParameter) {
	var ep = {requestParameter:""};
	ep[requestParameter] = JSON.stringify(elemento);
	
	Wicket.Ajax.ajax({"u":"${callbackUrl}", "c":"${dataTableId}","wr":true, "ep":ep});
}


cambiarEstado = function(elemento, estado, elementParameterName, stateParameterName) {
	
	var ep = {elementParameterName:"", stateParameterName:""};
	ep[elementParameterName] = JSON.stringify(elemento);
	ep[stateParameterName] = !estado;
	
	Wicket.Ajax.ajax({"u":"${callbackUrl}", "c":"${dataTableId}", "wr": false, "ep": ep});
	
	setTimeout(function(){oTable.fnReloadAjax(null,null,true);}, 100);
}



initTable = function () {
	
	defineJQueryFunctions();
	
    oTable = $('${selector}').dataTable({
        "oLanguage": ${language},
        "sDom": '${complements}',
        "bPaginate": ${pagination},
        "bSortClasses": false,
        "bFilter": true ,
        "bProcessing": true,
        "sAjaxSource": '${callbackUrl}',
        "bDeferRender": true,
        "bDestroy": true,
        "bRetrieve": true,
        "aoColumnDefs": ${columnasAccion},
        "aoColumns": ${columns}
    });
   
    
    for ( var i = 0; i < ${columnasAccion}.length; i++) {
    	
    	if (!(${columnasAccion}[i]).bVisible) {
    		var indice = ${columnasAccion}[i].aTargets;
    		oTable.fnSetColumnVis(indice, false);
		}
    	
	}
    
}


$(document).ready(initTable);