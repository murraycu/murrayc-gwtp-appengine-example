package com.murrayc.murraycgwtpexample.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by murrayc on 1/18/16.
 */
@RemoteServiceRelativePath("login-service")
public interface LoginService extends RemoteService {
    LoginInfo login(final String requestUri);
}
