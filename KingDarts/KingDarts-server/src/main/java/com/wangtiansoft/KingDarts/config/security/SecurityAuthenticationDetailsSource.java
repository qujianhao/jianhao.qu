package com.wangtiansoft.KingDarts.config.security;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public class SecurityAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>, Serializable {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
        return new SecurityWebAuthenticationDetails(request);
    }


    public class SecurityWebAuthenticationDetails extends WebAuthenticationDetails {

        private String session_id;
        private String validate_code;

        public SecurityWebAuthenticationDetails(HttpServletRequest request) {
            super(request);
            this.session_id = request.getSession().getId();
            this.validate_code = request.getParameter("validate_code");
        }

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public String getValidate_code() {
            return validate_code;
        }

        public void setValidate_code(String validate_code) {
            this.validate_code = validate_code;
        }
    }

}
