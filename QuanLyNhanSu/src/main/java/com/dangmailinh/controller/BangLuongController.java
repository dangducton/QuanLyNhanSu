package com.dangmailinh.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dangmailinh.entities.NvNhanvien;
import com.dangmailinh.entities.NvQuyetdinhkhenthuong;
import com.dangmailinh.entities.NvQuyetdinhkyluat;
import com.dangmailinh.entities.PhuCap;
import com.dangmailinh.entities.TlBangluong;
import com.dangmailinh.entities.TlTamung;
import com.dangmailinh.entities.User;
import com.dangmailinh.service.DmChitietthuongngayleServiceImpl;
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
import com.dangmailinh.service.PhuCapServiceImpl;
import com.dangmailinh.service.TIBangluongServiceImpl;
import com.dangmailinh.service.TITamungServiceImpl;
import com.dangmailinh.service.UserServiceImpl;
import com.dangmailinh.util.Pagination;

@Controller
public class BangLuongController {
	@Autowired
	NvQuyetdinhkhenthuongServiceImpl nvQuyetdinhkhenthuongServiceImpl;

	@Autowired
	DmChitietthuongngayleServiceImpl dmChitietthuongngayleServiceImpl;

	@Autowired
	NvQuyetdinhkyluatServiceImpl nvQuyetdinhkyluatServiceImpl;

	@Autowired
	PhuCapServiceImpl phuCapServiceImpl;

	@Autowired
	TIBangluongServiceImpl tIBangluongServiceImpl;

	@Autowired
	TITamungServiceImpl tITamungServiceImpl;

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
	private UserServiceImpl userServiceImpl;

	@Autowired
	DmTrinhdoServiceImpl dmTrinhdoServiceImpl;

	@Autowired
	NvQuanhegiadinhServiceImpl nvQuanhegiadinhServiceImpl;

	@GetMapping("/quantri/findallbangluong")
	public String showPage(Model model, @PageableDefault(size = 10) Pageable pageable,
			@ModelAttribute("statusSuccess") String statusSuccess,HttpServletRequest request) {
		Page<TlBangluong> nhanVienPage = tIBangluongServiceImpl.findAllByBangLuong(pageable);
		Pagination<TlBangluong> page = new Pagination<TlBangluong>(nhanVienPage, "/quantri/findallbangluong");
		model.addAttribute("statusSuccess", statusSuccess);
		model.addAttribute("quaTrinhCongTac", "Danh sách lương");
		model.addAttribute("listQuaTrinhCongTac", page.getContent());
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		model.addAttribute("page", page);
		int offset = (page.getNumber() - 1) * page.getSize();
		model.addAttribute("items", offset);
		return "admin/listbangluong";
	}

	@GetMapping("/quantri/baocaobangluong/{id}")
	public ResponseEntity<StreamingResponseBody> exportthongtinnhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status)
			throws IOException, XmlException, InvalidFormatException {
		XWPFDocument document = new XWPFDocument();
		TlBangluong qtct = tIBangluongServiceImpl.findAllById(id);

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
		run2.setText("Bảng lương nhân viên");
		
		Locale localeEN = new Locale("vi", "VN");
		NumberFormat en = NumberFormat.getInstance(localeEN);
		String str1 = en.format(qtct.getTamung());
		String str2 = en.format(qtct.getTienphucap());
		String str3 = en.format(qtct.getTienthuong());
		String str4 = en.format(qtct.getTienphat());
		String str5 = en.format(qtct.getTienthuongle());
		String str6 = en.format(qtct.getTienbaohiem());
		String str7 = en.format(qtct.getThuclinh());
		String str8 = en.format(qtct.getMucluongcoban());
		
		XWPFParagraph paragraph8 = document.createParagraph();
		paragraph8.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run8 = paragraph8.createRun();
		run8.setFontSize(13);
		run8.setFontFamily("Times New Roman");
		run8.setText("1. Tên nhân viên: " +qtct.getIdNhanvien().getHodem()+ " " +qtct.getIdNhanvien().getTen());
		run8.addBreak();
		run8.setText("2. Mã nhân viên: " +qtct.getIdNhanvien().getIdChiTiet());
		run8.addBreak();
		run8.setText("3. Tháng năm: tháng " +qtct.getThang() +" năm "+qtct.getNam());
		run8.addBreak();
		run8.setText("3. Lương cơ bản: " +str8);
		run8.addBreak();
		run8.setText("4. Tổng hệ số: " +qtct.getTongheso());
		run8.addBreak();
		run8.setText("5. Tạm ứng: " +str1);
		run8.addBreak();
		run8.setText("6. Phụ cấp: " +str2);
		run8.addBreak();
		run8.setText("7. Khen thưởng: " +str3);
		run8.addBreak();
		run8.setText("8. Kỷ luật: " +str4);
		run8.addBreak();
		run8.setText("9. Thưởng lễ: " +str5);
		run8.addBreak();
		run8.setText("10. Bảo hiểm: " +str6);
		run8.addBreak();
		run8.setText("11. Tổng thực lĩnh: " +str7);
		run8.addBreak();
		run8.setText("3 số cuối từ dấu phẩu không phải là tiền tệ");
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
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"bangluongnhanvien.docx\"")
				.body(document::write);
	}

	@GetMapping("/quantri/deletebangluong")
	public String deleteById(Integer id) {
		tIBangluongServiceImpl.delete(id);
		return "redirect:/quantri/findallbangluong"; 
	}

	@PostMapping("/quantri/thembangluong")
	public String addNhanVien(Model model, @RequestParam("id") Integer id, @RequestParam("ghichu") String ghichu,@RequestParam("thang") Integer thang,
			@RequestParam("thuongngayle") Integer thuongngayle, @RequestParam("nam") Integer nam,
			RedirectAttributes redirectAttributes) throws ParseException, IOException {
		if(thuongngayle == 0) {
			NvNhanvien nv = new NvNhanvien();
			nv = nvNhanvienServiceImpl.findById(id).get();
			TlBangluong bl = tIBangluongServiceImpl.findAllByBangLuong(nv.getId(), nam, thang);
			if(bl != null) {
				model.addAttribute("statusSuccess", "Bảng lương đã tồn tại");
				return "redirect:/quantri/findallbangluong";
			}else {
			List<NvQuyetdinhkhenthuong> list = nvQuyetdinhkhenthuongServiceImpl.findAllByKhenThuong(nv.getId(), nam,
					thang);
			double tienkhenthuong = 0;
			for (NvQuyetdinhkhenthuong nvQuyetdinhkhenthuong : list) {
				tienkhenthuong = tienkhenthuong + nvQuyetdinhkhenthuong.getTienthuong();
			}
			List<NvQuyetdinhkyluat> list1 = nvQuyetdinhkyluatServiceImpl.findAllKyLuat(nv.getId(), nam, thang);
			double tienkyluat = 0;
			for (NvQuyetdinhkyluat nvQuyetdinhkyluat : list1) {
				tienkyluat = tienkyluat + nvQuyetdinhkyluat.getTienphat();
			}
			List<PhuCap> list2 = phuCapServiceImpl.findAllByTamUng(id, nam, thang);
			double tienphucap = 0;
			for (PhuCap phuCap : list2) {
				tienphucap = tienphucap + phuCap.getIdPhucap().getMucphucap();
			}
			List<TlTamung> list3 = tITamungServiceImpl.findAllByTamUng(id, nam, thang);
			double tientamung = 0;
			for (TlTamung tlTamung : list3) {
				tientamung = tientamung + tlTamung.getSotien();
			}
			double tienbaohiem = (nv.getIdChucdanh().getLuongcoban() / 100) * 11.5;
			double tienthuongngayle = 0;
			model.addAttribute("themNhanVien", "Bảng lương");
			model.addAttribute("thongTinNhanVien", nv);
			model.addAttribute("tienthuongngayle", tienthuongngayle);
			model.addAttribute("tienbaohiem", tienbaohiem);
			model.addAttribute("tienkhenthuong", tienkhenthuong);
			model.addAttribute("tienkyluat", tienkyluat);
			model.addAttribute("thuongngayle", dmChitietthuongngayleServiceImpl.findById(id).get());
			model.addAttribute("tienphucap", tienphucap);
			model.addAttribute("tientamung", tientamung);
			double tongtien = (nv.getIdChucdanh().getLuongcoban() * (nv.getIdChucdanh().getHesochucvu()
					+ nv.getIdChucdanh().getHesotrachnhiem() + nv.getIdTrinhdo().getHesochuyenmon() + 1 ))
					+ (tienkhenthuong + tienphucap + tienthuongngayle) - (tienkyluat + tientamung + tienbaohiem);
			model.addAttribute("tongtien", tongtien);
			TlBangluong bangluong = new TlBangluong();
			bangluong.setGhichu(ghichu);
			bangluong.setHesochucvu(nv.getIdChucdanh().getHesochucvu());
			bangluong.setHesochuyenmon(nv.getIdTrinhdo().getHesochuyenmon());
			bangluong.setHesotrachnhiem(nv.getIdChucdanh().getHesotrachnhiem());
			bangluong.setIdNhanvien(nv);
			bangluong.setMucluongcoban(nv.getIdChucdanh().getLuongcoban());
			bangluong.setNam(nam);
			bangluong.setStatus(1);
			bangluong.setTamung(tientamung);
			bangluong.setThang(thang);
			bangluong.setThuclinh(tongtien);
			bangluong.setTienbaohiem(tienbaohiem);
			bangluong.setTienphat(tienkyluat);
			bangluong.setTienphucap(tienphucap);
			bangluong.setTienthuong(tienkhenthuong);
			bangluong.setTienthuongle(tienthuongngayle);
			bangluong.setTongheso(nv.getIdChucdanh().getHesochucvu() + nv.getIdChucdanh().getHesotrachnhiem()
					+ nv.getIdTrinhdo().getHesochuyenmon());
			tIBangluongServiceImpl.save(bangluong);
			return "redirect:/quantri/findallbangluong";
			}
		}else {
			NvNhanvien nv = new NvNhanvien();
			nv = nvNhanvienServiceImpl.findById(id).get();
			TlBangluong bl = tIBangluongServiceImpl.findAllByBangLuong(nv.getId(), nam, thang);
			if(bl != null) {
				model.addAttribute("statusSuccess", "Bảng lương đã tồn tại");
				return "redirect:/quantri/findallbangluong";
			}else {
			List<NvQuyetdinhkhenthuong> list = nvQuyetdinhkhenthuongServiceImpl.findAllByKhenThuong(nv.getId(), nam,
					thang);
			double tienkhenthuong = 0;
			for (NvQuyetdinhkhenthuong nvQuyetdinhkhenthuong : list) {
				tienkhenthuong = tienkhenthuong + nvQuyetdinhkhenthuong.getTienthuong();
			}
			List<NvQuyetdinhkyluat> list1 = nvQuyetdinhkyluatServiceImpl.findAllKyLuat(nv.getId(), nam, thang);
			double tienkyluat = 0;
			for (NvQuyetdinhkyluat nvQuyetdinhkyluat : list1) {
				tienkyluat = tienkyluat + nvQuyetdinhkyluat.getTienphat();
			}
			List<PhuCap> list2 = phuCapServiceImpl.findAllByTamUng(id, nam, thang);
			double tienphucap = 0;
			for (PhuCap phuCap : list2) {
				tienphucap = tienphucap + phuCap.getIdPhucap().getMucphucap();
			}
			List<TlTamung> list3 = tITamungServiceImpl.findAllByTamUng(id, nam, thang);
			double tientamung = 0;
			for (TlTamung tlTamung : list3) {
				tientamung = tientamung + tlTamung.getSotien();
			}
			double tienbaohiem = (nv.getIdChucdanh().getLuongcoban() / 100) * 11.5;
			double tienthuongngayle = 0;
			tienthuongngayle = nv.getIdChucdanh().getLuongcoban()
					* dmChitietthuongngayleServiceImpl.findById(thuongngayle).get().getMucthuong();
			model.addAttribute("themNhanVien", "Bảng lương");
			model.addAttribute("thongTinNhanVien", nv);
			model.addAttribute("tienthuongngayle", tienthuongngayle);
			model.addAttribute("tienbaohiem", tienbaohiem);
			model.addAttribute("tienkhenthuong", tienkhenthuong);
			model.addAttribute("tienkyluat", tienkyluat);
			model.addAttribute("thuongngayle", dmChitietthuongngayleServiceImpl.findById(id).get());
			model.addAttribute("tienphucap", tienphucap);
			model.addAttribute("tientamung", tientamung);
			double tongtien = (nv.getIdChucdanh().getLuongcoban() * (nv.getIdChucdanh().getHesochucvu()
					+ nv.getIdChucdanh().getHesotrachnhiem() + nv.getIdTrinhdo().getHesochuyenmon() +1))
					+ (tienkhenthuong + tienphucap + tienthuongngayle) - (tienkyluat + tientamung + tienbaohiem);
			model.addAttribute("tongtien", tongtien);
			TlBangluong bangluong = new TlBangluong();
			bangluong.setGhichu(ghichu);
			bangluong.setHesochucvu(nv.getIdChucdanh().getHesochucvu());
			bangluong.setHesochuyenmon(nv.getIdTrinhdo().getHesochuyenmon());
			bangluong.setHesotrachnhiem(nv.getIdChucdanh().getHesotrachnhiem());
			bangluong.setIdNhanvien(nv);
			bangluong.setMucluongcoban(nv.getIdChucdanh().getLuongcoban());
			bangluong.setNam(nam);
			bangluong.setStatus(1);
			bangluong.setTamung(tientamung);
			bangluong.setThang(thang);
			bangluong.setThuclinh(tongtien);
			bangluong.setTienbaohiem(tienbaohiem);
			bangluong.setTienphat(tienkyluat);
			bangluong.setTienphucap(tienphucap);
			bangluong.setTienthuong(tienkhenthuong);
			bangluong.setTienthuongle(tienthuongngayle);
			bangluong.setTongheso(nv.getIdChucdanh().getHesochucvu() + nv.getIdChucdanh().getHesotrachnhiem()
					+ nv.getIdTrinhdo().getHesochuyenmon());
			tIBangluongServiceImpl.save(bangluong);
			return "redirect:/quantri/findallbangluong";
			}
		}
		}
	

	@GetMapping(value = { "/quantri/taobangluong" })
	public String taobangluong(Model model, @RequestParam("id") Integer id, @RequestParam("thang") Integer thang,
			 @RequestParam("thuongngayle") Integer thuongngayle,HttpServletRequest request,
			@RequestParam("nam") Integer nam) {
		
		if (thuongngayle != null) {
			NvNhanvien nv = new NvNhanvien();
			nv = nvNhanvienServiceImpl.findById(id).get();
			List<NvQuyetdinhkhenthuong> list = nvQuyetdinhkhenthuongServiceImpl.findAllByKhenThuong(nv.getId(), nam,
					thang);
			double tienkhenthuong = 0;
			for (NvQuyetdinhkhenthuong nvQuyetdinhkhenthuong : list) {
				tienkhenthuong = tienkhenthuong + nvQuyetdinhkhenthuong.getTienthuong();
			}
			List<NvQuyetdinhkyluat> list1 = nvQuyetdinhkyluatServiceImpl.findAllKyLuat(nv.getId(), nam, thang);
			double tienkyluat = 0;
			for (NvQuyetdinhkyluat nvQuyetdinhkyluat : list1) {
				tienkyluat = tienkyluat + nvQuyetdinhkyluat.getTienphat();
			}
			List<PhuCap> list2 = phuCapServiceImpl.findAllByTamUng(id, nam, thang);
			double tienphucap = 0;
			for (PhuCap phuCap : list2) {
				tienphucap = tienphucap + phuCap.getIdPhucap().getMucphucap();
			}
			List<TlTamung> list3 = tITamungServiceImpl.findAllByTamUng(id, nam, thang);
			double tientamung = 0;
			for (TlTamung tlTamung : list3) {
				tientamung = tientamung + tlTamung.getSotien();
			}
			double tienbaohiem = (nv.getIdChucdanh().getLuongcoban() / 100) * 11.5;
			double tienthuongngayle = 0;
			tienthuongngayle = nv.getIdChucdanh().getLuongcoban()
					* dmChitietthuongngayleServiceImpl.findById(thuongngayle).get().getMucthuong();
			model.addAttribute("themNhanVien", "Bảng lương");
			model.addAttribute("thongTinNhanVien", nv);
			model.addAttribute("tienthuongngayle", tienthuongngayle);
			model.addAttribute("tienbaohiem", tienbaohiem);
			model.addAttribute("tienkhenthuong", tienkhenthuong);
			model.addAttribute("tienkyluat", tienkyluat);
			model.addAttribute("thuongngayle", dmChitietthuongngayleServiceImpl.findById(thuongngayle).get().getId());
			model.addAttribute("thang", thang);
			model.addAttribute("nam", nam);
			model.addAttribute("tienphucap", tienphucap);
			model.addAttribute("tientamung", tientamung);
			double tongtien = (nv.getIdChucdanh().getLuongcoban() * (nv.getIdChucdanh().getHesochucvu()
					+ nv.getIdChucdanh().getHesotrachnhiem() + nv.getIdTrinhdo().getHesochuyenmon() + 1))
					+ (tienkhenthuong + tienphucap + tienthuongngayle) - (tienkyluat + tientamung + tienbaohiem);
			model.addAttribute("tongtien", tongtien);
			Principal principal = request.getUserPrincipal();
			User ng = new User();
			ng = userServiceImpl.findBytendangnhap(principal.getName());
			model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
			model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
			return "admin/bangluong";
		} else {
			NvNhanvien nv = new NvNhanvien();
			nv = nvNhanvienServiceImpl.findById(id).get();
			List<NvQuyetdinhkhenthuong> list = nvQuyetdinhkhenthuongServiceImpl.findAllByKhenThuong(nv.getId(), nam,
					thang);
			double tienkhenthuong = 0;
			for (NvQuyetdinhkhenthuong nvQuyetdinhkhenthuong : list) {
				tienkhenthuong = tienkhenthuong + nvQuyetdinhkhenthuong.getTienthuong();
			}
			List<NvQuyetdinhkyluat> list1 = nvQuyetdinhkyluatServiceImpl.findAllKyLuat(nv.getId(), nam, thang);
			double tienkyluat = 0;
			for (NvQuyetdinhkyluat nvQuyetdinhkyluat : list1) {
				tienkyluat = tienkyluat + nvQuyetdinhkyluat.getTienphat();
			}
			List<PhuCap> list2 = phuCapServiceImpl.findAllByTamUng(id, nam, thang);
			double tienphucap = 0;
			for (PhuCap phuCap : list2) {
				tienphucap = tienphucap + phuCap.getIdPhucap().getMucphucap();
			}
			List<TlTamung> list3 = tITamungServiceImpl.findAllByTamUng(id, nam, thang);
			double tientamung = 0;
			for (TlTamung tlTamung : list3) {
				tientamung = tientamung + tlTamung.getSotien();
			}
			double tienbaohiem = (nv.getIdChucdanh().getLuongcoban() / 100) * 11.5;
			double tienthuongngayle = 0;
			model.addAttribute("themNhanVien", "Bảng lương");
			model.addAttribute("thongTinNhanVien", nv);
			model.addAttribute("tienthuongngayle", tienthuongngayle);
			model.addAttribute("tienbaohiem", tienbaohiem);
			model.addAttribute("tienkhenthuong", tienkhenthuong);
			model.addAttribute("tienkyluat", tienkyluat);
			model.addAttribute("thang", thang);
			model.addAttribute("nam", nam);
			model.addAttribute("thuongngayle", 0);
			model.addAttribute("tienphucap", tienphucap);
			model.addAttribute("tientamung", tientamung);
			double tongtien = (nv.getIdChucdanh().getLuongcoban() * (nv.getIdChucdanh().getHesochucvu()
					+ nv.getIdChucdanh().getHesotrachnhiem() + nv.getIdTrinhdo().getHesochuyenmon() +1))
					+ (tienkhenthuong + tienphucap + tienthuongngayle) - (tienkyluat + tientamung + tienbaohiem);
			model.addAttribute("tongtien", tongtien);
			Principal principal = request.getUserPrincipal();
			User ng = new User();
			ng = userServiceImpl.findBytendangnhap(principal.getName());
			model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
			model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
			return "admin/bangluong";
		}
	}

	@GetMapping(value = { "/quantri/listnhanvientheobangluong" })
	public String findAllChucDanh(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAll(pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage, "/quantri/listnhanvientheobangluong");
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
		return "admin/listnhanvientheobangluong";
	}

	@GetMapping("/quantri/findonenhanvienbybangluong/{id}")
	public String findonenhanvien(@PathVariable int id, Model model, @ModelAttribute("status") String status,HttpServletRequest request) {
		model.addAttribute("themNhanVien", "Tạo bảng lương");
		model.addAttribute("chucVu", dmChucdanhServiceImpl.findAllActiveDmChucDanhNative());
		model.addAttribute("chuyenNganh", dmChuyennganhServiceImpl.findAllActiveDmChuyenNganhNative());
		model.addAttribute("danToc", dmDantocServiceImpl.findAllActiveDmDanTocNative());
		model.addAttribute("phongBan", dmPhongbanServiceImpl.findAllActiveDmPhongBanNative());
		model.addAttribute("tinhTrangHonNhan", dmTinhtranghonnhanServiceImpl.findAllActiveDmTinhTrangHonNhanNative());
		model.addAttribute("tonGiao", dmTongiaoServiceImpl.findAllActiveDmTonGiaoNative());
		model.addAttribute("trinhDo", dmTrinhdoServiceImpl.findAllActiveDmTrinhDoNative());
		model.addAttribute("thongTinNhanVien", nvNhanvienServiceImpl.findNhanVienByID(id));
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
		model.addAttribute("chitietthuongle", dmChitietthuongngayleServiceImpl
				.findByIdChiTietThuongLeByChucDanh(nvNhanvienServiceImpl.findNhanVienByID(id).getIdChucdanh().getId()));
		Principal principal = request.getUserPrincipal();
		User ng = new User();
		ng = userServiceImpl.findBytendangnhap(principal.getName());
		model.addAttribute("tenNguoiDung", ng.getIdNhanvien().getHodem() + " " + ng.getIdNhanvien().getTen());
		model.addAttribute("anhNguoiDung", ng.getIdNhanvien().getHinhanh());
		return "admin/taobangluong";
	}

	@GetMapping("/quantri/timkiemnhanvientheotenbangluong")
	public String timKiemNhanVienTheoTen(Model model, @PageableDefault(size = 10) Pageable pageable,HttpServletRequest request,
			@ModelAttribute("statusSuccess") String statusSuccess, @RequestParam("search") String search) {
		Page<NvNhanvien> nhanVienPage = nvNhanvienServiceImpl.findAllByTenNhanVien(search, pageable);
		Pagination<NvNhanvien> page = new Pagination<NvNhanvien>(nhanVienPage,
				"/quantri/timkiemnhanvientheotenbangluong/" + search);
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
		return "admin/listnhanvientheobangluong";
	}

}
