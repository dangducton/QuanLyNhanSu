$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmPhongban, status) {
            	$('.myForm #id').val(DmPhongban.id);
                $('.myForm #tenphongban').val(DmPhongban.tenphongban);
                $('.myForm #ngaythanhlap').val(DmPhongban.ngaythanhlap);
                $('.myForm #status').val(DmPhongban.status);
                $('.myForm #ghichu').val(DmPhongban.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tenphongban').val('');
            $('.myForm #ngaythanhlap').val('');
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
		  tenphongban: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		},
		ngaythanhlap: {
			required: true,
		      date: true,
		}
	  },
	  messages: {
		  tenphongban: {
			required:'Tên phòng ban không được bỏ trống',
		    minlength:'Tên phòng ban gồm 5 đến 50 kí tự',
		    maxlength:'Tên phòng ban gồm 5 đến 50 kí tự',
		},
		ngaythanhlap: {
				required:'Ngày thành lập không được bỏ trống',
				date:'Yêu cầu nhập đúng định dạng ngày tháng',
			}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tenphongban: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		},
		ngaythanhlap: {
			required: true,
		      date: true,
		}
	  },
	  messages: {
		  tenphongban: {
			required:'Tên phòng ban không được bỏ trống',
		    minlength:'Tên phòng ban gồm 5 đến 50 kí tự',
		    maxlength:'Tên phòng ban gồm 5 đến 50 kí tự',
		},
		ngaythanhlap: {
				required:'Ngày thành lập không được bỏ trống',
				date:'Yêu cầu nhập đúng định dạng ngày tháng',
			}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
