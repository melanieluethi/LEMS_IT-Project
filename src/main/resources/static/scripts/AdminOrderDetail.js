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
			shippingMethod.selectedIndex = data.order.shipping.shippingMethod;			
			let shippingCost = document.getElementById('shippingCost');	
			shippingCost.value = data.order.shipping.shippingCost;		
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
		inputDiscountField.value = d.product.discount;
		rowData4.appendChild(inputDiscountField);
		let rowData5 = document.createElement('td');
		let inputQuantityField = document.createElement("input");
		inputQuantityField.value = d.quantity;
		rowData5.appendChild(inputQuantityField);
						
		row.appendChild(rowData);
		row.appendChild(rowData2);
		row.appendChild(rowData3);
		row.appendChild(rowData4);
		row.appendChild(rowData5);

		tbody.appendChild(row);
	});
}

function save() {
	// TODO LUM: SAVE CHANGES	
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
	
	debugger;
	$.ajax({
		type: 'PUT',
		url: '/api/order',
		data: JSON.stringify ({
			orderId: orderId,
			userId: userId,
			orderItem: orderItems,
			shipping: {
					shippingId: shippingId,
					shippingMethod: shippingMethod,
					shippingCost: shippingCost
				},
			totalPrice: totalPrice							
		}),	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {
			if(data) {
				window.location.href='/adminOrders';	
			} else {
				if(language === 'eng'){
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