/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

/**
 *
 * @author TruongDao
 */
public class News {

    private String link;
    private String image;
    private String title;
    private String content;
    private Timestamp time;

    public News() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public ArrayList<News> DataToNews() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse("http://localhost:8084/QLD_PTIT/xml/xmlfile.xml");
            org.w3c.dom.Element root = document.getDocumentElement();
            ArrayList<News> listNews = new ArrayList<>();
            String url = "http://genk.vn";
            Document doc = Jsoup.connect(url).get();
//            Element ul = doc.getElementById("LoadListCate");
            Element ul = doc.getElementById(root.getAttribute("id"));
            org.w3c.dom.Element liXml = null;
            for (int i = 0; i < root.getChildNodes().getLength(); i++) {
                if (!root.getChildNodes().item(i).getNodeName().equals("#text")) {
                    liXml = (org.w3c.dom.Element) root.getChildNodes().item(i);
                }
            }
//            Elements li = ul.getElementsByClass("shownews");
            Elements li = ul.getElementsByClass(liXml.getAttribute("class"));
            org.w3c.dom.Element link = (org.w3c.dom.Element) liXml.getChildNodes().item(1);
            org.w3c.dom.Element image = (org.w3c.dom.Element) liXml.getChildNodes().item(3);
            org.w3c.dom.Element title = (org.w3c.dom.Element) liXml.getChildNodes().item(5);
            org.w3c.dom.Element content = (org.w3c.dom.Element) liXml.getChildNodes().item(7);
            for (Element lii : li) {
                News news = new News();
//                try {
//                    Element link = lii.getElementsByTag("a").get(0);
//                    news.setLink(url + link.attr("href"));
//                    Element image = lii.getElementsByTag("img").first();
//                    news.setImage(image.attr("src"));
//                    Element title = lii.getElementsByClass("kscliw-ava").first();
//                    news.setTitle(title.text().replace("\"", "\'"));
//                    Element con = lii.getElementsByClass("knswli-sapo").first();
//                    news.setContent(con.text().replace("\"", "\\\""));
////                Element time = lii.getElementsByClass("knswli-time").first();
//                    news.setTime(new Timestamp(new Date().getTime()));
//                } catch (Exception e) {
//                    continue;
//                }
                try {
                    news.setLink(url + getString(link, lii));
                    news.setImage(getString(image, lii));
                    news.setTitle(getString(title, lii));
                    news.setContent(getString(content, lii));
//                    System.out.println(lii.attr("id").substring(6));
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmm");
                    Date date = format.parse(lii.attr("id").substring(6, 18));
                    news.setTime(new Timestamp(date.getTime()));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                listNews.add(news);

            }
            return listNews;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(News.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getString(org.w3c.dom.Element e1, org.jsoup.nodes.Element e2) {
        org.jsoup.nodes.Element b = null;
        b = e2.getElementsByTag(e1.getNodeName()).get(Integer.parseInt(e1.getAttribute("index")));
        if (!e1.getAttribute("attr").equals("")) {
            return b.attr(e1.getAttribute("attr"));
        }
        return b.text().replace("\"", "\\\"");
    }

    public String newsToJson(ArrayList<News> listNews) {
        if (listNews.size() == 0) {
            return "";
        }
        StringBuffer json = new StringBuffer();
        json.append("[");
        for (News news : listNews) {
            json.append("{");
            json.append("\"link\":\"" + news.getLink() + "\",");
            json.append("\"image\":\"" + news.getImage() + "\",");
            json.append("\"title\":\"" + news.getTitle() + "\",");
            json.append("\"content\":\"" + news.getContent() + "\",");
            Date date = new Date(news.getTime().getTime());
            SimpleDateFormat format = new SimpleDateFormat("HH : mm - dd/MM/yyyy");
            String time = format.format(date);
//            json.append("\"time\":\"" + news.getTime().toString() + "\"");
            json.append("\"time\":\"" + time + "\"");
            json.append("},");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]");

        return json.toString();
    }

}
