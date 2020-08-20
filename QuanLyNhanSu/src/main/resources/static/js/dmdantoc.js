$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmDantoc, status) {
            	$('.myForm #id').val(DmDantoc.id);
                $('.myForm #tendantoc').val(DmDantoc.tendantoc);
                $('.myForm #status').val(DmDantoc.status);
                $('.myForm #ghichu').val(DmDantoc.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tendantoc').val('');
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
		  tendantoc: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tendantoc: {
			required:'Tên dân tộc không được bỏ trống',
		    minlength:'Tên dân tộc gồm 5 đến 50 kí tự',
		    maxlength:'Tên dân tộc gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tendantoc: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tendantoc: {
			required:'Tên dân tộc không được bỏ trống',
		    minlength:'Tên dân tộc gồm 5 đến 50 kí tự',
		    maxlength:'Tên dân tộc gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
