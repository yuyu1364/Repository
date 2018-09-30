package com.example.demo;

import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("现在开始计算");
		Thread.sleep(5000);
		int sum = 0;
		for(int i = 0; i < 10000; i++) {
			sum += i;
		}
		System.out.println("计算结束，值为："+ sum);
		return sum;
	}

}
