
package servlet;

import home.control.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import home.model.News;

/**
 *
 * @author TruongDao
 */
public class NewsServlet extends HttpServlet {

   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        int sl = Integer.parseInt(request.getParameter("sl"));
        int offset = Integer.parseInt(request.getParameter("offset"));
        ArrayList<News> listNews;
        listNews = new News().DataToNews();
        NewsDAO newsDao = new NewsDAO("LapTrinhWeb", "sa", "1");
        newsDao.insert(listNews);
        listNews = newsDao.select(offset, sl);
        PrintWriter pw = response.getWriter();
        News news = new News();
        String tmp = news.newsToJson(listNews);
        pw.write(tmp);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
