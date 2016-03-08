package com.tqmall.wind.manager;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
@Service
public class ProxyManager {
	@Resource
	TireReleaseManager tireReleaseManager;
	
	public void biz() {
		tireReleaseManager.biz(21);
	}
	
	public static void main(String[] args) {
		DateTime dt = new DateTime();
		dt = dt.plusMonths(1).withDayOfMonth(1).withTime(0, 0, 0, 0);
		
        DateTime now = DateTime.now();
        String loanMonth = String.valueOf(now.getYear());
        if (now.getMonthOfYear() < 10) {
            loanMonth += "0" + now.getMonthOfYear();
        } else {
            loanMonth += now.getMonthOfYear();
        }
		System.out.println(loanMonth);
		System.out.println(now.toString("yyyyMM"));
	}
}
