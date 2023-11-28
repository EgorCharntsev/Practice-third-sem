package ru.kpfu.itis.charntsev.net.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


@WebFilter(filterName = "loggingFilter", urlPatterns = "/")
public class LoggingFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();

        this.context.log("LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest)servletRequest;
        Map<String, String[]> params = request.getParameterMap();
        if (params != null) {
            String param = params.keySet().stream().
                    map(key -> key + "=" + Arrays.toString(params.get(key))).
                    collect(Collectors.joining(",", "{", "}"));

            this.context.log(request.getRemoteAddr() + ": request params: " + params);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
