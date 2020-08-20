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

import com.dangmailinh.entities.DmTinhtranghonnhan;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmTinhtranghonnhanServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class DmTrinhTrangHonNhanController {
	@Autowired
	private DmTinhtranghonnhanServiceImpl dmTinhtranghonnhanServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping(value = { "/quantri/findalltinhtranghonnhan" })
	public String findAllNgoaiNgu(HttpServletRequest request, Model model, @ModelAttribute("status") String status,
			@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("tinhTrangHonNhan", "Tình trạng hôn nhân");
		model.addAttribute("status", status);
		Page<DmTinhtranghonnhan> tinhTrangHonNhanPage = dmTinhtranghonnhanServiceImpl
				.findAllByTinhTrangHonNhan(pageable);
		Pagination<DmTinhtranghonnhan> page = new Pagination<DmTinhtranghonnhan>(tinhTrangHonNhanPage,
				"/quantri/findalltinhtranghonnhan");
		model.addAttribute("listTrinhTrangHonNhan", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmtinhtranghonnhan";
	}

	@GetMapping("/quantri/findonetinhtranghonnhan")
	@ResponseBody
	public Optional<DmTinhtranghonnhan> findById(Integer id) {
		return dmTinhtranghonnhanServiceImpl.findById(id);
	}

	@GetMapping(path = "/quantri/updateNotActivateTinhTrangHonNhan/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmTinhtranghonnhan lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmTinhtranghonnhanServiceImpl.findById(id).get().getGhichu());
		lhd.setTentinhtranghonnhan(dmTinhtranghonnhanServiceImpl.findById(id).get().getTentinhtranghonnhan());
		lhd.setStatus(0);
		dmTinhtranghonnhanServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalltinhtranghonnhan";
	}

	@GetMapping(path = "/quantri/updateActivateTinhTrangHonNhan/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmTinhtranghonnhan lhd, RedirectAttributes ra) {
		lhd.setGhichu(dmTinhtranghonnhanServiceImpl.findById(id).get().getGhichu());
		lhd.setTentinhtranghonnhan(dmTinhtranghonnhanServiceImpl.findById(id).get().getTentinhtranghonnhan());
		lhd.setStatus(1);
		dmTinhtranghonnhanServiceImpl.save(lhd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalltinhtranghonnhan";
	}

	@PostMapping(value = "/quantri/addTinhTrangHonNhan")
	public String addPhongBan(@RequestParam(name = "tentinhtranghonnhan") String tenTinhTrangHonNhan,
			@RequestParam(name = "ghichu") String ghiChu, Model model, RedirectAttributes ra) {
		List<DmTinhtranghonnhan> listTinhTrangHonNhan = dmTinhtranghonnhanServiceImpl
				.findAllByTenTinhTrangHonNhan(tenTinhTrangHonNhan);
		if (listTinhTrangHonNhan.size() >= 1) {
			ra.addFlashAttribute("status", "Tên tình trạng hôn nhân đã tồn tại");
			return "redirect:/quantri/findalltinhtranghonnhan";
		} else {
			DmTinhtranghonnhan dt = new DmTinhtranghonnhan();
			dt.setGhichu(ghiChu);
			dt.setTentinhtranghonnhan(tenTinhTrangHonNhan);
			dt.setStatus(1);
			dmTinhtranghonnhanServiceImpl.save(dt);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findalltinhtranghonnhan";
		}
	}

	@PostMapping(value = "/quantri/updateTinhTrangHonNhan")
	public String updateChucDanh(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "tentinhtranghonnhan") String tenTinhTrangHonNhan,
			@RequestParam(name = "ghichu") String ghiChu, @RequestParam(name = "status") Integer status, Model model,
			RedirectAttributes ra) {
		DmTinhtranghonnhan dt = new DmTinhtranghonnhan();
		dt.setId(id);
		dt.setGhichu(ghiChu);
		dt.setTentinhtranghonnhan(tenTinhTrangHonNhan);
		dt.setStatus(status);
		dmTinhtranghonnhanServiceImpl.save(dt);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findalltinhtranghonnhan";
	}

}
