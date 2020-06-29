$(function() {
	
	//fillSizes();
	
	$('#slt-type').change(function() {
		fillSizes();
	});
	
	function fillSizes() {
		var typeId = $('#slt-type').val();

		if (typeId && $.isNumeric(typeId)) {
            $.ajax({
                type: 'get',
                url: 'sizesByClothingType',
                data: {'clothingTypeId' : typeId},
                dataType: 'html',
                success: function (json) {
                    $('#slt-size').empty();
                    $('#slt-size').append('<option selected disabled hidden th:value="0">-- Selecciona una opción --</option>');
                    $($.parseJSON(json)).each(function () {
                        var option = $(document.createElement('option'));
                        option.val(this.id);
                        option.text(this.name);
                        $('#slt-size').append(option);
                    });
                    $('#slt-size').attr('disabled', false);
                },
                error: function(xhr) {
					alert(xhr.responseText);
					$('#slt-size').attr('disabled', true);
                }
            });
		}
        else
        	$('#slt-size').attr('disabled', true);   
	}
});