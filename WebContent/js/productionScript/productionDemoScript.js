/**
 * This is the JS code implementation to
 * autofill HTML froms related to 
 * Production Management function by 
 * clicking DEMO button
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

// auto fill product notice data
$("#fillNotice").click(function() {
	document.getElementById("prodQty").value = 500;

	var comp = new Date(2021, 09, 13);
	var day = ("0" + comp.getDate()).slice(-2);
	var month = ("0" + (comp.getMonth() + 1)).slice(-2);
	var compDay = comp.getFullYear() + "-" + (month) + "-" + (day);

	$("#compDate").val(compDay);
});



// auto fill production plan data
$("#fillPlan").click(function() {

	$("#line").val(5001);
	$("#supervisor").val("1");
	
	var today = new Date();
	var startDay = String(today.getDate()).padStart(2, '0');
	var startMonth = String(today.getMonth() + 1).padStart(2, '0');
	var startYear = today.getFullYear();
	var strDay = (startYear) + "-" + (startMonth) + "-" + (startDay);
	
	$("#startDate").val(strDay);
	
	var comp = new Date(2021, 11, 14);
	var completionDay =  String(comp.getDate()).padStart(2, '0');
	var completionMonth = String(comp.getMonth() + 1).padStart(2, '0');
	var compYear = comp.getFullYear()
	var compDay = (compYear) + "-" + (completionMonth) + "-" + (completionDay);
	
	$("#endDate").val(compDay);
	
	$("#cuttingDays").val(0);
	$("#printingDays").val(0);
	$("#sewingDays").val(0);
	$("#washingDays").val(0);
	$("#pressingDays").val(0);
	$("#packagingDays").val(0);
});


// auto fill product data
	$("#fillProduct").click(function() {
		document.getElementById("productTitle").value = "Sample T-Shirt";
		document.getElementById("productCollection").value = 2;
		document.getElementById("mens").checked = true;
		document.getElementById("unitCost").value = 449;
		document.getElementById("s").checked = true;
		
		console.log("TEST");
	});