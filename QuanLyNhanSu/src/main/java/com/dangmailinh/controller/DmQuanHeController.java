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

import com.dangmailinh.entities.DmQuanhe;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmQuanheServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class DmQuanHeController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private DmQuanheServiceImpl dmQuanheServiceImpl;

	@GetMapping(value = { "/quantri/findallquanhe" })
	public String findAllNgoaiNgu(HttpServletRequest request,Model model, @ModelAttribute("status") String status,
			@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("quanHe", "Quan hệ");
		model.addAttribute("status", status);
		Page<DmQuanhe> quanHePage = dmQuanheServiceImpl.findAllByQuanHe(pageable);
		Pagination<DmQuanhe> page = new Pagination<DmQuanhe>(quanHePage, "/quantri/findallquanhe");
		model.addAttribute("listQuanHe", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmquanhe";
	}

	@GetMapping("/quantri/findonequanhe")
	@ResponseBody
	public Optional<DmQuanhe> findById(Integer id) {
		return dmQuanheServiceImpl.findById(id);
	}

	@GetMapping(path = "/quantri/updateNotActivateQuanHe/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmQuanhe lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmQuanheServiceImpl.findById(id).get().getGhichu());
		lhd.setTenquanhe(dmQuanheServiceImpl.findById(id).get().getTenquanhe());
		lhd.setStatus(0);
		dmQuanheServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallquanhe";
	}

	@GetMapping(path = "/quantri/updateActivateQuanHe/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmQuanhe lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmQuanheServiceImpl.findById(id).get().getGhichu());
		lhd.setTenquanhe(dmQuanheServiceImpl.findById(id).get().getTenquanhe());
		lhd.setStatus(1);
		dmQuanheServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallquanhe";
	}

	@PostMapping(value = "/quantri/addQuanHe")
	public String addPhongBan(@RequestParam(name = "tenquanhe") String tenQuanHe,
			@RequestParam(name = "ghichu") String ghiChu, Model model, RedirectAttributes ra) {
		List<DmQuanhe> listQuanHe = dmQuanheServiceImpl.findAllByTenQuanHe(tenQuanHe);
		if (listQuanHe.size() >= 1) {
			ra.addFlashAttribute("status", "Tên quan hệ đã tồn tại");
			return "redirect:/quantri/findallquanhe";
		} else {
			DmQuanhe dt = new DmQuanhe();
			dt.setGhichu(ghiChu);
			dt.setTenquanhe(tenQuanHe);
			dt.setStatus(1);
			dmQuanheServiceImpl.save(dt);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findallquanhe";
		}
	}

	@PostMapping(value = "/quantri/updateQuanHe")
	public String updateChucDanh(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "tenquanhe") String tenQuanHe, @RequestParam(name = "ghichu") String ghiChu,
			@RequestParam(name = "status") Integer status, Model model, RedirectAttributes ra) {
		DmQuanhe dt = new DmQuanhe();
		dt.setId(id);
		dt.setGhichu(ghiChu);
		dt.setTenquanhe(tenQuanHe);
		dt.setStatus(status);
		dmQuanheServiceImpl.save(dt);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallquanhe";
	}

}
