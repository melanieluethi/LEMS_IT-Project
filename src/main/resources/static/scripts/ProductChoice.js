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


function getProductsTable() {
	
    //holt eine Liste von allen Produkten
	
	 $.ajax({
		type: "GET",
		 url: "/api/products",
		 //Data ist eine Liste der Produkte
		success: function (data) {
			//get table element
			var table = document.getElementById('productTable');	
			var tr = table.insertRow(-1);
             
            for (var i = 0; i < cols.length; i++) {
                 
                // Create the table header th element
                var theader = document.createElement("th");
                theader.innerHTML = cols[i];
                 
                // Append columnName to the table row
                tr.appendChild(theader);
            }  
            
            // Adding the data to the table
            for (var i = 0; i < list.length; i++) {
                 
                // Create a new row
                trow = table.insertRow(-1);
                for (var j = 0; j < cols.length; j++) {
                    var cell = trow.insertCell(-1);
                     
                    // Inserting the cell at particular place
                    cell.innerHTML = list[i][cols[j]];
                }
                }  		
			
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
	
}