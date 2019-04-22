package qld.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import qld.model.DiemMonHoc;
import qld.model.SinhVienLHP;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-21T22:31:59")
@StaticMetamodel(SinhVien.class)
public class SinhVien_ { 

    public static volatile ListAttribute<SinhVien, SinhVienLHP> sinhVienLHPList;
    public static volatile SingularAttribute<SinhVien, String> diaChi;
    public static volatile SingularAttribute<SinhVien, String> sdt;
    public static volatile SingularAttribute<SinhVien, String> maSV;
    public static volatile ListAttribute<SinhVien, DiemMonHoc> diemMonHocList;
    public static volatile SingularAttribute<SinhVien, String> hoTen;
    public static volatile SingularAttribute<SinhVien, String> lop;
    public static volatile SingularAttribute<SinhVien, String> email;

}