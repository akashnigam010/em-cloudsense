var serverAddress;
var clientData;
$(document).ready(function() {
	serverAddress = 'http://localhost:8087/cloudsense/';
	$('#clientDetailsDiv').hide();
	$('#getClientDetailsBtn').show();
});

function getClientDetails() {
	$.ajax({
		method : "GET",
		url : serverAddress + 'register/getRegistrationDetails'
	}).success(
			function(data) {
				if (data.result == true) {
					$('#clientDetailsDiv').show();
					$('#getClientDetailsBtn').hide();
					clientData = data;
					$('#restaurantId').html(clientData.restaurantId);
					$('#password').html(clientData.password);
					$('#publicKey').html(clientData.publicKey);
					$('#privateKey').html(clientData.privateKey);
					$('#cloudUrl').html(clientData.cloudUrl);
					$('#cloudPublicKey').html(clientData.cloudPublicKey);
				} else {
					alert('Error occurred : '
							+ data.statusCodes.statusCode[0].description);
				}
			});
}

function saveClientDetails() {
	var clientDataToPost = {
		'restaurantId' : clientData.restaurantId,
		'password' : clientData.password,
		'publicKey' : clientData.publicKey
	};
	$.ajax({
		method : 'POST',
		url : serverAddress + 'register/saveClientDetails',
		headers : {
			//'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		data : clientDataToPost

		//dataType : 'json'
	}).success(
			function(data) {
				if (data.result == true) {
					$('#saveClientDetailsBtn').hide();
					alert('Client data saved successfully');
				} else {
					alert('Error occurred while saving data : '
							+ data.statusCodes.statusCode[0].description);
				}
			});
}