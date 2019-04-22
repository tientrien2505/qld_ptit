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
@Table(name = "DiemMonHoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiemMonHoc.findAll", query = "SELECT d FROM DiemMonHoc d")
    , @NamedQuery(name = "DiemMonHoc.findById", query = "SELECT d FROM DiemMonHoc d WHERE d.id = :id")
    , @NamedQuery(name = "DiemMonHoc.findByDiemCC", query = "SELECT d FROM DiemMonHoc d WHERE d.diemCC = :diemCC")
    , @NamedQuery(name = "DiemMonHoc.findByDiemBT", query = "SELECT d FROM DiemMonHoc d WHERE d.diemBT = :diemBT")
    , @NamedQuery(name = "DiemMonHoc.findByDiemGK", query = "SELECT d FROM DiemMonHoc d WHERE d.diemGK = :diemGK")
    , @NamedQuery(name = "DiemMonHoc.findByDiemCK", query = "SELECT d FROM DiemMonHoc d WHERE d.diemCK = :diemCK")})
public class DiemMonHoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DiemCC")
    private float diemCC;
    @Basic(optional = false)
    @Column(name = "DiemBT")
    private float diemBT;
    @Basic(optional = false)
    @Column(name = "DiemGK")
    private float diemGK;
    @Basic(optional = false)
    @Column(name = "DiemCK")
    private float diemCK;
    @JoinColumn(name = "MonHocID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private MonHoc monHocID;
    @JoinColumn(name = "SinhVienMaSV", referencedColumnName = "MaSV")
    @ManyToOne(optional = false)
    private SinhVien sinhVienMaSV;

    public DiemMonHoc() {
    }

    public DiemMonHoc(Integer id) {
        this.id = id;
    }

    public DiemMonHoc(Integer id, float diemCC, float diemBT, float diemGK, float diemCK) {
        this.id = id;
        this.diemCC = diemCC;
        this.diemBT = diemBT;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getDiemCC() {
        return diemCC;
    }

    public void setDiemCC(float diemCC) {
        this.diemCC = diemCC;
    }

    public float getDiemBT() {
        return diemBT;
    }

    public void setDiemBT(float diemBT) {
        this.diemBT = diemBT;
    }

    public float getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(float diemGK) {
        this.diemGK = diemGK;
    }

    public float getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(float diemCK) {
        this.diemCK = diemCK;
    }

    public MonHoc getMonHocID() {
        return monHocID;
    }

    public void setMonHocID(MonHoc monHocID) {
        this.monHocID = monHocID;
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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiemMonHoc)) {
            return false;
        }
        DiemMonHoc other = (DiemMonHoc) object;
        if (other.getId() == this.id && other.getMonHocID() == this.monHocID
                && other.getSinhVienMaSV() == this.sinhVienMaSV
                && other.getDiemBT() == this.diemBT && other.getDiemCC() == this.diemCC
                && other.getDiemCK() == this.diemCK && other.getDiemGK() == this.diemGK) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "qld.model.DiemMonHoc[ id=" + id + " ]";
    }

}
