var dataView;
var grid;
var options = {	editable: true,
				enableCellNavigation: true,
			    explicitInitialization: true};

var columns = ${columnsJson};
var data = ${dataJson};

//Filtrado
var columnFilters = {};

function prepareFilter() {
	//Hace que se muestre la fila superior de la tabla si hay alguna columna con el atributo searchable = true.	
	var showHeader = false;
	
	for ( var i = 0; i < columns.length; i++) {
		if (columns[i].searchable) {
			showHeader = true;
		}
	}

	options.showHeaderRow = showHeader;
}

function filter(item) {
	
	for ( var columnId in columnFilters) {
		
		if (columnId !== undefined && columnFilters[columnId] !== "") {
			var c = grid.getColumns()[grid.getColumnIndex(columnId)];
			
			//itemValue -> Contenido del item en ese campo
			//searchText -> Texto ingresado para la busqueda
			//La condicion es tipo LIKE %texto%

			var itemValue = item[c.field].toLowerCase();
			var searchText = columnFilters[columnId].toLowerCase(); 
			
			if (itemValue.indexOf(searchText) == -1) {
				return false;
			}
		}
		
	}
	return true;
}




var checkboxSelector = new Slick.CheckboxSelectColumn({
		cssClass: "slick-cell-checkboxsel"
	});

/*Agrupamiento. 
 * El atributo 'getter' define la agrupacion. (Es el atributo del JSON)*/
function groupData() {
	
	if ('${groupingAttribute}' != 'null') {
		dataView.setGrouping({
			getter: '${groupingAttribute}'
		});		
	}
	
}



function setActions() {
	
	grid.onCellChange.subscribe(function (e, args) {
		var item = args.item;
	    var column = args.cell;       
	    var row = args.row;
	    var field = grid.getColumns()[args.cell].field;
	    var value = args.item[field];
	    var idAttribute = '${idAttribute}';
	    
	    var parameters = {};
	    parameters[field] = value;
	    parameters[idAttribute] = item[idAttribute];
	    
	    Wicket.Ajax.ajax({"u":"${callbackUrl}", "c":"${slickGridId}", "wr":false, "ep":parameters});
	});
	
}



//Inicializacion de la tabla
$(function() {
	
	prepareFilter();
	
	/*Esta clase funciona como plugin dandole los event handlers para expandir y colapsar agrupaciones*/
	var groupItemMetadataProvider = new Slick.Data.GroupItemMetadataProvider();
	  	
	/*Generacion de dataView (contenido) y Grid*/
  	dataView = new Slick.Data.DataView({groupItemMetadataProvider: groupItemMetadataProvider});
 	grid = new Slick.Grid('${selector}', dataView, columns, options);

  	/*Siempre es necesario registrar plugins*/
  	grid.registerPlugin(groupItemMetadataProvider);
  	grid.registerPlugin(checkboxSelector);
  	
  	
  	grid.setSelectionModel(new Slick.CellSelectionModel());
		
  	
	dataView.onRowCountChanged.subscribe(function (e, args) {
		grid.updateRowCount();
	    grid.render();
	});
			
  	dataView.onRowsChanged.subscribe(function (e, args) {
    	grid.invalidateRows(args.rows);
    	grid.render();
  	});
	
  	setActions();
	
  	$(grid.getHeaderRow()).delegate(":input", "change keyup", function (e) {
        var columnId = $(this).data("columnId");
        if (columnId != null) {
          columnFilters[columnId] = $.trim($(this).val());
          dataView.refresh();
        }
      });

      grid.onHeaderRowCellRendered.subscribe(function(e, args) {
    	  var index = columns.indexOf(args.column);
    	  var aColumn = columns[index];
    	  
    	  if (aColumn.searchable) {  
	          $(args.node).empty();
	          $("<input type='text'>")
	             .data("columnId", args.column.id)
	             .val(columnFilters[args.column.id])
	             .appendTo(args.node);
    	  }
      });
  	
    grid.init();
	dataView.beginUpdate();
	dataView.setItems(data);
	dataView.setFilter(filter);
	groupData();
	dataView.endUpdate();
	
});
