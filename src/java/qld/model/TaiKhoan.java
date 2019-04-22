/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qld.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import qld.control.TaiKhoanDao;

/**
 *
 * @author TruongDao
 */
@Entity
@Table(name = "TaiKhoan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaiKhoan.findAll", query = "SELECT t FROM TaiKhoan t")
    , @NamedQuery(name = "TaiKhoan.findById", query = "SELECT t FROM TaiKhoan t WHERE t.id = :id")
    , @NamedQuery(name = "TaiKhoan.findByUsername", query = "SELECT t FROM TaiKhoan t WHERE t.username = :username")
    , @NamedQuery(name = "TaiKhoan.findByPassword", query = "SELECT t FROM TaiKhoan t WHERE t.password = :password")})
public class TaiKhoan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taiKhoanID")
    private List<GiaoVien> giaoVienList;

    public TaiKhoan() {
    }

    public TaiKhoan(Integer id) {
        this.id = id;
    }

    public TaiKhoan(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<GiaoVien> getGiaoVienList() {
        return giaoVienList;
    }

    public void setGiaoVienList(List<GiaoVien> giaoVienList) {
        this.giaoVienList = giaoVienList;
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
//        if (!(object instanceof TaiKhoan)) {
//            return false;
//        }
//        TaiKhoan other = (TaiKhoan) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "qld.model.TaiKhoan[ id=" + id + " ]";
    }

    public boolean isTaiKhoan() {
        ArrayList<TaiKhoan> listTK = new TaiKhoanDao("QLD_PTIT", "sa", "1").selectAll();
        for (TaiKhoan tk : listTK){
            if (this.username.equals(tk.getUsername()) && this.password.equals(tk.getPassword())){
                this.id = tk.getId();
                return true;
            }
        }
        return false;
    }
    
}
