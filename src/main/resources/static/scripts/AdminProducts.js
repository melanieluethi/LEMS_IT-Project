// LUM
// https://www.delftstack.com/de/howto/javascript/create-table-javascript/
function getProducts() {
	 $.ajax({
		type: "GET",
		 url: "/api/products",
		success: function (data) {
			//get dropdown element
			let table = document.getElementById('productAdminTable');	
			let tbody = document.createElement('tbody');
			
			table.appendChild(tbody);
	
			data.forEach(d => {
				// Creating and adding data to second row of the table
				let row = document.createElement('tr');
				let rowData = document.createElement('td');
				rowData.innerHTML = d.id;				
				let rowData2 = document.createElement('td');
				rowData2.innerHTML = d.productName;
				let rowData3 = document.createElement('td');
				rowData3.innerHTML = d.description;
				let rowData4 = document.createElement('td');
				rowData4.innerHTML = d.discount;
								
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
	$("#productAdminTable tr").click(function(){
	   $(this).addClass('selected').siblings().removeClass('selected');    
	});
}

function editProduct() {
	let id = $("#productAdminTable tr.selected td:first").html();
	window.location.href='/adminProductDetail?id=' + id;
}

window.onload = function() {
  getProducts();
};