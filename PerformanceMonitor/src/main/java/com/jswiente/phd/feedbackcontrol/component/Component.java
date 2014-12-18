package com.jswiente.phd.feedbackcontrol.component;

public interface Component<T, S> {
	public T process(S u);
}
