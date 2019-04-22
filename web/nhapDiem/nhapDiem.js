$(document).ready(function () {
    $("input").on('click', function () {
        this.select();
    });
    // ẩn ảnh động waiting
    $("#doiLuuDiem").hide();

    // gửi yêu cầu lấy dữ liệu về thông báo từ giáo vụ, trả về chuỗi json
    $.ajax({
        type: 'POST',
        url: 'GiaoVuServlet',
        data: {sl: 5,
            offset: 0
        },
        success: updateGiaoVu
    });

    function updateGiaoVu(jsonString) {
        console.log(jsonString);
        var object = JSON.parse(jsonString);
        console.log(object);
        var ul = document.getElementById("giaoVu");
        for (var i = 0; i < object.length; i++) {
            var li = document.createElement("li");
            var time = document.createElement("div");
            time.setAttribute("class", "timeGiaoVu");
            time.innerHTML = object[i].time;
            var a = document.createElement("a");
            a.setAttribute("href", object[i].link);
            a.setAttribute("class", "linkGiaoVu");
            a.setAttribute("target", "blank");
            a.innerHTML = object[i].title;
            li.appendChild(time);
            li.appendChild(a);
            ul.appendChild(li);
        }
    }

    //Gửi yêu cầu lấy các thông tin khoá học, kỳ học
    $.ajax({
        type: 'POST',
        url: 'KhoaHoc',
        data: {
        },
        success: updateKhoaHoc
    });

    function updateKhoaHoc(jsonString) {
        console.log(jsonString);
        if (jsonString === "")
            return;
        var object = JSON.parse(jsonString);
        console.log(object);
        var select = document.getElementById("khoaHoc");
        for (var i = 0; i < object.length; i++) {
            var option = document.createElement("option");
            option.setAttribute("value", object[i].khoaHoc);
            option.innerHTML = object[i].khoaHoc;
            select.appendChild(option);
        }
    }
    capNhatMonHoc();
    $("#khoaHoc").change(capNhatMonHoc);
    $("#kyHoc").change(capNhatMonHoc);
    $("#monHoc").change(capNhatLopHocPhan);
    $("#lopHoc").change(capNhatDanhSachSV);
    function capNhatMonHoc() {
        var KH = $("#khoaHoc").val();
        var Ky = $("#kyHoc").val();
        console.log(Ky);
        console.log(KH);
        $.ajax({
            type: 'POST',
            url: 'MonHoc',
            data: {
                khoaHoc: KH,
                kyHoc: Ky
            },
            success: updateMonHoc
        });
    }
    function updateMonHoc(jsonString) {
        console.log(jsonString);
        $("#monHoc").empty();
        var op = document.createElement("option");
        op.setAttribute("value", "");
        $("#monHoc").append(op);
        if (jsonString.toString() === "") {
            capNhatLopHocPhan();
            return;
        }
        var object = JSON.parse(jsonString);
        console.log(object);
        var select = document.getElementById("monHoc");
        for (var i = 0; i < object.length; i++) {
            var option = document.createElement("option");
            option.setAttribute("value", object[i].idMonHoc);
            option.innerHTML = object[i].monHoc;
            select.appendChild(option);
        }
        capNhatLopHocPhan();
    }
    function capNhatLopHocPhan() {
        var KH = $("#khoaHoc").val();
        var Ky = $("#kyHoc").val();
        var mh = $("#monHoc").val();
        console.log(Ky);
        console.log(KH);
        console.log(mh);
        $.ajax({
            type: 'POST',
            url: 'LopHocPhan',
            data: {
                khoaHoc: KH,
                kyHoc: Ky,
                idMonHoc: mh
            },
            success: updateLHP
        });
    }
    function updateLHP(jsonString) {
        console.log(jsonString);
        $("#lopHoc").empty();
        var op = document.createElement("option");
        op.setAttribute("value", "");
        $("#lopHoc").append(op);
        if (jsonString.toString() === "") {
            capNhatDanhSachSV();
            return;
        }
        var object = JSON.parse(jsonString);
        console.log(object);
        var select = document.getElementById("lopHoc");
        for (var i = 0; i < object.length; i++) {
            var option = document.createElement("option");
            option.setAttribute("value", object[i].idLopHocPhan);
            option.innerHTML = object[i].tenLopHocPhan;
            select.appendChild(option);
        }
        capNhatDanhSachSV();
    }
    function capNhatDanhSachSV() {
        var idLHP = $("#lopHoc").val();
        console.log("ma lop hoc phan: " + idLHP);
        $.ajax({
            type: 'POST',
            url: 'DanhSachSV',
            data: {
                idLopHocPhan: idLHP
            },
            success: updateDanhSachSV
        });
    }
    function updateDanhSachSV(jsonString) {
        console.log(jsonString);
        $(".ds").remove();
        if (jsonString.toString() === "")
            return;
        var object = JSON.parse(jsonString);
        console.log(object);
        var table = document.getElementById("danhSachSinhVien");
        for (var i = 0; i < object.length; i++) {
            var tr = document.createElement("tr");
            tr.setAttribute("class", "ds send");
            tr.setAttribute("value", object[i].maSinhVien);

            var hoTen = document.createElement("td");
            hoTen.setAttribute("class", "hoTen");
            hoTen.innerHTML = "<p>" + object[i].hoTen + "</p>";
            tr.appendChild(hoTen);
            var msv = document.createElement("td");
            msv.setAttribute("class", "maSinhVien");
            msv.innerHTML = "<p>" + object[i].maSinhVien + "</p>";
            tr.appendChild(msv);

            var diemCC = document.createElement("td");
            diemCC.setAttribute("class", "diem");
            var d = parseFloat(object[i].diemCC);
            console.log(typeof (d));
            diemCC.innerHTML = "<input type='text' value='" + d.toFixed(2)+ "'>";
            tr.appendChild(diemCC);
            var diemBT = document.createElement("td");
            diemBT.setAttribute("class", "diem");
            d = parseFloat(object[i].diemBT);
            diemBT.innerHTML = "<input type='text' value='" + d.toFixed(2) + "'>";
            tr.appendChild(diemBT);
            var diemGK = document.createElement("td");
            diemGK.setAttribute("class", "diem");
            d = parseFloat(object[i].diemGK);
            diemGK.innerHTML = "<input type='text' value='" + d.toFixed(2) + "'>";
            tr.appendChild(diemGK);
            var diemCK = document.createElement("td");
            diemCK.setAttribute("class", "diem");
            d = parseFloat(object[i].diemCK);
            diemCK.innerHTML = "<input type='text' value='" + d.toFixed(2) + "'>";
            tr.appendChild(diemCK);
            var diemTB = document.createElement("td");
            diemTB.setAttribute("class", "diem");
            var diem = object[i].diemCC * 0.1 + object[i].diemBT * 0.2 + object[i].diemGK * 0.2 + object[i].diemCK * 0.5;
            diemTB.innerHTML = diem.toFixed(2);
            console.log(typeof (diem));
            tr.appendChild(diemTB);

            table.appendChild(tr);
        }
        timSinhVien();
        $("input").on('click', function () {
            this.select();
        });
    }
    $("#msv").keyup(timSinhVien);
    function timSinhVien() {
        var mangMaSinhVien = $(".ds");
        for (var i = 0; i < mangMaSinhVien.length; i++) {
            if ($("#msv").val() === $(mangMaSinhVien[i]).attr("value").substring(0, $("#msv").val().length)) {
                $(mangMaSinhVien[i]).show();
                $(mangMaSinhVien[i]).addClass("send");
            } else {
                $(mangMaSinhVien[i]).hide();
                $(mangMaSinhVien[i]).removeClass("send");
            }
        }
    }
    $("#save").on('click', function () {
        $("#doiLuuDiem").show();
        setTimeout(function () {
            var mangSend = $(".send");
            if (mangSend.length === 0) {
                alert("Không có dữ liệu để lưu");
                $("#doiLuuDiem").hide();
            } else {
                console.log(mangSend);
                for (var i = 0; i < mangSend.length; i++) {
                    console.log(mangSend[i]);
                    if (khongDuDieuKien(mangSend[i])) {
                        alert("Dữ liệu nhập vào không đúng cho sinh viên " + $(mangSend[i]).attr("value")
                                + "\n Nhập điểm trong đoạn [0;10], dùng dấu chấm (.) để làm dấu thập phân, tối đa sau dấu chấm 2 chữ số!"
                                + "\n                                        (-_-)");
                        $("#doiLuuDiem").hide();
                    } else {
                        var idSV = $(mangSend[i]).attr("value");
                        console.log(idSV);
                        var idLHP = $("#lopHoc").val();
                        var CC = $($($(mangSend[i]).children()[2]).children()[0]).val();
                        var BT = $($($(mangSend[i]).children()[3]).children()[0]).val();
                        var GK = $($($(mangSend[i]).children()[4]).children()[0]).val();
                        var CK = $($($(mangSend[i]).children()[5]).children()[0]).val();
                        $.ajax({
                            type: 'POST',
                            url: 'NhapDiemServlet',
                            data: {
                                idSinhVien: idSV,
                                idLopHocPhan: idLHP,
                                diemCC: CC,
                                diemBT: BT,
                                diemGK: GK,
                                diemCK: CK
                            },
                            success: thongBao
                        });
                        function thongBao(message) {
                            console.log(message);
                            message = JSON.parse(message);
                            if (message.trangThai === "them")
                                alert("Thêm điểm thành công cho sinh viên " + message.idSV);
                            else if (message.trangThai === "capNhat")
                                alert("Cập nhật điểm thành công cho sinh viên " + message.idSV);
                            else
                                alert("Có sự cố, điểm sinh viên " + message.idSV + " chưa được cập nhật! Sorry (>.<)");
                            $("#doiLuuDiem").hide();
                            capNhatDanhSachSV();
                        }
                    }
                }
            }
        }, 1000);
    });

    function khongDuDieuKien(mangSend) {
        var reg = /(^[0-9]{1}$)|(^[0-9]{1}.[0-9]{1,2}$)|(^10$)|(^10.[0]{1,2}$)/;
        var CC = $($($(mangSend).children()[2]).children()[0]).val();
        var BT = $($($(mangSend).children()[3]).children()[0]).val();
        var GK = $($($(mangSend).children()[4]).children()[0]).val();
        var CK = $($($(mangSend).children()[5]).children()[0]).val();
        if (!reg.test(CC))
            return true;
        if (!reg.test(BT))
            return true;
        if (!reg.test(GK))
            return true;
        if (!reg.test(CK))
            return true;
        return false;
    }
});