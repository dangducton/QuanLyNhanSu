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

import com.dangmailinh.dto.QuaTrinhCongTacDTO;
import com.dangmailinh.entities.DmChucdanh;
import com.dangmailinh.entities.DmPhongban;
import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.entities.NvQuatrinhcongtac;
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
import com.dangmailinh.service.NvQuatrinhcongtacServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class QuaTrinhCongTacController {
	@Autowired
	private NvQuatrinhcongtacServiceImpl nvQuatrinhcongtacServiceImpl;

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
	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("/quantri/findallquatrinhcongtac")
	public String showPage(HttpServletRequest request,Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvQuatrinhcongtac> nhanVienPage = nvQuatrinhcongtacServiceImpl.findAllByQuaTrinhCongTac(pageable);
		Pagination<NvQuatrinhcongtac> page = new Pagination<NvQuatrinhcongtac>(nhanVienPage, "/quantri/findallquatrinhcongtac");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("quaTrinhCongTac", "Quá trình công tác");
		model.addAttribute("listQuaTrinhCongTac", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/listquatrinhcongtac";
	}

	@GetMapping("/quantri/baocaoquatrinhcongtac/{id}")
	public ResponseEntity<StreamingResponseBody> exportthongtinnhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();
		NvQuatrinhcongtac qtct = nvQuatrinhcongtacServiceImpl.findAllById(id);

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
		run2.setText("Về việc thuyên chuyển công tác");

		XWPFParagraph paragraph3 = document.createParagraph();
		paragraph3.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run3 = paragraph3.createRun();
		run3.setFontSize(13);
		run3.setFontFamily("Times New Roman");
		run3.setText("- Căn cứ vào các kế hoạch phát triển trong công ty ");
		run3.addBreak();
		run3.setText("- Căn cứ vào chức năng nhiệm vụ, quyền hạn và bộ máy tổ chức của Công ty TNHH Ánh Dương");
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
		cal.setTime(qtct.getDenngay());
		Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		cal1.setTime(qtct.getTungay());
		XWPFRun run8 = paragraph8.createRun();
		run8.setFontSize(13);
		run8.setFontFamily("Times New Roman");
		run8.setText("Điều 1. Nay chấp thuận cho Ông (Bà): " + qtct.getIdNhanvien().getHodem() + " "
				+ qtct.getIdNhanvien().getTen() + " đang công tác tại phòng " + qtct.getIdPhongban().getTenphongban()
				+ " với chức vụ " + qtct.getIdChucvu().getTenchucdanh() + " nay điều chuyển công tác tới phòng "
				+ qtct.getIdNhanvien().getIdPhongban().getTenphongban() + " với chức danh "
				+ qtct.getIdNhanvien().getIdChucdanh().getTenchucdanh() + " kể từ ngày "
				+ (cal.get(Calendar.DAY_OF_MONTH)+1) + " tháng " + (cal.get(Calendar.MONTH)+1) + " năm "
				+ cal.get(Calendar.YEAR));
		run8.addBreak();
		run8.setText("Điều 2. Quá trình công tác của Ông (Bà): " + qtct.getIdNhanvien().getHodem() + " "
				+ qtct.getIdNhanvien().getTen() + " kể từ ngày " + (cal1.get(Calendar.DAY_OF_MONTH)+1) + " tháng "
				+ (cal1.get(Calendar.MONTH)+1) + " năm " + cal1.get(Calendar.YEAR) + " công tác tại phòng "
				+ qtct.getIdPhongban().getTenphongban() + " nay điều chuyển công tác đến phòng "
				+ qtct.getIdNhanvien().getIdPhongban().getTenphongban() + " kể từ ngày "
				+ (cal.get(Calendar.DAY_OF_MONTH)+1) + " tháng " + (cal.get(Calendar.MONTH)+1) + " năm "
				+ cal.get(Calendar.YEAR) + " cho đến nay");
		run8.addBreak();
		run8.setText(
				"Điều 3. Những người liên quan có trách nhiệm hướng dẫn Ông (Bà): " + qtct.getIdNhanvien().getHodem()
						+ " " + qtct.getIdNhanvien().getTen() + " hoàn thành công việc ở phòng ban mới của mình");

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
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"quatrinhcongtac.docx\"")
				.body(document::write);
	}

	@PostMapping("/quantri/updateQuaTrinhCongTac")
	public String themMoiQuanHe(@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("id") Integer id,
			@RequestParam("tungay") String tungay, @RequestParam("denngay") String denngay,
			@RequestParam("ghichu") String ghiChu, RedirectAttributes redirectAttributes)
			throws ParseException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		NvQuatrinhcongtac cttl1 = new NvQuatrinhcongtac();
		Date date = formatter.parse(denngay);
		Date date1 = formatter.parse(tungay);
		cttl1.setId(id);
		cttl1.setIdChucvu(nvQuatrinhcongtacServiceImpl.findById(id).get().getIdChucvu());
		cttl1.setIdNhanvien(nvQuatrinhcongtacServiceImpl.findById(id).get().getIdNhanvien());
		cttl1.setIdPhongban(nvQuatrinhcongtacServiceImpl.findById(id).get().getIdPhongban());
		cttl1.setStatus(1);
		cttl1.setTungay(date1);
		cttl1.setDenngay(date);
		cttl1.setGhichu(ghiChu);
		nvQuatrinhcongtacServiceImpl.save(cttl1);
		redirectAttributes.addFlashAttribute("statusSuccess", "Update thành công");

		return "redirect:/quantri/findallquatrinhcongtac";
	}

	@GetMapping("/quantri/deletequatrinhcongtac")
	public String deleteById(Integer id) {
		nvQuatrinhcongtacServiceImpl.delete(id);
		return "redirect:/quantri/findallquatrinhcongtac";
	}

	@GetMapping("/quantri/findonequatrinhcongtac")
	@ResponseBody
	public QuaTrinhCongTacDTO findById(Integer id) {
		QuaTrinhCongTacDTO dto = new QuaTrinhCongTacDTO();
		dto.setId(nvQuatrinhcongtacServiceImpl.findById(id).get().getId());
		dto.setDenngay(nvQuatrinhcongtacServiceImpl.findById(id).get().getDenngay());
		dto.setGhichu(nvQuatrinhcongtacServiceImpl.findById(id).get().getGhichu());
		dto.setTungay(nvQuatrinhcongtacServiceImpl.findById(id).get().getTungay());
		return dto;
	}

	@PostMapping("/quantri/themmoiquatrinhcongtac")
	public String addNhanVien(Model model, @RequestParam("id") Integer id, @RequestParam("tungay") String tungay,
			@RequestParam("ghichu") String ghichu, @RequestParam("denngay") String denngay,
			@RequestParam("phongbanmoi") Integer phongbanmoi, RedirectAttributes redirectAttributes)
			throws ParseException, IOException {

		NvNhanvien nv = new NvNhanvien();
		nv = nvNhanvienServiceImpl.findById(id).get();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		NvQuatrinhcongtac qtct = new NvQuatrinhcongtac();

		DmPhongban phongbancu = new DmPhongban();
		phongbancu = dmPhongbanServiceImpl.findById(nv.getIdPhongban().getId()).get();
		DmChucdanh cd = new DmChucdanh();
		cd = dmChucdanhServiceImpl.findById(nv.getIdChucdanh().getId()).get();

		DmPhongban phongbanmoi1 = new DmPhongban();
		phongbanmoi1.setId(phongbanmoi);

		nv.setIdPhongban(phongbanmoi1);

		nvNhanvienServiceImpl.save(nv);

		qtct.setDenngay(formatter.parse(denngay));
		qtct.setTungay(formatter.parse(tungay));
		qtct.setIdChucvu(cd);
		qtct.setIdNhanvien(nv);
		qtct.setIdPhongban(phongbancu);
		qtct.setGhichu(ghichu);
		qtct.setStatus(1);
		nvQuatrinhcongtacServiceImpl.save(qtct);
		redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");

		return "redirect:/quantri/findallquatrinhcongtac";
	}

	@GetMapping(value = { "/quantri/listnhanvientheoquatrinhcongtac" })
	public String findAllChucDanh(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAll(pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage, "/quantri/listnhanvientheoquatrinhcongtac");
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
		return "admin/listnhanvientheoquatrinhcongtac";
	}

	@GetMapping("/quantri/findonenhanvienbyquatrinhcongtac/{id}")
	public String findonenhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status,HttpServletRequest request) {
		model.addAttribute("themNhanVien", "Tạo quá trình công tác");
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
		return "admin/taoquatrinhcongtac";
	}

	@GetMapping("/quantri/timkiemnhanvientheotenquatrinhcongtac")
	public String timKiemNhanVienTheoTen(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("search") String search) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllByTenNhanVien(search, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheotenquatrinhcongtac/" + search);
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
		return "admin/listnhanvientheoquatrinhcongtac";
	}

}
