package com.dangmailinh.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

import com.dangmailinh.entities.DmPhucap;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmPhucapServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class DmPhuCapController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private DmPhucapServiceImpl dmPhucapServiceImpl;

	@GetMapping(value = { "/quantri/findallphucap" })
	public String findAllKyLuat(HttpServletRequest request,Model model, @ModelAttribute("status") String status,
			@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("phuCap", "Phụ cấp");
		model.addAttribute("status", status);
		Page<DmPhucap> kyLuatPage = dmPhucapServiceImpl.findAllByPhuCap(pageable);
		Pagination<DmPhucap> page = new Pagination<DmPhucap>(kyLuatPage, "/quantri/findallphucap");
		model.addAttribute("listPhuCap", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmphucap";
	}

	@GetMapping("/quantri/findonephucap")
	@ResponseBody
	public Optional<DmPhucap> findById(Integer id) {
		return dmPhucapServiceImpl.findById(id);
	}

	@GetMapping(path = "/quantri/updateNotActivatePhuCap/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmPhucap kl, RedirectAttributes ra) {
		kl.setGhichu(dmPhucapServiceImpl.findById(id).get().getGhichu());
		kl.setTenphucap(dmPhucapServiceImpl.findById(id).get().getTenphucap());
		kl.setMucphucap(dmPhucapServiceImpl.findById(id).get().getMucphucap());
		kl.setStatus(0);
		dmPhucapServiceImpl.save(kl);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallphucap";
	}

	@GetMapping(path = "/quantri/updateActivatePhuCap/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmPhucap kl, RedirectAttributes ra) {
		kl.setGhichu(dmPhucapServiceImpl.findById(id).get().getGhichu());
		kl.setTenphucap(dmPhucapServiceImpl.findById(id).get().getTenphucap());
		kl.setMucphucap(dmPhucapServiceImpl.findById(id).get().getMucphucap());
		kl.setStatus(1);
		dmPhucapServiceImpl.save(kl);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallphucap";
	}

	@PostMapping(value = "/quantri/addPhuCap")
	public String addPhongBan(@RequestParam(name = "tenphucap") String tenPhuCap,
			@RequestParam(name = "mucphucap") float mucPhuCap, @RequestParam(name = "ghichu") String ghiChu,
			Model model, RedirectAttributes ra) throws java.text.ParseException {
		List<DmPhucap> listPhuCap = dmPhucapServiceImpl.findAllByTenPhuCap(tenPhuCap);
		if (listPhuCap.size() >= 1) {
			ra.addFlashAttribute("status", "Tên phụ cấp đã tồn tại");
			return "redirect:/quantri/findallphucap";
		} else {
			DmPhucap kl = new DmPhucap();
			kl.setGhichu(ghiChu);
			kl.setMucphucap(mucPhuCap);
			kl.setTenphucap(tenPhuCap);
			kl.setStatus(1);
			dmPhucapServiceImpl.save(kl);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findallphucap";
		}
	}

	@PostMapping(value = "/quantri/updatePhuCap")
	public String updatePhongBan(@RequestParam(name = "tenphucap") String tenPhuCap,
			@RequestParam(name = "id") Integer id, @RequestParam(name = "mucphucap") float mucPhuCap,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "ghichu") String ghiChu, Model model,
			RedirectAttributes ra) throws java.text.ParseException {
		DmPhucap kl = new DmPhucap();
		kl.setId(id);
		kl.setGhichu(ghiChu);
		kl.setMucphucap(mucPhuCap);
		kl.setTenphucap(tenPhuCap);
		kl.setStatus(status);
		dmPhucapServiceImpl.save(kl);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallphucap";
	}
}
