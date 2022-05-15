// SCL


function getProductsTable() {
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
				rowData.innerHTML = d.productName;
				let rowData2 = document.createElement('td');
				rowData2.innerHTML = d.description;
				let rowData3 = document.createElement('td');
				rowData3.innerHTML = d.discount;
				
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
  	getProductsTable();
};