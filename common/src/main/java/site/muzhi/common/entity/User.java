package site.muzhi.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lichuang
 * @date 2020/06/28
 * @description
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8190254860322263023L;

    private Long id;

    private String username;

    private Date createDate;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }
}
