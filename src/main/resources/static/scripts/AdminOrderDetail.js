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
			let userId = document.getElementById('userId');
			userId.value = data.order.user.userId;
			let username = document.getElementById('username');
			username.value = data.order.user.username;
			let firstname = document.getElementById('firstname');
			firstname.value = data.order.user.firstname;
			let lastname = document.getElementById('lastname');
			lastname.value = data.order.user.lastname;
			let address = document.getElementById('address');
			address.value = data.order.user.address;
			let postalCode = document.getElementById('postalCode');
			postalCode.value = data.order.user.postalCode;
			let city = document.getElementById('city');
			city.value = data.order.user.city;
			let country = document.getElementById('country');
			country.value = data.order.user.country;
			
			fillOrderItemTable(data);
					
			let shippingId = document.getElementById('shippingId');	
			shippingId.value = data.order.shipping.shippingId;	
			let shippingMethod = document.getElementById('shippingMethod');		
			let deliveryAvailable = document.getElementById('deliveryAvailable');
			deliveryAvailable.value = data.order.deliveryAvailable;
			let deliveryExpressAvailable = document.getElementById('deliveryExpressAvailable');
			deliveryExpressAvailable.value = data.order.deliveryExpressAvailable;
			if(!data.order.deliveryAvailable) {
				let packageOption = shippingMethod.options[2];
				packageOption.disabled = true
			}
			if(!data.order.deliveryExpressAvailable){
				let expressOption = shippingMethod.options[0];
				expressOption.disabled = true;		
			}
			
			let expressCost = document.getElementById('expressCost');
			expressCost.value = data.order.shipping.shippingExpressCost;
			let standardCost = document.getElementById('standardCost');
			standardCost.value = data.order.shipping.shippingStandardCost;
			let packageCost = document.getElementById('packageCost');
			packageCost.value = data.order.shipping.shippingPackageCost;
				
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
		let inputDiscountField = document.createElement("input");	
		inputDiscountField.setAttribute('onchange', 'changeTotalPrice()');
		inputDiscountField.value = d.orderDiscount;
		rowData4.appendChild(inputDiscountField);
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

function setShippingCost() {
	let shippingMethod = document.getElementById('shippingMethod').value;		
	let shippingCost = document.getElementById('shippingCost');	
	switch(shippingMethod) {
		case 'package':
			shippingMethod.selectedIndex = 2;	
		 	shippingCost.value = document.getElementById('packageCost').value;					 	
		  	break;
		case 'standard':
			shippingMethod.selectedIndex = 1;
			shippingCost.value = document.getElementById('standardCost').value;	
			break;
		case 'express':
			shippingMethod.selectedIndex = 0;
		   	shippingCost.value = document.getElementById('expressCost').value;	
		   	break;
		default:
		    break;
	}	
	changeTotalPrice();
}

function changeTotalPrice() {
	let totalPriceField = document.getElementById('totalPrice');
	let shippingCost = document.getElementById('shippingCost').value;
	let totalPrice = 0;
	
	let orderItemTable = document.getElementById('orderItemTable');
	for (let i = 1; i < orderItemTable.rows.length; i++) {
		let price = orderItemTable.rows[i].cells[2].innerHTML;
		let discount = orderItemTable.rows[i].cells[3].firstChild.value;
		let quantity = orderItemTable.rows[i].cells[4].innerHTML;
		totalPrice = totalPrice + (price * ((100 - discount) / 100)) * quantity;
	}
	let totalCost = parseFloat(totalPrice) + parseFloat(shippingCost);
	totalPriceField.value = (Math.round(totalCost)).toFixed(2);
}

function save() {	
	let orderId = document.getElementById('orderId').value;
	let userId = document.getElementById('userId').value;
	let shippingId = document.getElementById('shippingId').value;
	let shippingMethod = document.getElementById('shippingMethod').value;
	let shippingCost = document.getElementById('shippingCost').value;			
	let totalPrice = document.getElementById('totalPrice').value;
	
	// https://www.tutorialspoint.com/convert-html-table-to-array-in-javascript
	let orderItems = [];
   	$("#orderItemTable tr").each(function() {
	    let rowDataArray = [];
	    let actualData = $(this).find('td');
	    if (actualData.length > 0) {
	    	actualData.each(function() {
				if($(this).text() !== '') {
					rowDataArray.push($(this).text());
				} else {
					rowDataArray.push($(this).children().get(0).value);
				}
		     });
		     orderItems.push(rowDataArray);
	     }
   	});
	
	$.ajax({
		type: 'PUT',
		url: '/api/order',
		data: JSON.stringify ({
			orderId: orderId,
			userId: userId,
			orderItems: orderItems,
			shippingId: shippingId,
			shippingMethod: shippingMethod,
			shippingCost: shippingCost,
			totalPrice: totalPrice							
		}),	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {
			if(data) {
				window.location.href='/adminOrders';	
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
			if(language === 'eng'){
				alert('Something went wrong.');
			} else{
				alert('Etwas ist schief gelaufen');
			}
	  	}
	});
}

window.onload = function() {
  getOrder();
};