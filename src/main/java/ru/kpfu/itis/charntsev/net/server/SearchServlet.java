package ru.kpfu.itis.charntsev.net.server;

import org.json.JSONObject;
import ru.kpfu.itis.charntsev.net.client.HttpClientImplementation;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "searchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpClientImplementation netSample = new HttpClientImplementation();
        Map<String, String> params = new HashMap<>();
        params.put("appid", "3b3b368c72a5cc4ec4318d9bb9e0bb07");
        params.put("q", req.getParameter("city"));
        String answer = netSample.get("https://api.openweathermap.org/data/2.5/weather", params);

        JSONObject jsonObject = new JSONObject(answer);
        if (jsonObject.getInt("cod") == 200) {
            resp.addCookie(new Cookie("temp", "" + jsonObject.getJSONObject("main").getBigDecimal("temp")));
            resp.addCookie(new Cookie("humidity", "" + jsonObject.getJSONObject("main").getBigDecimal("humidity")));
            resp.addCookie(new Cookie("description", jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")));
            resp.addCookie(new Cookie("name", jsonObject.getString("name")));
            resp.addCookie(new Cookie("error", null));
        } else {
            resp.addCookie(new Cookie("error", "NotFound"));
        }

        resp.sendRedirect("info.jsp");
    }
}
