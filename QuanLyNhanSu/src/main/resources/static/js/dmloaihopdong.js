$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (DmLoaihopdong, status) {
            	$('.myForm #id').val(DmLoaihopdong.id);
                $('.myForm #tenloaihopdong').val(DmLoaihopdong.tenhopdong);
                $('.myForm #status').val(DmLoaihopdong.status);
                $('.myForm #ghichu').val(DmLoaihopdong.ghichu);
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tenloaihopdong').val('');
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
		  tenloaihopdong: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenloaihopdong: {
			required:'Tên loại hợp đồng không được bỏ trống',
		    minlength:'Tên loại hợp đồng gồm 5 đến 50 kí tự',
		    maxlength:'Tên loại hợp đồng gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

$('form[id="second_form2"]').validate({
	  rules: { 
		  tenloaihopdong: {
		      required: true,
		      minlength: 5,
		      maxlength: 50,
		}
	  },
	  messages: {
		  tenloaihopdong: {
			required:'Tên loại hợp đồng không được bỏ trống',
		    minlength:'Tên loại hợp đồng gồm 5 đến 50 kí tự',
		    maxlength:'Tên loại hợp đồng gồm 5 đến 50 kí tự',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
