<jsp:include page="header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="./nhapDiem/nhapDiem.css">
<%  int i; // i = 1 la da dang nhap, i = 0 la chua dang nhap
    String user = null;
    String pass = null;
    session = request.getSession();
    if (session.isNew()) {
        session.setAttribute("user", "");
        i = 0;
    } else {
        try {
            user = session.getAttribute("user").toString();
            pass = session.getAttribute("pass").toString();
            if (user.equals("")) {
                i = 0;
                response.sendRedirect("home");
            } else {
                i = 1;
            }
        } catch (Exception e) {
            i = 0;
            session.setAttribute("user", "");
            response.sendRedirect("home");
        }
    }
%>
<div id="wrap" class="wrap">
    <main>
        <article>
            <table id="options">
                <tr>
                    <td>
                        <label for="khoaHoc">Khoá học</label>
                        <select id="khoaHoc">
                            <!--<option selected="true">2018-2019</option>-->
                        </select>
                    </td>
                    <td>
                        <label for="kyHoc">Kỳ học</label>
                        <select id="kyHoc">
                            <option value="2">Kỳ II</option>
                            <option value="1">Kỳ I</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="monHoc">Môn học</label>
                    </td>
                    <td>
                        <select id="monHoc">
                            <option></option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="lopHoc">Lớp học phần</label>
                    </td>
                    <td>
                        <select id="lopHoc">
                            <option></option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="msv">Nhập mã sinh viên:</label>
                    </td>
                    <td>
                        <input id="msv" type="text">
                    </td>
                    <td>
                        <img id="doiLuuDiem" class="an" src="./images/loading.gif" width="15px">
                        <button id="save">Lưu điểm</button>
                    </td>
                </tr>
            </table>
            <table id="danhSachSinhVien">
                <tr>
                    <td class="hoTen" style="text-align: center">
                        <p>Họ tên</p>
                    </td>
                    <td class="maSinhVien">
                        <p>MSV</p>
                    </td>
                    <td class="diem">
                        <p>Điểm C.Cần</p>
                    </td>
                    <td class="diem">
                        <p>Điểm B.Tập</p>
                    </td>
                    <td class="diem">
                        <p>Điểm G.Kỳ</p>
                    </td>
                    <td class="diem">
                        <p>Điểm C.Kỳ</p>
                    </td>
                    <td class="diem">
                        <p>Điểm TB</p>
                    </td>
                </tr>
            </table>
        </article>
        <aside>
            <ul id="giaoVu">

            </ul>
        </aside>				
    </main>
</div>
<script type="text/javascript" src="./nhapDiem/nhapDiem.js"></script>
<jsp:include page="footer.jsp"></jsp:include>