<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version='1.0' xmlns="http://www.w3.org/TR/xhtml1/transitional"
	exclude-result-prefixes="#default">
	<xsl:import href="urn:docbkx:stylesheet" />
	<xsl:output method="html" encoding="UTF-8" indent="no" />
	<xsl:param name="generate.toc">
		article toc
		book toc
		part toc
		preface toc
		qandadiv toc
		qandaset toc
		reference toc
		section toc
	</xsl:param>
	<xsl:param name="toc.section.depth">
		1
	</xsl:param>
	<xsl:param name="section.autolabel" select="1" />
	<xsl:param name="section.label.includes.component.label"
		select="1" />
	<xsl:param name="callout.graphics.number.limit" select="'20'" />
	<xsl:param name="html.stylesheet">http://demo.ruslan.ru/doc.css</xsl:param>
	<!-- <xsl:variable name="use.extensions" select="1"/> <xsl:variable name="callouts.extension" 
		select="1"/> <xsl:variable name="textinsert.extension" select="1"/> -->
</xsl:stylesheet>
