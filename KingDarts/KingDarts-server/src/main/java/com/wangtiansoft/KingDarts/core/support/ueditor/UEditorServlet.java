package com.wangtiansoft.KingDarts.core.support.ueditor;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

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
@WebServlet(name = "ueditor", urlPatterns = "/ueditor/controller")
public class UEditorServlet extends HttpServlet {

    @Value("${ueditor.fileRootPath}")
    private String fileRootPath;
    @Value("${ueditor.fileServerUrl}")
    private String fileServerUrl;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        String result = new ActionEnter(request, fileRootPath, fileServerUrl).exec();
        if (StringUtils.equalsIgnoreCase("config", action)){
            JSONObject jsonObject = JSONObject.parseObject(result);
            jsonObject.put("fileManagerUrlPrefix", fileServerUrl);
            jsonObject.put("imageManagerUrlPrefix", fileServerUrl);
            result = jsonObject.toJSONString();
        }
        out.write(result);
        out.close();
    }

}
