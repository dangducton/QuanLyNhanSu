$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmTinhtranghonnhan, status) {
            	$('.myForm #id').val(DmTinhtranghonnhan.id);
                $('.myForm #tentinhtranghonnhan').val(DmTinhtranghonnhan.tentinhtranghonnhan);
                $('.myForm #status').val(DmTinhtranghonnhan.status);
                $('.myForm #ghichu').val(DmTinhtranghonnhan.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tentinhtranghonnhan').val('');
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
		  tentinhtranghonnhan: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tentinhtranghonnhan: {
			required:'Tên tình trạng hôn nhân không được bỏ trống',
		    minlength:'Tên tình trạng hôn nhân gồm 5 đến 50 kí tự',
		    maxlength:'Tên tình trạng hôn nhân gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tentinhtranghonnhan: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tentinhtranghonnhan: {
				required:'Tên tình trạng hôn nhân không được bỏ trống',
			    minlength:'Tên tình trạng hôn nhân gồm 5 đến 50 kí tự',
			    maxlength:'Tên tình trạng hôn nhân gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
