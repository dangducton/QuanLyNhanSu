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

import com.dangmailinh.entities.DmTongiao;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmTongiaoServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class DmTonGiaoController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private DmTongiaoServiceImpl dmTongiaoServiceImpl;

	@GetMapping(value = { "/quantri/findalltongiao" })
	public String findAllNgoaiNgu(HttpServletRequest request,Model model, @ModelAttribute("status") String status,
			@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("tonGiao", "Tôn giáo");
		model.addAttribute("status", status);
		Page<DmTongiao> tonGiaoPage = dmTongiaoServiceImpl.findAllByTonGiao(pageable);
		Pagination<DmTongiao> page = new Pagination<DmTongiao>(tonGiaoPage, "/quantri/findalltongiao");
		model.addAttribute("listTonGiao", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmtongiao";
	}

	@GetMapping("/quantri/findonetongiao")
	@ResponseBody
	public Optional<DmTongiao> findById(Integer id) {
		return dmTongiaoServiceImpl.findById(id);
	}

	@GetMapping(path = "/quantri/updateNotActivateTonGiao/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmTongiao lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmTongiaoServiceImpl.findById(id).get().getGhichu());
		lhd.setTentongiao(dmTongiaoServiceImpl.findById(id).get().getTentongiao());
		lhd.setStatus(0);
		dmTongiaoServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalltongiao";
	}

	@GetMapping(path = "/quantri/updateActivateTonGiao/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmTongiao lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmTongiaoServiceImpl.findById(id).get().getGhichu());
		lhd.setTentongiao(dmTongiaoServiceImpl.findById(id).get().getTentongiao());
		lhd.setStatus(1);
		dmTongiaoServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalltongiao";
	}

	@PostMapping(value = "/quantri/addTonGiao")
	public String addPhongBan(@RequestParam(name = "tentongiao") String tenTonGiao,
			@RequestParam(name = "ghichu") String ghiChu, Model model, RedirectAttributes ra) {
		List<DmTongiao> listTonGiao = dmTongiaoServiceImpl.findAllByTenTonGiao(tenTonGiao);
		if (listTonGiao.size() >= 1) {
			ra.addFlashAttribute("status", "Tên tôn giáo đã tồn tại");
			return "redirect:/quantri/findalltongiao";
		} else {
			DmTongiao dt = new DmTongiao();
			dt.setGhichu(ghiChu);
			dt.setTentongiao(tenTonGiao);
			dt.setStatus(1);
			dmTongiaoServiceImpl.save(dt);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findalltongiao";
		}
	}

	@PostMapping(value = "/updateTonGiao")
	public String updateChucDanh(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "tentongiao") String tenTonGiao, @RequestParam(name = "ghichu") String ghiChu,
			@RequestParam(name = "status") Integer status, Model model, RedirectAttributes ra) {
		DmTongiao dt = new DmTongiao();
		dt.setId(id);
		dt.setGhichu(ghiChu);
		dt.setTentongiao(tenTonGiao);
		dt.setStatus(status);
		dmTongiaoServiceImpl.save(dt);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalltongiao";
	}

}
