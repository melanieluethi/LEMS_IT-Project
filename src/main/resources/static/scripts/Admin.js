// SCL
function getTableProducts() {
	
    //holt eine Liste von allen Produkten
	
	 $.ajax({
		type: "GET",
		 url: "/api/products",
		 //Data ist eine Liste der Produkte
		success: function (data) {
			//get dropdown element
			var productname = document.getElementById('products');	
			

				$.each(data, function (key, val) {
 				$("<tr><td>" + key + "</td><td>" + val + "</td</tr>").appendTo("#products") 
});
			
        	
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
	
}

window.onload = function() {
  getProducts();
};
