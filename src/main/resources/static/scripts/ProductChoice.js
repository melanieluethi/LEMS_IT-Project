// SCL
function getProducts() {
	
    //holt eine Liste von allen Produkten
	
	 $.ajax({
		type: "GET",
		 url: "/api/products",
		 //Data ist eine Liste der Produkte
		success: function (data) {
			//get dropdown element
			var productname = document.getElementById('products');	
			for (var i = 0; i < data.length; ++i) {
    			// Append the element to the end of Array list
    			productname[productname.length] = new Option(data[i].productName, productname[i]);
			}
        	
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
}

// lum
window.onload = function() {
  getProducts();
};