package site.muzhi.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import site.muzhi.common.entity.User;

import java.util.List;

/**
 * @author lichuang
 * @date 2021/03/05
 * @description
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    List<User> list();

    User findById(Long id);

    List<User> listByName(@Param("name") String name);
}
