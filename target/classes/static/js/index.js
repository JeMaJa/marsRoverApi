let userId = getUrlParameter('userId')
if (userId == null || userId == '') {
	userId = localStorage.getItem('userId')
	if(userId == null) {
		document.getElementById('createUser').value = true
	}
}
if(userId != null && userId != ''){
	localStorage.setItem('userId', userId)
	document.getElementById('userId').value = userId
} 

let marsApiButtons = document.querySelectorAll("button[id*='marsApi']")

marsApiButtons.forEach(button => button.addEventListener('click', function () {
							const buttonId = this.id
							const roverId = buttonId.split('marsApi')[1]
							let apiData = document.getElementById('marsApiRoverData')
							apiData.value = roverId
							//alert(roverId)
							document.getElementById('frmRoverType').submit()
							}))

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};
let marsRoverType = document.getElementById('marsApiRoverData').value

if(marsRoverType =='')
marsRoverType = 'Curiosity'

highlightBtnByRover(marsRoverType)

let marsSol = document.getElementById('marsSol').value
if(marsSol == '')
marsSol = 1





function highlightBtnByRover(roverType) {

	if(marsRoverType =='')
		marsRoverType = 'Curiosity'

	document.getElementById('marsApi'+roverType).classList.remove('btn-outline-secundary')
	document.getElementById('marsApi'+roverType).classList.add('btn-outline-primary')
}
