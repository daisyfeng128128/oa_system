package com.baofeng.work;

import java.util.Observer;

public interface BaseObserver extends Observer {

	default public Object analysis(Object arg) {
		if (arg != null) {
			if (arg instanceof Object[]) {
				Object[] args = (Object[]) arg;
				if (args.length > 0)
					return args[0];
				else
					return null;
			}
		}
		return arg;
	}
}
