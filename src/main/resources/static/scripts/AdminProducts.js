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
				rowData.setAttribute('class', 'idColumn');
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
	let lang = window.location.search
	let id = $("#productAdminTable tr.selected td:first").html();
	if(id !== undefined) {
		window.location.href='/adminProductDetail' + lang + '&id=' + id;
	} else {
		if(lang.includes('eng')){
			alert('No element is selected! Please select a Product!');	
		} else {
			alert('Kein Element selektiert! Bitte ein Produkt selektieren.');			
		}
	}
}

window.onload = function() {
  getProducts();
};