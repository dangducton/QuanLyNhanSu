$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmTongiao, status) {
            	$('.myForm #id').val(DmTongiao.id);
                $('.myForm #tentongiao').val(DmTongiao.tentongiao);
                $('.myForm #status').val(DmTongiao.status);
                $('.myForm #ghichu').val(DmTongiao.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tentongiao').val('');
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
		  tentongiao: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tentongiao: {
			required:'Tên tôn giáo không được bỏ trống',
		    minlength:'Tên tôn giáo gồm 5 đến 50 kí tự',
		    maxlength:'Tên tôn giáo gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tentongiao: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tentongiao: {
				required:'Tên tôn giáo không được bỏ trống',
			    minlength:'Tên tôn giáo gồm 5 đến 50 kí tự',
			    maxlength:'Tên tôn giáo gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
