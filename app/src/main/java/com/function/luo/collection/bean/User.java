package com.function.luo.collection.bean;

import com.function.luo.collection.base.DbBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by luo on 2019/4/19.
 */
@Entity(nameInDb = "User")
public class User extends DbBean {
    @Id(autoincrement = true)
    private Long id;


    @NotNull
    @Property(nameInDb = "name")
    private String name;


    @NotNull
    @Property(nameInDb = "age")
    private String age;


    @Generated(hash = 2054556472)
    public User(Long id, @NotNull String name, @NotNull String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }


    @Generated(hash = 586692638)
    public User() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAge() {
        return this.age;
    }


    public void setAge(String age) {
        this.age = age;
    }



}
