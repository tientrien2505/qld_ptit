$(document).ready(function () {
    
    $.ajax({
        type: 'POST',
        url: 'GiaoVuServlet',
        data: {sl: 5,
            offset: 0
        },
        success: updateGiaoVu
    });
    
    function updateGiaoVu(json) {
        json = json.replace(/\\n/g, "\\n")
                .replace(/\\'/g, "\\'")
                .replace(/\\"/g, '\\"')
                .replace(/\\&/g, "\\&")
                .replace(/\\r/g, "\\r")
                .replace(/\\t/g, "\\t")
                .replace(/\\b/g, "\\b")
                .replace(/\\f/g, "\\f");
// remove non-printable and other non-valid JSON chars
        json = json.replace(/[\u0000-\u0019]+/g, "");
        console.log(json);
        var object = JSON.parse(json);
        console.log(object);
        var ul = document.getElementById("giaoVu");
        for (var i = 0; i < object.length; i++) {
            var li = document.createElement("li");
            var time = document.createElement("div");
            time.setAttribute("class","timeGiaoVu");
            time.innerHTML = object[i].time;
            var a = document.createElement("a");
            a.setAttribute("href",object[i].link);
            a.setAttribute("class","linkGiaoVu");
            a.setAttribute("target","blank");
            a.innerHTML = object[i].title;
            li.appendChild(time);
            li.appendChild(a);
            ul.appendChild(li);
        }

    }
});
