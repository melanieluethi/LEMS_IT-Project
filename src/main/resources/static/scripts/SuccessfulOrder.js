function getOrderId() {
	let baseUrl = window.location.href; 
	let id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
	let orderId = document.getElementById('orderId');
	orderId.innerHTML = orderId.innerHTML.replace('orderNumber', id);
}

window.onload = function() {
  getOrderId();
};