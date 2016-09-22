var db = connect('127.0.0.1:27017/sense-cloud-db');
/*--------------------------------------------- CREATE SCRIPTS -----------------------------------------*/
db.createCollection("restaurant");
db.createCollection("owner_info");
db.createCollection("cloud_details");
db.createCollection("sequence");
/*--------------------------------------------- INSERT SCRIPTS -----------------------------------------*/
db.sequence.insert([
	{
	"_id" : "restaurant_seq",
	"seq" : 100
	}, 
	{
	"_id" : "owner_info_seq",
	"seq" : 100
	}
]);
/*----------------------------------------------------------------------------------------------------*/
db.cloud_details.insert(
	{
		"_id" : 1,
	    "_class" : "in.cw.csense.app.entity.CloudDetails",
	    "publicKey" : "xxxCloudPublicKeyyyyy",
	    "privateKey" : "xxxCloudPrivateKeyyyyy",
	    "cloudUrl" : "http://www.cloudSenseUrl.com"
	}
);