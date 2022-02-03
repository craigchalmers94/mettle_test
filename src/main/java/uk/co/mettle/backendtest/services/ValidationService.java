package uk.co.mettle.backendtest.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.co.mettle.backendtest.model.Feature;
import uk.co.mettle.backendtest.util.UnauthorisedActionException;

import javax.servlet.http.HttpServletRequest;

@Service
public class ValidationService {

    public void actionPermitted(HttpServletRequest request, String requiredRole) throws UnauthorisedActionException {

        // TODO setup a filter to have the MettleUser as part of the request
//        MettleUser user = (MettleUser) request.getAttribute("uk.co.mettle.mettleuser");
//
//        if (user == null || user.getRole() == null || user.getRole().equals(requiredRole)) {
//            throw new UnauthorisedActionException(requiredRole);
//        }
    }

}
