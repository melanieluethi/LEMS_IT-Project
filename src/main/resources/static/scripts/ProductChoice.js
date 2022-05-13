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

window.onload = function() {
  getProducts();
};

function getProductID() {
	
    //holt eine Liste von allen Produkten
	
	 $.ajax({
		type: "GET",
		 url: "/api/product/{productId}",
		 data:{
			"productId" : VarA
					
		},
		cache: false,
		type: "POST",
		
		success: function (data) {
			   	
        }, 
        error: function(e) {
			console.log(e);
	  	}
	  	});
	
}

function getProductDescription() {
	
    //holt eine Liste von allen Produkten
	
	 $.ajax({
		type: "GET",
		 url: "/api/product",
		 //Data ist eine Liste der Produkte
		success: function (data) {
			//get dropdown element
			var productname = document.getElementById('products');	
			var description = productname.options[productname.selectedIndex].getAttribute('description')
			
        	
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
	
}