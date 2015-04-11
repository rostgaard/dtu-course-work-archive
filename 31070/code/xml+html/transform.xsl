<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <!-- Refresh the page every 5 seconds -->
  <meta http-equiv="refresh" content="5" />
  <link rel="stylesheet" type="text/css" href="styles.css" media="screen" />
  <h2>System information</h2>
  <table border="1">
    <tr bgcolor="green">
      <th>tag 1</th>
      <th>tag 2</th>
      <th>tag 3</th>
      <th>tag 4</th>
      <th>tag 5</th>
      <th>tag 6</th>
    </tr>
    <xsl:for-each select="system_information/network">
    <tr>
      <td><xsl:value-of select="tag1"/></td>
      <td><xsl:value-of select="tag2"/></td>
      <td><xsl:value-of select="tag3"/></td>
      <td><xsl:value-of select="tag4"/></td>
      <td><xsl:value-of select="tag5"/></td>
      <td><xsl:value-of select="tag6"/></td>
    </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>
