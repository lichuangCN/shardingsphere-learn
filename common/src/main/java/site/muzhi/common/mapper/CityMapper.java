package site.muzhi.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import site.muzhi.common.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lichuang
 * @date 2021/03/05
 * @description
 */
@Repository
public interface CityMapper extends BaseMapper<City> {

    List<City> list();

    City findById(Long id);

    List<City> listByName(@Param("name") String name);
}
