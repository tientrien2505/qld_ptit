package qld.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import qld.model.DiemMonHoc;
import qld.model.LopHocPhan;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-21T22:31:59")
@StaticMetamodel(MonHoc.class)
public class MonHoc_ { 

    public static volatile ListAttribute<MonHoc, DiemMonHoc> diemMonHocList;
    public static volatile ListAttribute<MonHoc, LopHocPhan> lopHocPhanList;
    public static volatile SingularAttribute<MonHoc, Integer> id;
    public static volatile SingularAttribute<MonHoc, String> ten;

}