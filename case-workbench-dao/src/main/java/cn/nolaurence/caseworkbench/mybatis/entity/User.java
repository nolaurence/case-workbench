package cn.nolaurence.caseworkbench.mybatis.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 游戏
     */
    private String email;

    /**
     * 用户状态  0-正常
     */
    private Integer userStatus;

    /**
     * 电话号码
     */
    private String phoneNo;

    /**
     * 
     */
    private Date gmtCreate;

    /**
     * 
     */
    private Date gmtModified;

    /**
     * 1-已删除  0-未删除
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}