package it.formazione.liferay.kafka.integration.api.log;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import reactor.util.Logger;

import java.util.function.BiConsumer;

public class LiferayReactorLogger implements Logger {

	public LiferayReactorLogger(Class<?> clazz) {

		_log = LogFactoryUtil.getLog(clazz);
	}

	@Override
	public String getName() {
		return LiferayReactorLogger.class.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		return _log.isTraceEnabled();
	}

	@Override
	public void trace(String msg) {
		_log.trace(msg);
	}

	@Override
	public void trace(String format, Object... arguments) {
		_formatAndLog(_log::trace, format, arguments);
	}

	@Override
	public void trace(String msg, Throwable t) {
		_log.trace(msg, t);
	}

	@Override
	public boolean isDebugEnabled() {
		return _log.isDebugEnabled();
	}

	@Override
	public void debug(String msg) {
		_log.debug(msg);
	}

	@Override
	public void debug(String format, Object... arguments) {
		_formatAndLog(_log::debug, format, arguments);
	}

	@Override
	public void debug(String msg, Throwable t) {
		_log.debug(msg, t);
	}

	@Override
	public boolean isInfoEnabled() {
		return _log.isInfoEnabled();
	}

	@Override
	public void info(String msg) {
		_log.info(msg);
	}

	@Override
	public void info(String format, Object... arguments) {
		_formatAndLog(_log::info, format, arguments);
	}

	@Override
	public void info(String msg, Throwable t) {
		_log.info(msg, t);
	}

	@Override
	public boolean isWarnEnabled() {
		return _log.isWarnEnabled();
	}

	@Override
	public void warn(String msg) {
		_log.warn(msg);
	}

	@Override
	public void warn(String format, Object... arguments) {
		_formatAndLog(_log::warn, format, arguments);
	}

	@Override
	public void warn(String msg, Throwable t) {
		_log.warn(msg, t);
	}

	@Override
	public boolean isErrorEnabled() {
		return _log.isErrorEnabled();
	}

	@Override
	public void error(String msg) {
		_log.error(msg);
	}

	@Override
	public void error(String format, Object... arguments) {
		_formatAndLog(_log::error, format, arguments);
	}

	@Override
	public void error(String msg, Throwable t) {
		_log.error(msg, t);
	}

	private void _formatAndLog(
		BiConsumer<String, Throwable> logFunction, String format,
		Object... arguments) {

		FormattingTuple tp = MessageFormatter.arrayFormat(format, arguments);

		logFunction.accept(tp.getMessage(), tp.getThrowable());
	}

	private final Log _log;

}
