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

import com.dangmailinh.dto.QuyetDinhKhenThuongDTO;
import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.entities.NvQuyetdinhnanghesochuyenmon;
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
import com.dangmailinh.service.NvQuyetdinhnanghesochuyenmonServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class QuyetDinhNangHeSoChuyenMon {
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	NvQuyetdinhnanghesochuyenmonServiceImpl nvQuyetdinhnanghesochuyenmonServiceImpl;

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

	@GetMapping("/quantri/findallquyetdinhnanghesochuyenmon")
	public String showPage(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvQuyetdinhnanghesochuyenmon> nhanVienPage = nvQuyetdinhnanghesochuyenmonServiceImpl
				.findAllByQuyetDinhNangHeSoChuyenMon(pageable);
		Pagination<NvQuyetdinhnanghesochuyenmon> page = new Pagination<NvQuyetdinhnanghesochuyenmon>(nhanVienPage,
				"/quantri/findallquyetdinhnanghesochuyenmon");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("quaTrinhCongTac", "Quyết định nâng hệ số chuyên môn");
		model.addAttribute("listQuaTrinhCongTac", page.getContent());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/listquyetdinhnanghesochuyenmon";
	}

	@GetMapping("/quantri/baocaoquyetdinhnanghesochuyenmon/{id}")
	public ResponseEntity<StreamingResponseBody> exportthongtinnhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();
		NvQuyetdinhnanghesochuyenmon qtct = nvQuyetdinhnanghesochuyenmonServiceImpl.findAllById(id);

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
		run2.setText("Về việc quyết định nâng hệ số chuyên môn");
		XWPFParagraph paragraph3 = document.createParagraph();
		paragraph3.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run3 = paragraph3.createRun();
		run3.setFontSize(13);
		run3.setFontFamily("Times New Roman");
		run3.setText("- Căn cứ vào Bộ Luật lao động ");
		run3.addBreak();
		run3.setText("- Căn cứ vào hồ sơ xét duyệt phòng nhân sự ");
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
				"Điều 1. Nay quyết định nâng hệ số chuyên môn thưởng Ông (Bà): " + qtct.getIdNhanvien().getHodem() + " " + qtct.getIdNhanvien().getTen()
						+ " đang công tác tại phòng " + qtct.getIdNhanvien().getIdPhongban().getTenphongban()
						+ " với chức vụ " + qtct.getIdNhanvien().getIdChucdanh().getTenchucdanh()+".");
		run8.addBreak();
		run8.setText("Điều 2. Ngày thành lập quyết định " + (cal.get(Calendar.DAY_OF_MONTH)+1) + " tháng " +(cal.get(Calendar.MONTH)+1) +" năm " + cal.get(Calendar.YEAR)+".");
		run8.addBreak();
		run8.setText("Điều 3. Hệ số chuyên môn cũ "+qtct.getIdTrinhdo().getHesochuyenmon() +" nay hệ số được nâng lên thành "+ qtct.getIdNhanvien().getIdTrinhdo().getHesochuyenmon());
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
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"quyetdinhnanghesochuyenmon.docx\"")
				.body(document::write);
	}

	@PostMapping("/quantri/updatequyetdinhnanghesochuyenmon")
	public String themMoiQuanHe(@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("id") Integer id,
			@RequestParam("tenquyetdinh") String tenquyetdinh, @RequestParam("noidung") String noidung,
			@RequestParam("ngayhieuluc") String hieuluc, @RequestParam("ghichu") String ghiChu,
			RedirectAttributes redirectAttributes) throws ParseException, IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		NvQuyetdinhnanghesochuyenmon cttl1 = new NvQuyetdinhnanghesochuyenmon();
		cttl1 = nvQuyetdinhnanghesochuyenmonServiceImpl.findById(id).get();
		cttl1.setGhichu(ghiChu);
		cttl1.setNgayhieuluc(formatter.parse(hieuluc));
		cttl1.setTenquyetdinh(tenquyetdinh);
		cttl1.setNoidung(noidung);
		nvQuyetdinhnanghesochuyenmonServiceImpl.save(cttl1);
		redirectAttributes.addFlashAttribute("statusSuccess", "Update thành công");
		return "redirect:/quantri/findallquyetdinhnanghesochuyenmon";
	}

	@GetMapping("/quantri/deletequyetdinhnanghesochuyenmon")
	public String deleteById(Integer id) {
		nvQuyetdinhnanghesochuyenmonServiceImpl.delete(id);
		return "redirect:/quantri/findallquyetdinhnanghesochuyenmon";
	}

	@GetMapping("/quantri/findonequyetdinhnanghesochuyenmon")
	@ResponseBody
	public QuyetDinhKhenThuongDTO findById(Integer id) {
		QuyetDinhKhenThuongDTO dto = new QuyetDinhKhenThuongDTO();
		dto.setId(nvQuyetdinhnanghesochuyenmonServiceImpl.findById(id).get().getId());
		dto.setNgayhieuluc(nvQuyetdinhnanghesochuyenmonServiceImpl.findById(id).get().getNgayhieuluc());
		dto.setNoidung(nvQuyetdinhnanghesochuyenmonServiceImpl.findById(id).get().getNoidung());
		dto.setTenquyetdinh(nvQuyetdinhnanghesochuyenmonServiceImpl.findById(id).get().getTenquyetdinh());
		dto.setGhichu(nvQuyetdinhnanghesochuyenmonServiceImpl.findById(id).get().getGhichu());
		return dto;
	}

	@PostMapping("/quantri/themmoiquyetdinhnanghesochuyenmon")
	public String addNhanVien(Model model, @RequestParam("id") Integer id,
			@RequestParam("tenquyetdinh") String tenquyetdinh, @RequestParam("hieuluc") String hieuluc,
			@RequestParam("chuyenmon") Integer chuyenmon, @RequestParam("noidung") String noidung,
			@RequestParam("ghichu") String ghichu, RedirectAttributes redirectAttributes)
			throws ParseException, IOException {

		NvNhanvien nv = new NvNhanvien();
		nv = nvNhanvienServiceImpl.findById(id).get();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
		Date date = formatter.parse(hieuluc);
		cal.setTime(date);
		NvQuyetdinhnanghesochuyenmon list = nvQuyetdinhnanghesochuyenmonServiceImpl.findAllByQuyetDinhNangHeSoChuyenMon(nv.getId(),
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);

		if (list != null) {
			redirectAttributes.addFlashAttribute("statusSuccess", "Quyết định đã tồn tại");
			return "redirect:/quantri/findallquyetdinhnanghesochuyenmon";
		} else {
			Date date1 = new Date();
			NvQuyetdinhnanghesochuyenmon qd = new NvQuyetdinhnanghesochuyenmon();
			qd.setIdNhanvien(nv);
			qd.setNgayhieuluc(formatter.parse(hieuluc));
			qd.setNgayquyetdinh(date1);
			qd.setNoidung(noidung);
			qd.setStatus(1);
			qd.setIdTrinhdo(nv.getIdTrinhdo());
			qd.setTenquyetdinh(tenquyetdinh);
			qd.setGhichu(ghichu);
			nv.setIdTrinhdo(dmTrinhdoServiceImpl.findById(chuyenmon).get());
			nvNhanvienServiceImpl.save(nv);
			nvQuyetdinhnanghesochuyenmonServiceImpl.save(qd);
			redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
			return "redirect:/quantri/findallquyetdinhnanghesochuyenmon";
		}
	}

	@GetMapping(value = { "/quantri/listnhanvientheoquyetdinhnanghesochuyenmon" })
	public String findAllChucDanh(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAll(pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/listnhanvientheoquyetdinhnanghesochuyenmon");
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
		return "admin/listnhanvientheoquyetdinhnanghesochuyenmon";
	}

	@GetMapping("/quantri/findonenhanvienbyquyetdinhnanghesochuyenmon/{id}")
	public String findonenhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status,HttpServletRequest request) {
		model.addAttribute("themNhanVien", "Tạo quyết định nâng hệ số chuyên môn");
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
		return "admin/taoquyetdinhnanghesochuyenmon";
	}

	@GetMapping("/quantri/timkiemnhanvientheotenquyetdinhnanghesochuyemon")
	public String timKiemNhanVienTheoTen(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("search") String search) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllByTenNhanVien(search, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheotenquyetdinhnanghesochuyemon/" + search);
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
		return "admin/listnhanvientheoquyetdinhnanghesochuyenmon";
	}

}
