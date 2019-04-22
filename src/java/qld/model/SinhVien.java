/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qld.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TruongDao
 */
@Entity
@Table(name = "SinhVien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SinhVien.findAll", query = "SELECT s FROM SinhVien s")
    , @NamedQuery(name = "SinhVien.findByMaSV", query = "SELECT s FROM SinhVien s WHERE s.maSV = :maSV")
    , @NamedQuery(name = "SinhVien.findByHoTen", query = "SELECT s FROM SinhVien s WHERE s.hoTen = :hoTen")
    , @NamedQuery(name = "SinhVien.findByLop", query = "SELECT s FROM SinhVien s WHERE s.lop = :lop")
    , @NamedQuery(name = "SinhVien.findByDiaChi", query = "SELECT s FROM SinhVien s WHERE s.diaChi = :diaChi")
    , @NamedQuery(name = "SinhVien.findBySdt", query = "SELECT s FROM SinhVien s WHERE s.sdt = :sdt")
    , @NamedQuery(name = "SinhVien.findByEmail", query = "SELECT s FROM SinhVien s WHERE s.email = :email")})
public class SinhVien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MaSV")
    private String maSV;
    @Column(name = "HoTen")
    private String hoTen;
    @Column(name = "Lop")
    private String lop;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "Sdt")
    private String sdt;
    @Column(name = "Email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sinhVienMaSV")
    private List<SinhVienLHP> sinhVienLHPList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sinhVienMaSV")
    private List<DiemMonHoc> diemMonHocList;

    public SinhVien() {
    }

    public SinhVien(String maSV) {
        this.maSV = maSV;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public List<SinhVienLHP> getSinhVienLHPList() {
        return sinhVienLHPList;
    }

    public void setSinhVienLHPList(List<SinhVienLHP> sinhVienLHPList) {
        this.sinhVienLHPList = sinhVienLHPList;
    }

    @XmlTransient
    public List<DiemMonHoc> getDiemMonHocList() {
        return diemMonHocList;
    }

    public void setDiemMonHocList(List<DiemMonHoc> diemMonHocList) {
        this.diemMonHocList = diemMonHocList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maSV != null ? maSV.hashCode() : 0);
        return hash;
    }

//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof SinhVien)) {
//            return false;
//        }
//        SinhVien other = (SinhVien) object;
//        if ((this.maSV == null && other.maSV != null) || (this.maSV != null && !this.maSV.equals(other.maSV))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "qld.model.SinhVien[ maSV=" + maSV + " ]";
    }
    
}
