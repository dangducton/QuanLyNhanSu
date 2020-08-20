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

import com.dangmailinh.entities.DmTrinhdo;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmTrinhdoServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class DmTrinhDoController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private DmTrinhdoServiceImpl dmTrinhdoServiceImpl;

	@GetMapping(value = { "/quantri/findalltrinhdo" })
	public String findAllNgoaiNgu(HttpServletRequest request,Model model, @ModelAttribute("status") String status,
			@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("trinhDo", "Trình độ");
		model.addAttribute("status", status);
		Page<DmTrinhdo> trinhDoPage = dmTrinhdoServiceImpl.findAllByTrinhDo(pageable);
		Pagination<DmTrinhdo> page = new Pagination<DmTrinhdo>(trinhDoPage, "/quantri/findalltrinhdo");
		model.addAttribute("listTrinhDo", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmtrinhdo";
	}

	@GetMapping("/quantri/findonetrinhdo")
	@ResponseBody
	public Optional<DmTrinhdo> findById(Integer id) {
		return dmTrinhdoServiceImpl.findById(id);
	}

	@GetMapping(path = "/quantri/updateNotActivateTrinhDo/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmTrinhdo lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmTrinhdoServiceImpl.findById(id).get().getGhichu());
		lhd.setTentrinhdo(dmTrinhdoServiceImpl.findById(id).get().getTentrinhdo());
		lhd.setHesochuyenmon(dmTrinhdoServiceImpl.findById(id).get().getHesochuyenmon());
		lhd.setStatus(0);
		dmTrinhdoServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalltrinhdo";
	}

	@GetMapping(path = "/quantri/updateActivateTrinhDo/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmTrinhdo lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmTrinhdoServiceImpl.findById(id).get().getGhichu());
		lhd.setTentrinhdo(dmTrinhdoServiceImpl.findById(id).get().getTentrinhdo());
		lhd.setHesochuyenmon(dmTrinhdoServiceImpl.findById(id).get().getHesochuyenmon());
		lhd.setStatus(1);
		dmTrinhdoServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalltrinhdo";
	}

	@PostMapping(value = "/quantri/addTrinhDo")
	public String addPhongBan(@RequestParam(name = "tentrinhdo") String tenTrinhDo,
			@RequestParam(name = "ghichu") String ghiChu,@RequestParam(name = "hesochuyenmon") float heSoChuyenMon, Model model, RedirectAttributes ra) {
		List<DmTrinhdo> listTrinhDo = dmTrinhdoServiceImpl.findAllByTenTrinhDo(tenTrinhDo);
		if (listTrinhDo.size() >= 1) {
			ra.addFlashAttribute("status", "Tên trình độ đã tồn tại");
			return "redirect:/quantri/findalltrinhdo";
		} else {
			DmTrinhdo dt = new DmTrinhdo();
			dt.setGhichu(ghiChu);
			dt.setTentrinhdo(tenTrinhDo);
			dt.setHesochuyenmon(heSoChuyenMon);
			dt.setStatus(1);
			dmTrinhdoServiceImpl.save(dt);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findalltrinhdo";
		}
	}

	@PostMapping(value = "/quantri/updateTrinhDo")
	public String updateChucDanh(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "tentrinhdo") String tenTrinhDo, @RequestParam(name = "ghichu") String ghiChu,
			@RequestParam(name = "hesochuyenmon") float heSoChuyenMon, @RequestParam(name = "status") Integer status,
			Model model, RedirectAttributes ra) {
		DmTrinhdo dt = new DmTrinhdo();
		dt.setId(id);
		dt.setGhichu(ghiChu);
		dt.setTentrinhdo(tenTrinhDo);
		dt.setHesochuyenmon(heSoChuyenMon);
		dt.setStatus(status);
		dmTrinhdoServiceImpl.save(dt);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalltrinhdo";
	}

}
