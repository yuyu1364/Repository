package com.example.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService  es = Executors.newCachedThreadPool();
		CallableDemo call = new CallableDemo();
		Future<Integer> submit = es.submit(call);
		Integer integer = submit.get();
		System.out.println("主线程打印值：" + integer);
		es.shutdown();
		System.out.println("执行完毕");
	}


}
