// lum
function setTimeoutForRedirectToLoginPage(){
	setTimeout(function(){
    	window.location.href = '/login';
    }, 5000);
}

// lum
window.onload = function(){
	setTimeoutForRedirectToLoginPage();
}