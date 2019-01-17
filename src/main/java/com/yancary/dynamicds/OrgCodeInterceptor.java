package com.yancary.dynamicds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class OrgCodeInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerInterceptor.class);

    private String orgCodeHeaderName = "orgCode";

    private Set<String> validOrgCodes;

    public void setOrgCodeHeaderName(String orgCodeName) {
        orgCodeHeaderName = orgCodeName;
    }

    public void setValidOrgCodes(Set<String> validOrgCodes) {
        this.validOrgCodes = validOrgCodes;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String orgCodeVal = httpServletRequest.getHeader(orgCodeHeaderName);
        LOGGER.debug(" Switching data sources to {}", orgCodeVal);
        if (orgCodeVal == null) {
            LOGGER.info(" If orgCode is not found, the first configuration in the configuration will be used as orgCode");
            orgCodeVal = validOrgCodes.iterator().next();
        }
        if (!validOrgCodes.contains(orgCodeVal)) {
            LOGGER.error(String.format(" the orgCode %s is not valid.", orgCodeVal));
            return false;
        }
        OrgCodeHolder.putOrgCode(orgCodeVal);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        OrgCodeHolder.remove();
    }

}
