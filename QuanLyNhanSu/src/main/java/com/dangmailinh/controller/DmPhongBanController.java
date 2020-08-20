package com.dangmailinh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.dangmailinh.entities.DmPhongban;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmPhongbanServiceImpl;
import com.dangmailinh.service.UserServiceImpl;

@Controller
public class DmPhongBanController {
	@Autowired
	private DmPhongbanServiceImpl dmPhongbanServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping(value = { "/quantri/findallphongban" })
	public String findAllPhongBan(HttpServletRequest request,Model model, @ModelAttribute("status") String status) {
		model.addAttribute("phongBan", "Phòng ban");
		model.addAttribute("status", status);
		model.addAttribute("listPhongBan", dmPhongbanServiceImpl.findAll());
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/dmphongban";
	}

	@GetMapping("/quantri/findonephongban")
	@ResponseBody
	public Optional<DmPhongban> findById(Integer id) {
		return dmPhongbanServiceImpl.findById(id);
	}

	@GetMapping(path = "/quantri/updateNotActivatePhongBan/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, DmPhongban pb, RedirectAttributes ra) {
		pb.setGhichu(dmPhongbanServiceImpl.findById(id).get().getGhichu());
		pb.setTenphongban(dmPhongbanServiceImpl.findById(id).get().getTenphongban());
		pb.setNgaythanhlap(dmPhongbanServiceImpl.findById(id).get().getNgaythanhlap());
		pb.setStatus(0);
		dmPhongbanServiceImpl.save(pb);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallphongban";
	}

	@GetMapping(path = "/quantri/updateActivatePhongBan/{id}")
	public String updateActivate(@PathVariable("id") Integer id, DmPhongban pb, RedirectAttributes ra) {
		pb.setGhichu(dmPhongbanServiceImpl.findById(id).get().getGhichu());
		pb.setTenphongban(dmPhongbanServiceImpl.findById(id).get().getTenphongban());
		pb.setNgaythanhlap(dmPhongbanServiceImpl.findById(id).get().getNgaythanhlap());
		pb.setStatus(1);
		dmPhongbanServiceImpl.save(pb);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallphongban";
	}

	@PostMapping(value = "/quantri/addPhongBan")
	public String addPhongBan(@RequestParam(name = "tenphongban") String tenPhongBan,
			@RequestParam(name = "ngaythanhlap") String ngayThanhLap, @RequestParam(name = "ghichu") String ghiChu,
			Model model, RedirectAttributes ra) throws java.text.ParseException {
		List<DmPhongban> listPhongBan = dmPhongbanServiceImpl.findAllByTenPhongBan(tenPhongBan);
		if (listPhongBan.size() >= 1) {
			ra.addFlashAttribute("status", "Tên phòng ban đã tồn tại");
			return "redirect:/quantri/findallphongban";
		} else {
			Date ngayThanhLapParse = null;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				ngayThanhLapParse = sdf.parse(ngayThanhLap);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			DmPhongban pb = new DmPhongban();
			pb.setGhichu(ghiChu);
			pb.setNgaythanhlap(ngayThanhLapParse);
			pb.setTenphongban(tenPhongBan);
			pb.setStatus(1);
			dmPhongbanServiceImpl.save(pb);
			ra.addFlashAttribute("status", "Thêm thành công");
			return "redirect:/quantri/findallphongban";
		}
	}

	@PostMapping(value = "/quantri/updatePhongBan")
	public String updatePhongBan(@RequestParam(name = "tenphongban") String tenPhongBan,
			@RequestParam(name = "id") Integer id, @RequestParam(name = "ngaythanhlap") String ngayThanhLap,
			@RequestParam(name = "status") Integer status, @RequestParam(name = "ghichu") String ghiChu, Model model,
			RedirectAttributes ra) throws java.text.ParseException {
		Date ngayThanhLapParse = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ngayThanhLapParse = sdf.parse(ngayThanhLap);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DmPhongban pb = new DmPhongban();
		pb.setId(id);
		pb.setGhichu(ghiChu);
		pb.setNgaythanhlap(ngayThanhLapParse);
		pb.setTenphongban(tenPhongBan);
		pb.setStatus(status);
		dmPhongbanServiceImpl.save(pb);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/quantri/findallphongban";
	}
}
