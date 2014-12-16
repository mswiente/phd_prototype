package com.jswiente.phd.performance.controller;

public interface Controller<T, S> {
	public void setInputValue(T input, long seqNum);
	public S getOutput();
}
