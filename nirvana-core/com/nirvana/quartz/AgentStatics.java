/**
 * Copyright 2014 SFI
 * @Author:guzhen
 * @Email:1132053388@qq.com
 * @Date:2015年9月2日 上午11:42:31
 */
package com.nirvana.quartz;

import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.nirvana.service.StatisticsService;

/**
 * @Title:AgentStatics.java
 * @Description:
 * @Version:v1.0
 */
@Component
public class AgentStatics {
	@Resource
	StatisticsService statisticsService;
	
	public void agentStatistics(){
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("开始统计");
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		long start=Calendar.getInstance().getTimeInMillis();
		statisticsService.daysAgentStatistics();
		System.out.println("统计结束");
		System.out.println("耗时："+(Calendar.getInstance().getTimeInMillis()-start)/1000);
	}
}
