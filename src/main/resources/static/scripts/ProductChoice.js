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
		url: "/api/productByName/" + selectedProductName,	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {
			let productId = document.getElementById('productId');
			productId.value = data.id;
			
			let productname = document.getElementById('productname');
			productname.value = data.productName;
			
			let description = document.getElementById('description');
			description.value = data.description;
			
			let price = document.getElementById('price');
			price.value = data.price;
			
			let productImg = document.getElementById('productImg');
			productImg.src = data.productImg;
			
			let discount = document.getElementById('discount');
			discount.value = data.discount;	
        }, 
        error: function(e) {
			console.log(e);
	  	}
	  	});
}

// lum
function addToCart() {
	let productId = document.getElementById('productId').value;
	let quantity = document.getElementById('quantity').value;	
	let price = document.getElementById('price').value;
	
	$.ajax({
		type: 'POST',
		url: '/api/addProductToCard',
		data: JSON.stringify ({
			productId: productId,
			quantity: quantity,
			price: price									
		}),	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {
			if(data) {
				if(window.location.search.includes('eng')){
					alert('Added to cart');
				} else{
					alert('Wurde dem Warenkorb hinzugefügt');
				} 
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