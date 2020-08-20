package com.dangmailinh.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dangmailinh.dto.DmThuongNgayLeDTO;
import com.dangmailinh.entities.DmThuongngayle;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmThuongngayleServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class DmThuongNgayLeController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private DmThuongngayleServiceImpl dmThuongngayleServiceImpl;

	@GetMapping(value = { "/quantri/findallthuongngayle" })
	public String findAllNgoaiNgu(HttpServletRequest request,Model model, @ModelAttribute("status") String status,
			@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("thuongNgayLe", "Thưởng ngày lễ");
		model.addAttribute("status", status);
		Page<DmThuongngayle> thuongNgayLePage = dmThuongngayleServiceImpl.findAllByThuongNgayLe(pageable);
		Pagination<DmThuongngayle> page = new Pagination<DmThuongngayle>(thuongNgayLePage, "/quantri/findallthuongngayle");
		model.addAttribute("listThuongNgayLe", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmthuongngayle";
	}

	@GetMapping("/quantri/findonethuongngayle")
	@ResponseBody
	public DmThuongNgayLeDTO findById(Integer id) {
		DmThuongngayle tnl = new DmThuongngayle();
		tnl = dmThuongngayleServiceImpl.findNgayLeById(id);
		DmThuongNgayLeDTO tnldto = new DmThuongNgayLeDTO();
		tnldto.setGhichu(tnl.getGhichu());
		tnldto.setId(tnl.getId());
		tnldto.setStatus(tnl.getStatus());
		tnldto.setTenngayle(tnl.getTenngayle());
		return tnldto;
	}

	@GetMapping(path = "/quantri/updateNotActivateThuongNgayLe/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmThuongngayle lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmThuongngayleServiceImpl.findById(id).get().getGhichu());
		lhd.setTenngayle(dmThuongngayleServiceImpl.findById(id).get().getTenngayle());
		lhd.setStatus(0);
		dmThuongngayleServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallthuongngayle";
	}

	@GetMapping(path = "/quantri/updateActivateThuongNgayLe/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmThuongngayle lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmThuongngayleServiceImpl.findById(id).get().getGhichu());
		lhd.setTenngayle(dmThuongngayleServiceImpl.findById(id).get().getTenngayle());
		lhd.setStatus(1);
		dmThuongngayleServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallthuongngayle";
	}

	@PostMapping(value = "/quantri/addThuongNgayLe")
	public String addPhongBan(@RequestParam(name = "tenngayle") String tenNgayLe,
			@RequestParam(name = "ghichu") String ghiChu, Model model, RedirectAttributes ra) {
		List<DmThuongngayle> listThuongNgayLe = dmThuongngayleServiceImpl.findAllByTenThuongNgayLe(tenNgayLe);
		if (listThuongNgayLe.size() >= 1) {
			ra.addFlashAttribute("status", "Tên ngày lễ đã tồn tại");
			return "redirect:/quantri/findallthuongngayle";
		} else {
			DmThuongngayle dt = new DmThuongngayle();
			dt.setGhichu(ghiChu);
			dt.setTenngayle(tenNgayLe);
			dt.setStatus(1);
			dmThuongngayleServiceImpl.save(dt);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findallthuongngayle";
		}
	}

	@PostMapping(value = "/quantri/updateThuongNgayLe")
	public String updateChucDanh(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "tenngayle") String tenNgayLe, @RequestParam(name = "ghichu") String ghiChu,
			@RequestParam(name = "status") Integer status, Model model, RedirectAttributes ra) {
		DmThuongngayle dt = new DmThuongngayle();
		dt.setId(id);
		dt.setGhichu(ghiChu);
		dt.setTenngayle(tenNgayLe);
		dt.setStatus(status);
		dmThuongngayleServiceImpl.save(dt);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallthuongngayle";
	}

}
