/**
 * This is the JS code implementation to
 * control bootstrap modals related to 
 * Production Management function
 * 
 * @author 	Gobisan, ITP21_S2_MT_11
 * @version 1.0 Sep 2021
 */

$(document).ready(function() {

	$('table .deleteProduct').on('click', function() {
		var pid = $(this).parent().parent().find('#pid').val();
		$('#deleteProduct #pid').val(pid);
	});
	
	$('table .deleteProduction').on('click', function() {
		var pid = $(this).parent().parent().find('#pid').val();
		$('#deleteProduction #pid').val(pid);
	});


	$('table .deleteNotice').on('click', function() {
		var nid = $(this).parent().parent().find('#nid').val();
		$('#deleteNotice #nid').val(nid);

	});
	
	$('table .updateStatus').on('click', function() {
		var pid = $(this).parent().parent().find('#productionID').val();
		$('#updateStatus #productionID').val(pid);
		$('#updateStatus #prnID').html(pid);
	});
	
	$('table .issueGatePass').on('click', function() {
		var pid = $(this).parent().parent().find('#productionID').val();
		$('#issueGatePass #productionID').val(pid);
		$('#issueGatePass #prnID').html(pid);
	});

	$('table .productDetails').on('click', function() {
		var mfgProductID = $(this).parent().parent().find('#mfgProductID').val();
		var noticeID = $(this).parent().parent().find('#nid').val();
		var color = $(this).parent().parent().find('#color').val();
		var size = $(this).parent().parent().find('#size').val();
		var quantity = $(this).parent().parent().find('#quantity').val();

		$('#productDetails #mfgProductID').html(mfgProductID);
		$('#productDetails #noticeID').html(noticeID);
		$('#productDetails #color').html(color);
		$('#productDetails #color').css("color", color);
		$('#productDetails #color').css("background-color", color);
		$('#productDetails #size').html(size);
		$('#productDetails #quantity').html(quantity);
	});

	$('table .materialUnits').on('click', function() {
		var cid = $(this).parent().parent().find('#cid').val();
		var materials = new Map()
		materials = $(this).parent().parent().find('#materials');

		console.log(materials);

		console.log(materials);
		$('#materialUnits #cid').val(cid);
		$('#materialUnits #materials').val(mid);
	});

	$("#addColor").click(function() {
		var html = '';
		html += '<div class="col-md-2 mt-1">';
		html += '<div class="col-md-1 mt-1">';
		html += '<input type="color" class="form-control form-control-color" name="color[]" value="#563d7c" title="Choose your color">';
		html += '<div class="input-group-append">';
		html += '<Span id="removeColor" type="button" class="btn btn-danger" style="width: 3px; height: 3px;">-</S>';
		html += '</div>';
		html += '</div>';

		$('#newColor').append(html);
	});

	var maxField = 3;
	var addButton = $('.addColor');
	var wrapper = $('.color_wrapper'); //Input field wrapper
	var colorField = '<div class="mx-3" style="display: flex;"><input class="form-control form-control-color" type="color" name="color[]" value="#303C6C" /> &nbsp;<label for="color[]" class="removeColor" title="Add field" style="border-radius: 100%;"><i class="bi bi-dash-lg text-danger"></i></label></div>';
	var x = 1;

	$(addButton).click(function() {
		if (x < maxField) {
			x++;
			$(wrapper).append(colorField); //Add field html
		}
	});

	$(wrapper).on('click', '.removeColor', function(e) {
		e.preventDefault();
		$(this).parent('div').remove();
		x--;
	});
});