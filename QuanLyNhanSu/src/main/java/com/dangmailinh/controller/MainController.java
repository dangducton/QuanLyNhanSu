package com.dangmailinh.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dangmailinh.entities.PhuCap;
import com.dangmailinh.entities.TlBangluong;
import com.dangmailinh.entities.TlTamung;
import com.dangmailinh.entities.User;
import com.dangmailinh.repository.RoleRepository;
import com.dangmailinh.service.DmChucdanhServiceImpl;
import com.dangmailinh.service.DmPhongbanServiceImpl;
import com.dangmailinh.service.NvNhanvienServiceImpl;
import com.dangmailinh.service.NvQuyetdinhchamduthopdongServiceImpl;
import com.dangmailinh.service.NvQuyetdinhkhenthuongServiceImpl;
import com.dangmailinh.service.NvQuyetdinhkyluatServiceImpl;
import com.dangmailinh.service.NvQuyetdinhnanghesochuyenmonServiceImpl;
import com.dangmailinh.service.NvQuyetdinhthangchucServiceImpl;
import com.dangmailinh.service.PhuCapServiceImpl;
import com.dangmailinh.service.TIBangluongServiceImpl;
import com.dangmailinh.service.TITamungServiceImpl;
import com.dangmailinh.service.UserServiceImpl;


@Controller
public class MainController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private NvQuyetdinhchamduthopdongServiceImpl nvQuyetdinhchamduthopdongServiceImpl;
	
	@Autowired
	private NvQuyetdinhkhenthuongServiceImpl nvQuyetdinhkhenthuongServiceImpl;
	
	@Autowired
	private NvQuyetdinhkyluatServiceImpl nvQuyetdinhkyluatServiceImpl;
	
	@Autowired
	private NvQuyetdinhnanghesochuyenmonServiceImpl nvQuyetdinhnanghesochuyenmonServiceImpl;
	
	@Autowired
	private NvQuyetdinhthangchucServiceImpl nvQuyetdinhthangchucServiceImpl;
	
	@Autowired
	private PhuCapServiceImpl phuCapServiceImpl;
	
	@Autowired
	private TITamungServiceImpl tITamungServiceImpl;
	
	@Autowired
	private TIBangluongServiceImpl tIBangluongServiceImpl;
	
	@Autowired
	private DmPhongbanServiceImpl dmPhongbanServiceImpl;

	@Autowired
	private DmChucdanhServiceImpl dmChucdanhServiceImpl;
	

	@Autowired
	NvNhanvienServiceImpl nvNhanvienServiceImpl;

	@GetMapping(value = { "/quantri" })
	public String homepage(HttpServletRequest request, Model model) {
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		model.addAttribute("title","Trang chủ");
		model.addAttribute("soQuanTri", userServiceImpl.countquantri());
		model.addAttribute("soNhanVien", nvNhanvienServiceImpl.countnhanvien());
		model.addAttribute("soQuyetDinhChamDutHopDong", nvQuyetdinhchamduthopdongServiceImpl.countquyetdinhchamduthopdong());
		model.addAttribute("soQuyetDinhKhenThuong", nvQuyetdinhkhenthuongServiceImpl.countquyetdinhkhenthuong());
		model.addAttribute("soQuyetDinhKyLuat", nvQuyetdinhkyluatServiceImpl.countquyetdinhkyluat());
		model.addAttribute("soQuyetDinhNangHeSoChuyenMon", nvQuyetdinhnanghesochuyenmonServiceImpl.countquyetnanghesochuyenmon());
		model.addAttribute("soQuyetDinhThangChuc", nvQuyetdinhthangchucServiceImpl.countquyetdinhthangchuc());
		model.addAttribute("soQuyetDinhKhenThuong", nvQuyetdinhkhenthuongServiceImpl.countquyetdinhkhenthuong());
		model.addAttribute("soPhongBan", dmPhongbanServiceImpl.countphongban());
		model.addAttribute("soChucDanh", dmChucdanhServiceImpl.countchucdanh());
		List<PhuCap> pc = phuCapServiceImpl.findAllActivePhuCapNative();
		double tongTienPhuCap = 0;
		for (PhuCap phuCap : pc) {
			tongTienPhuCap = tongTienPhuCap + phuCap.getIdPhucap().getMucphucap();
		}
		
		List<TlTamung> tu = tITamungServiceImpl.findAllActiveTamungNative();
		double tongTienTamUng = 0;
		for (TlTamung phuCap : tu) {
			tongTienTamUng = tongTienTamUng + phuCap.getSotien();
		}
		
		List<TlBangluong> bl = tIBangluongServiceImpl.findAllActiveBangLuongNative();
		double tongTienLuong = 0;
		for (TlBangluong phuCap : bl) {
			tongTienLuong = tongTienLuong + phuCap.getThuclinh();
		}
		model.addAttribute("tongTienPhuCap", tongTienPhuCap);
		model.addAttribute("tongTienTamUng", tongTienTamUng);
		model.addAttribute("tongTienLuong", tongTienLuong);
		
		return "admin/home";
	}

//	@GetMapping(value = { "/"})
//	public String chamCong() {
//		return "index";
//	}

}
