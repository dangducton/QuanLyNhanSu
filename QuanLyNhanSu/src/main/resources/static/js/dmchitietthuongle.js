$(document).ready(function() {
	$('.table .eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text(); // return New or Edit
		if (text === 'Sửa') {
			$.get(href, function(UpdateChiTietThuongLeDTO, status) {
				$('.myForm #id').val(UpdateChiTietThuongLeDTO.id);
				$('.myForm #mucthuong').val(UpdateChiTietThuongLeDTO.mucthuong);
				$('.myForm #status').val(UpdateChiTietThuongLeDTO.status);
				$('#idchucdanh1').val(UpdateChiTietThuongLeDTO.idChucdanh).prop('selected', true);
				$('#idthuongngayle1').val(UpdateChiTietThuongLeDTO.idThuongngayle).prop('selected', true);
				$('.myForm #ghichu').val(UpdateChiTietThuongLeDTO.ghichu);
			});
			$('.myForm #exampleModal').modal();
		}
	});
	
	$('.nBtn').on('click', function(event) {
		event.preventDefault();
		$('.myForm #mucthuong').val('');
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
		idthuongngayle : {
			required : true,
		},
		mucthuong: {
			required: true,
			number: true,
		},
		idchucdanh : {
			required : true,
		}
	},
	messages : {
		idthuongngayle : {
			required : 'Yêu cầu chọn ngày thưởng lễ',
		},
		mucthuong: {
			required:'Mức thưởng không được bỏ trống',
			number:'Yêu cầu nhập đúng định dạng số',
		},
		idchucdanh : {
			required : 'Yêu cầu chọn chức danh',
		}
	},
	submitHandler : function(form) {
		form.submit();
	}
});

$('form[id="second_form2"]').validate({
	rules : {
		idchucdanh1 : {
			required : true,
		},
		mucthuong: {
			required: true,
			number: true,
		},
		idthuongngayle1 : {
			required : true,
		}
	},
	messages : {
		idthuongngayle1 : {
			required : 'Yêu cầu chọn ngày thưởng lễ',
		},
		mucthuong: {
			required:'Mức thưởng không được bỏ trống',
			number:'Yêu cầu nhập đúng định dạng số',
		},
		idchucdanh1 : {
			required : 'Yêu cầu chọn chức danh',
		}
	},
	submitHandler : function(form) {
		form.submit();
	}
});

