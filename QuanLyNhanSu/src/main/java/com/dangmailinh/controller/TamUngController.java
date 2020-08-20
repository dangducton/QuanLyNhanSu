package com.dangmailinh.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dangmailinh.dto.TamUngDTO;
import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.entities.TlTamung;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmChucdanhServiceImpl;
import com.dangmailinh.service.DmChuyennganhServiceImpl;
import com.dangmailinh.service.DmDantocServiceImpl;
import com.dangmailinh.service.DmPhongbanServiceImpl;
import com.dangmailinh.service.DmQuanheServiceImpl;
import com.dangmailinh.service.DmTinhtranghonnhanServiceImpl;
import com.dangmailinh.service.DmTongiaoServiceImpl;
import com.dangmailinh.service.DmTrinhdoServiceImpl;
import com.dangmailinh.service.NvNhanvienServiceImpl;
import com.dangmailinh.service.NvQuanhegiadinhServiceImpl;
import com.dangmailinh.service.TITamungServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class TamUngController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	TITamungServiceImpl tITamungServiceImpl;

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

	@GetMapping("/quantri/findalltamung")
	public String showPage(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<TlTamung> nhanVienPage = tITamungServiceImpl.findAllByTamung(pageable);
		Pagination<TlTamung> page = new Pagination<TlTamung>(nhanVienPage, "/quantri/findalltamung");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("quaTrinhCongTac", "Tạm ứng");
		model.addAttribute("listQuaTrinhCongTac", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/listtamung";
	}

	@PostMapping("/quantri/updatetamung")
	public String themMoiQuanHe(@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("id") Integer id,
			@RequestParam("ngaytamung") String ngaytamung, @RequestParam("lydo") String lydo,
			@RequestParam("sotien") double sotien, @RequestParam("ghichu") String ghiChu,
			RedirectAttributes redirectAttributes) throws ParseException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		TlTamung tu = new TlTamung();
		tu = tITamungServiceImpl.findById(id).get();
		tu.setNgaytamung(formatter.parse(ngaytamung));
		tu.setGhichu(ghiChu);
		tu.setLydo(lydo);
		tu.setSotien(sotien);
		tITamungServiceImpl.save(tu);
		redirectAttributes.addFlashAttribute("statusSuccess", "Update thành công");
		return "redirect:/quantri/findalltamung";
	}

	@GetMapping("/quantri/deletetamung")
	public String deleteById(Integer id) {
		tITamungServiceImpl.delete(id);
		return "redirect:/quantri/findalltamung";
	}

	@GetMapping("/quantri/findonetamung")
	@ResponseBody
	public TamUngDTO findById(Integer id) {
		TamUngDTO dto = new TamUngDTO();
		dto.setId(tITamungServiceImpl.findById(id).get().getId());
		dto.setGhichu(tITamungServiceImpl.findById(id).get().getGhichu());
		dto.setLydo(tITamungServiceImpl.findById(id).get().getLydo());
		dto.setNgaytamung(tITamungServiceImpl.findById(id).get().getNgaytamung());
		dto.setSotien(tITamungServiceImpl.findById(id).get().getSotien());
		return dto;
	}

	@PostMapping("/quantri/themmoitamung")
	public String addNhanVien(Model model, @RequestParam("id") Integer id, @RequestParam("sotien") double sotien,
			@RequestParam("ngaytamung") String ngaytamung, @RequestParam("lydo") String lydo,
			@RequestParam("ghichu") String ghichu, RedirectAttributes redirectAttributes)
			throws ParseException, IOException {
		NvNhanvien nv = new NvNhanvien();
		nv = nvNhanvienServiceImpl.findById(id).get();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date date = formatter.parse(ngaytamung);

		TlTamung tl = new TlTamung();
		tl.setGhichu(ghichu);
		tl.setStatus(1);
		tl.setSotien(sotien);
		tl.setNgaytamung(date);
		tl.setLydo(lydo);
		tl.setIdNhanvien(nv);
		tITamungServiceImpl.save(tl);

		redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
		return "redirect:/quantri/findalltamung";

	}

	@GetMapping(value = { "/quantri/listnhanvientheotamung" })
	public String findAllChucDanh(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAll(pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage, "/quantri/listnhanvientheotamung");
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
		return "admin/listnhanvientheotamung";
	}

	@GetMapping("/quantri/findonenhanvienbytamung/{id}")
	public String findonenhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status,HttpServletRequest request) {
		model.addAttribute("themNhanVien", "Tạo tạm ứng nhân viên");
		model.addAttribute("chucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("chuyenNganh", dmChuyennganhServiceImpl.findAllActiveDmChuyenNganhNative());
		model.addAttribute("danToc", dmDantocServiceImpl.findAllActiveDmDanTocNative());
		model.addAttribute("phongBan", dmPhongbanServiceImpl.findAllActiveDmPhongBanNative());
		model.addAttribute("tinhTrangHonNhan", dmTinhtranghonnhanServiceImpl.findAllActiveDmTinhTrangHonNhanNative());
		model.addAttribute("tonGiao", dmTongiaoServiceImpl.findAllActiveDmTonGiaoNative());
		model.addAttribute("trinhDo", dmTrinhdoServiceImpl.findAllActiveDmTrinhDoNative());
		model.addAttribute("thongTinNhanVien", nvNhanvienServiceImpl.findNhanVienByID(id));
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/taotamung";
	}

	@GetMapping("/quantri/timkiemnhanvientheotentamung")
	public String timKiemNhanVienTheoTen(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("search") String search) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllByTenNhanVien(search, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheotentamung/" + search);
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
		return "admin/listnhanvientheotamung";
	}

}
