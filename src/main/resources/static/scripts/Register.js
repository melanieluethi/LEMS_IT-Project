//Wae

function setUserData(){
	const titleUserData = document.getElementById(titleUserData);
	$.ajax({
		type:"PUT",
		url: "/api/register",
		data:{
			username: '',
			
		},
		success: function (data){
			titleUserData.innerHTML = titleUserData
		}
	})
}