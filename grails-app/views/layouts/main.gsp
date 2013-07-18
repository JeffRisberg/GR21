<html>
	<head>
		<title>
			<g:layoutTitle default="Welcome to GR21" />
		</title>		
		<link rel="stylesheet" href="<g:createLinkTo dir='css' file='main.css' />" />
		<g:layoutHead />
		<r:layoutResources />		
	</head>
	<body>
	  <div id="page">
	  <g:render template="/header" />
		<div id="content">		    
			  <g:layoutBody />
		</div>
		<g:render template="/footer" />
		</div>
		<r:layoutResources />
	</body>
</html>