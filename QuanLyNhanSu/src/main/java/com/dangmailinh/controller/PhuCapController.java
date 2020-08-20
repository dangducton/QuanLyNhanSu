package com.dangmailinh.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.entities.PhuCap;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmChucdanhServiceImpl;
import com.dangmailinh.service.DmChuyennganhServiceImpl;
import com.dangmailinh.service.DmDantocServiceImpl;
import com.dangmailinh.service.DmPhongbanServiceImpl;
import com.dangmailinh.service.DmPhucapServiceImpl;
import com.dangmailinh.service.DmQuanheServiceImpl;
import com.dangmailinh.service.DmTinhtranghonnhanServiceImpl;
import com.dangmailinh.service.DmTongiaoServiceImpl;
import com.dangmailinh.service.DmTrinhdoServiceImpl;
import com.dangmailinh.service.NvNhanvienServiceImpl;
import com.dangmailinh.service.NvQuanhegiadinhServiceImpl;
import com.dangmailinh.service.PhuCapServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class PhuCapController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	PhuCapServiceImpl phuCapServiceImpl;

	@Autowired
	DmPhucapServiceImpl dmPhucapServiceImpl;

	@Autowired
	DmChucdanhServiceImpl dmChucdanhServiceImpl;

	@Autowired
	DmChuyennganhServiceImpl dmChuyennganhServiceImpl;

	@Autowired
	DmDantocServiceImpl dmDantocServiceImpl;

	@Autowired
	DmPhongbanServiceImpl dmPhongbanServiceImpl;

	@Autowired
	DmQuanheServiceImpl dmQuanheServiceImpl;

	@Autowired
	DmTinhtranghonnhanServiceImpl dmTinhtranghonnhanServiceImpl;

	@Autowired
	DmTongiaoServiceImpl dmTongiaoServiceImpl;

	@Autowired
	NvNhanvienServiceImpl nvNhanvienServiceImpl;

	@Autowired
	DmTrinhdoServiceImpl dmTrinhdoServiceImpl;

	@Autowired
	NvQuanhegiadinhServiceImpl nvQuanhegiadinhServiceImpl;

	@GetMapping("/quantri/findallphucapnhanvien")
	public String showPage(HttpServletRequest request,Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<PhuCap> nhanVienPage = phuCapServiceImpl.findAllByPhuCap(pageable);
		Pagination<PhuCap> page = new Pagination<PhuCap>(nhanVienPage, "/quantri/findallphucapnhanvien");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("quaTrinhCongTac", "Phụ cấp");
		model.addAttribute("listQuaTrinhCongTac", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/listphucap";
	}

	@PostMapping("/quantri/updatephucapnhanvien")
	public String themMoiQuanHe(Model model, @RequestParam("id") Integer id, @RequestParam("idphucap") Integer phuCap,
			@RequestParam("phucap") Integer phucap, @RequestParam("thang") Integer thang,
			@RequestParam("nam") Integer nam, @RequestParam("ghichu") String ghichu,
			RedirectAttributes redirectAttributes) throws ParseException, IOException {
		PhuCap tu = new PhuCap();
		tu = phuCapServiceImpl.findById(id).get();
		tu.setGhichu(ghichu);
		tu.setIdPhucap(dmPhucapServiceImpl.findById(phucap).get());
		tu.setNam(nam);
		tu.setThang(thang);
		tu.setStatus(1);
		phuCapServiceImpl.save(tu);
		redirectAttributes.addFlashAttribute("statusSuccess", "Update thành công");
		return "redirect:/quantri/findallphucapnhanvien";
	}

	@GetMapping("/quantri/deletephucapnhanvien")
	public String deleteById(Integer id) {
		phuCapServiceImpl.delete(id);
		return "redirect:/quantri/findallphucapnhanvien";
	}

	@GetMapping("/quantri/findonephucapnhanvien")
	public String findById(Integer id, Model model, @ModelAttribute("status") String status,HttpServletRequest request) {
		model.addAttribute("themNhanVien", "Tạo phụ cấp nhân viên");
		model.addAttribute("chucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("chuyenNganh", dmChuyennganhServiceImpl.findAllActiveDmChuyenNganhNative());
		model.addAttribute("danToc", dmDantocServiceImpl.findAllActiveDmDanTocNative());
		model.addAttribute("phongBan", dmPhongbanServiceImpl.findAllActiveDmPhongBanNative());
		model.addAttribute("tinhTrangHonNhan", dmTinhtranghonnhanServiceImpl.findAllActiveDmTinhTrangHonNhanNative());
		model.addAttribute("tonGiao", dmTongiaoServiceImpl.findAllActiveDmTonGiaoNative());
		model.addAttribute("trinhDo", dmTrinhdoServiceImpl.findAllActiveDmTrinhDoNative());
		model.addAttribute("phuCap", dmPhucapServiceImpl.findAllActiveDmPhuCapNative());
		model.addAttribute("thongTinNhanVien", nvNhanvienServiceImpl.findNhanVienByID(id));
		model.addAttribute("phuCap1", phuCapServiceImpl.findById(id).get());
		List<Integer> thang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			thang.add(i);
		}
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		List<Integer> nam = new ArrayList<Integer>();
		for (int i = 2010; i <= year; i++) {
			nam.add(i);
		}
		model.addAttribute("nam", nam);
		model.addAttribute("thang", thang);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/updatephucap";
	}

	@PostMapping("/quantri/themmoiphucapnhanvien")
	public String addNhanVien(Model model, @RequestParam("id") Integer id, @RequestParam("phucap") Integer phucap,HttpServletRequest request,
			@RequestParam("thang") Integer thang, @RequestParam("nam") Integer nam,
			@RequestParam("ghichu") String ghichu, RedirectAttributes redirectAttributes)
			throws ParseException, IOException {
		NvNhanvien nv = new NvNhanvien();
		nv = nvNhanvienServiceImpl.findById(id).get();
		PhuCap pc = new PhuCap();
		pc.setGhichu(ghichu);
		pc.setIdNhanvien(nv);
		pc.setIdPhucap(dmPhucapServiceImpl.findById(phucap).get());
		pc.setNam(nam);
		pc.setStatus(1);
		pc.setThang(thang);
		phuCapServiceImpl.save(pc);
		redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
		return "redirect:/quantri/findallphucapnhanvien";

	}

	@GetMapping(value = { "/quantri/listnhanvientheophucapnhanvien" })
	public String findAllChucDanh(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAll(pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage, "/quantri/listnhanvientheophucapnhanvien");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("nhanVien", "Nhân viên");
		model.addAttribute("listNhanVien", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/listnhanvientheophucap";
	}

	@GetMapping("/quantri/findonenhanvienbyphucapnhanvien/{id}")
	public String findonenhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status,HttpServletRequest request) {
		model.addAttribute("themNhanVien", "Tạo phụ cấp nhân viên");
		model.addAttribute("chucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("chuyenNganh", dmChuyennganhServiceImpl.findAllActiveDmChuyenNganhNative());
		model.addAttribute("danToc", dmDantocServiceImpl.findAllActiveDmDanTocNative());
		model.addAttribute("phongBan", dmPhongbanServiceImpl.findAllActiveDmPhongBanNative());
		model.addAttribute("tinhTrangHonNhan", dmTinhtranghonnhanServiceImpl.findAllActiveDmTinhTrangHonNhanNative());
		model.addAttribute("tonGiao", dmTongiaoServiceImpl.findAllActiveDmTonGiaoNative());
		model.addAttribute("trinhDo", dmTrinhdoServiceImpl.findAllActiveDmTrinhDoNative());
		model.addAttribute("phuCap", dmPhucapServiceImpl.findAllActiveDmPhuCapNative());
		model.addAttribute("thongTinNhanVien", nvNhanvienServiceImpl.findNhanVienByID(id));
		List<Integer> thang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			thang.add(i);
		}
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		List<Integer> nam = new ArrayList<Integer>();
		for (int i = 2010; i <= year; i++) {
			nam.add(i);
		}
		model.addAttribute("nam", nam);
		model.addAttribute("thang", thang);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/taophucap";
	}

	@GetMapping("/quantri/timkiemnhanvientheotenphucapnhanvien")
	public String timKiemNhanVienTheoTen(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("search") String search) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllByTenNhanVien(search, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheotenphucapnhanvien/" + search);
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("nhanVien", "Nhân viên");
		model.addAttribute("listNhanVien", page.getContent());
		model.addAttribute("phongBan", dmPhongbanServiceImpl.findAllActiveDmPhongBanNative());
		model.addAttribute("chucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("trinhDo", dmTrinhdoServiceImpl.findAllActiveDmTrinhDoNative());
		model.addAttribute("chuyenNganh", dmChuyennganhServiceImpl.findAllActiveDmChuyenNganhNative());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/listnhanvientheophucap";
	}

}
