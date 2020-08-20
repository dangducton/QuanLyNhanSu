package com.dangmailinh.controller;


import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dangmailinh.dto.QuyetDinhChamDutHopDongDTO;
import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.entities.NvQuyetdinhchamduthopdong;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmChucdanhServiceImpl;
import com.dangmailinh.service.DmChuyennganhServiceImpl;
import com.dangmailinh.service.DmDantocServiceImpl;
import com.dangmailinh.service.DmPhongbanServiceImpl;
import com.dangmailinh.service.DmQuanheServiceImpl;
import com.dangmailinh.service.DmTinhtranghonnhanServiceImpl;
import com.dangmailinh.service.DmTongiaoServiceImpl;
import com.dangmailinh.service.DmTrinhdoServiceImpl;
import com.dangmailinh.service.NvNhanvienServiceImpl;
import com.dangmailinh.service.NvQuanhegiadinhServiceImpl;
import com.dangmailinh.service.NvQuyetdinhchamduthopdongServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class QuyetDinhChamDutHopDongController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	NvQuyetdinhchamduthopdongServiceImpl nvQuyetdinhchamduthopdongServiceImpl;

	@Autowired
	DmChucdanhServiceImpl dmChucdanhServiceImpl;

	@Autowired
	DmChuyennganhServiceImpl dmChuyennganhServiceImpl;

	@Autowired
	DmDantocServiceImpl dmDantocServiceImpl;

	@Autowired
	DmPhongbanServiceImpl dmPhongbanServiceImpl;

	@Autowired
	DmQuanheServiceImpl dmQuanheServiceImpl;

	@Autowired
	DmTinhtranghonnhanServiceImpl dmTinhtranghonnhanServiceImpl;

	@Autowired
	DmTongiaoServiceImpl dmTongiaoServiceImpl;

	@Autowired
	NvNhanvienServiceImpl nvNhanvienServiceImpl;

	@Autowired
	DmTrinhdoServiceImpl dmTrinhdoServiceImpl;

	@Autowired
	NvQuanhegiadinhServiceImpl nvQuanhegiadinhServiceImpl;

	@GetMapping("/quantri/findallquyetdinhchamduthopdong")
	public String showPage(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvQuyetdinhchamduthopdong> nhanVienPage = nvQuyetdinhchamduthopdongServiceImpl
				.findAllByQuyetDinhChamDutHopDong(pageable);
		Pagination<NvQuyetdinhchamduthopdong> page = new Pagination<NvQuyetdinhchamduthopdong>(nhanVienPage,
				"/quantri/findallquyetdinhchamduthopdong");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("quaTrinhCongTac", "Quyết định chấm dứt hợp đồng");
		model.addAttribute("listQuaTrinhCongTac", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/listquyetdinhchamduthopdong";
	}

	@GetMapping("/quantri/baocaoquyetdinhchamduthopdong/{id}")
	public  ResponseEntity<StreamingResponseBody> exportthongtinnhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();
		NvQuyetdinhchamduthopdong qtct = nvQuyetdinhchamduthopdongServiceImpl.findAllById(id);

		XWPFParagraph paragraph1 = document.createParagraph();
		paragraph1.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run1 = paragraph1.createRun();
		run1.setFontSize(13);
		run1.setFontFamily("Times New Roman");
		run1.setText("CÔNG TY TNHH ÁNH DƯƠNG");
		run1.addBreak();
		run1.setText("Số 10, ngách 565/29 Lạc Long Quân,");
		run1.addBreak();
		run1.setText("Tây Hồ, Hà Nội");
		run1.addBreak();
		run1.setText("Điện thoại:0919.83.1002");
		run1.addBreak();
		run1.setText("EMAIL: caycanhanhduong.hn@gmail.com");
		XWPFParagraph paragraph2 = document.createParagraph();
		paragraph2.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run2 = paragraph2.createRun();
		run2.setFontSize(24);
		run2.isBold();
		run2.setFontFamily("Times New Roman");
		run2.setText("QUYẾT ĐỊNH");
		run2.addBreak();
		run2.setText("Về việc quyết định nghỉ việc của nhân viên");
		XWPFParagraph paragraph3 = document.createParagraph();
		paragraph3.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run3 = paragraph3.createRun();
		run3.setFontSize(13);
		run3.setFontFamily("Times New Roman");
		run3.setText("- Căn cứ vào Bộ Luật lao động ");
		run3.addBreak();
		run3.setText("- Căn cứ vào các kế hoạch phát triển trong công ty ");
		run3.addBreak();
		run3.setText("- Căn cứ kế hoạch tinh giảm biên chế, quyền hạn và bộ máy tổ chức của Công ty TNHH Ánh Dương");
		run3.addBreak();
		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.isBold();
		run5.setFontFamily("Times New Roman");
		run5.setText("QUYẾT ĐỊNH");
		XWPFParagraph paragraph8 = document.createParagraph();
		paragraph8.setAlignment(ParagraphAlignment.LEFT);
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		cal.setTime(qtct.getNgayhieuluc());
		XWPFRun run8 = paragraph8.createRun();
		run8.setFontSize(13);
		run8.setFontFamily("Times New Roman");
		run8.setText(
				"Điều 1. Nay cho Ông (Bà): " + qtct.getIdNhanvien().getHodem() + " " + qtct.getIdNhanvien().getTen()
						+ " đang công tác tại phòng " + qtct.getIdNhanvien().getIdPhongban().getTenphongban()
						+ " với chức vụ " + qtct.getIdNhanvien().getIdChucdanh().getTenchucdanh()
						+ " nay được nghỉ việc kể từ ngày " + (cal.get(Calendar.DAY_OF_MONTH)+1) + " tháng "
						+ (cal.get(Calendar.MONTH)+1) + " năm " + cal.get(Calendar.YEAR));
		run8.addBreak();
		run8.setText("Điều 2. Quá trình công tác của Ông (Bà): " + qtct.getIdNhanvien().getHodem() + " "
				+ qtct.getIdNhanvien().getTen()
				+ " bàn giao lại tất cả các thiết bị, giấy tờ liên quan đến công ty của mình trước khi nghỉ tại công ty.");
		run8.addBreak();
		run8.setText("Điều 3. Nội dung quyết định: " + qtct.getNoidung());
		run8.addBreak();
		XWPFParagraph paragraph6 = document.createParagraph();
		paragraph6.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run6 = paragraph6.createRun();
		run6.setFontSize(13);
		run6.setFontFamily("Times New Roman");
		run6.setText("Thành phố Hà Nội, ngày    tháng    năm     ");
		XWPFParagraph paragraph7 = document.createParagraph();
		paragraph7.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run7 = paragraph7.createRun();
		run7.isBold();
		run7.setFontSize(13);
		run7.setFontFamily("Times New Roman");
		run7.setText("Giám đốc ký tên");
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"quyetdinhchamduthopdong.docx\"")
				.body(document::write);
	}

	@PostMapping("/quantri/updatequyetdinhchamduthopdong")
	public String themMoiQuanHe(@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("id") Integer id,
			@RequestParam("tenquyetdinh") String tenquyetdinh, @RequestParam("noidung") String noidung,
			@RequestParam("ngayhieuluc") String hieuluc, @RequestParam("ghichu") String ghiChu,
			RedirectAttributes redirectAttributes) throws ParseException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		NvQuyetdinhchamduthopdong cttl1 = new NvQuyetdinhchamduthopdong();
		cttl1 = nvQuyetdinhchamduthopdongServiceImpl.findById(id).get();
		cttl1.setGhichu(ghiChu);
		cttl1.setNgayhieuluc(formatter.parse(hieuluc));
		cttl1.setTenquyetdinh(tenquyetdinh);
		cttl1.setNoidung(noidung);
		nvQuyetdinhchamduthopdongServiceImpl.save(cttl1);
		redirectAttributes.addFlashAttribute("statusSuccess", "Update thành công");
		return "redirect:/quantri/findallquyetdinhchamduthopdong";
	}

	@GetMapping("/quantri/deletequyetdinhchamduthopdong")
	public String deleteById(Integer id) {
		nvQuyetdinhchamduthopdongServiceImpl.delete(id);
		return "redirect:/quantri/findallquyetdinhchamduthopdong";
	}

	@GetMapping("/quantri/findonequyetdinhchamduthopdong")
	@ResponseBody
	public QuyetDinhChamDutHopDongDTO findById(Integer id) {
		QuyetDinhChamDutHopDongDTO dto = new QuyetDinhChamDutHopDongDTO();
		dto.setId(nvQuyetdinhchamduthopdongServiceImpl.findById(id).get().getId());
		dto.setNgayhieuluc(nvQuyetdinhchamduthopdongServiceImpl.findById(id).get().getNgayhieuluc());
		dto.setNoidung(nvQuyetdinhchamduthopdongServiceImpl.findById(id).get().getNoidung());
		dto.setTenquyetdinh(nvQuyetdinhchamduthopdongServiceImpl.findById(id).get().getTenquyetdinh());
		dto.setGhichu(nvQuyetdinhchamduthopdongServiceImpl.findById(id).get().getGhichu());
		return dto;
	}

	@PostMapping("/quantri/themmoiquyetdinhchamduthopdong")
	public String addNhanVien(Model model, @RequestParam("id") Integer id,
			@RequestParam("tenquyetdinh") String tenquyetdinh, @RequestParam("hieuluc") String hieuluc,
			@RequestParam("noidung") String noidung, @RequestParam("ghichu") String ghichu,
			RedirectAttributes redirectAttributes) throws ParseException, IOException {

		NvNhanvien nv = new NvNhanvien();
		nv = nvNhanvienServiceImpl.findById(id).get();
		NvQuyetdinhchamduthopdong list = nvQuyetdinhchamduthopdongServiceImpl
				.findAllByQuyetDinhChamDutHopDong(id);
		if (list != null) {
			redirectAttributes.addFlashAttribute("statusSuccess", "Quyết định đã tồn tại");
			return "redirect:/quantri/findallquyetdinhchamduthopdong";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			NvQuyetdinhchamduthopdong qd = new NvQuyetdinhchamduthopdong();
			Date date = new Date();
			qd.setIdNhanvien(nv);
			qd.setNgayhieuluc(formatter.parse(hieuluc));
			qd.setNgayquyetdinh(date);
			qd.setNoidung(noidung);
			qd.setStatus(1);
			qd.setTenquyetdinh(tenquyetdinh);
			qd.setGhichu(ghichu);
			nv.setTrangthaihopdong(0);
			nvNhanvienServiceImpl.save(nv);
			nvQuyetdinhchamduthopdongServiceImpl.save(qd);
			redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
			return "redirect:/quantri/findallquyetdinhchamduthopdong";
		}
	}

	@GetMapping(value = { "/quantri/listnhanvientheoquyetdinhchamduthopdong" })
	public String findAllChucDanh(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAll(pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/listnhanvientheoquyetdinhchamduthopdong");
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
		return "admin/listnhanvientheoquyetdinhchamduthopdong";
	}

	@GetMapping("/quantri/findonenhanvienbyquyetdinhchamduthopdong/{id}")
	public String findonenhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status,HttpServletRequest request) {
		model.addAttribute("themNhanVien", "Tạo quyết định chấm dứt hợp đồng");
		model.addAttribute("chucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("chuyenNganh", dmChuyennganhServiceImpl.findAllActiveDmChuyenNganhNative());
		model.addAttribute("danToc", dmDantocServiceImpl.findAllActiveDmDanTocNative());
		model.addAttribute("phongBan", dmPhongbanServiceImpl.findAllActiveDmPhongBanNative());
		model.addAttribute("tinhTrangHonNhan", dmTinhtranghonnhanServiceImpl.findAllActiveDmTinhTrangHonNhanNative());
		model.addAttribute("tonGiao", dmTongiaoServiceImpl.findAllActiveDmTonGiaoNative());
		model.addAttribute("trinhDo", dmTrinhdoServiceImpl.findAllActiveDmTrinhDoNative());
		model.addAttribute("thongTinNhanVien", nvNhanvienServiceImpl.findNhanVienByID(id));
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/taoquyetdinhchamduthopdong";
	}

	@GetMapping("/quantri/timkiemnhanvientheotenquyetdinhchamduthopdong")
	public String timKiemNhanVienTheoTen(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("search") String search) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllByTenNhanVien(search, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheotenquyetdinhchamduthopdong/" + search);
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("nhanVien", "Nhân viên");
		model.addAttribute("listNhanVien", page.getContent());
		model.addAttribute("phongBan", dmPhongbanServiceImpl.findAllActiveDmPhongBanNative());
		model.addAttribute("chucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("trinhDo", dmTrinhdoServiceImpl.findAllActiveDmTrinhDoNative());
		model.addAttribute("chuyenNganh", dmChuyennganhServiceImpl.findAllActiveDmChuyenNganhNative());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/listnhanvientheoquyetdinhchamduthopdong";
	}

}
