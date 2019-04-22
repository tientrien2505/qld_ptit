/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qld.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TruongDao
 */
@Entity
@Table(name = "SinhVienLHP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SinhVienLHP.findAll", query = "SELECT s FROM SinhVienLHP s")
    , @NamedQuery(name = "SinhVienLHP.findById", query = "SELECT s FROM SinhVienLHP s WHERE s.id = :id")})
public class SinhVienLHP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "LopHocPhanID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private LopHocPhan lopHocPhanID;
    @JoinColumn(name = "SinhVienMaSV", referencedColumnName = "MaSV")
    @ManyToOne(optional = false)
    private SinhVien sinhVienMaSV;

    public SinhVienLHP() {
    }

    public SinhVienLHP(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LopHocPhan getLopHocPhanID() {
        return lopHocPhanID;
    }

    public void setLopHocPhanID(LopHocPhan lopHocPhanID) {
        this.lopHocPhanID = lopHocPhanID;
    }

    public SinhVien getSinhVienMaSV() {
        return sinhVienMaSV;
    }

    public void setSinhVienMaSV(SinhVien sinhVienMaSV) {
        this.sinhVienMaSV = sinhVienMaSV;
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
//        if (!(object instanceof SinhVienLHP)) {
//            return false;
//        }
//        SinhVienLHP other = (SinhVienLHP) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "qld.model.SinhVienLHP[ id=" + id + " ]";
    }
    
}
