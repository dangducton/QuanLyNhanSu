package com.dangmailinh.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dangmailinh.dto.NguoiDungSignUpDTO;
import com.dangmailinh.entities.Role;
import com.dangmailinh.entities.User;
import com.dangmailinh.repository.RoleRepository;
import com.dangmailinh.service.NvNhanvienServiceImpl;
import com.dangmailinh.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private RoleRepository roleRespository;

	@Autowired
	private UserService userService;
	
	@Autowired
	NvNhanvienServiceImpl nvNhanvienServiceImpl;

	@GetMapping("/trangdangnhap")
	public String trangDangNhap(Model model, @ModelAttribute("errormessage") String status) {
		model.addAttribute("title", "Đăng nhập");
		model.addAttribute("errormessage", status);
		return "admin/login";
	}

	@GetMapping("/trangdangky")
	public String trangDangKy(Model model, @ModelAttribute("errormessage") String status) {
		model.addAttribute("title", "Đăng ký");
		model.addAttribute("nguoiDungSignUpDTO", new NguoiDungSignUpDTO());
		model.addAttribute("errormessage", status);
		return "admin/signup";
	}

	@GetMapping(value = "/loginfailure")
	public String loginfailure(Model model,RedirectAttributes ra) {
		ra.addFlashAttribute("errormessage", "Tài khoản và mật khẩu không chính xác");
		return "redirect:/trangdangnhap";
	}

	@GetMapping(value = "/accessdenied")
	public String accessdenied(Model model,RedirectAttributes ra) {
		ra.addFlashAttribute("errormessage", "Không đủ quyền try cập");
		return "redirect:/trangdangnhap";
	}

	@PostMapping(value = "/register")
	public String registration(@ModelAttribute NguoiDungSignUpDTO nguoiDungSignUpDTO, HttpServletRequest request,
			Model model, RedirectAttributes ra) {
		String confirmpassword = nguoiDungSignUpDTO.getConfirmpassword();
		String password = nguoiDungSignUpDTO.getPassword();
		User dbUser = userService.findBytendangnhap(nguoiDungSignUpDTO.getTendangnhap());
		if (dbUser == null) {
			User nd = new User();
			if (confirmpassword.equals(password) == true) {
					if (confirmpassword.length() >= 8 && password.length() >= 8) {
						Role role = roleRespository.findByName("ROLE_ADMIN");
						if (role == null) {
							role.setTenquyen("ROLE_ADMIN");
							role.setStatus(1);
							roleRespository.save(role);

						}
						nd.setRoleCollection(Arrays.asList(role));
						nd.setMatkhau(password);
						nd.setStatus(1);
						nd.setTendangnhap(nguoiDungSignUpDTO.getTendangnhap());
						nd.setIdNhanvien(nvNhanvienServiceImpl.findNhanVienByID(2));
						userService.save(nd);
						return "redirect:/quantri";
					} else {
						ra.addFlashAttribute("errormessage", "Mật khẩu gồm ít nhất 8 ký tự");
						return "redirect:/trangdangky";
					}
			} else {
				ra.addFlashAttribute("errormessage", "Yêu cầu nhập lại mật khẩu");
				return "redirect:/trangdangky";
			}
		} else {
			ra.addFlashAttribute("errormessage", "Email này đã được đăng ký");
			return "redirect:/trangdangky";
		}

	}
}
