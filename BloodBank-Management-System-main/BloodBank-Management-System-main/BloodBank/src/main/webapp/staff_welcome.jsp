<!DOCTYPE html>
<html>
<head>
  <title>ASP|Welcome</title>

<style>
    body {
      font-family: sans-serif;
      	background-image: linear-gradient(120deg, #fdfbfb 0%, #ebedee 100%);
    }
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
   background-image: linear-gradient(to top, #c4c5c7 0%, #dcdddf 52%, #ebebeb 100%);
      color: black;
      padding: 20px;
    }

    .header a {
      color: black;
      text-decoration: none;
    }
    
    
.logo {
  flex: 0 0 auto;
    margin-right: 20px;
}

.logo img {
  height: 50px;
}

.user-name {
  margin: 0 10px;
  
}
    .text-block {
      max-width: 800px;
      margin: 0 auto;
      padding: 20px;
      text-align: justify;
    }
    .btn {
    background-image: linear-gradient(to top, #a7a6cb 0%, #8989ba 52%, #8989ba 100%);
      color: white;
      padding: 20px 40px;
      border: none;
      cursor: pointer;
      font-size: 16px;
      border-radius: 4px;
    }
    .btn:hover {
      background-color: #3e8e41;
    }
    
    
footer {
  background: linear-gradient(-180deg, #BCC5CE 0%, #929EAD 98%), radial-gradient(at top left, rgba(255,255,255,0.30) 0%, rgba(0,0,0,0.30) 100%);
 background-blend-mode: screen;
  color: black;
  padding: 20px;
  text-align: center;
}

.contact-info {
  margin: 10px 0;
}
    
  </style>
</head>
<body>
<%String sname=(String)request.getAttribute("sname");
  String sbid=(String)request.getAttribute("sbid");
   String spwd=(String)request.getAttribute("spwd");
   
   pageContext.setAttribute("sname", sname, PageContext.SESSION_SCOPE);
   pageContext.setAttribute("sbid", sbid, PageContext.SESSION_SCOPE);
   pageContext.setAttribute("spwd", spwd, PageContext.SESSION_SCOPE);
 
   %>
   
   
  <div class="header">
         <div class="header__title">
        <img src="" />
        <div>${sname}</div>
        
      </div>
      <div class="user-name">
        <p><h1>Welcome</h1></p>
      </div>
    <nav class="header__nav">
        <div class="header__nav-item">
          <a href="home.html" class="header__nav-link">Logout</a>
        </div>
  </div>
  <img src="file:///C:/Users/Amritha%20S/eclipse-workspace/BloodBank/src/main/webapp/images/i1.jpg" width=300px alt="" style="display: block; margin: 0 auto;">
  <div class="text-block">
Welcome Staff!!!
           As you can see, there are few buttons which on clicking will assist you to update  the blood available. You can also view all the patients who have donated to your bank\hospital as wel as those who have received from it.You can always contact us on any query!! 
  </div>
<div style="width: 600px; margin: 0 auto;">
  <button class="btn" onclick="location.href='staff_insert.jsp'">Insert</button>
  <button class="btn" onclick="location.href='staff_delete.jsp'">Delete</button>
  <button class="btn" onclick="location.href='http://localhost:8080/BloodBank/s_ddisplay'">donar detail</button>
  <button class="btn" onclick="location.href='http://localhost:8080/BloodBank/s_pdisplay'">patient detail</button>
</div>
<br>
 <footer>
      <div class="contact-info">
        <p>Contact info:</p>
        <p>Phone: 555-555-5555</p>
        <p>Email: info@company.com</p>
      </div>
    </footer>

 
</body>
</html>
