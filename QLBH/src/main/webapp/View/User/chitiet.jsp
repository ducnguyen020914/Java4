<%@ page language="java" session="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section class="pt-5 pb-5">
	<div class="container">
		<div class="row w-100">
			<div class="col-lg-12 col-md-12 col-12">
				<h3 class="display-5 mb-2 text-center">CHI TIẾT ĐƠN HÀNG</h3>
				<p class="mb-5 text-center">
				<table id="shoppingCart"
					class="table table-condensed table-responsive">
					<thead>
						<tr class="fw-bold">
							<th> <h4>Mã đơn hàng</h4> </th>
							<th> <h4>Product</h4> </th>
							<th><h4>Số lượng</h4></th>
							<th><h4>Đơn giá</h4></th>
							<th><h4>Thành tiền</h4></th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list }">
							<tr class="fw-bold mt-3" >
								<td>${item.hoadon.id }</td>
								<td>${item.product.ten }</td>
								<td>${item.quantity }
									</td>
								<td>
								${item.price }
								</td>
								<td> ${item.quantity * item.price }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</section>