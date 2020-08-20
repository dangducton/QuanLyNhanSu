$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmKyluat, status) {
            	$('.myForm #id').val(DmKyluat.id);
                $('.myForm #tenkyluat').val(DmKyluat.tenkyluat);
                $('.myForm #mucphat').val(DmKyluat.mucphat);
                $('.myForm #status').val(DmKyluat.status);
                $('.myForm #ghichu').val(DmKyluat.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tenkyluat').val('');
            $('.myForm #mucphat').val('');
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
		  tenkyluat: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		},
		mucphat: {
			required: true,
			number: true,
		}
	  },
	  messages: {
		  tenkyluat: {
			required:'Tên kỷ luật không được bỏ trống',
		    minlength:'Tên kỷ luật gồm 5 đến 50 kí tự',
		    maxlength:'Tên kỷ luật gồm 5 đến 50 kí tự',
		},
		mucphat: {
				required:'Mức phạt không được bỏ trống',
				number:'Yêu cầu nhập đúng định dạng số',
			}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	 rules: { 
		  tenkyluat: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		},
		mucphat: {
			required: true,
			number: true,
		}
	  },
	  messages: {
		  tenkyluat: {
			required:'Tên kỷ luật không được bỏ trống',
		    minlength:'Tên kỷ luật gồm 5 đến 50 kí tự',
		    maxlength:'Tên kỷ luật gồm 5 đến 50 kí tự',
		},
		mucphat: {
				required:'Mức phạt không được bỏ trống',
				number:'Yêu cầu nhập đúng định dạng số',
			}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
