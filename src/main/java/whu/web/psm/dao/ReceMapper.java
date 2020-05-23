package whu.web.psm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import whu.web.psm.pojo.ReceExample;
import whu.web.psm.pojo.ReceKey;

@Mapper
public interface ReceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rece
     *
     * @mbggenerated
     */
    int countByExample(ReceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rece
     *
     * @mbggenerated
     */
    int deleteByExample(ReceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rece
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(ReceKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rece
     *
     * @mbggenerated
     */
    int insert(ReceKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rece
     *
     * @mbggenerated
     */
    int insertSelective(ReceKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rece
     *
     * @mbggenerated
     */
    List<ReceKey> selectByExample(ReceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rece
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") ReceKey record, @Param("example") ReceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table rece
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") ReceKey record, @Param("example") ReceExample example);

    List<Integer> getMidByPhone(String phone);
}