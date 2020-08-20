$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Đổi mật khẩu') {
            $.get(href, function (DTOUserRole, status) {
            	$('.myForm #id').val(DTOUserRole.id);
            });
            $('.myForm #exampleModal').modal();
        }});
 });

$('form[id="second_form"]').validate({
	  rules: { 
		  idquanhe: {
		      required: true,
		},
		 hoten: {
		      required: true,
		},
		 nghenghiep: {
		      required: true,
		},
		 diachi: {
		      required: true,
		}
	  },
	  messages: {
		  idquanhe: {
			required:'Yêu cầu chọn quan hệ',
		},
		 hoten: {
			required:'Yêu cầu nhập họ tên',
		},
		 nghenghiep: {
			required:'Yêu cầu nhập nghề nghiệp',
		},
		 diachi: {
			required:'Yêu cầu nhập địa chỉ',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});

