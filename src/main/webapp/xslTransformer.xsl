<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
<xsl:output 
  method="html"
  encoding="UTF-8"
  indent="yes" />
  

	
<xsl:template match="/">
			<h1>Affichage des articles</h1>
			<xsl:apply-templates select="@*|*|processing-instruction()" /> <!--@*|* ==> selectionne toutes les branches de l'arbre -->
</xsl:template>

<xsl:template match="article">
		<xsl:if test="title != ''">
			<h3><xsl:value-of select="./title"/></h3>
		</xsl:if>
	
	<ul>
		<xsl:if test="description != ''">
			<li>Description : <xsl:value-of select="./description"/></li>
		</xsl:if>
		<xsl:if test="link != ''">
			<li>Link : <a><xsl:attribute name='href' select="./link"/><xsl:value-of select="./link"/></a></li>
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