<%@ include file="/init.jsp" %>

<%
	long fileVersionId = ParamUtil.getLong(request, "fileVersionId");
	ViewDisplayContext displayContext = new ViewDisplayContext(fileVersionId, request);
%>

<liferay-frontend:edit-form
    name="fm"
>
    <liferay-frontend:edit-form-body>
        <liferay-frontend:fieldset-group>
            <liferay-frontend:fieldset>
                <aui:input label="document-creator-user-name"
                	name="Creator"
                	type="text"
                	value="<%= displayContext.getDocumentCreatorUserName() %>"
                	readOnly="<%= true %>"
				/>
                <aui:input
                	label="document-title"
                	name="docName"
                	type="text"
                	value="<%= displayContext.getDocumentTitle() %>"
					readOnly="<%= true %>"
				/>
				<aui:input
					label="document-creation-date"
					name="creationDate"
					type="text"
                	value="<%= displayContext.getDocumentCreationDate() %>"
					readOnly="<%= true %>"
				/>
				<aui:input
					label="document-description"
					name="docDescription"
					type="text"
					value="<%= displayContext.getDocumentDescription() %>"
					readOnly="<%= true %>"
				/>
            </liferay-frontend:fieldset>
        </liferay-frontend:fieldset-group>
    </liferay-frontend:edit-form-body>

    <liferay-frontend:edit-form-footer>
		<aui:button type="submit" value="add-to-session-favorite" />
	</liferay-frontend:edit-form-footer>

</liferay-frontend:edit-form>