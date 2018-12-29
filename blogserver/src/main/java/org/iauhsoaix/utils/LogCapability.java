package org.iauhsoaix.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface LogCapability {

	default Logger getLogger() {
		return LoggerFactory.getLogger(this.getClass());
	}
}
