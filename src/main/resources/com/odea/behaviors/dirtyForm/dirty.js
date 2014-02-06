window.setTimeout(agregar,10);


function agregar(){
	var a = $("form");
	a.find(':input').each(function(){
		  $(this).change(function(){
			$('label[for="' + $(this).attr("name") + '"]').addClass("dirty");
		    $(this).addClass("dirty");
		  });
		})

}