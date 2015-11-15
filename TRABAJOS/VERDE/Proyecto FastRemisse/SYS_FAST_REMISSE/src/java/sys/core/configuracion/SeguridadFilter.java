/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.core.configuracion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Indra
 */
public class SeguridadFilter implements Filter {

    private static String URL_LOGIN = "/login.xhtml";

    @Override
    public void init(FilterConfig fc) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = ((HttpServletRequest) request).getSession(false);
        
        if(httpRequest.getServletPath().endsWith(".xhtml")&&countOccurrences(httpRequest.getServletPath(),'.')==1){
            if(session == null || session.getAttribute("terminal")==null) {                
                if (validarURL(httpRequest.getServletPath())) {                    
                    chain.doFilter(request, response);
                }else{                    
                    //SessionMBean sessionMBean = (SessionMBean) WebServletContextListener.getApplicationContext().getBean("sessionMBean");
                    //sessionMBean.setMensajeExpiraSession("Session Expirada");
                    if(session!=null){
                        session.removeAttribute("terminal");
                        session.invalidate();
                    }
                    httpResponse.sendRedirect(httpRequest.getContextPath() + URL_LOGIN);
                }
            } else {
                //httpResponse.sendRedirect(httpRequest.getContextPath() + URL_LOGIN);
                chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }        
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean validarURL(String url) {        
        List<String> listaUrls = new ArrayList<String>();
        listaUrls.add("");
      //  listaUrls.add("/levanta.jsp");
        listaUrls.add("/faces/login.xhtml");
        listaUrls.add("/login.xhtml");
        //listaUrls.add("/inicio.xhtml");
        
        if (!listaUrls.contains(url)) {
            return false;
        }
        return true;
    }
    
    public int countOccurrences(String haystack, char needle){
        int count = 0;
        for (int i=0; i < haystack.length(); i++){
            if (haystack.charAt(i) == needle){
                count++;
            }
        }        
        return count;
    }
}
