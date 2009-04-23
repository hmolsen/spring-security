package org.springframework.security.web.logout;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.logout.LogoutFilter;
import org.springframework.security.web.logout.SecurityContextLogoutHandler;

/**
 * @author Luke Taylor
 * @version $Id$
 */
public class LogoutHandlerTests extends TestCase {
    LogoutFilter filter;

    protected void setUp() throws Exception {
        filter = new LogoutFilter("/success", new SecurityContextLogoutHandler());
    }

    public void testRequiresLogoutUrlWorksWithPathParams() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setRequestURI("/j_spring_security_logout;someparam=blah?otherparam=blah");

        assertTrue(filter.requiresLogout(request, response));
    }

    public void testRequiresLogoutUrlWorksWithQueryParams() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContextPath("/context");
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setRequestURI("/context/j_spring_security_logout?param=blah");

        assertTrue(filter.requiresLogout(request, response));
    }

}