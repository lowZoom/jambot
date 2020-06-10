<html>
<head>
<#include "/common/header.ftl">
</head>
<!---------------------------------->
<body>
<table>
  <tr>
    <#list table['header'] as cell>
    <th>${cell}</th>
    </#list>
  </tr>

  <#list table['body'] as row>
  <tr>
    <#list row as cell>
    <td>${cell}</td>
    </#list>
  </tr>
  </#list>
</table>
</body>
</html>
