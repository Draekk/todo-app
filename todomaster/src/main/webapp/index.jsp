<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
HttpSession mySession = request.getSession(false);
if(mySession.getAttribute("user") != null){

%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="./styles/global.css" />
    <link rel="stylesheet" href="./styles/mobile/index.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Mansalva&display=swap"
      rel="stylesheet"
    />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;400;700&display=swap"
      rel="stylesheet"
    />
    <title>ToDo Master - Main menu</title>
  </head>
  <body>
    <main class="container c-vert c-center-se">
      <h1>TODO MASTER</h1>
      <section id="main-container" class="container c-vert c-center">
				<nav id="nav-bar" class="container c-start-sb">
					<h2>Your tasks!</h2>
					<ul class="container c-vert c-center-all">
						<li class="container c-center-all">
							<p>username</p>
							<picture class="container c-center-all">
								<img src="https://i.ibb.co/gwGnVBJ/user-icon-50x50.png" alt="profile-logo">
							</picture>
						</li>
						<li class="inactive container c-vert c-center-all">
							<div class="line-80"></div>
							<a href="login.html">Log out</a>
						</li>
					</ul>
				</nav>
				<button id="btnAddTask">
					<i class="ico fa-solid fa-plus"></i>
				</button>
				<div class="container c-vert c-center-all">
					<input type="text" name="newTask" id="newTask" placeholder="Create new Task">
					<ul id="slider" class="container c-vert c-center-se">
						<li class="task container c-center-sb">
							<div class="container">
								<input type="checkbox" name="checkbox" id="taskId">
								<label title="task name" for="taskId">Task name</label>
							</div>
							<div class="container c-center-sb">
								<button id="btnEdit">
									<i class="fa-solid fa-pen"></i>
								</button>
								<button id="btnDelete">
									<i class="fa-solid fa-x"></i>
								</button>
							</div>
						</li>
						<!--
						<li class="task container c-center-sb">
							<div class="container c-center-all">
								<input type="checkbox" name="" id="">
								<label for="">Task name</label>
							</div>
							<div class="container c-center-sb">
								<button id="btnEdit"></button>
								<button id="btnDelete"></button>
							</div>
						</li>
						<li class="task container c-center-sb">
							<div class="container c-center-all">
								<input type="checkbox" name="" id="">
								<label for="">Task name</label>
							</div>
							<div class="container c-center-sb">
								<button id="btnEdit"></button>
								<button id="btnDelete"></button>
							</div>
						</li>
						<li class="task container c-center-sb">
							<div class="container c-center-all">
								<input type="checkbox" name="" id="">
								<label for="">Task name</label>
							</div>
							<div class="container c-center-sb">
								<button id="btnEdit"></button>
								<button id="btnDelete"></button>
							</div>
						</li>
					</ul>
				-->
				</div>
				<div id="disable-screen" class="inactive"></div>
      </section>
    </main>
  </body>
  <script
    src="https://kit.fontawesome.com/4dba61bd48.js"
    crossorigin="anonymous"
  ></script>
  <script src="./scripts/index.js"></script>
</html>

<%

  } else {
    response.sendRedirect("login.html");
}

%>
