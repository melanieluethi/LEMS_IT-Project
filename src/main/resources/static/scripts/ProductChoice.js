//SCL
function getProduct() {

	var element = document.getElementById('products');
    var name = element.options[element.selectedIndex].text;
    var wert = element.options[element.selectedIndex].value;
    alert('Wert: '+wert+' - Name: '+name);
    
    
	//const titleFullName = document.getElementById('titleFullName');	
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
  getProduct();
};