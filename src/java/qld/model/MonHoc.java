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
@Table(name = "MonHoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MonHoc.findAll", query = "SELECT m FROM MonHoc m")
    , @NamedQuery(name = "MonHoc.findById", query = "SELECT m FROM MonHoc m WHERE m.id = :id")
    , @NamedQuery(name = "MonHoc.findByTen", query = "SELECT m FROM MonHoc m WHERE m.ten = :ten")})
public class MonHoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Ten")
    private String ten;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monHocID")
    private List<LopHocPhan> lopHocPhanList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monHocID")
    private List<DiemMonHoc> diemMonHocList;

    public MonHoc() {
    }

    public MonHoc(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @XmlTransient
    public List<LopHocPhan> getLopHocPhanList() {
        return lopHocPhanList;
    }

    public void setLopHocPhanList(List<LopHocPhan> lopHocPhanList) {
        this.lopHocPhanList = lopHocPhanList;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof MonHoc)) {
//            return false;
//        }
//        MonHoc other = (MonHoc) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "qld.model.MonHoc[ id=" + id + " ]";
    }
    
}
