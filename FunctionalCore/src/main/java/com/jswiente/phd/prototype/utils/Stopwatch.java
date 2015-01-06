package com.jswiente.phd.prototype.utils;

import com.jswiente.phd.prototype.utils.LogUtils.Event;

public class Stopwatch {
	
	private com.google.common.base.Stopwatch _stopwatch;
	private String name;
	private String id;
	
	private long start;
	private long end;
	
	public Stopwatch() {
		//_stopwatch = new com.google.common.base.Stopwatch();
	}
	
	public Stopwatch(String name, String id) {
		//_stopwatch = new com.google.common.base.Stopwatch();
		this.name = name;
		this.id = id;
	}
	
	public Stopwatch start() {
		//_stopwatch.start();
		start = System.currentTimeMillis();
		return this;
	}
	
	public Stopwatch stop() {
		//_stopwatch.stop();
		end = System.currentTimeMillis();
		return this;
	}
	
	public long elapsedMillis() {
		//return _stopwatch.elapsedMillis();
		return end - start;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public static Stopwatch start(String name, Long id) {
		return new Stopwatch(name, Long.toString(id)).start();
	}
	
	public static Stopwatch start(String name, String id) {
		return new Stopwatch(name, id).start();
	}
	
}
