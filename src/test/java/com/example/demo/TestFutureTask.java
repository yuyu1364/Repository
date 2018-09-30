package com.example.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class TestFutureTask {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newCachedThreadPool();
		CallableDemo call = new CallableDemo();
		FutureTask<Integer> reuslt = new FutureTask<>(call);
//		es.submit(reuslt);
		Thread thred = new Thread(reuslt);
		thred.start();
		System.out.println("主线程执行");
//		System.out.println("TASK程执行结果" + reuslt.get() );
		int i = 1;
		if ( i > 0) {
			reuslt.cancel(true);
			System.out.println("取消成功");
		}
//		es.shutdown();
		Thread.sleep(1000);
		System.out.println("主线程等待一秒");
		System.out.println("TASK程执行结果" + reuslt.get() );
		System.out.println("执行完毕");
	}
}
