package com.function.luo.collection.bean;

import com.function.luo.collection.base.BaseDaggerActivity;
import com.function.luo.collection.base.DbBean;

/**
 * Created by luo on 2019/4/27.
 */

public class LoginBean extends DbBean {

    public  Long id;
    public String name;
    public String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
