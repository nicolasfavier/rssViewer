<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
<xsl:output 
  method="html"
  encoding="UTF-8"
  indent="yes" />
  

	
<xsl:template match="/">
	<html><head>
		<title>Presentations</title>
		</head><body>
			<h1>Affichage des articles</h1>
			<xsl:apply-templates select="@*|*|processing-instruction()" /> <!--@*|* ==> selectionne toutes les branches de l'arbre -->
		</body></html>
</xsl:template>

<xsl:template match="article">
	<ul>
		<li><xsl:value-of select="./title"/></li>
		<li><xsl:value-of select="./desc"/></li>
		<li><xsl:value-of select="./link"/></li>
		<li><xsl:value-of select="./date"/></li>
		<li><xsl:value-of select="./language"/></li>
		<li><xsl:value-of select="./creator"/></li>
	</ul>
</xsl:template>


</xsl:stylesheet>