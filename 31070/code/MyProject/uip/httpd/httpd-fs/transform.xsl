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
  <h3>Measurement</h3>
   <table>
    <tr>
      <th>realtime-frequency</th>
      <th>Vpp (Vmax/Vmin)</th>
	  <th>RMS current</th>
	  <th>RMS voltage</th>
	  <th>Time (S:mS)</th>
	  <th>Relay 1</th>
	  <th>Relay 2</th>
	  <th>LCD Pressed</th>
	  <th>LCD Coordinates</th>

	  
	  
    </tr>
    <xsl:for-each select="system-information/measurements">
    <tr>
      <td><xsl:value-of select="realtime-frequency"/></td>
	  <xsl:for-each select="Vpp">
	   <td><xsl:value-of select="max"/> / <xsl:value-of select="min"/></td>
	  </xsl:for-each>
	  
	  <xsl:for-each select="RMS">
	   <td><xsl:value-of select="current"/></td>
	   <td><xsl:value-of select="voltage"/></td>
	  </xsl:for-each>
	  
	  <xsl:for-each select="time">
	   <td><xsl:value-of select="second"/>:<xsl:value-of select="millisecond"/></td>
	  </xsl:for-each>
	  
	  <xsl:for-each select="relay-status">
	   <td><xsl:value-of select="relay1"/></td>
	   <td><xsl:value-of select="relay2"/></td>
	  </xsl:for-each>	  

	  <xsl:for-each select="lcd">
	   <td><xsl:value-of select="pressed"/></td>
	   <td><xsl:value-of select="coordinates"/></td>
	  </xsl:for-each>	  	  
	  
    </tr>
    </xsl:for-each>
  </table> 
  
  <h3>configuration</h3>
   <table>
    <tr>
      <th>frequency</th>
      <th>sample-frequency</th>
	  <th>vref</th>
	  <th>ad-cutoff</th>
    </tr>
	<tr>
	<xsl:for-each select="system-information/configuration">
	 <td><xsl:value-of select="frequency"/></td>
	 <td><xsl:value-of select="sample-frequency"/></td>
	 <td><xsl:value-of select="vref"/></td>
	 <td><xsl:value-of select="ad-cutoff"/></td>
    </xsl:for-each>
   </tr>
  </table> 	  

  
  
  <h3>tcp-connections</h3>
  <table>
    <tr>
      <th>local</th>
      <th>remote</th>
      <th>state</th>
      <th>retransmissions</th>
      <th>timer</th>
      <th>flags</th>
    </tr>
    <xsl:for-each select="system-information/tcp-connections/connection">
    <tr>
      <td><xsl:value-of select="local"/></td>
      <td><xsl:value-of select="remote"/></td>
      <td><xsl:value-of select="state"/></td>
      <td><xsl:value-of select="retransmissions"/></td>
      <td><xsl:value-of select="timer"/></td>
      <td><xsl:value-of select="flags"/></td>
    </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>