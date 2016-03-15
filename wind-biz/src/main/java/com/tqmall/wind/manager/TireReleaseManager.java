package com.tqmall.wind.manager;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tqmall.wind.dao.TireReleaseDao;
import com.tqmall.wind.model.TireReleaseDO;

@Service
public class TireReleaseManager {
	Log log = LogFactory.getLog(this.getClass());
	
	@Resource
	TireReleaseDao tireReleaseDao;
	
	
	private static final Object lock = new Object();
	
	public void biz(Integer age) {
		biz2(age);
	}
	
	@Transactional(rollbackFor = Exception.class)
	private void biz2(Integer age) {
		
		TireReleaseDO record = new TireReleaseDO();
		record.setAge(21);
		record.setName("zhangsan");
		String name = Thread.currentThread().getName();
		log.info(name + " 开始同步块外查询time=" + System.currentTimeMillis());
		List<TireReleaseDO> list = tireReleaseDao.selectByAge(age);
		
		log.info(name + " 结束同步块外查询time=" + System.currentTimeMillis());
		synchronized(lock) {
			log.info(name + " 占有锁time=" + System.currentTimeMillis());
			log.info(name + " 开始同步块内查询time=" + System.currentTimeMillis());
			list = tireReleaseDao.selectByAge(age);
			log.info(name + " 结束同步块内查询time=" + System.currentTimeMillis());
			boolean flag = list.isEmpty();
			log.info(name + " current=" + System.currentTimeMillis() +" flag=" + flag);
			if(!flag) {
				log.error(name + "重复操作～～");
				return;
			}
			log.info(name + " 开始同步块内插入sqltime=" + System.currentTimeMillis());
			tireReleaseDao.insert(record);
//			record.setName("currentTimeMilliscurrentTimeMilliscurrentTimeMilliscurrentTimeMillis");
//			tireReleaseDao.insert(record);
			log.info(name + " 结束同步块内插入sqltime=" + System.currentTimeMillis());
			log.info(name + " 退出锁time=" + System.currentTimeMillis());
		}
		log.info(name + "操作成功");
	}


}
