// LUM
function getOrder() {
	let baseUrl = window.location.href; 
	let orderId = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
	let orderIdField = document.getElementById('orderId');
	orderIdField.value = orderId;
	$.ajax({
		type: "GET",
		url: "/api/order/" + orderId,	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {		
			let orderId = document.getElementById('orderId');
			orderId.value = data.order.orderId;
			
			fillOrderItemTable(data);
					
			let shippingId = document.getElementById('shippingId');	
			shippingId.value = data.order.shipping.shippingId;	
			
			let shippingMethod = document.getElementById('shippingMethod');		
			if(!data.order.deliveryAvailable) {
				let packageOption = shippingMethod.options[2];
				packageOption.disabled = true
			}
			if(!data.order.deliveryExpressAvailable){
				let expressOption = shippingMethod.options[0];
				expressOption.disabled = true;
			}
				
			let shippingCost = document.getElementById('shippingCost');	
			switch(data.order.shipping.shippingMethod) {
				case 'package':
					shippingMethod.selectedIndex = 2;	
				 	shippingCost.value = data.order.shipping.shippingPackageCost;				 	
				  	break;
				case 'standard':
					shippingMethod.selectedIndex = 1;
					shippingCost.value = data.order.shipping.shippingStandardCost;	
					break;
				case 'express':
					shippingMethod.selectedIndex = 0;
				   	shippingCost.value = data.order.shipping.shippingExpressCost;	
				   	break;
				default:
				    break;
			}	
			let totalPrice = document.getElementById('totalPrice');
			totalPrice.value = data.order.totalPrice;		
        }, 
        error: function(e) {
			console.log(e);
	  	}
	});
}

function fillOrderItemTable(data) {
	let table = document.getElementById('orderItemTable');	
	let tbody = document.createElement('tbody');
			
	table.appendChild(tbody);
			
	data.order.orderItems.forEach(d => {
		// Creating and adding data to second row of the table
		let row = document.createElement('tr');
		let rowData = document.createElement('td');
		rowData.setAttribute('class', 'idColumn');
		rowData.innerHTML = d.orderItemId;				
		let rowData2 = document.createElement('td');
		rowData2.innerHTML = d.product.productName;
		let rowData3 = document.createElement('td');
		rowData3.innerHTML = d.product.price;
		let rowData4 = document.createElement('td');
		rowData4.innerHTML = d.product.discount;
		let rowData5 = document.createElement('td');
		rowData5.innerHTML = d.quantity;
								
		row.appendChild(rowData);
		row.appendChild(rowData2);
		row.appendChild(rowData3);
		row.appendChild(rowData4);
		row.appendChild(rowData5);

		tbody.appendChild(row);
	});
}

function back() {
	let baseUrl = window.location.href;
	let lang = 'de';
	if(baseUrl.includes('eng')){
		lang = 'eng';
	}		
	window.location.href='/userManagement?lang=' + lang; 
}

window.onload = function() {
  getOrder();
};