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
    <#list row['data'] as cell>
    <td>${cell}</td>
    </#list>

    <#list row['jump'] as jump>
    <td><a href="${jump['to']}">${jump['name']}</a></td>
    </#list>

    <#list row['op'] as op>
    <td style="text-align:center">
      <a href="op?cmd=${op['cmd']}&param=${op['param']}&from=${springMacroRequestContext.requestUri}">
        ${op['name']}
      </a>
    </td>
    </#list>
  </tr>
  </#list>
</table>

</body>
</html>
