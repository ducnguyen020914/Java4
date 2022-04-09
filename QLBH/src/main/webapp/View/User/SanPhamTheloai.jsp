<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="row mt-5">
	
	<div class="col-12">
	<div class="row text-center"><h1 class="fw-bold">${c.ten }</h1></div>
		 <section class="py-5">
            <div class="container px-4 px-lg-5 ">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                  <c:forEach var="item" items="${products }">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="/QLBH/image/${item.img }" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${item.ten }</h5>
                                      <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                   <p class="fw-bold"> <fmt:formatNumber value="${item.donGia }" pattern="###,###"></fmt:formatNumber> VNĐ  </p>
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="/QLBH/users/productDetail?id=${item.id }">
                                View options</a></div>
                            </div>
                        </div>
                    </div>
                  </c:forEach>
                </div>
                </div>
        </section>
	</div>
</div>