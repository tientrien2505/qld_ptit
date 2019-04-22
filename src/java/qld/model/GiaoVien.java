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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "GiaoVien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GiaoVien.findAll", query = "SELECT g FROM GiaoVien g")
    , @NamedQuery(name = "GiaoVien.findByMaGV", query = "SELECT g FROM GiaoVien g WHERE g.maGV = :maGV")
    , @NamedQuery(name = "GiaoVien.findByHoTen", query = "SELECT g FROM GiaoVien g WHERE g.hoTen = :hoTen")
    , @NamedQuery(name = "GiaoVien.findBySdt", query = "SELECT g FROM GiaoVien g WHERE g.sdt = :sdt")
    , @NamedQuery(name = "GiaoVien.findByDiaChi", query = "SELECT g FROM GiaoVien g WHERE g.diaChi = :diaChi")
    , @NamedQuery(name = "GiaoVien.findByEmail", query = "SELECT g FROM GiaoVien g WHERE g.email = :email")})
public class GiaoVien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MaGV")
    private String maGV;
    @Column(name = "HoTen")
    private String hoTen;
    @Column(name = "Sdt")
    private String sdt;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "Email")
    private String email;
    @JoinColumn(name = "TaiKhoanID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TaiKhoan taiKhoanID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "giaoVienMaGV")
    private List<LopHocPhan> lopHocPhanList;

    public GiaoVien() {
    }

    public GiaoVien(String maGV) {
        this.maGV = maGV;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TaiKhoan getTaiKhoanID() {
        return taiKhoanID;
    }

    public void setTaiKhoanID(TaiKhoan taiKhoanID) {
        this.taiKhoanID = taiKhoanID;
    }

    @XmlTransient
    public List<LopHocPhan> getLopHocPhanList() {
        return lopHocPhanList;
    }

    public void setLopHocPhanList(List<LopHocPhan> lopHocPhanList) {
        this.lopHocPhanList = lopHocPhanList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maGV != null ? maGV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GiaoVien)) {
            return false;
        }
        GiaoVien other = (GiaoVien) object;
        if (other.getDiaChi()==this.diaChi
                && other.getEmail()==this.email
                && other.getHoTen()==this.hoTen
                && other.getMaGV()==this.maGV
                && other.getSdt()==this.sdt
                && other.getTaiKhoanID()==this.taiKhoanID){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "qld.model.GiaoVien[ maGV=" + maGV + " ]";
    }
    
}
