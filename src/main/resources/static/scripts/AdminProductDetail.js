// LUM
function getProduct() {
	let baseUrl = window.location.href; 
	let productId = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
	let productIdField = document.getElementById('productId');
	productIdField.value = productId;
	$.ajax({
		type: "GET",
		url: "/api/productById/" + productId,	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {
			let productname = document.getElementById('productname');
			productname.value = data.productName;
			
			let descriptionDe = document.getElementById('descriptionDe');
			descriptionDe.value = data.description;
			
			let descriptionEng = document.getElementById('descriptionEng');
			descriptionEng.value = data.descriptionEng;
			
			let productImg = document.getElementById('productImg');
			productImg.src = data.productImg;
			
			let discount = document.getElementById('discount');
			discount.value = data.discount;		
        }, 
        error: function(e) {
			console.log(e);
	  	}
	});
}

function save() {
	let productId = document.getElementById('productId').value;
	let productname = document.getElementById('productname').value;
	let descriptionDe = document.getElementById('descriptionDe').value;
	let descriptionEng = document.getElementById('descriptionEng').value;
	let discount = document.getElementById('discount').value;
		
	$.ajax({
		type: 'PUT',
		url: '/api/changeProduct/',
		data: JSON.stringify ({
			productId: productId,
			productName: productname,
			description: descriptionDe,
			descriptionEng: descriptionEng,
			discount: discount					
		}),	
		dataType: 'json',
		contentType: 'application/json',	
		success: function (data) {
			if(data) {
				window.location.href='/adminProducts';	
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
			if(window.location.search.includes('eng')){
				alert('Something went wrong.');
			} else{
				alert('Etwas ist schief gelaufen');
			}
	  	}
	});
}

window.onload = function() {
  getProduct();
};