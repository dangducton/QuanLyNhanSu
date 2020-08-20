package com.dangmailinh.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "nv_nhanvien")
public class NvNhanvien implements Serializable {

	 private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "id")
	    private Integer id;
	    @Basic(optional = false)
	    @Column(name = "diachihientai")
	    private String diachihientai;
	    @Basic(optional = false)
	    @Column(name = "diachithuongtru")
	    private String diachithuongtru;
	    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
	    @Basic(optional = false)
	    @Column(name = "email")
	    private String email;
	    @Column(name = "ghichu")
	    private String ghichu;
	    @Basic(optional = false)
	    @Column(name = "gioitinh")
	    private int gioitinh;
	    @Column(name = "hinhanh")
	    private String hinhanh;
	    @Basic(optional = false)
	    @Column(name = "hodem")
	    private String hodem;
	    @Basic(optional = false)
	    @Column(name = "luong")
	    private double luong;
	    @Basic(optional = false)
	    @Column(name = "id_chitiet")
	    private String idChiTiet;
	    @Basic(optional = false)
	    @Column(name = "ngaysinh")
	    @Temporal(TemporalType.DATE)
	    private Date ngaysinh;
	    @Basic(optional = false)
	    @Column(name = "noicapcmnd")
	    private String noicapcmnd;
	    @Basic(optional = false)
	    @Column(name = "noisinh")
	    private String noisinh;
	    @Basic(optional = false)
	    @Column(name = "sodienthoai")
	    private String sodienthoai;
	    @Basic(optional = false)
	    @Column(name = "status")
	    private int status;
	    @Basic(optional = false)
	    @Column(name = "ten")
	    private String ten;
	    @Basic(optional = false)
	    @Column(name = "trangthaihopdong")
	    private int trangthaihopdong;
	    @Basic(optional = false)
	    @Column(name = "quoctich")
	    private String quoctich;
	    @Basic(optional = false)
	    @Column(name = "cmnd")
	    private String cmnd;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<NvQuyetdinhkyluat> nvQuyetdinhkyluatCollection;
	    @JoinColumn(name = "id_chucdanh", referencedColumnName = "id")
	    @ManyToOne(optional = false)
	    private DmChucdanh idChucdanh;
	    @JoinColumn(name = "id_chuyennganh", referencedColumnName = "id")
	    @ManyToOne(optional = false)
	    private DmChuyennganh idChuyennganh;
	    @JoinColumn(name = "id_dantoc", referencedColumnName = "id")
	    @ManyToOne(optional = false)
	    private DmDantoc idDantoc;
	    @JoinColumn(name = "id_phongban", referencedColumnName = "id")
	    @ManyToOne(optional = false)
	    private DmPhongban idPhongban;
	    @JoinColumn(name = "id_tinhtranghonnhan", referencedColumnName = "id")
	    @ManyToOne(optional = false)
	    private DmTinhtranghonnhan idTinhtranghonnhan;
	    @JoinColumn(name = "id_tongiao", referencedColumnName = "id")
	    @ManyToOne(optional = false)
	    private DmTongiao idTongiao;
	    @JoinColumn(name = "id_trinhdo", referencedColumnName = "id")
	    @ManyToOne(optional = false)
	    private DmTrinhdo idTrinhdo;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<NvQuyetdinhthangchuc> nvQuyetdinhthangchucCollection;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<NvQuyetdinhchamduthopdong> nvQuyetdinhchamduthopdongCollection;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<NvQuatrinhcongtac> nvQuatrinhcongtacCollection;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<TlTamung> tlTamungCollection;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<TlBangluong> tlBangluongCollection;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<NvQuanhegiadinh> nvQuanhegiadinhCollection;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<NvQuyetdinhkhenthuong> nvQuyetdinhkhenthuongCollection;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<PhuCap> phucapCollection;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<User> userCollection;
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNhanvien")
	    private Collection<NvQuyetdinhnanghesochuyenmon> nvQuyetdinhnanghesochuyenmonCollection;

	    public NvNhanvien() {
	    }

	    public NvNhanvien(Integer id) {
	        this.id = id;
	    }

	    public NvNhanvien(Integer id, String diachihientai, String diachithuongtru, String email, int gioitinh, String hodem, double luong, String idChiTiet, Date ngaysinh, String noicapcmnd, String noisinh, String sodienthoai, int status, String ten, int trangthaihopdong, String quoctich, String cmnd) {
	        this.id = id;
	        this.diachihientai = diachihientai;
	        this.diachithuongtru = diachithuongtru;
	        this.email = email;
	        this.gioitinh = gioitinh;
	        this.hodem = hodem;
	        this.luong = luong;
	        this.idChiTiet = idChiTiet;
	        this.ngaysinh = ngaysinh;
	        this.noicapcmnd = noicapcmnd;
	        this.noisinh = noisinh;
	        this.sodienthoai = sodienthoai;
	        this.status = status;
	        this.ten = ten;
	        this.trangthaihopdong = trangthaihopdong;
	        this.quoctich = quoctich;
	        this.cmnd = cmnd;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getDiachihientai() {
	        return diachihientai;
	    }

	    public void setDiachihientai(String diachihientai) {
	        this.diachihientai = diachihientai;
	    }

	    public String getDiachithuongtru() {
	        return diachithuongtru;
	    }

	    public void setDiachithuongtru(String diachithuongtru) {
	        this.diachithuongtru = diachithuongtru;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getGhichu() {
	        return ghichu;
	    }

	    public void setGhichu(String ghichu) {
	        this.ghichu = ghichu;
	    }

	    public int getGioitinh() {
	        return gioitinh;
	    }

	    public void setGioitinh(int gioitinh) {
	        this.gioitinh = gioitinh;
	    }

	    public String getHinhanh() {
	        return hinhanh;
	    }

	    public void setHinhanh(String hinhanh) {
	        this.hinhanh = hinhanh;
	    }

	    public String getHodem() {
	        return hodem;
	    }

	    public void setHodem(String hodem) {
	        this.hodem = hodem;
	    }

	    public double getLuong() {
	        return luong;
	    }

	    public void setLuong(double luong) {
	        this.luong = luong;
	    }

	    public String getIdChiTiet() {
	        return idChiTiet;
	    }

	    public void setIdChiTiet(String idChiTiet) {
	        this.idChiTiet = idChiTiet;
	    }

	    public Date getNgaysinh() {
	        return ngaysinh;
	    }

	    public void setNgaysinh(Date ngaysinh) {
	        this.ngaysinh = ngaysinh;
	    }

	    public String getNoicapcmnd() {
	        return noicapcmnd;
	    }

	    public void setNoicapcmnd(String noicapcmnd) {
	        this.noicapcmnd = noicapcmnd;
	    }

	    public String getNoisinh() {
	        return noisinh;
	    }

	    public void setNoisinh(String noisinh) {
	        this.noisinh = noisinh;
	    }

	    public String getSodienthoai() {
	        return sodienthoai;
	    }

	    public void setSodienthoai(String sodienthoai) {
	        this.sodienthoai = sodienthoai;
	    }

	    public int getStatus() {
	        return status;
	    }

	    public void setStatus(int status) {
	        this.status = status;
	    }

	    public String getTen() {
	        return ten;
	    }

	    public void setTen(String ten) {
	        this.ten = ten;
	    }

	    public int getTrangthaihopdong() {
	        return trangthaihopdong;
	    }

	    public void setTrangthaihopdong(int trangthaihopdong) {
	        this.trangthaihopdong = trangthaihopdong;
	    }

	    public String getQuoctich() {
	        return quoctich;
	    }

	    public void setQuoctich(String quoctich) {
	        this.quoctich = quoctich;
	    }

	    public String getCmnd() {
	        return cmnd;
	    }

	    public void setCmnd(String cmnd) {
	        this.cmnd = cmnd;
	    }

	    public Collection<NvQuyetdinhkyluat> getNvQuyetdinhkyluatCollection() {
	        return nvQuyetdinhkyluatCollection;
	    }

	    public void setNvQuyetdinhkyluatCollection(Collection<NvQuyetdinhkyluat> nvQuyetdinhkyluatCollection) {
	        this.nvQuyetdinhkyluatCollection = nvQuyetdinhkyluatCollection;
	    }

	    public DmChucdanh getIdChucdanh() {
	        return idChucdanh;
	    }

	    public void setIdChucdanh(DmChucdanh idChucdanh) {
	        this.idChucdanh = idChucdanh;
	    }

	    public DmChuyennganh getIdChuyennganh() {
	        return idChuyennganh;
	    }

	    public void setIdChuyennganh(DmChuyennganh idChuyennganh) {
	        this.idChuyennganh = idChuyennganh;
	    }

	    public DmDantoc getIdDantoc() {
	        return idDantoc;
	    }

	    public void setIdDantoc(DmDantoc idDantoc) {
	        this.idDantoc = idDantoc;
	    }

	    public DmPhongban getIdPhongban() {
	        return idPhongban;
	    }

	    public void setIdPhongban(DmPhongban idPhongban) {
	        this.idPhongban = idPhongban;
	    }

	    public DmTinhtranghonnhan getIdTinhtranghonnhan() {
	        return idTinhtranghonnhan;
	    }

	    public void setIdTinhtranghonnhan(DmTinhtranghonnhan idTinhtranghonnhan) {
	        this.idTinhtranghonnhan = idTinhtranghonnhan;
	    }

	    public DmTongiao getIdTongiao() {
	        return idTongiao;
	    }

	    public void setIdTongiao(DmTongiao idTongiao) {
	        this.idTongiao = idTongiao;
	    }

	    public DmTrinhdo getIdTrinhdo() {
	        return idTrinhdo;
	    }

	    public void setIdTrinhdo(DmTrinhdo idTrinhdo) {
	        this.idTrinhdo = idTrinhdo;
	    }

	    public Collection<NvQuyetdinhthangchuc> getNvQuyetdinhthangchucCollection() {
	        return nvQuyetdinhthangchucCollection;
	    }

	    public void setNvQuyetdinhthangchucCollection(Collection<NvQuyetdinhthangchuc> nvQuyetdinhthangchucCollection) {
	        this.nvQuyetdinhthangchucCollection = nvQuyetdinhthangchucCollection;
	    }

	    public Collection<NvQuyetdinhchamduthopdong> getNvQuyetdinhchamduthopdongCollection() {
	        return nvQuyetdinhchamduthopdongCollection;
	    }

	    public void setNvQuyetdinhchamduthopdongCollection(Collection<NvQuyetdinhchamduthopdong> nvQuyetdinhchamduthopdongCollection) {
	        this.nvQuyetdinhchamduthopdongCollection = nvQuyetdinhchamduthopdongCollection;
	    }

	    public Collection<NvQuatrinhcongtac> getNvQuatrinhcongtacCollection() {
	        return nvQuatrinhcongtacCollection;
	    }

	    public void setNvQuatrinhcongtacCollection(Collection<NvQuatrinhcongtac> nvQuatrinhcongtacCollection) {
	        this.nvQuatrinhcongtacCollection = nvQuatrinhcongtacCollection;
	    }

	    public Collection<TlTamung> getTlTamungCollection() {
	        return tlTamungCollection;
	    }

	    public void setTlTamungCollection(Collection<TlTamung> tlTamungCollection) {
	        this.tlTamungCollection = tlTamungCollection;
	    }

	    public Collection<TlBangluong> getTlBangluongCollection() {
	        return tlBangluongCollection;
	    }

	    public void setTlBangluongCollection(Collection<TlBangluong> tlBangluongCollection) {
	        this.tlBangluongCollection = tlBangluongCollection;
	    }

	    public Collection<NvQuanhegiadinh> getNvQuanhegiadinhCollection() {
	        return nvQuanhegiadinhCollection;
	    }

	    public void setNvQuanhegiadinhCollection(Collection<NvQuanhegiadinh> nvQuanhegiadinhCollection) {
	        this.nvQuanhegiadinhCollection = nvQuanhegiadinhCollection;
	    }

	    public Collection<NvQuyetdinhkhenthuong> getNvQuyetdinhkhenthuongCollection() {
	        return nvQuyetdinhkhenthuongCollection;
	    }

	    public void setNvQuyetdinhkhenthuongCollection(Collection<NvQuyetdinhkhenthuong> nvQuyetdinhkhenthuongCollection) {
	        this.nvQuyetdinhkhenthuongCollection = nvQuyetdinhkhenthuongCollection;
	    }

	    public Collection<PhuCap> getPhucapCollection() {
	        return phucapCollection;
	    }

	    public void setPhucapCollection(Collection<PhuCap> phucapCollection) {
	        this.phucapCollection = phucapCollection;
	    }

	    public Collection<User> getUserCollection() {
	        return userCollection;
	    }

	    public void setUserCollection(Collection<User> userCollection) {
	        this.userCollection = userCollection;
	    }

	    public Collection<NvQuyetdinhnanghesochuyenmon> getNvQuyetdinhnanghesochuyenmonCollection() {
	        return nvQuyetdinhnanghesochuyenmonCollection;
	    }

	    public void setNvQuyetdinhnanghesochuyenmonCollection(Collection<NvQuyetdinhnanghesochuyenmon> nvQuyetdinhnanghesochuyenmonCollection) {
	        this.nvQuyetdinhnanghesochuyenmonCollection = nvQuyetdinhnanghesochuyenmonCollection;
	    }

	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (id != null ? id.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof NvNhanvien)) {
	            return false;
	        }
	        NvNhanvien other = (NvNhanvien) object;
	        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "com.dangmailinh.NvNhanvien[ id=" + id + " ]";
	    }
	    
	}
