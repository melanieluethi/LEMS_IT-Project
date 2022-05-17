//Wae

function setUserData(){
debugger;
	const titleUserData = document.getElementById(titleUserData);
	let fname = document.getElementById("fname");
	let lname = document.getElementById("lname");
	let mail = document.getElementById("mail");
	let password = document.getElementById("password");
	let language = document.getElementById("language");
	let address = document.getElementById("address");
	let zip = document.getElementById("zip");
	let city = document.getElementById("city");
	let country = document.getElementById("country");
	let username = document.getElementById("username");
	$.ajax({
		type:"POST",
		url: "/api/createUser",
		data: JSON.stringify ({
			firstname: fname,
			lastname: lname,
			email: mail,
			username: username,
			password: password,
			language: language,
			address: address,
			postalCode: zip,
			city: city,
			country: country
						
		}),
		dataType: 'json',
    	contentType: 'application/json',
        success: function (data) {
	debugger;
			if(data.success === true) {
				if(language === "eng"){
					confirm("User successfully created");
				} else{
					confirm("Benutzer wurde erfolgreich erstellt");
				} 
			} else{
				if(language === "eng"){
					confirm("User was not created");
				} else{
					confirm("Benutzer wurde nicht erstellt");
				} 
			}
        }, error: function(e) {
			console.log(e);
	  	}
	});
}