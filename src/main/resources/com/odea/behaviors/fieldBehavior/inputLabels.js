/*************************************/
/*   Archivo asociado con form.css  */
/** ****************************** */

/*
 * Textbox inputs
 */

function onFocusTextboxInput(element) {
	try {
		if (element.getAttribute("class") == "clearInput") {
			element.value = "";
			element.setAttribute("class", "");
		}
	} catch (e) {
		alert(e.message);
	}
}

function onBlurTextboxInput(element, value) {
	try {
		if (element.value == "") {
			element.setAttribute("class", "clearInput");
			element.value = value;
		}
	} catch (e) {
		alert(e.message);
	}
}

/*
 * Password inputs
 */

function onFocusPasswordInput(element) {
	try {
		if (element.getAttribute("class") == "clearInput") {
			element.value = "";
			element.setAttribute("class", "");
			element.setAttribute('type', 'password');
		}
	} catch (e) {
		alert(e.message);
	}
}

function onBlurPasswordInput(element, value) {
	try {
		if (element.value == "") {
			element.setAttribute("class", "clearInput");
			element.value = value;
			element.setAttribute('type', 'text');
		}
	} catch (e) {
		alert(e.message);
	}
}
