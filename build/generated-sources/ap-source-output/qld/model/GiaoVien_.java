package qld.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import qld.model.LopHocPhan;
import qld.model.TaiKhoan;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-05T12:37:07")
@StaticMetamodel(GiaoVien.class)
public class GiaoVien_ { 

    public static volatile SingularAttribute<GiaoVien, String> maGV;
    public static volatile SingularAttribute<GiaoVien, String> diaChi;
    public static volatile SingularAttribute<GiaoVien, String> sdt;
    public static volatile SingularAttribute<GiaoVien, TaiKhoan> taiKhoanID;
    public static volatile ListAttribute<GiaoVien, LopHocPhan> lopHocPhanList;
    public static volatile SingularAttribute<GiaoVien, String> hoTen;
    public static volatile SingularAttribute<GiaoVien, String> email;

}