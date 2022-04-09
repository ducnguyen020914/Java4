<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Quần áo</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/QLBH/User/css/styles.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand fw-bold" href="#!">Shop Quần Áo</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item fw-bold"><a class="nav-link active" aria-current="page" href="/QLBH/users/index">Home</a></li>
                        <li class="nav-item fw-bold"><a class="nav-link" href="#!">About</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle fw-bold" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu fw-bold" aria-labelledby="navbarDropdown">
                              
                               <c:forEach var="item" items="${categories }">
                                  <li><a class="dropdown-item fw-bold" href="/QLBH/users/categories?id=${item.id }">${item.ten }</a></li>
                               </c:forEach>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex">
                        <a class="btn btn-outline-dark fw-bold" href="/QLBH/users/formcart">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                           
                        </a>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                      
                        <c:if test="${empty sessionScope.taikhoan }">
                        <li class="nav-item fw-bold"><a class="nav-link" href="/QLBH/login">Login</a></li>
                        </c:if>
                           <c:if test="${!empty sessionScope.taikhoan }">
                            <li class="nav-item dropdown">
                       <a class="nav-link dropdown-toggle fw-bold" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Xin chào ${sessionScope.taikhoan.hoTen }</a>
                         <ul class="dropdown-menu fw-bold" aria-labelledby="navbarDropdown">              
                                  <li><a class="dropdown-item fw-bold" href="/QLBH/logout"> Đăng xuất</a></li>
                                    <li><a class="dropdown-item fw-bold" href="/QLBH/users/history"> Lịch sử đơn hàng</a></li>
                      
                            </ul>
                           </li>
                        </c:if>
                        
                    </ul>
                    </form>
                    
                </div>
            </div>
        </nav>
        <jsp:include page="${view }"></jsp:include>
        
      <!-- Footer-->
        <footer class="py-5 bg-dark mt-5" style="margin-top: 350px">
            <div class="container "><p class="m-0 text-center text-white"> Nguyễn Văn Đức Ph16402</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
