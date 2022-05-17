//Wae

function setUserData(){
	const titleUserData = document.getElementById(titleUserData);
	$.ajax({
		type:"POST",
		url: "/api/createUser",
		data: JSON.stringify ({
			firstname: fname,
			lastname: lname,
			email: mail,
			password: password,
			language: language,
			address: address,
			postalCode: zip,
			city: city,
			country: country,
			role: role,
						
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