// LUM
function getProduct() {
	debugger;
	 $.ajax({
		type: "GET",
		 url: "/api/product",
		success: function (data) {
				
        }, error: function(e) {
			console.log(e);
	  	}
	});
}

function save() {
	
	window.location.href='/adminProducts';
}

window.onload = function() {
  getProduct();
};