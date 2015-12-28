<!DOCTYPE>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<title>Workfront Documents List</title>

<style>

input.submit {
	background-color: darkorange;
	border: thin solid orange;
	border-radius: 3px;
	color: #fff;
	font-size: 10pt;
	padding: 2px 5px;
	cursor: pointer;
}
body, table {
	font-size: 12px;
}


</style>

</head>
<body>

<div class="container">
<c:if test="${not list.validSubsribtion}">
<div class="centered text-center">
  <ul class="list-group">
    <li class="list-group-item list-group-item-danger">Your subscription to this utility has expired. Please ask your Workfront administrator to contact your Aurotech account manager.</li>
  </ul>
</div>
</c:if>

<ul class="nav nav-tabs">
  <li class="active"><a href="#">Documents List</a></li>    
</ul>

<table class="table table-hover">
<thead>
      <tr>
        <th>Document</th>
        <th></th>
        <th>Current<br>Version</th>
        <th></th>
        <th></th>
      </tr>
    </thead>
    
	<tbody>
		<c:forEach var="proj" items="${list.lookup}">
<tr>
 	<td class="rowstyle">
					<b><a href="${list.serverURL}/document/view?ID=${proj.documentID}" target="_blank">${proj.documentName}</a></b><br>
						<span style="color: #666;">on the <a href="${list.serverURL}/${proj.objectType}/view?ID=${proj.objectID}" target="_blank">${proj.objectName}</a> ${proj.objectType}</span>
				</td>
				<td class="rowstyle">
					<a href="${list.serverURL}/document/preview?versionID=${proj.currentVersionID}&amp;ID=${proj.documentID}" target="_blank">Preview</a>
				</td>
				<td class="rowstyle" style="text-align: center;">${proj.currentVersion}</td>
				<td class="rowstyle">
				<form action="#" method="post">
							<input type="hidden" name="docID" value="${proj.documentID}">
							<input type="hidden" name="version" value="${proj.currentVersion}">
							<input type="hidden" name="versionID" value="${proj.currentVersionID}">
							<c:if test="${list.validSubsribtion}">
							<input type="submit" name="archive" value="Archive" class="save-button submit primary">
							</c:if>
							<c:if test="${not list.validSubsribtion}">
							<input type="submit" name="archive" value="Archive" class="btn save-button submit disabled" disabled="disabled">
							</c:if>
							<input type="hidden" name="RECALL_DOC" value="RECALL_DOC">
						</form></td>
				<td class="rowstyle" style="">Archived to <a href="${list.spFolderPath}" target="_blank" title="View in SharePoint">SharePoint</a></td>
				<td class="rowstyle"></td>
			</tr>
	</c:forEach>
	</tbody>
	</table>

</div>

</body>
</html>