function ${jsNamespace}showView(title, url) {
		Liferay.Util.openWindow(
			{
				cache: false,
				title: title,
				dialog: {
					destroyOnHide: true,
					height: 600,
					width: 1200,
					resizable: false
				},
				id: "${jsNamespace}showDocumentView",
				uri: url
			},
			function(dialog) {
				dialog.after("destroy", function() {
					Liferay.Portlet.refresh("#p_p_id${originalPortletNamespace}");
				});
			}
		)
}
