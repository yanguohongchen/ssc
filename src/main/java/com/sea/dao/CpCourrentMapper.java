package com.sea.dao;

import java.util.List;

import com.sea.model.CpCourrent;

public interface CpCourrentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cp_courrent
     *
     * @mbggenerated Sat Oct 11 16:04:36 CST 2014
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cp_courrent
     *
     * @mbggenerated Sat Oct 11 16:04:36 CST 2014
     */
    int insert(CpCourrent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cp_courrent
     *
     * @mbggenerated Sat Oct 11 16:04:36 CST 2014
     */
    int insertSelective(CpCourrent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cp_courrent
     *
     * @mbggenerated Sat Oct 11 16:04:36 CST 2014
     */
    CpCourrent selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cp_courrent
     *
     * @mbggenerated Sat Oct 11 16:04:36 CST 2014
     */
    int updateByPrimaryKeySelective(CpCourrent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cp_courrent
     *
     * @mbggenerated Sat Oct 11 16:04:36 CST 2014
     */
    int updateByPrimaryKey(CpCourrent record);
    
    
    List<CpCourrent> getList();
}