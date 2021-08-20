package it.formazione.liferay.dml.portlet.display.context.factory;

import com.liferay.document.library.display.context.BaseDLDisplayContextFactory;
import com.liferay.document.library.display.context.DLDisplayContextFactory;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileVersion;
import it.formazione.liferay.dml.portlet.display.context.MyCustomDLViewFileVersionDisplayContext;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Virgilio Alessandro 18/ago/2021
 **/
@Component(service = DLDisplayContextFactory.class, immediate = true)
public class MyCustomDisplayContextFactory extends BaseDLDisplayContextFactory {

	@Override
	public DLViewFileVersionDisplayContext getDLViewFileVersionDisplayContext(
		DLViewFileVersionDisplayContext parentDLViewFileVersionDisplayContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, FileVersion fileVersion) {

		_log.info(
			"MY CUSTOM DISPLAY CONTEXT FACTORY - DL VIEW FILE VERSION");

		return new MyCustomDLViewFileVersionDisplayContext(
			parentDLViewFileVersionDisplayContext, httpServletRequest,
			httpServletResponse, fileVersion);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MyCustomDisplayContextFactory.class);
}
