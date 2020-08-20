$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmPhucap, status) {
            	$('.myForm #id').val(DmPhucap.id);
                $('.myForm #tenphucap').val(DmPhucap.tenphucap);
                $('.myForm #mucphucap').val(DmPhucap.mucphucap);
                $('.myForm #status').val(DmPhucap.status);
                $('.myForm #ghichu').val(DmPhucap.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tenphucap').val('');
            $('.myForm #mucphucap').val('');
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
		  tenphucap: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		},
		mucphucap: {
			required: true,
			number: true,
		}
	  },
	  messages: {
		  tenphucap: {
			required:'Tên phụ cấp không được bỏ trống',
		    minlength:'Tên phụ cấp gồm 5 đến 50 kí tự',
		    maxlength:'Tên phụ cấp gồm 5 đến 50 kí tự',
		},
		mucphucap: {
				required:'Mức phụ cấp không được bỏ trống',
				number:'Yêu cầu nhập đúng định dạng số',
			}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	 rules: { 
		 tenphucap: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		},
		mucphucap: {
			required: true,
			number: true,
		}
	  },
	  messages: {
		  tenphucap: {
			required:'Tên phụ cấp không được bỏ trống',
		    minlength:'Tên phụ cấp gồm 5 đến 50 kí tự',
		    maxlength:'Tên phụ cấp gồm 5 đến 50 kí tự',
		},
		mucphucap: {
				required:'Mức phụ cấp không được bỏ trống',
				number:'Yêu cầu nhập đúng định dạng số',
			}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
