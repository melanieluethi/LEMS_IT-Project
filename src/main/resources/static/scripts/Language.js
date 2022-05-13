// wae angelehnt an https://stackoverflow.com/questions/46008760/how-to-build-multiple-language-website-using-pure-html-js-jquery
function setLanguage(){
	debugger;
	window.location.replace('?lang=' + userLang);
}

// lum
function getLanguage(){
	$.ajax({
        type: "GET",
		url: "/api/language",
        success: function (data) {
			let userLang;
			if(data.successful === true) {
				userLang = data.language;
			} else {
				// get browser language - https://stackoverflow.com/questions/8199760/how-to-get-the-browser-language-using-javascript				
				userLang = navigator.language || navigator.userLanguage; 
			}
			if(!window.location.href.includes(userLang)) {
				window.location.replace('?lang=' + userLang);	
			}
        }, error: function(e) {
			console.log(e);
	  	}
	});
}

// lum
window.onload = function(){
	getLanguage();
}