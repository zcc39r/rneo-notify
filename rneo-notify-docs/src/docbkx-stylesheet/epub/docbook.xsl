<?xml version='1.0'?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version='1.0'
                xmlns="http://www.w3.org/TR/xhtml1/transitional"
                exclude-result-prefixes="#default">
<xsl:import href="urn:docbkx:stylesheet"/>
<xsl:output method="html"
            encoding="UTF-8"
            indent="no"/>
<xsl:variable name="generate.toc">
article   toc
book      toc
part      toc
preface   toc
qandadiv  toc
qandaset  toc
reference toc
section   toc
</xsl:variable>
<xsl:variable name="toc.section.depth">1</xsl:variable>
<xsl:variable name="section.autolabel" select="1"/>
<xsl:variable name="section.label.includes.component.label" select="1"/>
<xsl:variable name="epub.oebps.dir" select="'./'"/>
</xsl:stylesheet>
