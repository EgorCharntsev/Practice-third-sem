package ru.kpfu.itis.charntsev.net.server;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class HttpClientInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String first = req.getParameter("param1");
        String second = req.getParameter("param2");

        String headers = getHeadersAsString(req);

        //немного не в консоли, но мне показалось, что так интереснее
        PrintWriter writer = resp.getWriter();
        writer.println("GET Request" + "\n" +
                "First param: " + first + "\n" +
                "Second param: " + second + "\n" +
                "Headers:\n" + headers);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String first = req.getParameter("param1");
        String second = req.getParameter("param2");

        String reqBody = getRequestToString(req);

        String headers = getHeadersAsString(req);

        //немного не в консоли, но мне показалось, что так интереснее
        PrintWriter writer = resp.getWriter();
        writer.println("GET Request" + "\n" +
                "First param: " + first + "\n" +
                "Second param: " + second + "\n" +
                "Request Body: " + reqBody + "\n" +
                "Headers:\n" + headers);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String first = req.getParameter("param1");
        String second = req.getParameter("param2");

        String reqBody = getRequestToString(req);

        String headers = getHeadersAsString(req);

        //немного не в консоли, но мне показалось, что так интереснее
        PrintWriter writer = resp.getWriter();
        writer.println("GET Request" + "\n" +
                "First param: " + first + "\n" +
                "Second param: " + second + "\n" +
                "Request Body: " + reqBody + "\n" +
                "Headers:\n" + headers);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String first = req.getParameter("param1");
        String second = req.getParameter("param2");

        String headers = getHeadersAsString(req);

        //немного не в консоли, но мне показалось, что так интереснее
        PrintWriter writer = resp.getWriter();
        writer.println("GET Request" + "\n" +
                "First param: " + first + "\n" +
                "Second param: " + second + "\n" +
                "Headers:\n" + headers);
    }

    private String getRequestToString(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        String str;
        BufferedReader br = req.getReader();
        while((str = br.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    private String getHeadersAsString(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            sb.append(headerName).append(":").append(req.getHeader(headerName)).append("\n");
        }
        return sb.toString();
    }
}
