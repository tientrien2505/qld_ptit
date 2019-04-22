$(document).ready(function () {

    $.ajax({
        type: 'POST',
        url: 'NewsServlet',
        data: {sl: 30,
            offset: 0
        },
        success: updateNews
    });
    $.ajax({
        type: 'POST',
        url: 'GiaoVuServlet',
        data: {sl: 5,
            offset: 0
        },
        success: updateGiaoVu
    });
    function updateNews(json) {
        if (json === "")
            return;
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
        for (var i = 0; i < object.length; i++) {
            var li = document.createElement("li");
            var left_news = document.createElement("div");
            left_news.className = "left_news";
            var a1 = document.createElement("a");
            a1.setAttribute("href", "SubContent?link=" + object[i].link);
            var img = document.createElement("img");
            img.setAttribute("src", object[i].image);
            var right_news = document.createElement("div");
            right_news.className = "right_news";
            var a2 = document.createElement("a");
            a2.setAttribute("href", "SubContent?link=" + object[i].link);
            var h2 = document.createElement("h2");
            h2.className = "title";
            h2.innerHTML = object[i].title;
            var time = document.createElement("p");
            time.setAttribute("class", "time");
            time.innerHTML = object[i].time;
            var content = document.createElement("p");
            content.setAttribute("class", "content");
            content.innerHTML = object[i].content;
            a1.appendChild(img);
            left_news.appendChild(a1);
            li.appendChild(left_news);
            a2.appendChild(h2);
            right_news.appendChild(a2);
            right_news.appendChild(time);
            right_news.appendChild(content);
            li.appendChild(right_news);
            var ul = document.getElementById("listNews");
            ul.appendChild(li);
        }

    }
    function updateGiaoVu(json) {
        if (json === "")
            return;
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
});