// wae angelehnt an https://stackoverflow.com/questions/46008760/how-to-build-multiple-language-website-using-pure-html-js-jquery
function getLanguage(){
	(localStorage.getItem('language')== null)? setLanguage('en'):false;
	$ajax({
		url:'/language/' + localStorage.getItem('language') + '.json',
		dataType:'json', async:false, dataType:'json',
		success:function(lang){language = lang}});
	}
	
function setLanguage(){
	localStorage.setItem('language', lang);
}
window.onload = function(){
	getLanguage();
}
