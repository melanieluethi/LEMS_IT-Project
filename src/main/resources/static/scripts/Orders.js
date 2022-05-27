// LUM
// https://www.delftstack.com/de/howto/javascript/create-table-javascript/
function getOrders() {
	 $.ajax({
		type: "GET",
		 url: "/api/orders",
		success: function (data) {
			//get dropdown element
			let table = document.getElementById('orderUserTable');	
			let tbody = document.createElement('tbody');
			
			table.appendChild(tbody);
			
			data.forEach(d => {
				// Creating and adding data to second row of the table
				let row = document.createElement('tr');
				let rowData = document.createElement('td');
				rowData.setAttribute('class', 'idColumn');
				rowData.innerHTML = d.id;
				let rowData2 = document.createElement('td');
				rowData2.innerHTML = d.order.orderId;
				let rowData3 = document.createElement('td');
				rowData3.innerHTML = d.order.totalPrice;
								
				row.appendChild(rowData);
				row.appendChild(rowData2);
				row.appendChild(rowData3);

				tbody.appendChild(row);
				
				handlingSelectedTableRow();
			});	
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
}

function handlingSelectedTableRow() {
	$("#orderUserTable tr").click(function(){
	   $(this).addClass('selected').siblings().removeClass('selected');    
	});
}

function showOrderDetail() {
	let lang = window.location.search
	let id = $("#orderUserTable tr.selected td:first").html();
	if(id !== undefined) {
		window.location.href='/orderDetail' + lang + '&id=' + id;	
	} else {
		if(lang.includes('eng')){
			alert('No element is selected! Please select an Order!');	
		} else {
			alert('Kein Element selektiert! Bitte einen Auftrag selektieren.');			
		}
	}
}

window.onload = function() {
  getOrders();
};
