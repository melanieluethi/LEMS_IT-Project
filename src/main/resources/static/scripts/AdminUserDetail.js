// LUM
function getUser() {
	let baseUrl = window.location.href; 
	let userId = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
	let userIdField = document.getElementById('userId');
	userIdField.value = userId;
	$.ajax({
		type: "GET",
		url: "/api/user/" + userId,	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {			
			let userId = document.getElementById('userId');
			userId.value = data.userId;
				
        }, 
        error: function(e) {
			console.log(e);
	  	}
	});
}

function save() {
	
	
	$.ajax({
		type: 'PUT',
		url: '/api/user',
		data: JSON.stringify ({
			userId: userId
									
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
			if(language === 'eng'){
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