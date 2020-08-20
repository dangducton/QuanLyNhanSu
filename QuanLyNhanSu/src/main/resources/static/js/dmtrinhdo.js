$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmTrinhdo, status) {
            	$('.myForm #id').val(DmTrinhdo.id);
                $('.myForm #tentrinhdo').val(DmTrinhdo.tentrinhdo);
                $('.myForm #hesochuyenmon').val(DmTrinhdo.hesochuyenmon);
                $('.myForm #status').val(DmTrinhdo.status);
                $('.myForm #ghichu').val(DmKylDmTrinhdouat.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tentrinhdo').val('');
            $('.myForm #hesochuyenmon').val('');
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
		  tentrinhdo: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		},
		hesochuyenmon: {
			required: true,
			number: true,
		}
	  },
	  messages: {
		  tentrinhdo: {
			required:'Tên trình độ không được bỏ trống',
		    minlength:'Tên trình độ gồm 5 đến 50 kí tự',
		    maxlength:'Tên trình độ gồm 5 đến 50 kí tự',
		},
		hesochuyenmon: {
				required:'Hệ số chuyên môn không được bỏ trống',
				number:'Yêu cầu nhập đúng định dạng số',
			}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	 rules: { 
		 tentrinhdo: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		},
		hesochuyenmon: {
			required: true,
			number: true,
		}
	  },
	  messages: {
		  tentrinhdo: {
			required:'Tên trình độ không được bỏ trống',
		    minlength:'Tên trình độ gồm 5 đến 50 kí tự',
		    maxlength:'Tên trình độ gồm 5 đến 50 kí tự',
		},
		hesochuyenmon: {
				required:'Hệ số chuyên môn không được bỏ trống',
				number:'Yêu cầu nhập đúng định dạng số',
			}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
