// LUM
// https://www.delftstack.com/de/howto/javascript/create-table-javascript/
function getUsers() {
	 $.ajax({
		type: "GET",
		 url: "/api/userProfileSettings",
		success: function (data) {
			let table = document.getElementById('adminUserTable');	
			let tbody = document.createElement('tbody');
			
			table.appendChild(tbody);
			
			data.forEach(d => {
				// Creating and adding data to second row of the table
				let row = document.createElement('tr');
				let rowData = document.createElement('td');
				rowData.setAttribute('class', 'idColumn');
				rowData.innerHTML = d.id;				
				let rowData1 = document.createElement('td');
				rowData1.innerHTML = d.username;
				let rowData2 = document.createElement('td');
				rowData2.innerHTML = d.firstname;
				let rowData3 = document.createElement('td');
				rowData3.innerHTML = d.lastname;
				let rowData4 = document.createElement('td');
				rowData4.innerHTML = d.address;
				let rowData5 = document.createElement('td');
				rowData5.innerHTML = d.postalCode;
				let rowData6 = document.createElement('td');
				rowData6.innerHTML = d.city;
				let rowData7 = document.createElement('td');
				rowData7.innerHTML = d.country;
				let rowData8 = document.createElement('td');
				rowData8.innerHTML = d.email;
				let rowData9 = document.createElement('td');
				rowData9.innerHTML = d.password;
				let rowData10 = document.createElement('td');
				rowData10.innerHTML = d.role;
				let rowData11 = document.createElement('td');
				rowData11.innerHTML = d.language;			
				
				row.appendChild(rowData);
				row.appendChild(rowData1);
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
				
				handlingSelectedTableRow();
			});	
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
}

function handlingSelectedTableRow() {
	$("#adminUserTable tr").click(function(){
	   $(this).addClass('selected').siblings().removeClass('selected');    
	});
}

function editUser() {
	let lang = window.location.search
	let id = $("#adminUserTable tr.selected td:first").html();
	if(id !== undefined) {
		window.location.href='/adminUserDetail' + lang + '&id=' + id;
	} else {
		if(lang.includes('eng')){
			alert('No element is selected! Please select a User!');	
		} else {
			alert('Kein Element selektiert! Bitte einen Benutzer selektieren.');			
		}
	}
}

window.onload = function() {
  getUsers();
};
