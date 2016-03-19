package com.tqmall.wind.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tqmall.wind.mapper.TireReleaseMapper;
import com.tqmall.wind.model.TireReleaseDO;

@Repository
public class TireReleaseDao {
    @Resource
    private TireReleaseMapper tireReleaseMapper;
    
    public Integer insert(TireReleaseDO record) {
        Integer o = tireReleaseMapper.insertSelective(record);
        if(o == null) {
        	return null;
        }
        return record.getId();
    }
    
    public List<TireReleaseDO> selectByAge(Integer age) {
    	TireReleaseDO r = new TireReleaseDO();
    	r.setAge(age);
    	List<TireReleaseDO> result = tireReleaseMapper.selectByAge(r);
    	return result;
    }
    
    public TireReleaseDO selectByKey(Integer id) {
    	return tireReleaseMapper.selectByPrimaryKey(id);
    }
    
    
    public Integer transaction(TireReleaseDO record) {
        Integer o = tireReleaseMapper.insertSelective(record);
        if(o == null) {
        	return null;
        }
        return record.getId();
    }
}
