<%@ include file="/init.jsp" %>

<%
	String text = ParamUtil.getString(request, "text");
%>

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>

<div class="container">
	<div class="row justify-content-center">
		<div class="col-md-auto">
			<h1 style="color:red"> Hello From My Render Command View! </h1>
			<h2> Text: <%= text %> </h2>
		</div>
	</div>
	<div class="row justify-content-center">
		<button
			type="button"
			onclick="location.href='<%= viewURL %>';"
			class="btn btn-primary"
		>
			Go back to view
		</button>
	</div>
</div>