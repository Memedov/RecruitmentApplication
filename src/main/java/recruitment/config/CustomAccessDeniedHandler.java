package recruitment.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException, ServletException {
        boolean isRecruiter = false;
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        isRecruiter = recruitment.config.MySimpleUrlAuthenticationSuccessHandler.isRecruiter(isRecruiter, authentication);
        if (isRecruiter) {
            response.sendRedirect(request.getContextPath() + "/list-applications");
        } else if (!isRecruiter) {
            response.sendRedirect(request.getContextPath() + "/apply");
        } else {
            throw new IllegalStateException();
        }
    }
}
