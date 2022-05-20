function getShipping() {
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

window.onload = function() {
  getShipping();
};