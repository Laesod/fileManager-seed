package com;

import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        if(req instanceof RequestFacade){
            if(((RequestFacade) req).getHeaders("Origin") != null && ((RequestFacade) req).getHeaders("Origin").hasMoreElements()){

                String origin = ((RequestFacade) req).getHeaders("Origin").nextElement().toString();

                if(origin.equals("http://localhost:2000") || origin.equals("http://localhost:1000") || origin.equals("http://localhost:1001")){
                    response.setHeader("Access-Control-Allow-Origin", origin);
                }
            }
        }

        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}
