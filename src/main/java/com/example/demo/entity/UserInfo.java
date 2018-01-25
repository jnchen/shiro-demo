package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by caojingchen on 2018/1/23.
 */
@Entity
@Data
@ToString(exclude = "roleList")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    private String salt;
    private byte state; //0 创建未认证 1 正常 2锁定

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole",joinColumns = {@JoinColumn(name = "uid")},inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList;

    /**
     * 密码盐
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
}
