$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmNgoaingu, status) {
            	$('.myForm #id').val(DmNgoaingu.id);
                $('.myForm #tenngoaingu').val(DmNgoaingu.tenngoaingu);
                $('.myForm #status').val(DmNgoaingu.status);
                $('.myForm #ghichu').val(DmNgoaingu.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tenngoaingu').val('');
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
		  tenngoaingu: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenngoaingu: {
			required:'Tên ngoại ngữ không được bỏ trống',
		    minlength:'Tên ngoại ngữ gồm 5 đến 50 kí tự',
		    maxlength:'Tên ngoại ngữ gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tenngoaingu: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenngoaingu: {
			required:'Tên ngoại ngữ không được bỏ trống',
		    minlength:'Tên ngoại ngữ gồm 5 đến 50 kí tự',
		    maxlength:'Tên ngoại ngữ gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
