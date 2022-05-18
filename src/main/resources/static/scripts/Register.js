//Wae

function setUserData(){
	let fname = document.getElementById("fname").value;
	let lname = document.getElementById("lname").value;
	let mail = document.getElementById("mail").value;
	let password = document.getElementById("password").value;
	// get browser language - https://stackoverflow.com/questions/8199760/how-to-get-the-browser-language-using-javascript				
	let language = navigator.language || navigator.userLanguage;
	let address = document.getElementById("address").value;
	let zip = document.getElementById("zip").value;
	let city = document.getElementById("city").value;
	let country = document.getElementById("country").value;
	let username = document.getElementById("username").value;
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
        success: function () {			
        }, error: function(e) {
			console.log(e);			
	  	}
	});
}