package com.tqmall.wind.mapper.autoparts;

import java.util.List;

import com.tqmall.wind.model.TireReleaseDO;

/**
 * TireReleaseMapper数据库操作接口类
 */
public interface TireReleaseMapper {


    /**
     * 查询（根据主键ID查询）
     */
    TireReleaseDO selectByPrimaryKey(Integer id);
    
    List<TireReleaseDO> selectByAge(TireReleaseDO record);

    /**
     * 添加 （匹配有值的字段）
     */
    Integer insertSelective(TireReleaseDO record);

    /**
     * 修改 （匹配有值的字段）
     */
    Integer updateByPrimaryKeySelective(TireReleaseDO record);

}