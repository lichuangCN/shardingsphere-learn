package site.muzhi.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.muzhi.common.entity.City;
import site.muzhi.common.mapper.CityMapper;

import java.util.List;

/**
 * @author lichuang
 * @date 2021/03/05
 * @description
 */
@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public void save(City city) {
        cityMapper.insert(city);
    }

    public List<City> list() {
        return cityMapper.list();
    }

    public City findById(Long id) {
        return cityMapper.findById(id);
    }

    public List<City> listByName(String name) {
        return cityMapper.listByName(name);
    }
}
