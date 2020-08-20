$(document).ready(function () {
    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text(); // return New or Edit
        if (text === 'Sửa') {
            $.get(href, function (QuaTrinhCongTacDTO, status) {
            	$('.myForm #id').val(QuaTrinhCongTacDTO.id);
                $('.myForm #ghichu').val(QuaTrinhCongTacDTO.ghichu);
                $('.myForm #tungay').val(QuaTrinhCongTacDTO.tungay);
                $('.myForm #denngay').val(QuaTrinhCongTacDTO.denngay);
      
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
	 	 denngay: {
		      required: true,
		      },
		  tungay: {
		      required: true,
		}
	  },
	  messages: {
	    tungay: {
			required:'Không được bỏ trống',
		},
		  denngay: {
			required:'Không được bỏ trống',
		}
	  },
	  submitHandler: function(form) {
	    form.submit();
	  }
	});
