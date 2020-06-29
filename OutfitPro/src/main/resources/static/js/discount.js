$(function() {
	
	$('#rad-fijo').change(function() {
		$('#rad-porc').prop('checked', !$(this).is(':checked'));
		$('#amount').attr('disabled', !$(this).is(':checked'));
		$('#percentage').attr('disabled', $(this).is(':checked'));
		$('#percentage').val(0);
	});
	
	$('#rad-porc').change(function() {
		$('#rad-fijo').prop('checked', !$(this).is(':checked'));
		$('#percentage').attr('disabled', !$(this).is(':checked'));
		$('#amount').attr('disabled', $(this).is(':checked'));
		$('#amount').val(0);
	});
});