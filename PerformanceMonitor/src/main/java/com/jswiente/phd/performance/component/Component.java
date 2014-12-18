package com.jswiente.phd.performance.component;

public interface Component<T, S> {
	public T process(S u);
}
