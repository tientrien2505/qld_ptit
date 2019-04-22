/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home.model;

import java.io.IOException;
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
public class GiaoVu {

    private String link;
    private String time;
    private String title;

    public GiaoVu(String link, String time, String title) {
        this.link = link;
        this.time = time;
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GiaoVu() {
    }

    public ArrayList<GiaoVu> getListGiaoVu() {
        try {
            ArrayList<GiaoVu> list = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document document = builder.parse("http://localhost:8084/QLD_PTIT/xml/giaovu.xml");
            org.w3c.dom.Element root = document.getDocumentElement();
            String url = "http://portal.ptit.edu.vn/giaovu/";
            Document doc = Jsoup.connect(url).get();
            Element ul = doc.getElementsByClass(root.getAttribute("class")).get(Integer.parseInt(root.getAttribute("index")));
            org.w3c.dom.Element liXml = null;
            for (int i = 0; i < root.getChildNodes().getLength(); i++) {
                if (!root.getChildNodes().item(i).getNodeName().equals("#text")) {
                    liXml = (org.w3c.dom.Element) root.getChildNodes().item(i);
                }
            }
//            Elements li = ul.getElementsByClass("shownews");
            Elements li = ul.getElementsByClass(liXml.getAttribute("class"));
            org.w3c.dom.Element time = (org.w3c.dom.Element) liXml.getChildNodes().item(1);
            org.w3c.dom.Element title = (org.w3c.dom.Element) liXml.getChildNodes().item(3);
            org.w3c.dom.Element link = (org.w3c.dom.Element) liXml.getChildNodes().item(5);
            for (Element lii : li) {
                GiaoVu giaoVu = new GiaoVu();
                try {
                    giaoVu.setTime(getString(time, lii));
                    giaoVu.setTitle(getString(title, lii));
                    giaoVu.setLink(getString(link, lii));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                list.add(giaoVu);

            }
            return list;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GiaoVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GiaoVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GiaoVu.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public String giaoVuToJson(ArrayList<GiaoVu> listGiaoVu) {
        if (listGiaoVu.size() == 0)
            return "";
        StringBuffer json = new StringBuffer();
        json.append("[");
        for (GiaoVu giaoVu : listGiaoVu) {
            json.append("{");
            json.append("\"link\":\"" + giaoVu.getLink() + "\",");
            json.append("\"time\":\"" + giaoVu.getTime()+ "\",");
            json.append("\"title\":\"" + giaoVu.getTitle() + "\"");
            json.append("},");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]");

        return json.toString();
    }
}
