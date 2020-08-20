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

import com.dangmailinh.entities.DmKyluat;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmKyluatServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class DmKyLuatController {
	@Autowired
	private DmKyluatServiceImpl dmKyluatServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping(value = { "/quantri/findallkyluat" })
	public String findAllKyLuat(HttpServletRequest request,Model model, @ModelAttribute("status") String status,@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("kyLuat", "Kỷ luật");
		model.addAttribute("status", status);
		Page<DmKyluat> kyLuatPage = dmKyluatServiceImpl.findAllByKyLuat(pageable);
		Pagination<DmKyluat> page = new Pagination<DmKyluat>(kyLuatPage, "/quantri/findallkyluat");
		model.addAttribute("listKyLuat", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmkyluat";
	}

	@GetMapping("/quantri/findonekyluat")
	@ResponseBody
	public Optional<DmKyluat> findById(Integer id) {
		return dmKyluatServiceImpl.findById(id);
	}

	@GetMapping(path = "/quantri/updateNotActivateKyLuat/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmKyluat kl, RedirectAttributes ra) {
		kl.setGhichu(dmKyluatServiceImpl.findById(id).get().getGhichu());
		kl.setTenkyluat(dmKyluatServiceImpl.findById(id).get().getTenkyluat());
		kl.setMucphat(dmKyluatServiceImpl.findById(id).get().getMucphat());
		kl.setStatus(0);
		dmKyluatServiceImpl.save(kl);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallkyluat";
	}

	@GetMapping(path = "/quantri/updateActivateKyLuat/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmKyluat kl, RedirectAttributes ra) {
		kl.setGhichu(dmKyluatServiceImpl.findById(id).get().getGhichu());
		kl.setTenkyluat(dmKyluatServiceImpl.findById(id).get().getTenkyluat());
		kl.setMucphat(dmKyluatServiceImpl.findById(id).get().getMucphat());
		kl.setStatus(1);
		dmKyluatServiceImpl.save(kl);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallkyluat";
	}

	@PostMapping(value = "/quantri/addKyLuat")
	public String addPhongBan(@RequestParam(name = "tenkyluat") String tenKyLuat,
			@RequestParam(name = "mucphat") float mucPhat, @RequestParam(name = "ghichu") String ghiChu, Model model,
			RedirectAttributes ra) throws java.text.ParseException {
		List<DmKyluat> listKyLuat = dmKyluatServiceImpl.findAllByTenKyLuat(tenKyLuat);
		if (listKyLuat.size() >= 1) {
			ra.addFlashAttribute("status", "Tên kỷ luật đã tồn tại");
			return "redirect:/quantri/findallkyluat";
		} else {
			DmKyluat kl = new DmKyluat();
			kl.setGhichu(ghiChu);
			kl.setMucphat(mucPhat);
			kl.setTenkyluat(tenKyLuat);
			kl.setStatus(1);
			dmKyluatServiceImpl.save(kl);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findallkyluat";
		}
	}

	@PostMapping(value = "/quantri/updateKyLuat")
	public String updatePhongBan(@RequestParam(name = "tenkyluat") String tenKyLuat,
			@RequestParam(name = "id") Integer id, @RequestParam(name = "mucphat") float mucPhat,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "ghichu") String ghiChu, Model model,
			RedirectAttributes ra) throws java.text.ParseException {
		DmKyluat kl = new DmKyluat();
		kl.setId(id);
		kl.setGhichu(ghiChu);
		kl.setMucphat(mucPhat);
		kl.setTenkyluat(tenKyLuat);
		kl.setStatus(status);
		dmKyluatServiceImpl.save(kl);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallkyluat";
	}
}
