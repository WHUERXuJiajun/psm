package whu.web.psm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import whu.web.psm.pojo.Collection;

import java.util.List;

public interface CollectionMapper extends JpaRepository<Collection,String> {

    List<Collection> findByPhone(String phone);

    List<Collection> findByPhoneAndMid(String phone, Integer mid);

    void deleteByPhoneAndMid(String phone, Integer mid);
}
