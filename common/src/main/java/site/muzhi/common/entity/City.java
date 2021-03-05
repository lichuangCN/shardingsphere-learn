package site.muzhi.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lichuang
 * @date 2020/06/28
 * @description
 */
@Data
public class City implements Serializable {

    private static final long serialVersionUID = 991729937968468384L;

    private Long id;

    private String cityName;

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }
}
