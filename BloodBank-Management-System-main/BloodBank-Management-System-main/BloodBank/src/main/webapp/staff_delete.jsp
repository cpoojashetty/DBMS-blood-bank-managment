<!DOCTYPE html>
<html>
<head>
 <meta charset="ISO-8859-1">
 <link rel="stylesheet" type="text/css" href="css/styles.css"/>
 <title>user | Login</title> 
</head>
<body>
   
<%
String sname = pageContext.getAttribute("sname", PageContext.SESSION_SCOPE).toString();
String sbid = pageContext.getAttribute("sbid", PageContext.SESSION_SCOPE).toString();
String spwd = pageContext.getAttribute("spwd", PageContext.SESSION_SCOPE).toString();


 
   %>


   <form action="s_delete" method="post"> 
   
         <input type=hidden name=sname value="${sname }">
          <input type=hidden name=sbid value="${sbid }">
           <input type=hidden name=spwd value="${spwd }">
          
   		<input type=text name=btype size=25 placeholder="Bloodtype" autofocus required/>
 
  		<input type=number name=bqnt size=25 placeholder="QUANTITY"/>
 
  		<input type=submit value="DONE">
   </form>


   
   
</body>
</html>
