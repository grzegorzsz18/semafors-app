package com.semafors.grzegorz.semafors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by grzegorz on 06.11.17.
 */

public class UserService {

    private static UserService userService;
    private static ConnectionService connectionService;

    private UserService(){
        connectionService = ConnectionService.getConnectionService();
    }

    public static UserService getUserService(){
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }
}
