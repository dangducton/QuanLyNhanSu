$(document).ready(function () {
    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (QuyetDinhKhenThuongDTO, status) {
            	$('.myForm #id').val(QuyetDinhKhenThuongDTO.id);
                $('.myForm #ghichu').val(QuyetDinhKhenThuongDTO.ghichu);
                $('.myForm #tenquyetdinh').val(QuyetDinhKhenThuongDTO.tenquyetdinh);
                $('.myForm #noidung').val(QuyetDinhKhenThuongDTO.noidung);
                $('.myForm #ngayhieuluc').val(QuyetDinhKhenThuongDTO.ngayhieuluc);
                 $('.myForm #tienthuong').val(QuyetDinhKhenThuongDTO.tienthuong);      
            });
            $('.myForm #exampleModal').modal();
        }});
 
    
    	$('.nBtn').on('click', function (event) {
    		event.preventDefault();
    		$('.myForm #tentrinhdotinhoc').val('');
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



$('form[id="second_form2"]').validate({
	  rules: { 
	 	 tenquyetdinh: {
		      required: true,
		      },
		  noidung: {
		      required: true,
		}
	  },
	  messages: {
	    tenquyetdinh: {
			required:'Không được bỏ trống',
		},
		  noidung: {
			required:'Không được bỏ trống',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
