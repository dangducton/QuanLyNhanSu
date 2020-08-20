$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmLydo, status) {
            	$('.myForm #id').val(DmLydo.id);
                $('.myForm #tenlydo').val(DmLydo.tenlydo);
                $('.myForm #status').val(DmLydo.status);
                $('.myForm #ghichu').val(DmLydo.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tenlydo').val('');
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
		  tenlydo: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenlydo: {
			required:'Tên lý do không được bỏ trống',
		    minlength:'Tên lý do gồm 5 đến 50 kí tự',
		    maxlength:'Tên lý do gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tenlydo: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenlydo: {
			required:'Tên lý do không được bỏ trống',
		    minlength:'Tên lý do gồm 5 đến 50 kí tự',
		    maxlength:'Tên lý do gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
