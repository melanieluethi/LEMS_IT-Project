// lum
function setLanguage(userLang){
	$.ajax({
        type: "PUT",
		url: "/api/changeLanguage",
		data: JSON.stringify({ 
			"language": userLang 
		}),
		dataType: 'json',
    	contentType: 'application/json',
        success: function (data) {
			if(data === true) {
				window.location.replace('?lang=' + userLang);	
			} 
        }, error: function(e) {
			console.log(e);
	  	}
	});
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
			if(!window.location.search.includes(userLang)) {
				window.location.replace('?lang=' + userLang);	
			}
        }, error: function(e) {
			console.log(e);
	  	}
	});
}

(function () {
    getLanguage();
})();