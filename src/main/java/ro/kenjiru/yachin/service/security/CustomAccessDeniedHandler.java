package ro.kenjiru.yachin.service.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {
	
	@Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
		 response.sendRedirect(request.getContextPath()+"/accessDenied.xhtml");
		 request.getSession().setAttribute("message",
		  "You do not have permission to access this page!");
}

}
