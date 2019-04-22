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
import qld.control.MonHocDao;

/**
 *
 * @author TruongDao
 */
@Entity
@Table(name = "LopHocPhan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LopHocPhan.findAll", query = "SELECT l FROM LopHocPhan l")
    , @NamedQuery(name = "LopHocPhan.findById", query = "SELECT l FROM LopHocPhan l WHERE l.id = :id")
    , @NamedQuery(name = "LopHocPhan.findByPhong", query = "SELECT l FROM LopHocPhan l WHERE l.phong = :phong")
    , @NamedQuery(name = "LopHocPhan.findByKhoaHoc", query = "SELECT l FROM LopHocPhan l WHERE l.khoaHoc = :khoaHoc")
    , @NamedQuery(name = "LopHocPhan.findByKyHoc", query = "SELECT l FROM LopHocPhan l WHERE l.kyHoc = :kyHoc")
    , @NamedQuery(name = "LopHocPhan.findByThu", query = "SELECT l FROM LopHocPhan l WHERE l.thu = :thu")
    , @NamedQuery(name = "LopHocPhan.findByKip", query = "SELECT l FROM LopHocPhan l WHERE l.kip = :kip")})
public class LopHocPhan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Phong")
    private String phong;
    @Column(name = "KhoaHoc")
    private String khoaHoc;
    @Basic(optional = false)
    @Column(name = "KyHoc")
    private int kyHoc;
    @Column(name = "Thu")
    private String thu;
    @Basic(optional = false)
    @Column(name = "Kip")
    private int kip;
    @JoinColumn(name = "GiaoVienMaGV", referencedColumnName = "MaGV")
    @ManyToOne(optional = false)
    private GiaoVien giaoVienMaGV;
    @JoinColumn(name = "MonHocID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private MonHoc monHocID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lopHocPhanID")
    private List<SinhVienLHP> sinhVienLHPList;

    public LopHocPhan() {
    }

    public LopHocPhan(Integer id) {
        this.id = id;
    }

    public LopHocPhan(Integer id, int kyHoc, int kip) {
        this.id = id;
        this.kyHoc = kyHoc;
        this.kip = kip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(String khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public int getKyHoc() {
        return kyHoc;
    }

    public void setKyHoc(int kyHoc) {
        this.kyHoc = kyHoc;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public int getKip() {
        return kip;
    }

    public void setKip(int kip) {
        this.kip = kip;
    }

    public GiaoVien getGiaoVienMaGV() {
        return giaoVienMaGV;
    }

    public void setGiaoVienMaGV(GiaoVien giaoVienMaGV) {
        this.giaoVienMaGV = giaoVienMaGV;
    }

    public MonHoc getMonHocID() {
        return monHocID;
    }

    public void setMonHocID(MonHoc monHocID) {
        this.monHocID = monHocID;
    }

    @XmlTransient
    public List<SinhVienLHP> getSinhVienLHPList() {
        return sinhVienLHPList;
    }

    public void setSinhVienLHPList(List<SinhVienLHP> sinhVienLHPList) {
        this.sinhVienLHPList = sinhVienLHPList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof LopHocPhan)) {
//            return false;
//        }
//        LopHocPhan other = (LopHocPhan) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "Môn " + new MonHocDao("QLD_PTIT", "sa", "1").getMonHoc(this).getTen() + ", phòng " + this.phong + ", thứ " + this.thu + ", nhóm " + this.kip;
    }

}
