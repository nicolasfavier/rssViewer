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
		<xsl:if test="title != ''">
			<li>Title : <xsl:value-of select="./title"/></li>
		</xsl:if>
		<xsl:if test="desc != ''">
			<li>Description : <xsl:value-of select="./desc"/></li>
		</xsl:if>
		<xsl:if test="link != ''">
			<li>Link : <xsl:value-of select="./link"/></li>
		</xsl:if>
		<xsl:if test="date != ''">
			<li>Date : <xsl:value-of select="./date"/></li>
		</xsl:if>
		<xsl:if test="language != ''">
			<li>Language : <xsl:value-of select="./language"/></li>
		</xsl:if>
		<xsl:if test="creator != ''">
			<li>Author : <xsl:value-of select="./creator"/></li>
		</xsl:if>
		<xsl:if test="category != ''">
			<li>Category : <xsl:value-of select="./category"/></li>
		</xsl:if>
	</ul>
</xsl:template>


</xsl:stylesheet>