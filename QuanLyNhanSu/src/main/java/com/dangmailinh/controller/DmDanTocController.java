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

import com.dangmailinh.entities.DmDantoc;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmDantocServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class DmDanTocController {
		@Autowired
		private DmDantocServiceImpl dmDantocServiceImpl;
		
		@Autowired
		private UserServiceImpl userServiceImpl;

	@GetMapping(value = { "/quantri/findalldantoc" })
	public String findAllChucDanh(HttpServletRequest request,Model model, @ModelAttribute("status") String status,
			@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("danToc", "Dân tộc");
		model.addAttribute("status", status);
		Page<DmDantoc> danTocPage = dmDantocServiceImpl.findAllByDanToc(pageable);
		Pagination<DmDantoc> page = new Pagination<DmDantoc>(danTocPage, "/quantri/findalldantoc");
		model.addAttribute("listDanToc", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmdantoc";
	}

	@GetMapping("/quantri/findonedantoc")
	@ResponseBody
	public Optional<DmDantoc> findById(Integer id) {
		return dmDantocServiceImpl.findById(id);
	}

	@GetMapping(path = "/quantri/updateNotActivateDanToc/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmDantoc dt, RedirectAttributes ra) {
		dt.setGhichu(dmDantocServiceImpl.findById(id).get().getGhichu());
		dt.setTendantoc(dmDantocServiceImpl.findById(id).get().getTendantoc());
		dt.setStatus(0);
		dmDantocServiceImpl.save(dt);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalldantoc";
	}

	@GetMapping(path = "/quantri/updateActivateDanToc/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmDantoc dt, RedirectAttributes ra) {
		dt.setGhichu(dmDantocServiceImpl.findById(id).get().getGhichu());
		dt.setTendantoc(dmDantocServiceImpl.findById(id).get().getTendantoc());
		dt.setStatus(1);
		dmDantocServiceImpl.save(dt);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalldantoc";
	}

	@PostMapping(value = "/quantri/addDanToc")
	public String addPhongBan(@RequestParam(name = "tendantoc") String tenDanToc,
			@RequestParam(name = "ghichu") String ghiChu, Model model, RedirectAttributes ra) {
		List<DmDantoc> listDanToc = dmDantocServiceImpl.findAllByTenDanToc(tenDanToc);
		if (listDanToc.size() >= 1) {
			ra.addFlashAttribute("status", "Tên dân tộc đã tồn tại");
			return "redirect:/findalldantoc";
		} else {
			DmDantoc dt = new DmDantoc();
			dt.setGhichu(ghiChu);
			dt.setTendantoc(tenDanToc);
			dt.setStatus(1);
			dmDantocServiceImpl.save(dt);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findalldantoc";
		}
	}

	@PostMapping(value = "/quantri/updateDanToc")
	public String updateChucDanh(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "tendantoc") String tenDanToc, @RequestParam(name = "ghichu") String ghiChu,
			@RequestParam(name = "status") Integer status, Model model, RedirectAttributes ra) {
		DmDantoc dt = new DmDantoc();
		dt.setId(id);
		dt.setGhichu(ghiChu);
		dt.setTendantoc(tenDanToc);
		dt.setStatus(status);
		dmDantocServiceImpl.save(dt);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalldantoc";
	}
}
