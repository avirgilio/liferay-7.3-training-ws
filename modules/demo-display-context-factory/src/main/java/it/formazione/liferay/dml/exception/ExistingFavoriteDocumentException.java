package it.formazione.liferay.dml.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Virgilio Alessandro 20/ago/2021
 **/
public class ExistingFavoriteDocumentException extends PortalException {

	public ExistingFavoriteDocumentException() {
	}

	public ExistingFavoriteDocumentException(String msg) {
		super(msg);
	}

	public ExistingFavoriteDocumentException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public ExistingFavoriteDocumentException(Throwable throwable) {
		super(throwable);
	}
}
