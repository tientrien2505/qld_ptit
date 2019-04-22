package qld.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import qld.model.GiaoVien;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-21T22:31:59")
@StaticMetamodel(TaiKhoan.class)
public class TaiKhoan_ { 

    public static volatile SingularAttribute<TaiKhoan, String> password;
    public static volatile SingularAttribute<TaiKhoan, Integer> id;
    public static volatile SingularAttribute<TaiKhoan, String> username;
    public static volatile ListAttribute<TaiKhoan, GiaoVien> giaoVienList;

}