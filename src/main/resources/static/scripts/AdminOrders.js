// LUM
// https://www.delftstack.com/de/howto/javascript/create-table-javascript/
function getOrders() {
	 $.ajax({
		type: "GET",
		 url: "/api/orders",
		success: function (data) {
			//get dropdown element
			let table = document.getElementById('adminOrderTable');	
			let tbody = document.createElement('tbody');
			
			table.appendChild(tbody);
			
			data.forEach(d => {
				// Creating and adding data to second row of the table
				let row = document.createElement('tr');
				let rowData = document.createElement('td');
				rowData.innerHTML = d.user.username;
				let rowData2 = document.createElement('td');
				rowData2.innerHTML = d.order.orderId;
				let rowData3 = document.createElement('td');
				rowData3.innerHTML = d.totalPrice;
				
				row.appendChild(rowData);
				row.appendChild(rowData2);
				row.appendChild(rowData3);
				tbody.appendChild(row);
			});	
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
}

window.onload = function() {
  getOrders();
};
