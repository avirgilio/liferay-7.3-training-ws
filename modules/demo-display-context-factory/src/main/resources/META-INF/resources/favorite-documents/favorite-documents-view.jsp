<%@ include file="../init.jsp" %>

<%
	MyFavoriteDocumentsDisplayContext displayContext =
		new MyFavoriteDocumentsDisplayContext(themeDisplay.getUserId(), request);
%>

<div class="container">
	<c:choose>
		<c:when test="${empty favoriteDocuments}">
			<clay:alert
				displayType="info"
				message="no-favorite-documents"
			/>
		</c:when>
		<c:otherwise>
			<table class="table">
				<thead>
					<tr>
						<th>Title</th>
						<th>Description</th>
						<th>Creation Date</th>
						<th>Creator User</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${favoriteDocuments}" var="document">
					  <tr>
						<td><strong>${document.title}</strong></td>
						<td>${document.description}</td>
						<td><strong>${document.creationDate}</strong></td>
						<td>
							<liferay-ui:user-portrait
								size="lg"
								userId="${document.userId}"
							/>
							<strong> ${document.userName} </strong>
						</td>
						<td>
							 <button
								type="button"
								onclick="location.href='${document.downloadURL}';"
								class="btn btn-secondary"
							>
								Download
							</button>
						</td>
					  </tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
