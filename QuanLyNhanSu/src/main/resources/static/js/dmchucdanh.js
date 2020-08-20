$(document).ready(function() {

	$('.table .eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text(); // return New or Edit
		if (text === 'Sửa') {
			$.get(href, function(DmChucDanhDTO, status) {
				$('.myForm #id').val(DmChucDanhDTO.id);
				$('.myForm #tenchucdanh').val(DmChucDanhDTO.tenchucdanh);
				$('.myForm #hesochucvu').val(DmChucDanhDTO.hesochucvu);
				$('.myForm #hesotrachnhiem').val(DmChucDanhDTO.hesotrachnhiem);
				$('.myForm #luongcoban').val(DmChucDanhDTO.luongcoban);
				$('.myForm #status').val(DmChucDanhDTO.status);
				$('.myForm #ghichu').val(DmChucDanhDTO.ghichu);
			});
			$('.myForm #exampleModal').modal();
		}
	});

	$('.nBtn').on('click', function(event) {
		event.preventDefault();
		$('.myForm #tenchucdanh').val('');
		$('.myForm #hesochucvu').val('');
		$('.myForm #hesotrachnhiem').val('');
		$('.myForm #luongcoban').val('');
		$('.myForm #ghichu').val('');
		$('.myForm1 #exampleModal1').modal();
	});

	$('.table .activateBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#myModal #updateRef').attr('href', href);
		$('#myModal').modal();
	});
});

$('form[id="second_form"]').validate({
	rules : {
		tenchucdanh : {
			required : true,
			minlength : 5,
			maxlength : 50,
		},
		hesochucvu : {
			required : true,
			number : true
		},
		hesotrachnhiem : {
			required : true,
			number : true
		},
		luongcoban : {
			required : true,
			number : true
		}
	},
	messages : {
		tenchucdanh : {
			required : 'Tên chức vụ không được bỏ trống',
			minlength : 'Tên chức vụ gồm 5 đến 50 kí tự',
			maxlength : 'Tên chức vụ gồm 5 đến 50 kí tự',
		},
		hesochucvu : {
			required : 'Hệ số chức vụ không được bỏ trống',
			number : 'Yêu cầu nhập đúng định dạng số',
		},
		hesotrachnhiem : {
			required : 'Hệ số trách nhiệm không được bỏ trống',
			number : 'Yêu cầu nhập đúng định dạng số',
		},
		luongcoban : {
			required : 'Lương cơ bản không được bỏ trống',
			number : 'Yêu cầu nhập đúng định dạng số',
		}
	},
	submitHandler : function(form) {
		form.submit();
	}
});

$('form[id="second_form2"]').validate({
	rules : {
		tenchucdanh : {
			required : true,
			minlength : 5,
			maxlength : 50,
		},
		hesochucvu : {
			required : true,
			number : true
		},
		hesotrachnhiem : {
			required : true,
			number : true
		},
		luongcoban : {
			required : true,
			number : true
		}
	},
	messages : {
		tenchucdanh : {
			required : 'Tên chức vụ không được bỏ trống',
			minlength : 'Tên chức vụ gồm 5 đến 50 kí tự',
			maxlength : 'Tên chức vụ gồm 5 đến 50 kí tự',
		},
		hesochucvu : {
			required : 'Hệ số chức vụ không được bỏ trống',
			number : 'Yêu cầu nhập đúng định dạng số',
		},
		hesotrachnhiem : {
			required : 'Hệ số trách nhiệm không được bỏ trống',
			number : 'Yêu cầu nhập đúng định dạng số',
		},
		luongcoban : {
			required : 'Lương cơ bản không được bỏ trống',
			number : 'Yêu cầu nhập đúng định dạng số',
		}
	},
	submitHandler : function(form) {
		form.submit();
	}
});
