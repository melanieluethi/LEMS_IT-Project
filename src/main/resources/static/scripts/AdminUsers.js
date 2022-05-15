// LUM
// https://www.delftstack.com/de/howto/javascript/create-table-javascript/
function getUsers() {
	 $.ajax({
		type: "GET",
		 url: "/api/user",
		success: function (data) {
			//get dropdown element
			let table = document.getElementById('UserTable');	
			let tbody = document.createElement('tbody');
			
			table.appendChild(tbody);
			
			data.forEach(d => {
				// Creating and adding data to second row of the table
				let row = document.createElement('tr');
				let rowData = document.createElement('td');
				rowData.innerHTML = d.user.username;
				let rowData2 = document.createElement('td');
				rowData2.innerHTML = d.user.firstname;
				let rowData3 = document.createElement('td');
				rowData3.innerHTML = d.user.lastname;
				let rowData4 = document.createElement('td');
				rowData4.innerHTML = d.user.address;
				let rowData5 = document.createElement('td');
				rowData5.innerHTML = d.user.postalCode;
				let rowData6 = document.createElement('td');
				rowData6.innerHTML = d.user.city;
				let rowData7 = document.createElement('td');
				rowData7.innerHTML = d.user.country;
				let rowData8 = document.createElement('td');
				rowData8.innerHTML = d.user.email;
				let rowData9 = document.createElement('td');
				rowData9.innerHTML = d.user.password;
				let rowData10 = document.createElement('td');
				rowData10.innerHTML = d.user.roleId;
				let rowData11 = document.createElement('td');
				rowData11.innerHTML = d.user.language;			
				
				row.appendChild(rowData);
				row.appendChild(rowData2);
				row.appendChild(rowData3);
				row.appendChild(rowData4);
				row.appendChild(rowData5);
				row.appendChild(rowData6);
				row.appendChild(rowData7);
				row.appendChild(rowData8);
				row.appendChild(rowData9);
				row.appendChild(rowData10);
				row.appendChild(rowData11);
				
				
				tbody.appendChild(row);
			});	
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
}

window.onload = function() {
  getUsers();
};
