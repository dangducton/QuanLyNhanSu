package com.dangmailinh.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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

import com.dangmailinh.entities.DmChuyennganh;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmChuyennganhServiceImpl;
import com.dangmailinh.service.UserServiceImpl;

@Controller
public class DmChuyenNganhController {
	@Autowired
	private DmChuyennganhServiceImpl dmChuyennganhServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping(value = { "/quantri/findallchuyennganh" })
	public String findAllChucDanh(HttpServletRequest request,Model model, @ModelAttribute("status") String status) {
		model.addAttribute("chuyenNganh", "Chuyên Ngành");
		model.addAttribute("status", status);
		model.addAttribute("listChuyenNganh", dmChuyennganhServiceImpl.findAll());
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmchuyennganh";
	}

	@GetMapping("/quantri/findonechuyennganh")
	@ResponseBody
	public Optional<DmChuyennganh> findById(Integer id) {
		return dmChuyennganhServiceImpl.findById(id);
	}

	@GetMapping(path = "/quantri/updateNotActivateChuyenNganh/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmChuyennganh cn, RedirectAttributes ra) {
		cn.setGhichu(dmChuyennganhServiceImpl.findById(id).get().getGhichu());
		cn.setTenchuyennganh(dmChuyennganhServiceImpl.findById(id).get().getTenchuyennganh());
		cn.setStatus(0);
		dmChuyennganhServiceImpl.save(cn);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallchuyennganh";
	}

	@GetMapping(path = "/updateActivateChuyenNganh/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmChuyennganh cn, RedirectAttributes ra) {
		cn.setGhichu(dmChuyennganhServiceImpl.findById(id).get().getGhichu());
		cn.setTenchuyennganh(dmChuyennganhServiceImpl.findById(id).get().getTenchuyennganh());
		cn.setStatus(1);
		dmChuyennganhServiceImpl.save(cn);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallchuyennganh";
	}

	@PostMapping(value = "/quantri/addChuyenNganh")
	public String addPhongBan(@RequestParam(name = "tenchuyennganh") String tenChuyenNganh,
			 @RequestParam(name = "ghichu") String ghiChu,
			Model model, RedirectAttributes ra) {
		List<DmChuyennganh> listChuyenNganh = dmChuyennganhServiceImpl.findAllByTenChuyenNganh(tenChuyenNganh);
		if (listChuyenNganh.size() >= 1) {
			ra.addFlashAttribute("status", "Tên chuyên ngành đã tồn tại");
			return "redirect:/quantri/findallchuyennganh";
		} else {
			DmChuyennganh cn = new DmChuyennganh();
			cn.setGhichu(ghiChu);
			cn.setTenchuyennganh(tenChuyenNganh);
			cn.setStatus(1);
			dmChuyennganhServiceImpl.save(cn);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findallchuyennganh";
		}
	}

	@PostMapping(value = "/quantri/updateChuyenNganh")
	public String updateChucDanh(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "tenchuyennganh") String tenChuyenNganh,
			 @RequestParam(name = "ghichu") String ghiChu,@RequestParam(name = "status") Integer status,
			Model model, RedirectAttributes ra) {
		DmChuyennganh cn = new DmChuyennganh();
		cn.setId(id);
		cn.setGhichu(ghiChu);
		cn.setTenchuyennganh(tenChuyenNganh);
		cn.setStatus(status);
		dmChuyennganhServiceImpl.save(cn);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallchuyennganh";
	}
}
