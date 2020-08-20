$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmTrinhdotinhoc, status) {
            	$('.myForm #id').val(DmTrinhdotinhoc.id);
                $('.myForm #tentrinhdotinhoc').val(DmTrinhdotinhoc.tentrinhdotinhoc);
                $('.myForm #status').val(DmTrinhdotinhoc.status);
                $('.myForm #ghichu').val(DmTrinhdotinhoc.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tentrinhdotinhoc').val('');
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
		  tentrinhdotinhoc: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tentrinhdotinhoc: {
			required:'Tên trình độ tin học không được bỏ trống',
		    minlength:'Tên trình độ tin học gồm 5 đến 50 kí tự',
		    maxlength:'Tên trình độ tin học gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tentrinhdotinhoc: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tentrinhdotinhoc: {
			required:'Tên trình độ tin học không được bỏ trống',
		    minlength:'Tên trình độ tin học gồm 5 đến 50 kí tự',
		    maxlength:'Tên trình độ tin học gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
