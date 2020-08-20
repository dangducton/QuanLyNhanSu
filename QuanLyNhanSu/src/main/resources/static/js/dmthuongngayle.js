$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmThuongngayle, status) {
            	$('.myForm #id').val(DmThuongngayle.id);
                $('.myForm #tenngayle').val(DmThuongngayle.tenngayle);
                $('.myForm #status').val(DmThuongngayle.status);
                $('.myForm #ghichu').val(DmThuongngayle.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tenngayle').val('');
            $('.myForm #ghichu').val('');
            $('.myForm1 #exampleModal1').modal();
    	});
    

    $('.table .activateBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #updateRef').attr('href', href);
        $('#myModal').modal();
    });
});

$('form[id="second_form"]').validate({
	  rules: { 
		  tenngayle: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenngayle: {
			required:'Tên ngày lễ không được bỏ trống',
		    minlength:'Tên ngày lễ gồm 5 đến 50 kí tự',
		    maxlength:'Tên ngày lễ gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tenngayle: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenngayle: {
				required:'Tên ngày lễ không được bỏ trống',
			    minlength:'Tên ngày lễ gồm 5 đến 50 kí tự',
			    maxlength:'Tên ngày lễ gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
