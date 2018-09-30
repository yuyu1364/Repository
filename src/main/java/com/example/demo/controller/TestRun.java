package com.example.demo.controller;

public class TestRun implements Runnable{

	@Override
	public void run() {
		for(int i = 0;i<1000;i++) {
			System.out.println(Thread.currentThread().getName()+"   序号为：" + i);
		}
	}

}
