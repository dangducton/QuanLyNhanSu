package com.dangmailinh.controller;

import java.security.Principal;
import java.util.Arrays;

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

import com.dangmailinh.dto.DTOUserRole;
import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.entities.Role;
import com.dangmailinh.entities.User;
import com.dangmailinh.repository.RoleRepository;
import com.dangmailinh.service.NvNhanvienServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	NvNhanvienServiceImpl nvNhanvienServiceImpl;

	@GetMapping("/admin/themnhanvienquantri")
	public String showPage(Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess,HttpServletRequest request) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAll(pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage, "/admin/themnhanvienquantri");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("nhanVien", "Nhân viên");
		model.addAttribute("listNhanVien", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/danhsachnhanvienthemvaohethong";
	}

	@GetMapping(value = { "/admin/doimatkhaubyid" })
	@ResponseBody
	public DTOUserRole findonequantri(Integer id, Model model, @ModelAttribute("status") String status,
			@PageableDefault(size = 10) Pageable pageable) {
		DTOUserRole dto = new DTOUserRole();
		User u = new User();
		u = userServiceImpl.findById(id).get();
		dto.setId(u.getId());
		return dto;
	}

	@PostMapping(value = { "/admin/updateNhanVienHeThong" })
	public String updateNhanVienHeThong(Model model, @RequestParam("id") Integer id,
			@RequestParam("password") String password, @RequestParam("confirmpassword") String confirmpassword,
			RedirectAttributes ra) {
		if (password.equals(confirmpassword) == true && password.length() >= 8 && confirmpassword.length() >= 8) {
			User nd = new User();
			nd = userServiceImpl.findById(id).get();
			nd.setMatkhau(password);
			nd.setStatus(1);
			userServiceImpl.save(nd);
			ra.addFlashAttribute("statusSuccess", "Thêm thành công");
			return "redirect:/admin/danhsachquantri";
		} else {
			ra.addFlashAttribute("statusSuccess", "Yêu cầu nhập lại mật khẩu");
			return "redirect:/admin/danhsachquantri";
		}
	}

	@GetMapping(path = "/admin/updateNotActivateNguoiDung/{id}")
	public String updateNotActivate(@PathVariable("id") Integer id, RedirectAttributes ra) {
		User ng = userServiceImpl.findById(id).get();
		ng.setStatus(0);
		userServiceImpl.update(ng);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/admin/danhsachquantri";
	}

	@GetMapping(path = "/admin/updateActivateNguoiDung/{id}")
	public String updateActivate(@PathVariable("id") Integer id, RedirectAttributes ra) {
		User ng = userServiceImpl.findById(id).get();
		ng.setStatus(1);
		userServiceImpl.update(ng);
		ra.addFlashAttribute("status", "Update thành công");
		return "redirect:/admin/danhsachquantri";
	}

	@PostMapping(value = { "/admin/addNhanVienHeThong" })
	public String addNhanVienHeThong(Model model, @RequestParam("idquanhe") String idquanhe,
			@RequestParam("confirmpassword") String confirmpassword, @RequestParam("tendangnhap") String tendangnhap,
			@RequestParam("password") String password, @RequestParam("id") Integer id,

			RedirectAttributes ra) {
		User dbUser = userServiceImpl.findByIDNhanVien(id, tendangnhap);
		if (dbUser == null) {
			User nd = new User();
			if (confirmpassword.equals(password) == true) {
				if (confirmpassword.length() >= 8 && password.length() >= 8) {
					Role role = roleRepository.findByName(idquanhe);
					if (role == null) {
						role.setTenquyen(idquanhe);
						role.setStatus(1);
						roleRepository.save(role);

					}
					nd.setRoleCollection(Arrays.asList(role));
					nd.setMatkhau(password);
					nd.setStatus(1);
					nd.setTendangnhap(tendangnhap);
					nd.setIdNhanvien(nvNhanvienServiceImpl.findNhanVienByID(id));
					userServiceImpl.save(nd);
					ra.addFlashAttribute("statusSuccess", "Thêm thành công");
					return "redirect:/admin/danhsachquantri";
				} else {
					ra.addFlashAttribute("statusSuccess", "Mật khẩu gồm ít nhất 8 ký tự");
					return "redirect:/admin/themnhanvienquantri";
				}

			} else {
				ra.addFlashAttribute("statusSuccess", "Yêu cầu nhập lại mật khẩu");
				return "redirect:/admin/themnhanvienquantri";
			}
		} else {
			ra.addFlashAttribute("status", "Email này đã được đăng ký");
			return "redirect:/admin/themnhanvienquantri";
		}

	}

	@GetMapping(value = { "/admin/danhsachquantri" })
	public String danhsachquantri(Model model, @ModelAttribute("status") String status,HttpServletRequest request,
			@PageableDefault(size = 10) Pageable pageable) {
		model.addAttribute("danhMuc", "Danh sách quản trị hệ thống");
		model.addAttribute("status", status);
		Page<User> danTocPage = userServiceImpl.findAllQuanTri(pageable);
		Pagination<User> page = new Pagination<User>(danTocPage, "/admin/danhsachquantri");

		model.addAttribute("listNhanVien", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/danhsachquantri";
	}
}
