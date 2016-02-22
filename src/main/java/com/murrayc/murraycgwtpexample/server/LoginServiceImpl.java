package com.murrayc.murraycgwtpexample.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.murrayc.murraycgwtpexample.client.LoginInfo;
import com.murrayc.murraycgwtpexample.client.LoginService;

/**
 * Created by murrayc on 1/18/16.
 */
public class LoginServiceImpl extends ServiceWithUser implements
        LoginService {

    public LoginInfo login(final String requestUri) {
        final LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoggedIn(false);

        final User user = getUser();
        final UserService userService = UserServiceFactory.getUserService();
        if (user != null) {
            loginInfo.setLoggedIn(true);
            loginInfo.setEmailAddress(user.getEmail());
            loginInfo.setNickname(user.getNickname());
            loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
        } else {
            loginInfo.setLoggedIn(false);

            if (userService != null) {
                loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
            }
        }
        return loginInfo;
    }

}
