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
			getSpecificProduct();
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
	
}

window.onload = function() {
  getProducts();
};

function getSpecificProduct() {	
    let selectedProductName = document.getElementById('products').value;
	 $.ajax({
		type: "GET",
		url: "/api/product/" + selectedProductName,	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {
			let productname = document.getElementById('productname');
			productname.textContent = data.productName;
			
			let description = document.getElementById('description');
			description.textContent = data.description;
			
			let productImg = document.getElementById('productImg');
			productImg.setIcon = data.productImg;
			
			let discount = document.getElementById('discount');
			discount.textContent = data.discount;
			
			
			
        }, 
        error: function(e) {
			console.log(e);
	  	}
	  	});
	
}
