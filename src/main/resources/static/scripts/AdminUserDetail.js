// LUM
function getUser() {
	let baseUrl = window.location.href; 
	let userId = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
	let userIdField = document.getElementById('userId');
	userIdField.value = userId;
	$.ajax({
		type: "GET",
		url: "/api/profileSettings/" + userId,	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {			
			let userId = document.getElementById('userId');
			userId.value = data.id;
			let fname = document.getElementById('fname');
			fname.value = data.firstname;
			let lname = document.getElementById('lname');
			lname.value = data.lastname;
			let address = document.getElementById('address');
			address.value = data.address;
			let zip = document.getElementById('zip');
			zip.value = data.postalCode;
			let city = document.getElementById('city');
			city.value = data.city;
			let country = document.getElementById('country');
			country.value = data.country;
			let mail = document.getElementById('mail');
			mail.value = data.email;
			let password = document.getElementById('password');
			password.value = data.password;
			let username = document.getElementById('username');
			username.value = data.username;	
			let role = document.getElementById('role');			
			role.value  = data.role;			
        }, 
        error: function(e) {
			console.log(e);
	  	}
	});
}

function save() {
	let userId = document.getElementById('userId').value;
	let fname = document.getElementById('fname').value;
	let lname = document.getElementById('lname').value;
	let address = document.getElementById('address').value;
	let zip = document.getElementById('zip').value;
	let city = document.getElementById('city').value;
	let country = document.getElementById('country').value;
	let mail = document.getElementById('mail').value;
	let username = document.getElementById('username').value;
	let role = document.getElementById('role').value;		
	
	debugger;
	$.ajax({
		type: 'PUT',
		url: '/api/profileSettings',
		data: JSON.stringify ({
			userId: userId,
			username: username,
			firstname: fname,
			lastname: lname,
	 		address: address,
			postalCode: zip,
			city: city,
			country: country,
			email: mail,
			password: '',
			role: role, 
			language: ''									
		}),	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {
			if(data) {
				window.location.href='/adminUsers';	
			} else {
				if(window.location.search.includes('eng')){
					alert('Something went wrong.');
				} else{
					alert('Etwas ist schief gelaufen');
				} 
			}
        }, 
        error: function(e) {
			console.log(e);
			if(window.location.search.includes('eng')){
				alert('Something went wrong.');
			} else{
				alert('Etwas ist schief gelaufen');
			}
	  	}
	});
}

function changePw() {
	let userId = document.getElementById('userId').value;
	let password = document.getElementById('password').value;
	
	$.ajax({
		type: 'PUT',
		url: '/api/changePassword/' + userId,
		data: JSON.stringify ({
			newPassword: password									
		}),	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {
			if(data) {
				window.location.href='/adminUsers';	
			} else {
				if(window.location.search.includes('eng')){
					alert('Something went wrong.');
				} else{
					alert('Etwas ist schief gelaufen');
				} 
			}
        }, 
        error: function(e) {
			console.log(e);
			if(window.location.search.includes('eng')){
				alert('Something went wrong.');
			} else{
				alert('Etwas ist schief gelaufen');
			}
	  	}
	});
}

window.onload = function() {
  getUser();
};