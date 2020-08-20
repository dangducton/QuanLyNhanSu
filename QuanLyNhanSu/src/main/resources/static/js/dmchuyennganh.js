$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmChuyennganh, status) {
            	$('.myForm #id').val(DmChuyennganh.id);
                $('.myForm #tenchuyennganh').val(DmChuyennganh.tenchuyennganh);
                $('.myForm #status').val(DmChuyennganh.status);
                $('.myForm #ghichu').val(DmChuyennganh.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tenchuyennganh').val('');
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
		  tenchuyennganh: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenchuyennganh: {
			required:'Tên chuyên ngành không được bỏ trống',
		    minlength:'Tên chuyên ngành gồm 5 đến 50 kí tự',
		    maxlength:'Tên chuyên ngành gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tenchuyennganh: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenchuyennganh: {
			required:'Tên chuyên ngành không được bỏ trống',
		    minlength:'Tên chuyên ngành gồm 5 đến 50 kí tự',
		    maxlength:'Tên chuyên ngành gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
