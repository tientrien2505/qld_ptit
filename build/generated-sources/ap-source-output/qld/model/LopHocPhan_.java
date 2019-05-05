package qld.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import qld.model.GiaoVien;
import qld.model.MonHoc;
import qld.model.SinhVienLHP;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T12:37:07")
@StaticMetamodel(LopHocPhan.class)
public class LopHocPhan_ { 

    public static volatile SingularAttribute<LopHocPhan, String> thu;
    public static volatile ListAttribute<LopHocPhan, SinhVienLHP> sinhVienLHPList;
    public static volatile SingularAttribute<LopHocPhan, String> khoaHoc;
    public static volatile SingularAttribute<LopHocPhan, Integer> kip;
    public static volatile SingularAttribute<LopHocPhan, Integer> kyHoc;
    public static volatile SingularAttribute<LopHocPhan, MonHoc> monHocID;
    public static volatile SingularAttribute<LopHocPhan, GiaoVien> giaoVienMaGV;
    public static volatile SingularAttribute<LopHocPhan, Integer> id;
    public static volatile SingularAttribute<LopHocPhan, String> phong;

}