package com.dangmailinh.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dangmailinh.dto.DmChucDanhDTO;
import com.dangmailinh.entities.DmChucdanh;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmChucdanhServiceImpl;
import com.dangmailinh.service.UserServiceImpl;

@Controller
public class DmChucDanhController {
	@Autowired
	private DmChucdanhServiceImpl dmChucdanhServiceImpl;
	

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping(value = { "/quantri/findallchucdanh" })
	public String findAllChucDanh(HttpServletRequest request,Model model, @ModelAttribute("status") String status) {
		model.addAttribute("chucDanh", "Chức danh");
		model.addAttribute("status", status);
		model.addAttribute("listChucDanh", dmChucdanhServiceImpl.findAll());
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmchucdanh";
	}

	@GetMapping("/quantri/findonechucdanh")
	@ResponseBody
	public DmChucDanhDTO findById(Integer id) {
		DmChucdanh cd =  dmChucdanhServiceImpl.findChucDanhByID(id);
		DmChucDanhDTO cddto = new DmChucDanhDTO();
		cddto.setGhichu(cd.getGhichu());
		cddto.setHesochucvu(cd.getHesochucvu());
		cddto.setHesotrachnhiem(cd.getHesotrachnhiem());
		cddto.setId(cd.getId());
		cddto.setLuongcoban(cd.getLuongcoban());
		cddto.setTenchucdanh(cd.getTenchucdanh());
		cddto.setStatus(cd.getStatus());
		return cddto;
	}

	@GetMapping(path = "/quantri/updateNotActivateChucDanh/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmChucdanh cd, RedirectAttributes ra) {
		cd.setGhichu(dmChucdanhServiceImpl.findById(id).get().getGhichu());
		cd.setTenchucdanh(dmChucdanhServiceImpl.findById(id).get().getTenchucdanh());
		cd.setHesochucvu(dmChucdanhServiceImpl.findById(id).get().getHesochucvu());
		cd.setHesotrachnhiem(dmChucdanhServiceImpl.findById(id).get().getHesotrachnhiem());
		cd.setLuongcoban(dmChucdanhServiceImpl.findById(id).get().getLuongcoban());
		cd.setStatus(0);
		dmChucdanhServiceImpl.save(cd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallchucdanh";
	}

	@GetMapping(path = "/quantri/updateActivateChucDanh/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmChucdanh cd, RedirectAttributes ra) {
		cd.setGhichu(dmChucdanhServiceImpl.findById(id).get().getGhichu());
		cd.setTenchucdanh(dmChucdanhServiceImpl.findById(id).get().getTenchucdanh());
		cd.setHesochucvu(dmChucdanhServiceImpl.findById(id).get().getHesochucvu());
		cd.setHesotrachnhiem(dmChucdanhServiceImpl.findById(id).get().getHesotrachnhiem());
		cd.setLuongcoban(dmChucdanhServiceImpl.findById(id).get().getLuongcoban());
		cd.setStatus(1);
		dmChucdanhServiceImpl.save(cd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallchucdanh";
	}

	@PostMapping(value = "/quantri/addChucDanh")
	public String addPhongBan(@RequestParam(name = "tenchucdanh") String tenChucDanh,
			@RequestParam(name = "hesochucvu") float heSoChucVu,
			@RequestParam(name = "hesotrachnhiem") float heSoTrachNhiem,
			@RequestParam(name = "luongcoban") double luongCoBan, @RequestParam(name = "ghichu") String ghiChu,
			Model model, RedirectAttributes ra) {
		List<DmChucdanh> listChucDanh = dmChucdanhServiceImpl.findAllByTenChucDanh(tenChucDanh);
		if (listChucDanh.size() >= 1) {
			ra.addFlashAttribute("status", "Tên chức danh đã tồn tại");
			return "redirect:/findallchucdanh";
		} else {
			DmChucdanh cd = new DmChucdanh();
			cd.setGhichu(ghiChu);
			cd.setTenchucdanh(tenChucDanh);
			cd.setHesochucvu(heSoChucVu);
			cd.setHesotrachnhiem(heSoTrachNhiem);
			cd.setLuongcoban(luongCoBan);
			cd.setStatus(1);
			dmChucdanhServiceImpl.save(cd);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findallchucdanh";
		}
	}

	@PostMapping(value = "/quantri/updateChucDanh")
	public String updateChucDanh(@RequestParam(name = "tenchucdanh") String tenChucDanh,@RequestParam(name = "id") Integer id,
			@RequestParam(name = "hesochucvu") float heSoChucVu,
			@RequestParam(name = "hesotrachnhiem") float heSoTrachNhiem,
			@RequestParam(name = "luongcoban") double luongCoBan, @RequestParam(name = "ghichu") String ghiChu,@RequestParam(name = "status") Integer status,
			Model model, RedirectAttributes ra) {
		DmChucdanh cd = new DmChucdanh();
		cd.setId(id);
		cd.setGhichu(ghiChu);
		cd.setTenchucdanh(tenChucDanh);
		cd.setHesochucvu(heSoChucVu);
		cd.setHesotrachnhiem(heSoTrachNhiem);
		cd.setLuongcoban(luongCoBan);
		cd.setStatus(status);
		dmChucdanhServiceImpl.save(cd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallchucdanh";
	}
}
