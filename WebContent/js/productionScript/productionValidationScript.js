/**
 * This is the JQuery code implementation for
 * front-end validation related to
 * Production Management function 
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

$(document).ready(function() {


	$.validator.addMethod("validCost", function(value, element) {

		return this.optional(element)
			|| (value > 0);
	});

	$.validator.addMethod("validSize", function(value, element) {

		return this.optional(element)
			|| (("element").length == 0);
	});

	$.validator.addMethod("validQuantity", function(value, element) {

		return this.optional(element)
			|| (value > 0);
	});

	$.validator.addMethod("validBudget", function(value, element, param) {
		return this.optional(element)
			|| (value > ($(param[0]).val() * $(param[1]).val()));
	});

	$.validator.addMethod("validTotal", function(value, element) {

		return this.optional(element)
			|| (value > 0);
	});

	$.validator.addMethod("validDays", function(value, element, param) {
		return this.optional(element) || (value == $(param).val());
	});


	// front-end validation
	$("#productForm").validate({

		// Specify validation rules
		rules: {
			productTitle: "required",
			productCollection: "required",
			productType: "required",
			unitCost: {
				required: true,
				validCost: true,
				number: true
			},

			//color[]: "required",

			"size[]": "required"
		},

		// Specify validation error messages
		messages: {
			productTitle: "Please provide valid product title",
			productCollection: "Please select a product collection",
			productType: "Please select a product type",
			unitCost: {
				required: "Please provide a valid manufacturing cost",
				validCost: "Unit cost should be greater than 0.",
				number: "Unit cost should be a numeric value"
			},

			"size[]": "Select at least one size"
		},

		submitHandler: function(form) {
			form.submit();
		}
	});


	// front-end validation
	$("#noticeForm").validate({

		// Specify validation rules
		rules: {
			productID: "required",
			noticeTitle: "required",
			origin: "required",
			compDate: "required",
			color: "required",
			size: "required",

			prodQty: {
				required: true,
				validQuantity: true
			},

			unitCost: {
				required: true,
				validCost: true
			},

			estBudget: {
				required: true,
				number: true,
				validCost: true,
				validBudget: ["#prodQty", "#unitCost"]
			},
		},

		// Specify validation error messages
		messages: {
			productID: "MSG",
			noticeTitle: "MSG",
			origin: "Please select a product origin",
			compDate: "Please provide an expected completion date",
			color: "Please select a color",
			size: "Please select a size",

			prodQty: {
				required: "Please provide valid quantity",
				validQuantity: "Please provide quantity more than 0"
			},

			estBudget: {
				required: "Please provide valid budget amount",
				number: "Budget should be a numeric value",
				validCost: "Budget should be greater than 0",
				validBudget: "Budget amount should be  atleast " + ($("#prodQty").val() * $("#unitCost").val()) + " or reduce the quantity",
			}
		},

		submitHandler: function(form) {
			form.submit();
		}
	});

	// front-end validation
	$("#planForm").validate({

		// Specify validation rules
		rules: {
			line: "required",
			supervisor: "required",
			startDate: "required",
			endDate: "required",


			cuttingDays: "required",
			printingDays: "required",
			sewingDays: "required",
			washingDays: "required",
			pressingDays: "required",

			totalDays: {
				required: true,
				validTotal: true
			},

			countDays: {
				required: true,
				validDays: "#totalDays"
			},
		},

		// Specify validation error messages
		messages: {
			line: "Please select a production line",
			supervisor: "Please select a manufacturing supervisor",
			startDate: "Please provide a valid start date",
			endDate: "Please provide a valid end date",

			cuttingDays: "Please provide a valid number of days",
			printingDays: "Please provide a valid number of days",
			sewingDays: "Please provide a valid number of days",
			washingDays: "Please provide a valid number of days",
			pressingDays: "Please provide a valid number of days",

			totalDays: {
				required: "Please provide a valid number of days",
				validTotal: "Total days should be at least 1, please change dates accordingly"
			},

			countDays: {
				required: "Please allocate valid number of days",
				validDays: "Please allocate days according to the total days"
			},
		},

		submitHandler: function(form) {
			form.submit();
		}
	});

});