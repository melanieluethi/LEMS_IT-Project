// SCL
function getProduct() {
	
    
	const productname = document.getElementById('products');	
	 $.ajax({
		success: function (data) {
        productname.innerHTML = productname.innerHTML.replace('pName');
        }, error: function(e) {
			console.log(e);
	  	}
	  	});
}

// lum
window.onload = function() {
  getProduct();
};