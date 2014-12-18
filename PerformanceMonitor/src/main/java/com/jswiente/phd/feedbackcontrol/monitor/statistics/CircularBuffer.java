package com.jswiente.phd.feedbackcontrol.monitor.statistics;

import java.lang.reflect.Array;


public class CircularBuffer<T> {
	private final T[] buffer;
	private int head = 0;
	private int tail = 0;
	private int insertPosition = 0;
	private int size = 0;
	private final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public CircularBuffer(int size, final Class <T> type) {
		this.buffer = (T[]) new Object[size];
		this.type = type;
	}
	
	public synchronized void insert(T item) {
		buffer[insertPosition] = item;
		tail = insertPosition;
		insertPosition = (insertPosition + 1) % buffer.length;
		if (size == buffer.length) {
			head = (head + 1) % buffer.length;
		} else {
			size++;
		}
	}
	
	public synchronized T getFirst() {
		return buffer[head];
	}
	
	public synchronized T getLast() {
		return buffer[tail];
	}
	
	public synchronized int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized T[] getSnapshot() {
		int length = 0;
		
		//buffer is empty
		if (size == 0) {
			return (T[]) Array.newInstance(type, 0);
		}
		
		if (head == 0) {
			length = tail + 1;
		} else {
			length = buffer.length;
		}
		
		T[] snapshot = (T[]) Array.newInstance(type, length);
		
		if (head > tail) {
			System.arraycopy(buffer, head, snapshot, 0, length - head);
			System.arraycopy(buffer, 0, snapshot, length - head,  tail + 1);
		} else {
			System.arraycopy(buffer, 0, snapshot, 0, length);
		}
		
		return snapshot;
	}
}
