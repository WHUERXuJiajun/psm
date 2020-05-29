package whu.web.psm.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import whu.web.psm.pojo.MissionTable;

/**
 * 对于任务的检索接口
 */
public interface MissionTableElasticSearchMapper extends ElasticsearchRepository<MissionTable, String> {

    Page<MissionTable> findByTitleOrDescriptionLike(String title, String description, Pageable pageable);

    void deleteAll();
}
