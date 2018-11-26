package com.wangtiansoft.KingDarts.core.support.wtuploader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by weitong on 17/4/20.
 */
@WebServlet(name = "wtuploader", urlPatterns = "/wtuploader/controller")
public class WtUploaderServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        String result = new WtUploaderActionEnter(request).exec();
        out.write(result);
        out.close();
    }

}
