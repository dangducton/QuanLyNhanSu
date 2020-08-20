$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmQuanhe, status) {
            	$('.myForm #id').val(DmQuanhe.id);
                $('.myForm #tenquanhe').val(DmQuanhe.tenquanhe);
                $('.myForm #status').val(DmQuanhe.status);
                $('.myForm #ghichu').val(DmQuanhe.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tenquanhe').val('');
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
		  tenquanhe: {
		      required: true,
		      minlength: 2,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenquanhe: {
			required:'Tên quan hệ không được bỏ trống',
		    minlength:'Tên quan hệ gồm 2 đến 50 kí tự',
		    maxlength:'Tên quan hệ gồm 2 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tenquanhe: {
		      required: true,
		      minlength: 2,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenquanhe: {
				required:'Tên quan hệ không được bỏ trống',
			    minlength:'Tên quan hệ gồm 2 đến 50 kí tự',
			    maxlength:'Tên quan hệ gồm 2 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
