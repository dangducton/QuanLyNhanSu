package com.dangmailinh.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dangmailinh.dto.QuanHeGiaDinhDTO;
import com.dangmailinh.entities.NvQuanhegiadinh;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.NvQuanhegiadinhServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class NvQuanHeGiaDinhController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	NvQuanhegiadinhServiceImpl nvQuanhegiadinhServiceImpl;
	
	@GetMapping("/quantri/listquanhe")
	public String showPage(HttpServletRequest request,Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvQuanhegiadinh> nhanVienPage = nvQuanhegiadinhServiceImpl.findAllByPhuCap(pageable);
		Pagination<NvQuanhegiadinh> page = new Pagination<NvQuanhegiadinh>(nhanVienPage, "/quantri/listquanhe");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("quanHeGiaDinh", "Quan hệ gia đình");
		model.addAttribute("listQuanHe", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/quanhegiadinh";
	}
	
	@GetMapping("/findonequanhegiadinh")
	@ResponseBody
	public QuanHeGiaDinhDTO findById(Integer id) {
		NvQuanhegiadinh cttl = nvQuanhegiadinhServiceImpl.findAllById(id);
		QuanHeGiaDinhDTO dto = new QuanHeGiaDinhDTO();
		dto.setDiachi(cttl.getDiachi());
		dto.setGhichu(cttl.getGhichu());
		dto.setHoten(cttl.getHoten());
		dto.setId(cttl.getId());
		dto.setNghenghiep(cttl.getNghenghiep());
		return dto;
	}
	
	
	@GetMapping("/quantri/deletequanhegiadinh")
	public String deleteQuanHeById(Integer id,RedirectAttributes redirectAttributes) {
		nvQuanhegiadinhServiceImpl.delete(id);
		redirectAttributes.addFlashAttribute("statusSuccess", "Xóa thành công");
		return "redirect:/quantri/listquanhe";
	}
	
	
	@PostMapping("/quantri/updateQuanHeGiaDinh")
	public String themMoiQuanHe(@ModelAttribute("statusSuccess") String statusSuccess,
			@RequestParam("id") Integer id,
			@RequestParam("hoten") String hoTen, @RequestParam("nghenghiep") String ngheNghiep,
			@RequestParam("diachi") String diaChi, @RequestParam("ghichu") String ghiChu,
			RedirectAttributes redirectAttributes) {
		NvQuanhegiadinh cttl = new NvQuanhegiadinh();
		cttl = nvQuanhegiadinhServiceImpl.findById(id).get();
		cttl.setDiachi(diaChi);
		cttl.setGhichu(ghiChu);
		cttl.setHoten(hoTen);
		cttl.setNghenghiep(ngheNghiep);
		cttl.setStatus(1);
		nvQuanhegiadinhServiceImpl.save(cttl);
		redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
		return "redirect:/quantri/listquanhe";
	}
}
