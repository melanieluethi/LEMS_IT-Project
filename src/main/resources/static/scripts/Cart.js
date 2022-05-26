// lum
function getCart() {
	 $.ajax({
        type: 'GET',
        url: '/api/shoppingCart',
        success: function (data) {
			if(data) {
				let totalProductCost = 0;
				let cartId = document.getElementById('cartId');
				cartId.value = data.id;
				
				//get dropdown element
				let table = document.getElementById('cartTable');	
				let tbody = document.createElement('tbody');
				
				table.appendChild(tbody);
		
				data.orderItems.forEach(d => {
					// Creating and adding data to second row of the table
					let row = document.createElement('tr');
					let rowData = document.createElement('td');
					rowData.setAttribute('class', 'idColumn');
					rowData.innerHTML = d.product.productId;				
					let rowData2 = document.createElement('td');
					rowData2.innerHTML = d.product.productName;
					let rowData3 = document.createElement('td');
					rowData3.innerHTML = d.quantity;
					let rowData4 = document.createElement('td');
					rowData4.innerHTML = d.product.price * d.quantity;
								
					row.appendChild(rowData);
					row.appendChild(rowData2);
					row.appendChild(rowData3);
					row.appendChild(rowData4);
					
					tbody.appendChild(row);
					
					switch(d.product.productId) {
						case 1:
					    	let amountProduct1 = document.getElementById('amountProduct1');
					    	amountProduct1.value = d.quantity;
					    	break;
						case 2:
							let amountProduct2 = document.getElementById('amountProduct2');
					    	amountProduct2.value = d.quantity;
						    break;
						case 3:
					    	let amountProduct3 = document.getElementById('amountProduct3');
					    	amountProduct3.value = d.quantity;
					    	break;
						case 4:
							let amountProduct4 = document.getElementById('amountProduct4');
					    	amountProduct4.value = d.quantity;
						    break;
						default:
						    break;
					}
					
					totalProductCost = totalProductCost + (d.product.price * d.quantity);
				});
				
				let totalProductCostInputField = document.getElementById('totalProductCost');
				totalProductCostInputField.value = totalProductCost;
				
				getTransportCost(data.id);
			} else {
				
			}			
        }, error: function(e) {
			console.log(e);
	  	}
	});
}

function getTransportCost(cartId) {
	let shippingMethod = document.getElementById('shipping').value;
	let amountProduct1 = document.getElementById('amountProduct1').value;
	let amountProduct2 = document.getElementById('amountProduct2').value;
	let amountProduct3 = document.getElementById('amountProduct3').value;
	let amountProduct4 = document.getElementById('amountProduct4').value;
	 $.ajax({
        type: 'GET',
        url: '/api/transportCostCart?msgCartId=' + cartId + '&msgShippingMethod="' + shippingMethod 
        		+ '"&msgAmountProduct1=' + amountProduct1 + '&msgAmountProduct2=' + amountProduct2
        		 + '&msgAmountProduct3=' + amountProduct3 + '&msgAmountProduct4=' + amountProduct4,
		dataType: 'json',
		contentType: 'application/json', 
        success: function (data) {
			let expressCost = document.getElementById('expressCost');
			expressCost.value = data.transportCostExpress;
			let standardCost = document.getElementById('standardCost');
			standardCost.value = data.transportCostStandard;
			let packageCost = document.getElementById('packageCost');
			packageCost.value = data.priceForDelivery;
			
			if(!data.deliveryAvailable) {
				let packageOption = document.getElementById('shipping').options[2];
				packageOption.disabled = true
			}
			if(!data.deliveryExpressAvailable){
				let expressOption = document.getElementById('shipping').options[0];
				expressOption.disabled = true
				
			}
			
			// default selected value is standard	
			let selectedShippingMethod = document.getElementById('shipping');
			selectedShippingMethod.selectedIndex = 1;
			
			setShippingCost();			
        }, error: function(e) {
			console.log(e);
	  	}
	});
}

function setShippingCost() {
	let selectedShippingMethod = document.getElementById('shipping').value;
	let shippingCost = document.getElementById('shippingCost');
	switch(selectedShippingMethod) {
		case 'express':
			let expressCost = document.getElementById('expressCost').value;
			shippingCost.textContent = (Math.round(expressCost)).toFixed(2);
			break;
		case 'standard':
			let standardCost = document.getElementById('standardCost').value;
			shippingCost.textContent = (Math.round(standardCost)).toFixed(2);
			break;
		case 'package':
			let packageCost = document.getElementById('packageCost').value;
			shippingCost.textContent = (Math.round(packageCost)).toFixed(2);
			break;
		default:
			shippingCost.textContent = 0.0;
		    break;
	}
	totalCost(shippingCost.textContent);
}

function totalCost(shippingCost) {
	let totalProductCost = document.getElementById('totalProductCost').value;
	let totalCost = totalProductCost + shippingCost;
	let totalCostLabel = document.getElementById('totalCost');
	totalCostLabel.textContent = (Math.round(totalCost)).toFixed(2);
}

function saveOrder() {
	allert('click save order');
}

window.onload = function() {
	getCart();
};