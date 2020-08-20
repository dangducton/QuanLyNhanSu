package com.dangmailinh.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Principal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.dangmailinh.dto.DTOQuyetDinhKhenThuong;
import com.dangmailinh.dto.DTOQuyetDinhKyLuat;
import com.dangmailinh.entities.DmChucdanh;
import com.dangmailinh.entities.DmChuyennganh;
import com.dangmailinh.entities.DmPhongban;
import com.dangmailinh.entities.DmTrinhdo;
import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.entities.NvQuyetdinhkhenthuong;
import com.dangmailinh.entities.NvQuyetdinhkyluat;
import com.dangmailinh.entities.TlBangluong;
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
import com.dangmailinh.service.NvQuyetdinhkhenthuongServiceImpl;
import com.dangmailinh.service.NvQuyetdinhkyluatServiceImpl;
import com.dangmailinh.service.TIBangluongServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class BaoCaoThongKeController {
	@Autowired
	NvQuyetdinhkhenthuongServiceImpl nvQuyetdinhkhenthuongServiceImpl;

	@Autowired
	DmChucdanhServiceImpl dmChucdanhServiceImpl;

	@Autowired
	TIBangluongServiceImpl tIBangluongServiceImpl;

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
	NvQuyetdinhkyluatServiceImpl nvQuyetdinhkyluatServiceImpl;

	@Autowired
	NvNhanvienServiceImpl nvNhanvienServiceImpl;

	@Autowired
	DmTrinhdoServiceImpl dmTrinhdoServiceImpl;

	@Autowired
	NvQuanhegiadinhServiceImpl nvQuanhegiadinhServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	public static String uploadDirectory = System.getProperty("user.dir") + "/upload";

	@GetMapping("/quantri/baocaothongkeluongtheophongban")
	public String baocaothongkelluongtheophongban(Model model, @PageableDefault(size = 10) Pageable pageable,
			HttpServletRequest request, @ModelAttribute("statusSuccess") String statusSuccess) {
		model.addAttribute("phongBan", "Thống kê theo phòng ban");
		model.addAttribute("listPhongBan", dmPhongbanServiceImpl.findAll());
		List<Integer> thang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			thang.add(i);
		}
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		List<Integer> nam = new ArrayList<Integer>();
		for (int i = 2010; i <= year; i++) {
			nam.add(i);
		}
		model.addAttribute("nam", nam);
		model.addAttribute("thang", thang);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/baocaothongkeluongtheophongban";
	}
	
	@GetMapping("/quantri/baocaothongkeluongtheothang")
	public String baocaothongkeluongtheophongban(Model model, @PageableDefault(size = 10) Pageable pageable,
			HttpServletRequest request, @ModelAttribute("statusSuccess") String statusSuccess) {
		model.addAttribute("phongBan", "Thống kê theo tháng");
		List<Integer> thang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			thang.add(i);
		}
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		List<Integer> nam = new ArrayList<Integer>();
		for (int i = 2010; i <= year; i++) {
			nam.add(i);
		}
		model.addAttribute("nam", nam);
		model.addAttribute("thang", thang);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/baocaothongkeluongtheothang";
	}

	@GetMapping("/quantri/initbaocaothongkekhenthuong")
	public String initbaocaothongkekhenthuong(Model model, @PageableDefault(size = 10) Pageable pageable,
			HttpServletRequest request, @ModelAttribute("statusSuccess") String statusSuccess) {
		model.addAttribute("phongBan", "Thống kê khen thưởng");

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		List<Integer> nam = new ArrayList<Integer>();
		for (int i = 2010; i <= year; i++) {
			nam.add(i);
		}
		model.addAttribute("nam", nam);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());

		return "admin/initbaocaothongkekhenthuong";
	}

	@GetMapping("/quantri/initbaocaothongkekyluat")
	public String initbaocaothongkekyluat(Model model, @PageableDefault(size = 10) Pageable pageable,
			HttpServletRequest request, @ModelAttribute("statusSuccess") String statusSuccess) {
		model.addAttribute("phongBan", "Thống kê kỷ luật");

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		List<Integer> nam = new ArrayList<Integer>();
		for (int i = 2010; i <= year; i++) {
			nam.add(i);
		}
		model.addAttribute("nam", nam);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());

		return "admin/initbaocaothongkekyluat";
	}

	@GetMapping("/quantri/danhsachquyetdinhkyluattheonam")
	public String danhsachquyetdinhkyluattheonam(@RequestParam("nam") Integer nam, Model model,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {
		Page<NvQuyetdinhkyluat> nhanVienPage = nvQuyetdinhkyluatServiceImpl.findAllByKyLuatTheoNam(nam, pageable);
		Pagination<NvQuyetdinhkyluat> page = new Pagination<NvQuyetdinhkyluat>(nhanVienPage,
				"/quantri/danhsachquyetdinhkyluattheonam");
		model.addAttribute("quaTrinhCongTac", "Danh sách quyết định");
		List<DTOQuyetDinhKyLuat> dto = new ArrayList<>();
		for (NvQuyetdinhkyluat nvQuyetdinhkhenthuong : nhanVienPage) {
			List<NvQuyetdinhkyluat> soLan = nvQuyetdinhkyluatServiceImpl.findAllByKyLuatTheoNamNhanVien(nam,
					nvQuyetdinhkhenthuong.getIdNhanvien().getId());
			int soLanKhenThuong = soLan.size();
			dto.add(new DTOQuyetDinhKyLuat(nvQuyetdinhkhenthuong.getIdNhanvien().getId(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdChiTiet(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getHodem() + " "
							+ nvQuyetdinhkhenthuong.getIdNhanvien().getTen(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getDiachihientai(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getNgaysinh(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getGioitinh(), soLanKhenThuong, nam,
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdChucdanh().getTenchucdanh(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdPhongban().getTenphongban(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdChucdanh().getLuongcoban(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdTrinhdo().getTentrinhdo()));
		}
		model.addAttribute("listNhanVien", dto);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		return "admin/danhsachquyetdinhkyluattheonam";
	}

	@GetMapping("/quantri/danhsachquyetdinhkhenthuongtheonam")
	public String danhsachquyetdinhkhenthuongtheonam(@RequestParam("nam") Integer nam, Model model,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest request) {
		Page<NvQuyetdinhkhenthuong> nhanVienPage = nvQuyetdinhkhenthuongServiceImpl.findAllByKhenThuongTheoNam(nam,
				pageable);
		Pagination<NvQuyetdinhkhenthuong> page = new Pagination<NvQuyetdinhkhenthuong>(nhanVienPage,
				"/quantri/danhsachquyetdinhkhenthuongtheonam");
		model.addAttribute("quaTrinhCongTac", "Danh sách quyết định");
		List<DTOQuyetDinhKhenThuong> dto = new ArrayList<>();
		for (NvQuyetdinhkhenthuong nvQuyetdinhkhenthuong : nhanVienPage) {
			List<NvQuyetdinhkhenthuong> soLan = nvQuyetdinhkhenthuongServiceImpl.findAllByKhenThuongTheoNamNhanVien(nam,
					nvQuyetdinhkhenthuong.getIdNhanvien().getId());
			int soLanKhenThuong = soLan.size();
			dto.add(new DTOQuyetDinhKhenThuong(nvQuyetdinhkhenthuong.getIdNhanvien().getId(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdChiTiet(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getHodem() + " "
							+ nvQuyetdinhkhenthuong.getIdNhanvien().getTen(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getDiachihientai(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getNgaysinh(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getGioitinh(), soLanKhenThuong, nam,
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdChucdanh().getTenchucdanh(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdPhongban().getTenphongban(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdChucdanh().getLuongcoban(),
					nvQuyetdinhkhenthuong.getIdNhanvien().getIdTrinhdo().getTentrinhdo()));
		}
		model.addAttribute("listNhanVien", dto);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		return "admin/danhsachquyetdinhkhenthuongtheonam";
	}

	@GetMapping("/quantri/exportthongtinnhanvienkyluat/{id}/{nam}")
	public ResponseEntity<StreamingResponseBody> exportthongtinnhanvienkyluat(@PathVariable int id,
			@PathVariable int nam, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();
		NvNhanvien nv = nvNhanvienServiceImpl.findNhanVienByID(id);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(nv.getNgaysinh());
		Locale localeEN = new Locale("en", "EN");
		NumberFormat en = NumberFormat.getInstance(localeEN);
		double longNumber = nv.getIdChucdanh().getLuongcoban();
		String str1 = en.format(longNumber);

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
		paragraph2.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run2 = paragraph2.createRun();
		run2.setFontSize(24);
		run2.isBold();
		run2.setFontFamily("Times New Roman");
		run2.setText("HỒ SƠ KỶ LUẬT NHÂN VIÊN        ");
		String imgFile = uploadDirectory + "\\" + nv.getHinhanh();
		FileInputStream is = new FileInputStream(imgFile);
		run2.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(70), Units.toEMU(70));
		is.close();

		XWPFParagraph paragraph3 = document.createParagraph();
		paragraph3.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run3 = paragraph3.createRun();
		run3.setFontSize(13);
		run3.setFontFamily("Times New Roman");
		run3.setText("I. Thông tin nhân viên: ");
		run3.addBreak();
		run3.setText("1. Mã nhân viên: " + nv.getIdChiTiet());
		run3.addBreak();
		run3.setText("2. Họ và tên: " + nv.getHodem() + " " + nv.getTen());
		run3.addBreak();
		run3.setText("3. Ngày sinh:" + strDate);
		run3.addBreak();
		run3.setText("4: Địa chỉ hiện tại: " + nv.getDiachihientai());
		run3.addBreak();
		run3.setText("5: Địa chỉ thường trú: " + nv.getDiachithuongtru());
		run3.addBreak();
		run3.setText("6: Số chứng minh nhân dân: " + nv.getCmnd());
		run3.addBreak();
		run3.setText("7: Nơi cấp: " + nv.getNoicapcmnd());
		run3.addBreak();
		run3.setText("8: Quốc tịch: " + nv.getQuoctich());
		run3.addBreak();
		run3.setText("9: Dân tộc: " + nv.getIdDantoc().getTendantoc());
		run3.addBreak();
		run3.setText("10: Tôn giáo: " + nv.getIdTongiao().getTentongiao());
		run3.addBreak();
		run3.setText("11: Tình trạng hôn nhân: " + nv.getIdTinhtranghonnhan().getTentinhtranghonnhan());
		run3.addBreak();
		run3.setText("12: Phòng ban: " + nv.getIdPhongban().getTenphongban());
		run3.addBreak();
		run3.setText("13: Chức danh: " + nv.getIdChucdanh().getTenchucdanh());
		run3.addBreak();
		run3.setText("14: Chuyên ngành: " + nv.getIdChucdanh().getTenchucdanh());
		run3.addBreak();
		run3.setText("15: Trình độ: " + nv.getIdTrinhdo().getTentrinhdo());
		run3.addBreak();
		run3.setText("16: Lương cơ bản: " + str1 + " đ");

		XWPFTable table = document.createTable();
		table.getCTTbl().getTblPr().unsetTblBorders();
		List<NvQuyetdinhkyluat> list = nvQuyetdinhkyluatServiceImpl.findAllByKyLuatTheoNamNhanVien(nam, id);
		XWPFTableRow tableRowTwo = table.getRow(0);
		XWPFRun run = tableRowTwo.addNewTableCell().addParagraph().createRun();
		run.setFontSize(13);
		run.setFontFamily("Times New Roman");
		run.setText("II. Số lần kỷ luật: ");
		run.addBreak();
		int lanquyetdinh = 1;
		double tongtien = 0;
		for (NvQuyetdinhkyluat list1 : list) {
			String strDate1 = formatter.format(list1.getNgayhieuluc());
			String str2 = en.format(list1.getTienphat());
			run.setText("Lần thứ: " + lanquyetdinh);
			run.addBreak();
			run.setText("Tên quyết định: " + list1.getTenquyetdinh());
			run.addBreak();
			run.setText("Nội dung khen thưởng: " + list1.getNoidung());
			run.addBreak();
			run.setText("Ngày hiệu lực: " + strDate1);
			run.addBreak();
			run.setText("Tiền khen thưởng: " + str2 + "đ");
			tongtien = tongtien + list1.getTienphat();
			run.addBreak();
			run.addBreak();
			lanquyetdinh++;
		}
		String str3 = en.format(tongtien);
		run.setText("Tổng tiền khen thưởng: " + str3 + "đ");
		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.setFontFamily("Times New Roman");
		run5.setText("Thành phố Hà Nội, ngày    tháng    năm     ");

		XWPFParagraph paragraph6 = document.createParagraph();
		paragraph6.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run6 = paragraph6.createRun();
		run6.isBold();
		run6.setFontSize(13);
		run6.setFontFamily("Times New Roman");
		run6.setText("Người tạo");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"baocaokhenthuongtheonamnhanvien.docx\"")
				.body(document::write);
	}

	@GetMapping("/quantri/exportthongtinnhanvienkhenthuong/{id}/{nam}")
	public ResponseEntity<StreamingResponseBody> exportthongtinnhanvienkhenthuong(@PathVariable int id,
			@PathVariable int nam, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();
		NvNhanvien nv = nvNhanvienServiceImpl.findNhanVienByID(id);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(nv.getNgaysinh());
		Locale localeEN = new Locale("en", "EN");
		NumberFormat en = NumberFormat.getInstance(localeEN);
		double longNumber = nv.getIdChucdanh().getLuongcoban();
		String str1 = en.format(longNumber);

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
		paragraph2.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run2 = paragraph2.createRun();
		run2.setFontSize(24);
		run2.isBold();
		run2.setFontFamily("Times New Roman");
		run2.setText("HỒ SƠ KHEN THƯỞNG NHÂN VIÊN        ");
		String imgFile = uploadDirectory + "\\" + nv.getHinhanh();
		FileInputStream is = new FileInputStream(imgFile);
		run2.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(70), Units.toEMU(70));
		is.close();

		XWPFParagraph paragraph3 = document.createParagraph();
		paragraph3.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run3 = paragraph3.createRun();
		run3.setFontSize(13);
		run3.setFontFamily("Times New Roman");
		run3.setText("I. Thông tin nhân viên: ");
		run3.addBreak();
		run3.setText("1. Mã nhân viên: " + nv.getIdChiTiet());
		run3.addBreak();
		run3.setText("2. Họ và tên: " + nv.getHodem() + " " + nv.getTen());
		run3.addBreak();
		run3.setText("3. Ngày sinh:" + strDate);
		run3.addBreak();
		run3.setText("4: Địa chỉ hiện tại: " + nv.getDiachihientai());
		run3.addBreak();
		run3.setText("5: Địa chỉ thường trú: " + nv.getDiachithuongtru());
		run3.addBreak();
		run3.setText("6: Số chứng minh nhân dân: " + nv.getCmnd());
		run3.addBreak();
		run3.setText("7: Nơi cấp: " + nv.getNoicapcmnd());
		run3.addBreak();
		run3.setText("8: Quốc tịch: " + nv.getQuoctich());
		run3.addBreak();
		run3.setText("9: Dân tộc: " + nv.getIdDantoc().getTendantoc());
		run3.addBreak();
		run3.setText("10: Tôn giáo: " + nv.getIdTongiao().getTentongiao());
		run3.addBreak();
		run3.setText("11: Tình trạng hôn nhân: " + nv.getIdTinhtranghonnhan().getTentinhtranghonnhan());
		run3.addBreak();
		run3.setText("12: Phòng ban: " + nv.getIdPhongban().getTenphongban());
		run3.addBreak();
		run3.setText("13: Chức danh: " + nv.getIdChucdanh().getTenchucdanh());
		run3.addBreak();
		run3.setText("14: Chuyên ngành: " + nv.getIdChucdanh().getTenchucdanh());
		run3.addBreak();
		run3.setText("15: Trình độ: " + nv.getIdTrinhdo().getTentrinhdo());
		run3.addBreak();
		run3.setText("16: Lương cơ bản: " + str1 + " đ");

		XWPFTable table = document.createTable();
		table.getCTTbl().getTblPr().unsetTblBorders();
		List<NvQuyetdinhkhenthuong> list = nvQuyetdinhkhenthuongServiceImpl.findAllByKhenThuongTheoNamNhanVien(nam, id);
		XWPFTableRow tableRowTwo = table.getRow(0);
		XWPFRun run = tableRowTwo.addNewTableCell().addParagraph().createRun();
		run.setFontSize(13);
		run.setFontFamily("Times New Roman");
		run.setText("II. Số lần khen thưởng: ");
		run.addBreak();
		int lanquyetdinh = 1;
		double tongtien = 0;
		for (NvQuyetdinhkhenthuong list1 : list) {
			String strDate1 = formatter.format(list1.getNgayhieuluc());
			String str2 = en.format(list1.getTienthuong());
			run.setText("Lần thứ: " + lanquyetdinh);
			run.addBreak();
			run.setText("Tên quyết định: " + list1.getTenquyetdinh());
			run.addBreak();
			run.setText("Nội dung khen thưởng: " + list1.getNoidung());
			run.addBreak();
			run.setText("Ngày hiệu lực: " + strDate1);
			run.addBreak();
			run.setText("Tiền khen thưởng: " + str2 + "đ");
			tongtien = tongtien + list1.getTienthuong();
			run.addBreak();
			run.addBreak();
			lanquyetdinh++;
		}
		String str3 = en.format(tongtien);
		run.setText("Tổng tiền khen thưởng: " + str3 + "đ");
		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.setFontFamily("Times New Roman");
		run5.setText("Thành phố Hà Nội, ngày    tháng    năm     ");

		XWPFParagraph paragraph6 = document.createParagraph();
		paragraph6.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run6 = paragraph6.createRun();
		run6.isBold();
		run6.setFontSize(13);
		run6.setFontFamily("Times New Roman");
		run6.setText("Người tạo");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"baocaokyluattheonamnhanvien.docx\"")
				.body(document::write);
	}

	@GetMapping("/quantri/baocaothongkeluongtheochucvu")
	public String baocaothongkeluongtheochucvu(HttpServletRequest request, Model model,
			@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("statusSuccess") String statusSuccess) {
		model.addAttribute("phongBan", "Thống kê theo chức vụ");
		model.addAttribute("listChucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		List<Integer> thang = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			thang.add(i);
		}
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		List<Integer> nam = new ArrayList<Integer>();
		for (int i = 2010; i <= year; i++) {
			nam.add(i);
		}
		model.addAttribute("nam", nam);
		model.addAttribute("thang", thang);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/baocaothongkeluongtheochucvu";
	}

	@GetMapping("/quantri/baocaothongkenhanvientheophongban")
	public String showPage(HttpServletRequest request, Model model,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		model.addAttribute("phongBan", "Phòng ban");
		model.addAttribute("listPhongBan", dmPhongbanServiceImpl.findAll());
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/baocaothongketheophongban";
	}

	@GetMapping(value = { "/quantri/baocaothongkenhanvientheochucvu" })
	public String findAllChucDanh(HttpServletRequest request, Model model) {
		model.addAttribute("chucDanh", "Chức danh");
		model.addAttribute("listChucDanh", dmChucdanhServiceImpl.findAll());
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/baocaothongketheochucvu";
	}

	@GetMapping(value = { "/quantri/baocaothongkenhanvientheochuyenganh" })
	public String findAllChucDanh(HttpServletRequest request, Model model, @ModelAttribute("status") String status) {
		model.addAttribute("chuyenNganh", "Chuyên Ngành");
		model.addAttribute("status", status);
		model.addAttribute("listChuyenNganh", dmChuyennganhServiceImpl.findAll());
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/baocaothongketheochuyennganh";
	}

	@GetMapping(value = { "/quantri/baocaothongkenhanvientheotrinhdo" })
	public String trinhdo(HttpServletRequest request, Model model, @ModelAttribute("status") String status,
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
		return "admin/baocaothongketheotrinhdo";
	}
	@GetMapping("/quantri/taobaocaoluongtheothang")
	public ResponseEntity<StreamingResponseBody> taobaocaoluongtheothang(@RequestParam("thang") Integer thang, @RequestParam("nam") Integer nam,
			Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();

		List<TlBangluong> list = tIBangluongServiceImpl.findAllBaoCaoLuongTheoThang(thang, nam);
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
		run2.setText("BÁO CÁO THỐNG KÊ LƯƠNNG");
		run2.addBreak();

		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.isBold();
		run5.setFontFamily("Times New Roman");
		run5.setText("Tháng " + thang + " năm " + nam);
		XWPFTable tab = document.createTable();
		XWPFTableRow row = tab.getRow(0); // First row

		CTTblWidth width = tab.getCTTbl().addNewTblPr().addNewTblW();
		width.setType(STTblWidth.DXA);
		width.setW(BigInteger.valueOf(9072));
		row.getCell(0).setText("ID nhân viên.");
		row.addNewTableCell().setText("Họ tên nhân viên");
		row.addNewTableCell().setText("Lương cơ bản");
		row.addNewTableCell().setText("Hệ số chức vụ");
		row.addNewTableCell().setText("Hệ số chuyên môn");
		row.addNewTableCell().setText("Hệ số trách nhiệm");
		row.addNewTableCell().setText("Tổng hệ số");
		row.addNewTableCell().setText("Tạm ứng");
		row.addNewTableCell().setText("Bảo hiểm");
		row.addNewTableCell().setText("Tiền phạt");
		row.addNewTableCell().setText("Tiền thưởng");
		row.addNewTableCell().setText("Tiền thưởng lễ");
		row.addNewTableCell().setText("Tiền phụ cấp");
		row.addNewTableCell().setText("Thực lĩnh");

		Locale localeEN = new Locale("vi", "VN");

		NumberFormat en = NumberFormat.getInstance(localeEN);
		for (TlBangluong qtct : list) {
			row = tab.createRow();
			String str1 = en.format(qtct.getTamung());
			String str2 = en.format(qtct.getTienphucap());
			String str3 = en.format(qtct.getTienthuong());
			String str4 = en.format(qtct.getTienphat());
			String str5 = en.format(qtct.getTienthuongle());
			String str6 = en.format(qtct.getTienbaohiem());
			String str7 = en.format(qtct.getThuclinh());
			String str8 = en.format(qtct.getMucluongcoban());
			row.getCell(0).setText(qtct.getIdNhanvien().getIdChiTiet());
			row.getCell(1).setText(qtct.getIdNhanvien().getHodem() + " " + qtct.getIdNhanvien().getTen());
			row.getCell(2).setText(str8);
			row.getCell(3).setText("" + qtct.getHesochucvu());
			row.getCell(4).setText("" + qtct.getHesochuyenmon());
			row.getCell(5).setText("" + qtct.getHesotrachnhiem());
			row.getCell(6).setText("" + qtct.getTongheso());
			row.getCell(7).setText(str1);
			row.getCell(8).setText(str6);
			row.getCell(9).setText(str4);
			row.getCell(10).setText(str3);
			row.getCell(11).setText(str5);
			row.getCell(12).setText(str2);
			row.getCell(13).setText(str7);
		}

		XWPFParagraph paragraph6 = document.createParagraph();
		paragraph6.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run6 = paragraph6.createRun();
		run6.setFontSize(13);
		run6.setFontFamily("Times New Roman");
		run6.setText("3 số cuối từ dấu phẩu không phải là tiền tệ");
		run6.addBreak();
		run6.setText("Thành phố Hà Nội, ngày    tháng    năm     ");
		XWPFParagraph paragraph7 = document.createParagraph();
		paragraph7.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run7 = paragraph7.createRun();
		run7.isBold();
		run7.setFontSize(13);
		run7.setFontFamily("Times New Roman");
		run7.setText("Người tạo phiếu");
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"aocaoluongtheothang.docx\"")
				.body(document::write);
	}

	
	@GetMapping("/quantri/taobaocaoluongtheochucvu")
	public ResponseEntity<StreamingResponseBody> taobaocaoluongtheochucvu(@RequestParam("thang") Integer thang, @RequestParam("nam") Integer nam,
			@RequestParam("chucvu") Integer chucvu, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();

		List<TlBangluong> list = tIBangluongServiceImpl.findAllBaoCaoLuongTheoChucDanh(chucvu, thang, nam);
		DmChucdanh pb = new DmChucdanh();
		pb = dmChucdanhServiceImpl.findById(chucvu).get();
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
		run2.setText("BÁO CÁO THỐNG KÊ LƯƠNNG");
		run2.addBreak();

		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.isBold();
		run5.setFontFamily("Times New Roman");
		run5.setText(pb.getTenchucdanh() + " tháng " + thang + " năm " + nam);
		XWPFTable tab = document.createTable();
		XWPFTableRow row = tab.getRow(0); // First row

		CTTblWidth width = tab.getCTTbl().addNewTblPr().addNewTblW();
		width.setType(STTblWidth.DXA);
		width.setW(BigInteger.valueOf(9072));
		row.getCell(0).setText("ID nhân viên.");
		row.addNewTableCell().setText("Họ tên nhân viên");
		row.addNewTableCell().setText("Lương cơ bản");
		row.addNewTableCell().setText("Hệ số chức vụ");
		row.addNewTableCell().setText("Hệ số chuyên môn");
		row.addNewTableCell().setText("Hệ số trách nhiệm");
		row.addNewTableCell().setText("Tổng hệ số");
		row.addNewTableCell().setText("Tạm ứng");
		row.addNewTableCell().setText("Bảo hiểm");
		row.addNewTableCell().setText("Tiền phạt");
		row.addNewTableCell().setText("Tiền thưởng");
		row.addNewTableCell().setText("Tiền thưởng lễ");
		row.addNewTableCell().setText("Tiền phụ cấp");
		row.addNewTableCell().setText("Thực lĩnh");

		Locale localeEN = new Locale("vi", "VN");

		NumberFormat en = NumberFormat.getInstance(localeEN);
		for (TlBangluong qtct : list) {
			row = tab.createRow();
			String str1 = en.format(qtct.getTamung());
			String str2 = en.format(qtct.getTienphucap());
			String str3 = en.format(qtct.getTienthuong());
			String str4 = en.format(qtct.getTienphat());
			String str5 = en.format(qtct.getTienthuongle());
			String str6 = en.format(qtct.getTienbaohiem());
			String str7 = en.format(qtct.getThuclinh());
			String str8 = en.format(qtct.getMucluongcoban());
			row.getCell(0).setText(qtct.getIdNhanvien().getIdChiTiet());
			row.getCell(1).setText(qtct.getIdNhanvien().getHodem() + " " + qtct.getIdNhanvien().getTen());
			row.getCell(2).setText(str8);
			row.getCell(3).setText("" + qtct.getHesochucvu());
			row.getCell(4).setText("" + qtct.getHesochuyenmon());
			row.getCell(5).setText("" + qtct.getHesotrachnhiem());
			row.getCell(6).setText("" + qtct.getTongheso());
			row.getCell(7).setText(str1);
			row.getCell(8).setText(str6);
			row.getCell(9).setText(str4);
			row.getCell(10).setText(str3);
			row.getCell(11).setText(str5);
			row.getCell(12).setText(str2);
			row.getCell(13).setText(str7);
		}

		XWPFParagraph paragraph6 = document.createParagraph();
		paragraph6.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run6 = paragraph6.createRun();
		run6.setFontSize(13);
		run6.setFontFamily("Times New Roman");
		run6.setText("3 số cuối từ dấu phẩu không phải là tiền tệ");
		run6.addBreak();
		run6.setText("Thành phố Hà Nội, ngày    tháng    năm     ");
		XWPFParagraph paragraph7 = document.createParagraph();
		paragraph7.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run7 = paragraph7.createRun();
		run7.isBold();
		run7.setFontSize(13);
		run7.setFontFamily("Times New Roman");
		run7.setText("Người tạo phiếu");
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"baocaoluongtheochucvu.docx\"")
				.body(document::write);
	}

	@GetMapping("/quantri/taobaocaoluongtheophongban")
	public ResponseEntity<StreamingResponseBody> taobaocaoluongtheophongban(@RequestParam("thang") Integer thang, @RequestParam("nam") Integer nam,
			@RequestParam("phongban") Integer phongban, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();

		List<TlBangluong> list = tIBangluongServiceImpl.findAllBaoCaoLuongTheoPhongBan(phongban, thang, nam);
		DmPhongban pb = new DmPhongban();
		pb = dmPhongbanServiceImpl.findById(phongban).get();
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
		run2.setText("BÁO CÁO THỐNG KÊ LƯƠNNG");
		run2.addBreak();

		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.isBold();
		run5.setFontFamily("Times New Roman");
		run5.setText(pb.getTenphongban() + " tháng " + thang + " năm " + nam);
		XWPFTable tab = document.createTable();
		XWPFTableRow row = tab.getRow(0); // First row

		CTTblWidth width = tab.getCTTbl().addNewTblPr().addNewTblW();
		width.setType(STTblWidth.DXA);
		width.setW(BigInteger.valueOf(9072));
		row.getCell(0).setText("ID nhân viên.");
		row.addNewTableCell().setText("Họ tên nhân viên");
		row.addNewTableCell().setText("Lương cơ bản");
		row.addNewTableCell().setText("Hệ số chức vụ");
		row.addNewTableCell().setText("Hệ số chuyên môn");
		row.addNewTableCell().setText("Hệ số trách nhiệm");
		row.addNewTableCell().setText("Tổng hệ số");
		row.addNewTableCell().setText("Tạm ứng");
		row.addNewTableCell().setText("Bảo hiểm");
		row.addNewTableCell().setText("Tiền phạt");
		row.addNewTableCell().setText("Tiền thưởng");
		row.addNewTableCell().setText("Tiền thưởng lễ");
		row.addNewTableCell().setText("Tiền phụ cấp");
		row.addNewTableCell().setText("Thực lĩnh");

		Locale localeEN = new Locale("vi", "VN");

		NumberFormat en = NumberFormat.getInstance(localeEN);
		for (TlBangluong qtct : list) {
			row = tab.createRow();
			String str1 = en.format(qtct.getTamung());
			String str2 = en.format(qtct.getTienphucap());
			String str3 = en.format(qtct.getTienthuong());
			String str4 = en.format(qtct.getTienphat());
			String str5 = en.format(qtct.getTienthuongle());
			String str6 = en.format(qtct.getTienbaohiem());
			String str7 = en.format(qtct.getThuclinh());
			String str8 = en.format(qtct.getMucluongcoban());
			row.getCell(0).setText(qtct.getIdNhanvien().getIdChiTiet());
			row.getCell(1).setText(qtct.getIdNhanvien().getHodem() + " " + qtct.getIdNhanvien().getTen());
			row.getCell(2).setText(str8);
			row.getCell(3).setText("" + qtct.getHesochucvu());
			row.getCell(4).setText("" + qtct.getHesochuyenmon());
			row.getCell(5).setText("" + qtct.getHesotrachnhiem());
			row.getCell(6).setText("" + qtct.getTongheso());
			row.getCell(7).setText(str1);
			row.getCell(8).setText(str6);
			row.getCell(9).setText(str4);
			row.getCell(10).setText(str3);
			row.getCell(11).setText(str5);
			row.getCell(12).setText(str2);
			row.getCell(13).setText(str7);
		}

		XWPFParagraph paragraph6 = document.createParagraph();
		paragraph6.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run6 = paragraph6.createRun();
		run6.setFontSize(13);
		run6.setFontFamily("Times New Roman");
		run6.setText("3 số cuối từ dấu phẩu không phải là tiền tệ");
		run6.addBreak();
		run6.setText("Thành phố Hà Nội, ngày    tháng    năm     ");
		XWPFParagraph paragraph7 = document.createParagraph();
		paragraph7.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run7 = paragraph7.createRun();
		run7.isBold();
		run7.setFontSize(13);
		run7.setFontFamily("Times New Roman");
		run7.setText("Người tạo phiếu");

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"baocaoluongtheophongban.docx\"")
				.body(document::write);
	}

	@GetMapping("/quantri/baocaonhanvientheotrinhdo/{id}")
	public ResponseEntity<StreamingResponseBody> trinhdotheo(@PathVariable int id, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();

		List<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllNhanVienBaoCaoTheoTrinhDo(id);
		DmTrinhdo pb = new DmTrinhdo();
		pb = dmTrinhdoServiceImpl.findById(id).get();
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
		run2.setText("BÁO CÁO THỐNG KÊ");
		run2.addBreak();

		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.isBold();
		run5.setFontFamily("Times New Roman");
		run5.setText(pb.getTentrinhdo());
		XWPFTable tab = document.createTable();
		XWPFTableRow row = tab.getRow(0); // First row

		row.getCell(0).setText("ID nhân viên.");
		row.addNewTableCell().setText("Họ tên nhân viên");
		row.addNewTableCell().setText("Lương cơ bản");
		row.addNewTableCell().setText("Phòng ban");
		row.addNewTableCell().setText("Chức danh");
		row.addNewTableCell().setText("Trình độ");
		row.addNewTableCell().setText("Chuyên ngành");
		Locale localeEN = new Locale("vi", "VN");
		NumberFormat en = NumberFormat.getInstance(localeEN);
		for (NvNhanvien nvNhanvien : nhanVienPage) {
			String str1 = en.format(nvNhanvien.getIdChucdanh().getLuongcoban());
			row = tab.createRow(); // Second Row
			row.getCell(0).setText(nvNhanvien.getIdChiTiet());
			row.getCell(1).setText(nvNhanvien.getHodem() + " " + nvNhanvien.getTen());
			row.getCell(2).setText(str1);
			row.getCell(3).setText(nvNhanvien.getIdPhongban().getTenphongban());
			row.getCell(4).setText(nvNhanvien.getIdChucdanh().getTenchucdanh());
			row.getCell(5).setText(nvNhanvien.getIdTrinhdo().getTentrinhdo());
			row.getCell(6).setText(nvNhanvien.getIdChuyennganh().getTenchuyennganh());
		}

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
		run7.setText("Người tạo phiếu");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"baocaonhanvientheotrinhdo.docx\"")
				.body(document::write);
	}

	@GetMapping("/quantri/baocaonhanvientheochuyennganh/{id}")
	public ResponseEntity<StreamingResponseBody> chuyennganh(@PathVariable int id, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();

		List<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllNhanVienBaoCaoTheoChuyenNganh(id);
		DmChuyennganh pb = new DmChuyennganh();
		pb = dmChuyennganhServiceImpl.findById(id).get();
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
		run2.setText("BÁO CÁO THỐNG KÊ");
		run2.addBreak();

		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.isBold();
		run5.setFontFamily("Times New Roman");
		run5.setText(pb.getTenchuyennganh());
		XWPFTable tab = document.createTable();
		XWPFTableRow row = tab.getRow(0); // First row

		row.getCell(0).setText("ID nhân viên.");
		row.addNewTableCell().setText("Họ tên nhân viên");
		row.addNewTableCell().setText("Lương cơ bản");
		row.addNewTableCell().setText("Phòng ban");
		row.addNewTableCell().setText("Chức danh");
		row.addNewTableCell().setText("Trình độ");
		row.addNewTableCell().setText("Chuyên ngành");
		Locale localeEN = new Locale("vi", "VN");
		NumberFormat en = NumberFormat.getInstance(localeEN);
		for (NvNhanvien nvNhanvien : nhanVienPage) {
			String str1 = en.format(nvNhanvien.getIdChucdanh().getLuongcoban());
			row = tab.createRow(); // Second Row
			row.getCell(0).setText(nvNhanvien.getIdChiTiet());
			row.getCell(1).setText(nvNhanvien.getHodem() + " " + nvNhanvien.getTen());
			row.getCell(2).setText(str1);
			row.getCell(3).setText(nvNhanvien.getIdPhongban().getTenphongban());
			row.getCell(4).setText(nvNhanvien.getIdChucdanh().getTenchucdanh());
			row.getCell(5).setText(nvNhanvien.getIdTrinhdo().getTentrinhdo());
			row.getCell(6).setText(nvNhanvien.getIdChuyennganh().getTenchuyennganh());
		}

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
		run7.setText("Người tạo phiếu");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"baocaonhanvientheochuyennganh.docx\"")
				.body(document::write);
	}

	@GetMapping("/quantri/baocaonhanvientheophongban/{id}")
	public ResponseEntity<StreamingResponseBody> exportthongtinnhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();

		List<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllNhanVienBaoCaoTheoPhongBan(id);
		DmPhongban pb = new DmPhongban();
		pb = dmPhongbanServiceImpl.findById(id).get();
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
		run2.setText("BÁO CÁO THỐNG KÊ");
		run2.addBreak();

		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.isBold();
		run5.setFontFamily("Times New Roman");
		run5.setText(pb.getTenphongban());
		XWPFTable tab = document.createTable();
		XWPFTableRow row = tab.getRow(0); // First row

		row.getCell(0).setText("ID nhân viên.");
		row.addNewTableCell().setText("Họ tên nhân viên");
		row.addNewTableCell().setText("Lương cơ bản");
		row.addNewTableCell().setText("Phòng ban");
		row.addNewTableCell().setText("Chức danh");
		row.addNewTableCell().setText("Trình độ");
		row.addNewTableCell().setText("Chuyên ngành");
		Locale localeEN = new Locale("vi", "VN");
		NumberFormat en = NumberFormat.getInstance(localeEN);
		for (NvNhanvien nvNhanvien : nhanVienPage) {
			String str1 = en.format(nvNhanvien.getIdChucdanh().getLuongcoban());
			row = tab.createRow(); // Second Row
			row.getCell(0).setText(nvNhanvien.getIdChiTiet());
			row.getCell(1).setText(nvNhanvien.getHodem() + " " + nvNhanvien.getTen());
			row.getCell(2).setText(str1);
			row.getCell(3).setText(nvNhanvien.getIdPhongban().getTenphongban());
			row.getCell(4).setText(nvNhanvien.getIdChucdanh().getTenchucdanh());
			row.getCell(5).setText(nvNhanvien.getIdTrinhdo().getTentrinhdo());
			row.getCell(6).setText(nvNhanvien.getIdChuyennganh().getTenchuyennganh());
		}

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
		run7.setText("Người tạo phiếu");
	
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"baocaonhanvientheophongban.docx\"")
				.body(document::write);
		
	}

	@GetMapping("/quantri/baocaonhanvientheochucvu/{id}")
	public ResponseEntity<StreamingResponseBody> excel(@PathVariable int id, Model model,
			@ModelAttribute("status") String status) throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();

		List<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllNhanVienBaoCaoTheoChucDanh(id);
		DmChucdanh pb = new DmChucdanh();
		pb = dmChucdanhServiceImpl.findById(id).get();
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
		run2.setText("BÁO CÁO THỐNG KÊ");
		run2.addBreak();

		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.isBold();
		run5.setFontFamily("Times New Roman");
		run5.setText(pb.getTenchucdanh());
		XWPFTable tab = document.createTable();
		XWPFTableRow row = tab.getRow(0); // First row

		row.getCell(0).setText("ID nhân viên.");
		row.addNewTableCell().setText("Họ tên nhân viên");
		row.addNewTableCell().setText("Lương cơ bản");
		row.addNewTableCell().setText("Phòng ban");
		row.addNewTableCell().setText("Chức danh");
		row.addNewTableCell().setText("Trình độ");
		row.addNewTableCell().setText("Chuyên ngành");
		Locale localeEN = new Locale("vi", "VN");
		NumberFormat en = NumberFormat.getInstance(localeEN);
		for (NvNhanvien nvNhanvien : nhanVienPage) {
			String str1 = en.format(nvNhanvien.getIdChucdanh().getLuongcoban());
			row = tab.createRow(); // Second Row
			row.getCell(0).setText(nvNhanvien.getIdChiTiet());
			row.getCell(1).setText(nvNhanvien.getHodem() + " " + nvNhanvien.getTen());
			row.getCell(2).setText(str1);
			row.getCell(3).setText(nvNhanvien.getIdPhongban().getTenphongban());
			row.getCell(4).setText(nvNhanvien.getIdChucdanh().getTenchucdanh());
			row.getCell(5).setText(nvNhanvien.getIdTrinhdo().getTentrinhdo());
			row.getCell(6).setText(nvNhanvien.getIdChuyennganh().getTenchuyennganh());
		}

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
		run7.setText("Người tạo phiếu");

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"baocaonhanvientheochucvu.docx\"")
				.body(document::write);
	}
	
	@GetMapping("/quantri/baocaothongkenhanvientoanbonhanvien")
	public ResponseEntity<StreamingResponseBody> baocaotoanbonhanvien( Model model,
			@ModelAttribute("status") String status) throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();

		List<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllNhanVienAcctive();
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
		run2.setText("BÁO CÁO THỐNG KÊ");
		run2.addBreak();

		XWPFParagraph paragraph5 = document.createParagraph();
		paragraph5.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun run5 = paragraph5.createRun();
		run5.setFontSize(13);
		run5.isBold();
		run5.setFontFamily("Times New Roman");
		run5.setText("TOÀN BỘ NHÂN VIÊN");
		XWPFTable tab = document.createTable();
		XWPFTableRow row = tab.getRow(0); // First row

		row.getCell(0).setText("ID nhân viên.");
		row.addNewTableCell().setText("Họ tên nhân viên");
		row.addNewTableCell().setText("Lương cơ bản");
		row.addNewTableCell().setText("Phòng ban");
		row.addNewTableCell().setText("Chức danh");
		row.addNewTableCell().setText("Trình độ");
		row.addNewTableCell().setText("Chuyên ngành");
		Locale localeEN = new Locale("vi", "VN");
		NumberFormat en = NumberFormat.getInstance(localeEN);
		for (NvNhanvien nvNhanvien : nhanVienPage) {
			String str1 = en.format(nvNhanvien.getIdChucdanh().getLuongcoban());
			row = tab.createRow(); // Second Row
			row.getCell(0).setText(nvNhanvien.getIdChiTiet());
			row.getCell(1).setText(nvNhanvien.getHodem() + " " + nvNhanvien.getTen());
			row.getCell(2).setText(str1);
			row.getCell(3).setText(nvNhanvien.getIdPhongban().getTenphongban());
			row.getCell(4).setText(nvNhanvien.getIdChucdanh().getTenchucdanh());
			row.getCell(5).setText(nvNhanvien.getIdTrinhdo().getTentrinhdo());
			row.getCell(6).setText(nvNhanvien.getIdChuyennganh().getTenchuyennganh());
		}

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
		run7.setText("Người tạo phiếu");

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"baocaotoanbonhanvien.docx\"")
				.body(document::write);
	}
}
