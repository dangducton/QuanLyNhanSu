$(document).ready(function () {

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (QuanHeGiaDinhDTO, status) {
            	$('.myForm #id').val(QuanHeGiaDinhDTO.id);
                $('.myForm #nghenghiep').val(QuanHeGiaDinhDTO.nghenghiep);
                $('.myForm #hoten').val(QuanHeGiaDinhDTO.hoten);
                $('.myForm #diachi').val(QuanHeGiaDinhDTO.diachi);
                $('.myForm #status').val(QuanHeGiaDinhDTO.status);
                $('.myForm #ghichu').val(QuanHeGiaDinhDTO.ghichu);
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
		  hoten: {
		      required: true,
		}
	  },
	  messages: {
		  hoten: {
			required:'Họ tên không được bỏ trống',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
