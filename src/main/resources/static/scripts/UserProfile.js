// lum
function getUserFullName() {
	const titleFullName = document.getElementById('titleFullName');	
	 $.ajax({
        type: "GET",
        url: "/api/user",
        success: function (data) {
			titleFullName.innerHTML = titleFullName.innerHTML.replace('fullname', data.firstname + ' ' + data.lastname);
        }, error: function(e) {
			console.log(e);
	  	}
	});
}

// lum
window.onload = function() {
  getUserFullName();
};