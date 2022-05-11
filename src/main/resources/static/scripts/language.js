// wae angelehnt an https://stackoverflow.com/questions/46008760/how-to-build-multiple-language-website-using-pure-html-js-jquery
function setLanguage(){
	debugger;
	localStorage.setItem('language', lang);
}

window.onload = function(){
debugger;
    $("#locales").change(function () {
	debugger;
        var selectedOption = $('#locales').val();
        if (selectedOption != ''){
            window.location.replace('international?lang=' + selectedOption);
        }
    });
}