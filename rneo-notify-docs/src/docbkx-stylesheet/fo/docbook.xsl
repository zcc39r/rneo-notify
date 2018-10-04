<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:import href="urn:docbkx:stylesheet" />

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

	<!-- Important links: - http://www.sagehill.net/docbookxsl/ - http://docbkx-tools.sourceforge.net/ -->

</xsl:stylesheet>