package com.bitbuy;

import com.bitbuy.entity.User;
import com.bitbuy.exception.UserRestrictedException;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    public boolean isValidRequestForRegister(User user) throws UserRestrictedException {
        if(user.getUserName() == null || user.getUserName() == ""){

            throw new UserRestrictedException("USR-001","UserName should not be null");

        }
        if(user.getPassword() == null || user.getPassword() == ""){
            throw new UserRestrictedException("USR-002","Password should not be null");
        }

        return true;
    }
}
