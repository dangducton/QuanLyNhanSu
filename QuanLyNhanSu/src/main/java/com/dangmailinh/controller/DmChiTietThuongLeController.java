package com.dangmailinh.controller;

import java.security.Principal;

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

import com.dangmailinh.dto.UpdateChiTietThuongLeDTO;
import com.dangmailinh.entities.DmChitietthuongngayle;
import com.dangmailinh.entities.DmChucdanh;
import com.dangmailinh.entities.DmThuongngayle;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmChitietthuongngayleServiceImpl;
import com.dangmailinh.service.DmChucdanhServiceImpl;
import com.dangmailinh.service.DmThuongngayleServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class DmChiTietThuongLeController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private DmChucdanhServiceImpl dmChucdanhServiceImpl;
	@Autowired
	private DmThuongngayleServiceImpl dmThuongngayleServiceImpl;
	@Autowired
	private DmChitietthuongngayleServiceImpl dmChitietthuongngayleServiceImpl;

	@GetMapping(value = { "/quantri/findallchitietthuongle" })
	public String findAllChucDanh(HttpServletRequest request,Model model, @ModelAttribute("status") String status,
			@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("chiTietThuongLe", "Chi tiết thưởng lễ");
		model.addAttribute("status", status);
		model.addAttribute("listChucDanh", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("listThuongNgayLe", dmThuongngayleServiceImpl.findAllActiveDmThuongNgayLeNative());
		model.addAttribute("listChucDanh1", dmChucdanhServiceImpl.findAll());
		model.addAttribute("listThuongNgayLe1", dmThuongngayleServiceImpl.findAll());
		model.addAttribute("status", status);
		Page<DmChitietthuongngayle> chiTietThuongLePage = dmChitietthuongngayleServiceImpl
				.findAllByChiTietThuongLe(pageable);
		Pagination<DmChitietthuongngayle> page = new Pagination<DmChitietthuongngayle>(chiTietThuongLePage,
				"/quantri/findallchitietthuongle");
		model.addAttribute("listChiTietThuongLe", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmchitietthuongle";
	}

	@GetMapping("/quantri/findonechitietthuongle")
	@ResponseBody
	public UpdateChiTietThuongLeDTO findById(Integer id) {
		DmChitietthuongngayle cttl = new DmChitietthuongngayle();
		cttl = dmChitietthuongngayleServiceImpl.findByIdChiTietThuongLe(id);
		UpdateChiTietThuongLeDTO cttlDTO = new UpdateChiTietThuongLeDTO();
		cttlDTO.setGhichu(cttl.getGhichu());
		cttlDTO.setId(cttl.getId());
		cttlDTO.setIdChucdanh(dmChucdanhServiceImpl.findChucDanhByID(cttl.getIdChucdanh().getId()).getId());
		cttlDTO.setIdThuongngayle(cttl.getIdThuongngayle().getId());
		cttlDTO.setMucthuong(cttl.getMucthuong());
		cttlDTO.setStatus(cttl.getStatus());
		return cttlDTO;
	}

	@GetMapping(path = "/quantri/updateNotActivateChiTietThuongLe/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmChitietthuongngayle cttl, RedirectAttributes ra) {
		int idChucdanh = dmChitietthuongngayleServiceImpl.findById(id).get().getIdChucdanh().getId();
		int idThuongNgayLe = dmChitietthuongngayleServiceImpl.findById(id).get().getIdThuongngayle().getId();
		DmChucdanh cd = dmChucdanhServiceImpl.findChucDanhByID(idChucdanh);
		DmThuongngayle tnl = dmThuongngayleServiceImpl.findNgayLeById(idThuongNgayLe);
		cttl.setIdChucdanh(cd);
		cttl.setIdThuongngayle(tnl);
		cttl.setMucthuong(dmChitietthuongngayleServiceImpl.findById(id).get().getMucthuong());
		cttl.setGhichu(dmChitietthuongngayleServiceImpl.findById(id).get().getGhichu());
		cd.setStatus(0);
		dmChitietthuongngayleServiceImpl.save(cttl);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallchitietthuongle";
	}

	@GetMapping(path = "/quantri/updateActivateChiTietThuongLe/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmChitietthuongngayle cttl, RedirectAttributes ra) {
		int idChucdanh = dmChitietthuongngayleServiceImpl.findById(id).get().getIdChucdanh().getId();
		int idThuongNgayLe = dmChitietthuongngayleServiceImpl.findById(id).get().getIdThuongngayle().getId();
		DmChucdanh cd = dmChucdanhServiceImpl.findChucDanhByID(idChucdanh);
		DmThuongngayle tnl = dmThuongngayleServiceImpl.findNgayLeById(idThuongNgayLe);
		cttl.setIdChucdanh(cd);
		cttl.setIdThuongngayle(tnl);
		cttl.setMucthuong(dmChitietthuongngayleServiceImpl.findById(id).get().getMucthuong());
		cttl.setGhichu(dmChitietthuongngayleServiceImpl.findById(id).get().getGhichu());
		cttl.setStatus(1);
		dmChitietthuongngayleServiceImpl.save(cttl);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallchitietthuongle";
	}

	@PostMapping(value = "/quantri/addChiTietThuongLe")
	public String addPhongBan(@RequestParam(name = "idchucdanh") Integer idChucDanh,
			@RequestParam(name = "idthuongngayle") Integer IdThuongNgayLe,
			@RequestParam(name = "mucthuong") float mucThuong, @RequestParam(name = "ghichu") String ghiChu,
			Model model, RedirectAttributes ra) {
		DmChitietthuongngayle cd = new DmChitietthuongngayle();
		DmChucdanh dmcd = dmChucdanhServiceImpl.findChucDanhByID(idChucDanh);
		DmThuongngayle tnl = dmThuongngayleServiceImpl.findNgayLeById(IdThuongNgayLe);
		cd.setGhichu(ghiChu);
		cd.setIdChucdanh(dmcd);
		cd.setIdThuongngayle(tnl);
		cd.setMucthuong(mucThuong);
		cd.setStatus(1);
		dmChitietthuongngayleServiceImpl.save(cd);
		ra.addFlashAttribute("status", "Thêm thành công");
		return "redirect:/quantri/findallchitietthuongle";
	}

	@PostMapping(value = "/quantri/updateChiTietThuongLe")
	public String updateChucDanh(@RequestParam(name = "idchucdanh1") Integer idChucDanh,@RequestParam(name = "id") Integer id,
			@RequestParam(name = "idthuongngayle1") Integer IdThuongNgayLe,
			@RequestParam(name = "mucthuong") float mucThuong, @RequestParam(name = "ghichu") String ghiChu,
			@RequestParam(name = "status") Integer status, Model model, RedirectAttributes ra) {
		DmChitietthuongngayle cd = new DmChitietthuongngayle();
		DmChucdanh dmcd = dmChucdanhServiceImpl.findChucDanhByID(idChucDanh);
		DmThuongngayle tnl = dmThuongngayleServiceImpl.findNgayLeById(IdThuongNgayLe);
		cd.setId(id);
		cd.setGhichu(ghiChu);
		cd.setIdChucdanh(dmcd);
		cd.setIdThuongngayle(tnl);
		cd.setMucthuong(mucThuong);
		cd.setStatus(status);
		dmChitietthuongngayleServiceImpl.save(cd);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallchitietthuongle";
	}
}
