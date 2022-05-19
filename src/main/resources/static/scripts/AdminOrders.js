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
				rowData.setAttribute('class', 'idColumn');
				rowData.innerHTML = d.id;				
				let rowData2 = document.createElement('td');
				rowData2.innerHTML = d.order.user.username;
				let rowData3 = document.createElement('td');
				rowData3.innerHTML = d.order.orderId;
				let rowData4 = document.createElement('td');
				rowData4.innerHTML = d.order.totalPrice;
								
				row.appendChild(rowData);
				row.appendChild(rowData2);
				row.appendChild(rowData3);
				row.appendChild(rowData4);

				tbody.appendChild(row);
				
				handlingSelectedTableRow();
			});	
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
}

function handlingSelectedTableRow() {
	$("#adminOrderTable tr").click(function(){
	   $(this).addClass('selected').siblings().removeClass('selected');    
	});
}

function editOrder() {
	let id = $("#adminOrderTable tr.selected td:first").html();
	window.location.href='/adminOrderDetail?id=' + id;
}

window.onload = function() {
  getOrders();
};
