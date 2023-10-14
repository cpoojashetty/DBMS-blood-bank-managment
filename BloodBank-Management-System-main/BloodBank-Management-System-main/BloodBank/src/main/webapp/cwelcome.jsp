
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
<%String uid=(String)request.getAttribute("uid");
String uname=(String)request.getAttribute("uname");

String upwd=(String)request.getAttribute("upwd");

pageContext.setAttribute("uid", uid, PageContext.SESSION_SCOPE);
pageContext.setAttribute("uname", uname, PageContext.SESSION_SCOPE);

pageContext.setAttribute("upwd", upwd, PageContext.SESSION_SCOPE);
   %>
   
   
  <div class="header">
         <div class="header__title">
        <img src="" alt="" />
        <div>${uname}</div>
        
      </div>
      <div class="user-name">
        <p><h1>Welcome</h1></p>
      </div>
    <nav class="header__nav">
        <div class="header__nav-item">
          <a href="http://localhost:8080/BloodBank/account.html" class="header__nav-link">ACCOUNT</a>
        </div>
  </div>
  <img src="file:///C:/Users/Amritha%20S/eclipse-workspace/BloodBank/src/main/webapp/images/DonorRecipient-Chart_1.jpeg" width=300px alt="" style="display: block; margin: 0 auto;">
  <div class="text-block">
   Hello user, you can click the below buttons to either donate or receive the blood. If you have doubts on which bank to either donate or receive from you can cick on display. 
  </div>
<div style="width: 600px; margin: 50px auto;">
  <button class="btn" onclick="location.href='donar.html'">Donate</button>
  <button class="btn" onclick="location.href='receiver.html'">Need Blood</button>
  <button class="btn" onclick="location.href='http://localhost:8080/BloodBank/b_display'">Bank Details</button>
</div>
 <p><center>Hard time finding the required blood?<a href='http://localhost:8080/BloodBank/search.jsp'>Click</a> to search for donar</center></p>

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
 