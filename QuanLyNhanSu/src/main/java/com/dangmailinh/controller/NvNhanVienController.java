package com.dangmailinh.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import static com.dangmailinh.util.NhanVienSystem.*;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dangmailinh.dto.FormQuanHeDTO;
import com.dangmailinh.entities.DmChucdanh;
import com.dangmailinh.entities.DmChuyennganh;
import com.dangmailinh.entities.DmDantoc;
import com.dangmailinh.entities.DmPhongban;
import com.dangmailinh.entities.DmQuanhe;
import com.dangmailinh.entities.DmTinhtranghonnhan;
import com.dangmailinh.entities.DmTongiao;
import com.dangmailinh.entities.DmTrinhdo;
import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.entities.NvQuanhegiadinh;
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
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;

@Controller
public class NvNhanVienController {
	@Autowired
	DmChucdanhServiceImpl dmChucdanhServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;

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

	public static String uploadDirectory = System.getProperty("user.dir") + "/upload";

	@GetMapping("/quantri/listnhanvien")
	public String showPage(HttpServletRequest request,Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAll(pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage, "/quantri/listnhanvien");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("nhanVien", "Nhân viên");
		model.addAttribute("listNhanVien", page.getContent());
		model.addAttribute("phongBan", dmPhongbanServiceImpl.findAllActiveDmPhongBanNative());
		model.addAttribute("chucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("trinhDo", dmTrinhdoServiceImpl.findAllActiveDmTrinhDoNative());
		model.addAttribute("chuyenNganh", dmChuyennganhServiceImpl.findAllActiveDmChuyenNganhNative());
		model.addAttribute("quanHe", dmQuanheServiceImpl.findAllActiveDmQuanHeNative());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/listnhanvien";
	}

	@GetMapping("/quantri/findonenhanvien")
	@ResponseBody
	public FormQuanHeDTO findById(Integer id) {
		NvNhanvien nv = nvNhanvienServiceImpl.findNhanVienByID(id);
		FormQuanHeDTO dto = new FormQuanHeDTO();
		dto.setId(nv.getId());
		dto.setStatus(nv.getStatus());
		return dto;
	}

	@PostMapping("/quantri/themMoiQuanHe")
	public String themMoiQuanHe(@ModelAttribute("statusSuccess") String statusSuccess,HttpServletRequest request,
			@RequestParam("id") Integer idNhanVien, @RequestParam("idquanhe") Integer idQuanHe,
			@RequestParam("hoten") String hoTen, @RequestParam("nghenghiep") String ngheNghiep,
			@RequestParam("diachi") String diaChi, @RequestParam("ghichu") String ghiChu,
			RedirectAttributes redirectAttributes) {
		DmQuanhe dmqh = new DmQuanhe();
		dmqh = dmQuanheServiceImpl.findById(idQuanHe).get();
		NvNhanvien nv = new NvNhanvien();
		nv = nvNhanvienServiceImpl.findNhanVienByID(idNhanVien);
		NvQuanhegiadinh qhgd = new NvQuanhegiadinh();
		qhgd.setDiachi(diaChi);
		qhgd.setGhichu(ghiChu);
		qhgd.setHoten(hoTen);
		qhgd.setIdNhanvien(nv);
		qhgd.setIdQuanhe(dmqh);
		qhgd.setNghenghiep(ngheNghiep);
		qhgd.setStatus(1);
		nvQuanhegiadinhServiceImpl.save(qhgd);
		redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
		return "redirect:/quantri/listnhanvien";

	}

	@GetMapping("/quantri/timkiemnhanvientheophongban")
	public String timKiemNhanVienTheoPhongBan(Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("idphongban") Integer idphongban) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllNhanVienTheoPhongBan(idphongban, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheophongban/" + idphongban);
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
		return "admin/listnhanvien";
	}

	@GetMapping("/quantri/timkiemnhanvientheochucvu")
	public String timKiemNhanVienTheoChucVu(Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("idchucvu") Integer idchucvu) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllNhanVienTheoChucDanh(idchucvu, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheochucvu/" + idchucvu);
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
		return "admin/listnhanvien";
	}

	@GetMapping("/quantri/timkiemnhanvientheotrinhdo")
	public String timKiemNhanVienTheoTrinhDo(Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("idtrinhdo") Integer idtrinhdo) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllNhanVienTheoTrinhDo(idtrinhdo, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheotrinhdo/" + idtrinhdo);
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
		
		return "admin/listnhanvien";
	}

	@GetMapping("/quantri/timkiemnhanvientheochuyennganh")
	public String timKiemNhanVienTheoChuyenNganh(Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess,
			@RequestParam("idchuyennganh") Integer idchuyennganh) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllNhanVienTheoChuyenNganh(idchuyennganh, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheochuyennganh/" + idchuyennganh);
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
	
		return "admin/listnhanvien";
	}

	@GetMapping("/quantri/timkiemnhanvientheoten")
	public String timKiemNhanVienTheoTen(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("search") String search) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllByTenNhanVien(search, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage, "/quantri/timkiemnhanvientheoten?search=" + search);
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
		return "admin/listnhanvien";
	}

	@GetMapping("/quantri/findonenhanvien/{id}")
	public String findonenhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status,HttpServletRequest request) {
		model.addAttribute("themNhanVien", "Chi Tiết Nhân Viên");
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

		return "admin/updatenhanvien";
	}

	@GetMapping("/quantri/exportthongtinnhanvien/{id}")
	public ResponseEntity<StreamingResponseBody> exportthongtinnhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status)
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
		run2.setText("HỒ SƠ NHÂN VIÊN        ");
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
		List<NvQuanhegiadinh> list = nvQuanhegiadinhServiceImpl.findAllByQuanHeGiaDinh(id);
		XWPFTableRow tableRowTwo = table.getRow(0);
		XWPFRun run = tableRowTwo.addNewTableCell().addParagraph().createRun();
		run.setFontSize(13);
		run.setFontFamily("Times New Roman");
		run.setText("II. Quan hệ gia đình: ");
		run.addBreak();
		for (NvQuanhegiadinh list1 : list) {
			run.setText(list1.getIdQuanhe().getTenquanhe() + ": " + list1.getHoten() + ". Nghề nghiệp: "
					+ list1.getNghenghiep());
			run.addBreak();
		}

		XWPFParagraph paragraph4 = document.createParagraph();
		paragraph4.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run4 = paragraph4.createRun();
		run4.setFontSize(13);
		run4.setFontFamily("Times New Roman");
		run4.setText(
				"Tôi xin cam đoan bản khai sơ yếu lý lịch trên là đúng sự thật, nếu không đúng tôi xin chịu trách nhiệm trước pháp luật về lời khai của mình.");

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
		run6.setText("Người khai ký tên");

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"thongtinnhanvien.docx\"")
				.body(document::write);
	}

	@GetMapping(value = { "/quantri/initthemnhanvien" })
	public String findAllChucDanh(Model model, @ModelAttribute("status") String status) {
		model.addAttribute("themNhanVien", "Thêm nhân viên");
		model.addAttribute("chucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("chuyenNganh", dmChuyennganhServiceImpl.findAllActiveDmChuyenNganhNative());
		model.addAttribute("danToc", dmDantocServiceImpl.findAllActiveDmDanTocNative());
		model.addAttribute("phongBan", dmPhongbanServiceImpl.findAllActiveDmPhongBanNative());
		model.addAttribute("tinhTrangHonNhan", dmTinhtranghonnhanServiceImpl.findAllActiveDmTinhTrangHonNhanNative());
		model.addAttribute("tonGiao", dmTongiaoServiceImpl.findAllActiveDmTonGiaoNative());
		model.addAttribute("trinhDo", dmTrinhdoServiceImpl.findAllActiveDmTrinhDoNative());
		return "admin/nvthemnhanvien";
	}

	@PostMapping("/quantri/addNhanVien")
	public String addNhanVien(Model model, @RequestParam("file") MultipartFile file,
			@RequestParam("hodem") String hodem, @RequestParam("ten") String ten,
			@RequestParam("diachihientai") String diachihientai,
			@RequestParam("diachithuongchu") String diachithuongchu, @RequestParam("noisinh") String noisinh,
			@RequestParam("quoctich") String quoctich, @RequestParam("sodienthoai") String sodienthoai,
			@RequestParam("email") String email, @RequestParam("ngaysinh") String ngaysinh,
			@RequestParam("chungminhnhandan") String chungminhnhandan, @RequestParam("noicap") String noicap,
			@RequestParam("gioitinh") int gioitinh, @RequestParam("chucvu") Integer chucvu,
			@RequestParam("phongban") Integer phongban, @RequestParam("trinhdohocvan") Integer trinhdohocvan,
			@RequestParam("chuyennganh") Integer chuyennganh, @RequestParam("dantoc") Integer dantoc,
			@RequestParam("tinhtranghonnhan") Integer tinhtranghonnhan, @RequestParam("tongiao") Integer tongiao,
			@RequestParam("ghichu") String ghichu, RedirectAttributes redirectAttributes)
			throws ParseException, IOException {

		if (1 == dmChucdanhServiceImpl.findById(chucvu).get().getId()) {
			NvNhanvien nv = new NvNhanvien();
			DmChucdanh cv = new DmChucdanh();
			cv.setId(chucvu);
			DmPhongban pb = new DmPhongban();
			pb.setId(phongban);
			DmTrinhdo tdhv = new DmTrinhdo();
			tdhv.setId(trinhdohocvan);
			DmChuyennganh cn = new DmChuyennganh();
			cn.setId(chuyennganh);
			DmDantoc dt = new DmDantoc();
			dt.setId(chuyennganh);
			DmTinhtranghonnhan tthn = new DmTinhtranghonnhan();
			tthn.setId(tinhtranghonnhan);
			DmTongiao tg = new DmTongiao();
			tg.setId(tongiao);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			BufferedOutputStream stream = null;
			try {
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadDirectory, fileName).toString();
				stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (IOException e) {
				stream.close();
			}

			int max = (nvNhanvienServiceImpl.findMaxID());
			String NhanVienID = NHAN_VIEN + (String.format("%04d", max + 1));
			nv.setCmnd(chungminhnhandan);
			nv.setDiachihientai(diachihientai);
			nv.setDiachithuongtru(diachithuongchu);
			nv.setEmail(email);
			nv.setGhichu(ghichu);
			nv.setGioitinh(gioitinh);
			nv.setHinhanh(file.getOriginalFilename());
			nv.setHodem(hodem);
			nv.setIdChiTiet(NhanVienID);
			nv.setIdChucdanh(cv);
			nv.setIdChuyennganh(cn);
			nv.setIdDantoc(dt);
			nv.setIdPhongban(pb);
			nv.setIdTinhtranghonnhan(tthn);
			nv.setIdTongiao(tg);
			nv.setIdTrinhdo(tdhv);
			nv.setLuong(dmChucdanhServiceImpl.findChucDanhByID(chucvu).getLuongcoban());
			nv.setNgaysinh(formatter.parse(ngaysinh));
			nv.setNoicapcmnd(noicap);
			nv.setNoisinh(noisinh);
			nv.setQuoctich(quoctich);
			nv.setSodienthoai(sodienthoai);
			nv.setStatus(1);
			nv.setTen(ten);
			nv.setTrangthaihopdong(1);
			nvNhanvienServiceImpl.save(nv);
			redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
			return "redirect:/quantri/listnhanvien";
		}
		if (2 == dmChucdanhServiceImpl.findById(chucvu).get().getId()) {
			NvNhanvien nv = new NvNhanvien();
			DmChucdanh cv = new DmChucdanh();
			cv.setId(chucvu);
			DmPhongban pb = new DmPhongban();
			pb.setId(phongban);
			DmTrinhdo tdhv = new DmTrinhdo();
			tdhv.setId(trinhdohocvan);
			DmChuyennganh cn = new DmChuyennganh();
			cn.setId(chuyennganh);
			DmDantoc dt = new DmDantoc();
			dt.setId(chuyennganh);
			DmTinhtranghonnhan tthn = new DmTinhtranghonnhan();
			tthn.setId(tinhtranghonnhan);
			DmTongiao tg = new DmTongiao();
			tg.setId(tongiao);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			BufferedOutputStream stream = null;
			try {
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadDirectory, fileName).toString();
				stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (IOException e) {
				stream.close();
			}

			int max = (nvNhanvienServiceImpl.findMaxID());
			String NhanVienID = TO_TRUONG + (String.format("%04d", max + 1));
			nv.setCmnd(chungminhnhandan);
			nv.setDiachihientai(diachihientai);
			nv.setDiachithuongtru(diachithuongchu);
			nv.setEmail(email);
			nv.setGhichu(ghichu);
			nv.setGioitinh(gioitinh);
			nv.setHinhanh(file.getOriginalFilename());
			nv.setHodem(hodem);
			nv.setIdChiTiet(NhanVienID);
			nv.setIdChucdanh(cv);
			nv.setIdChuyennganh(cn);
			nv.setIdDantoc(dt);
			nv.setIdPhongban(pb);
			nv.setIdTinhtranghonnhan(tthn);
			nv.setIdTongiao(tg);
			nv.setIdTrinhdo(tdhv);
			nv.setLuong(dmChucdanhServiceImpl.findChucDanhByID(chucvu).getLuongcoban());
			nv.setNgaysinh(formatter.parse(ngaysinh));
			nv.setNoicapcmnd(noicap);
			nv.setNoisinh(noisinh);
			nv.setQuoctich(quoctich);
			nv.setSodienthoai(sodienthoai);
			nv.setStatus(1);
			nv.setTen(ten);
			nv.setTrangthaihopdong(1);
			nvNhanvienServiceImpl.save(nv);
			redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
			return "redirect:/quantri/listnhanvien";
		}
		if (3 == dmChucdanhServiceImpl.findById(chucvu).get().getId()) {
			NvNhanvien nv = new NvNhanvien();
			DmChucdanh cv = new DmChucdanh();
			cv.setId(chucvu);
			DmPhongban pb = new DmPhongban();
			pb.setId(phongban);
			DmTrinhdo tdhv = new DmTrinhdo();
			tdhv.setId(trinhdohocvan);
			DmChuyennganh cn = new DmChuyennganh();
			cn.setId(chuyennganh);
			DmDantoc dt = new DmDantoc();
			dt.setId(chuyennganh);
			DmTinhtranghonnhan tthn = new DmTinhtranghonnhan();
			tthn.setId(tinhtranghonnhan);
			DmTongiao tg = new DmTongiao();
			tg.setId(tongiao);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			BufferedOutputStream stream = null;
			try {
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadDirectory, fileName).toString();
				stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (IOException e) {
				stream.close();
			}

			int max = (nvNhanvienServiceImpl.findMaxID());
			String NhanVienID = PHO_PHONG + (String.format("%04d", max + 1));
			nv.setCmnd(chungminhnhandan);
			nv.setDiachihientai(diachihientai);
			nv.setDiachithuongtru(diachithuongchu);
			nv.setEmail(email);
			nv.setGhichu(ghichu);
			nv.setGioitinh(gioitinh);
			nv.setHinhanh(file.getOriginalFilename());
			nv.setHodem(hodem);
			nv.setIdChiTiet(NhanVienID);
			nv.setIdChucdanh(cv);
			nv.setIdChuyennganh(cn);
			nv.setIdDantoc(dt);
			nv.setIdPhongban(pb);
			nv.setIdTinhtranghonnhan(tthn);
			nv.setIdTongiao(tg);
			nv.setIdTrinhdo(tdhv);
			nv.setLuong(dmChucdanhServiceImpl.findChucDanhByID(chucvu).getLuongcoban());
			nv.setNgaysinh(formatter.parse(ngaysinh));
			nv.setNoicapcmnd(noicap);
			nv.setNoisinh(noisinh);
			nv.setQuoctich(quoctich);
			nv.setSodienthoai(sodienthoai);
			nv.setStatus(1);
			nv.setTen(ten);
			nv.setTrangthaihopdong(1);
			nvNhanvienServiceImpl.save(nv);
			redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
			return "redirect:/quantri/listnhanvien";

		}
		if (4 == dmChucdanhServiceImpl.findById(chucvu).get().getId()) {
			NvNhanvien nv = new NvNhanvien();
			DmChucdanh cv = new DmChucdanh();
			cv.setId(chucvu);
			DmPhongban pb = new DmPhongban();
			pb.setId(phongban);
			DmTrinhdo tdhv = new DmTrinhdo();
			tdhv.setId(trinhdohocvan);
			DmChuyennganh cn = new DmChuyennganh();
			cn.setId(chuyennganh);
			DmDantoc dt = new DmDantoc();
			dt.setId(chuyennganh);
			DmTinhtranghonnhan tthn = new DmTinhtranghonnhan();
			tthn.setId(tinhtranghonnhan);
			DmTongiao tg = new DmTongiao();
			tg.setId(tongiao);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			BufferedOutputStream stream = null;
			try {
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadDirectory, fileName).toString();
				stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (IOException e) {
				stream.close();
			}
			int max = (nvNhanvienServiceImpl.findMaxID());
			String NhanVienID = TRUONG_PHONG + (String.format("%04d", max + 1));
			nv.setCmnd(chungminhnhandan);
			nv.setDiachihientai(diachihientai);
			nv.setDiachithuongtru(diachithuongchu);
			nv.setEmail(email);
			nv.setGhichu(ghichu);
			nv.setGioitinh(gioitinh);
			nv.setHinhanh(file.getOriginalFilename());
			nv.setHodem(hodem);
			nv.setIdChiTiet(NhanVienID);
			nv.setIdChucdanh(cv);
			nv.setIdChuyennganh(cn);
			nv.setIdDantoc(dt);
			nv.setIdPhongban(pb);
			nv.setIdTinhtranghonnhan(tthn);
			nv.setIdTongiao(tg);
			nv.setIdTrinhdo(tdhv);
			nv.setLuong(dmChucdanhServiceImpl.findChucDanhByID(chucvu).getLuongcoban());
			nv.setNgaysinh(formatter.parse(ngaysinh));
			nv.setNoicapcmnd(noicap);
			nv.setNoisinh(noisinh);
			nv.setQuoctich(quoctich);
			nv.setSodienthoai(sodienthoai);
			nv.setStatus(1);
			nv.setTen(ten);
			nv.setTrangthaihopdong(1);
			nvNhanvienServiceImpl.save(nv);
			redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
			return "redirect:/quantri/listnhanvien";
		}
		if (5 == dmChucdanhServiceImpl.findById(chucvu).get().getId()) {
			NvNhanvien nv = new NvNhanvien();
			DmChucdanh cv = new DmChucdanh();
			cv.setId(chucvu);
			DmPhongban pb = new DmPhongban();
			pb.setId(phongban);
			DmTrinhdo tdhv = new DmTrinhdo();
			tdhv.setId(trinhdohocvan);
			DmChuyennganh cn = new DmChuyennganh();
			cn.setId(chuyennganh);
			DmDantoc dt = new DmDantoc();
			dt.setId(chuyennganh);
			DmTinhtranghonnhan tthn = new DmTinhtranghonnhan();
			tthn.setId(tinhtranghonnhan);
			DmTongiao tg = new DmTongiao();
			tg.setId(tongiao);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			BufferedOutputStream stream = null;
			try {
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadDirectory, fileName).toString();
				stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (IOException e) {
				stream.close();
			}

			int max = (nvNhanvienServiceImpl.findMaxID());
			String NhanVienID = PHO_GIAM_DOC + (String.format("%04d", max + 1));
			nv.setCmnd(chungminhnhandan);
			nv.setDiachihientai(diachihientai);
			nv.setDiachithuongtru(diachithuongchu);
			nv.setEmail(email);
			nv.setGhichu(ghichu);
			nv.setGioitinh(gioitinh);
			nv.setHinhanh(file.getOriginalFilename());
			nv.setHodem(hodem);
			nv.setIdChiTiet(NhanVienID);
			nv.setIdChucdanh(cv);
			nv.setIdChuyennganh(cn);
			nv.setIdDantoc(dt);
			nv.setIdPhongban(pb);
			nv.setIdTinhtranghonnhan(tthn);
			nv.setIdTongiao(tg);
			nv.setIdTrinhdo(tdhv);
			nv.setLuong(dmChucdanhServiceImpl.findChucDanhByID(chucvu).getLuongcoban());
			nv.setNgaysinh(formatter.parse(ngaysinh));
			nv.setNoicapcmnd(noicap);
			nv.setNoisinh(noisinh);
			nv.setQuoctich(quoctich);
			nv.setSodienthoai(sodienthoai);
			nv.setStatus(1);
			nv.setTen(ten);
			nv.setTrangthaihopdong(1);
			nvNhanvienServiceImpl.save(nv);
			redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
			return "redirect:/quantri/listnhanvien";

		}
		if (6 == dmChucdanhServiceImpl.findById(chucvu).get().getId()) {
			NvNhanvien nv = new NvNhanvien();
			DmChucdanh cv = new DmChucdanh();
			cv.setId(chucvu);
			DmPhongban pb = new DmPhongban();
			pb.setId(phongban);
			DmTrinhdo tdhv = new DmTrinhdo();
			tdhv.setId(trinhdohocvan);
			DmChuyennganh cn = new DmChuyennganh();
			cn.setId(chuyennganh);
			DmDantoc dt = new DmDantoc();
			dt.setId(chuyennganh);
			DmTinhtranghonnhan tthn = new DmTinhtranghonnhan();
			tthn.setId(tinhtranghonnhan);
			DmTongiao tg = new DmTongiao();
			tg.setId(tongiao);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			BufferedOutputStream stream = null;
			try {
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadDirectory, fileName).toString();
				stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (IOException e) {
				stream.close();
			}

			int max = (nvNhanvienServiceImpl.findMaxID());
			String NhanVienID = GIAM_DOC + (String.format("%04d", max + 1));
			nv.setCmnd(chungminhnhandan);
			nv.setDiachihientai(diachihientai);
			nv.setDiachithuongtru(diachithuongchu);
			nv.setEmail(email);
			nv.setGhichu(ghichu);
			nv.setGioitinh(gioitinh);
			nv.setHinhanh(file.getOriginalFilename());
			nv.setHodem(hodem);
			nv.setIdChiTiet(NhanVienID);
			nv.setIdChucdanh(cv);
			nv.setIdChuyennganh(cn);
			nv.setIdDantoc(dt);
			nv.setIdPhongban(pb);
			nv.setIdTinhtranghonnhan(tthn);
			nv.setIdTongiao(tg);
			nv.setIdTrinhdo(tdhv);
			nv.setLuong(dmChucdanhServiceImpl.findChucDanhByID(chucvu).getLuongcoban());
			nv.setNgaysinh(formatter.parse(ngaysinh));
			nv.setNoicapcmnd(noicap);
			nv.setNoisinh(noisinh);
			nv.setQuoctich(quoctich);
			nv.setSodienthoai(sodienthoai);
			nv.setStatus(1);
			nv.setTen(ten);
			nv.setTrangthaihopdong(1);
			nvNhanvienServiceImpl.save(nv);
			redirectAttributes.addFlashAttribute("statusSuccess", "Thêm thành công");
			return "redirect:/quantri/listnhanvien";
		} else {
			return "redirect:/quantri/listnhanvien";
		}
	}

	@PostMapping("/quantri/updateNhanVien")
	public String updateNhanVien(Model model, @RequestParam("hodem") String hodem, @RequestParam("ten") String ten,
			@RequestParam("diachihientai") String diachihientai,
			@RequestParam("diachithuongchu") String diachithuongchu, @RequestParam("noisinh") String noisinh,
			@RequestParam("quoctich") String quoctich, @RequestParam("sodienthoai") String sodienthoai,
			@RequestParam("email") String email, @RequestParam("ngaysinh") String ngaysinh,
			@RequestParam("chungminhnhandan") String chungminhnhandan, @RequestParam("noicap") String noicap,
			@RequestParam("gioitinh") int gioitinh, @RequestParam("chuyennganh") Integer chuyennganh,
			@RequestParam("dantoc") Integer dantoc, @RequestParam("tinhtranghonnhan") Integer tinhtranghonnhan,
			@RequestParam("tongiao") Integer tongiao, @RequestParam("ghichu") String ghichu,
			@RequestParam("id") Integer id, RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile file) throws ParseException, IOException {
		if (!file.isEmpty()) {
			NvNhanvien nv = nvNhanvienServiceImpl.findById(id).get();
			DmChuyennganh cn = new DmChuyennganh();
			cn.setId(chuyennganh);
			DmDantoc dt = new DmDantoc();
			dt.setId(chuyennganh);
			DmTinhtranghonnhan tthn = new DmTinhtranghonnhan();
			tthn.setId(tinhtranghonnhan);
			DmTongiao tg = new DmTongiao();
			tg.setId(tongiao);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			BufferedOutputStream stream = null;
			try {
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadDirectory, fileName).toString();
				stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (IOException e) {
				stream.close();
			}
			nv.setHinhanh(file.getOriginalFilename());
			nv.setCmnd(chungminhnhandan);
			nv.setDiachihientai(diachihientai);
			nv.setDiachithuongtru(diachithuongchu);
			nv.setEmail(email);
			nv.setGhichu(ghichu);
			nv.setGioitinh(gioitinh);
			nv.setHodem(hodem);
			nv.setIdChuyennganh(cn);
			nv.setIdDantoc(dt);
			nv.setIdTinhtranghonnhan(tthn);
			nv.setIdTongiao(tg);
			nv.setNgaysinh(formatter.parse(ngaysinh));
			nv.setNoicapcmnd(noicap);
			nv.setNoisinh(noisinh);
			nv.setQuoctich(quoctich);
			nv.setSodienthoai(sodienthoai);
			nv.setTen(ten);
			nvNhanvienServiceImpl.save(nv);
			redirectAttributes.addFlashAttribute("statusSuccess", "Update thành công");
			return "redirect:/quantri/listnhanvien";
		} else {
			NvNhanvien nv = nvNhanvienServiceImpl.findById(id).get();
			DmChuyennganh cn = new DmChuyennganh();
			cn.setId(chuyennganh);
			DmDantoc dt = new DmDantoc();
			dt.setId(chuyennganh);
			DmTinhtranghonnhan tthn = new DmTinhtranghonnhan();
			tthn.setId(tinhtranghonnhan);
			DmTongiao tg = new DmTongiao();
			tg.setId(tongiao);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			nv.setCmnd(chungminhnhandan);
			nv.setDiachihientai(diachihientai);
			nv.setDiachithuongtru(diachithuongchu);
			nv.setEmail(email);
			nv.setGhichu(ghichu);
			nv.setGioitinh(gioitinh);
			nv.setHodem(hodem);
			nv.setIdChuyennganh(cn);
			nv.setIdDantoc(dt);
			nv.setIdTinhtranghonnhan(tthn);
			nv.setIdTongiao(tg);
			nv.setNgaysinh(formatter.parse(ngaysinh));
			nv.setNoicapcmnd(noicap);
			nv.setNoisinh(noisinh);
			nv.setQuoctich(quoctich);
			nv.setSodienthoai(sodienthoai);
			nv.setTen(ten);
			nvNhanvienServiceImpl.save(nv);
			redirectAttributes.addFlashAttribute("statusSuccess", "Update thành công");
			return "redirect:/quantri/listnhanvien";
		}
	}
}
