<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <section class="pt-5 pb-5">
      <div class="container">
        <div class="row w-100">
        <div class="col-12">
        <c:if test="${! empty sessionScope.message }">
        <label class="alert alert-info">${sessionScope.message }</label>
        <c:remove var="message" scope="session"/>
        </c:if>
        </div>
            <div class="col-lg-12 col-md-12 col-12">
                <h3 class="display-5 mb-2 text-center">Shopping Cart</h3>
                <p class="mb-5 text-center">
                  
                <table id="shoppingCart" class="table table-condensed table-responsive">
                    <thead>
                        <tr>
                            <th style="width:60%">Product</th>
                            <th style="width:12%">Price</th>
                            <th style="width:10%">Quantity</th>
                            <th style="width:16%"></th>
                        </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="item" items="${sessionScope.cart.order }">
                     <c:if test="${item.id == sessionScope.taikhoan.id }">
                        <tr>
                            <td data-th="Product">
                                <div class="row">
                                    <div class="col-md-3 text-left">
                                        <img src="/QLBH/image/${item.product.img }" alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                                    </div>
                                    <div class="col-md-9 text-left mt-sm-2">
                                        <h4> <a href="/QLBH/users/productDetail?id=${item.product.id}"> ${item.product.ten }</a></h4>
                                        
                                    </div>
                                </div>
                            </td>
                            <td data-th="Price"> <h5> <fmt:formatNumber value="${item.price }" pattern="###,###.##"></fmt:formatNumber>VND</h5></td>
                            <td data-th="Quantity" class="text-center">
                                <h5> ${item.quantity }</h5>
                            </td>
                            <td class="actions" data-th="">
                                <div class="text-center">
                                    <a class="btn btn-white border-secondary bg-white btn-md mb-2" href="/QLBH/users/xoaitem?id=${item.product.id}">
                                        <i class="bi bi-basket"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                     </c:if>
                      </c:forEach>
                  
                    </tbody>
                </table>
               
            </div>
            <div class="row">
                <div class="col-10" ></div>
                <div class="float-right text-right col-2">
                    <h4>Tổng cộng:</h4>
                     <h4> <fmt:formatNumber value="${sessionScope.price }" pattern="###,###.##"></fmt:formatNumber></h4>
                </div>
            </div>
        </div>
        <div class="row mt-4 d-flex align-items-center">
            <div class="col-6 ">
                <a href="/QLBH/users/index">
                    <i class="fas fa-arrow-left mr-2"></i> Continue Shopping</a>
            </div>
            <div class="col-4"></div>
            <div class="col-2">
            <form action="/QLBH/users/thanhtoan" method="post">
                <button  class="btn btn-primary mb-4 btn-lg pl-5 pr-5">Thanh toán</button>
                </form>
            </div>
           
        </div>
    </div>
    </section>