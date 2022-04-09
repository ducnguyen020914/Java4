<%@ page language="java" session="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section class="pt-5 pb-5">
	<div class="container">
		<div class="row w-100">
			<div class="col-lg-12 col-md-12 col-12">
				<h3 class="display-5 mb-2 text-center">LỊCH SỬ ĐƠN HÀNG</h3>
				<p class="mb-5 text-center">
				<table id="shoppingCart"
					class="table table-condensed table-responsive">
					<thead>
						<tr class="fw-bold">
							<th>
								<h4>Mã đơn hàng</h4>
							</th>
							<th>
								<h4>Mô tả</h4>
							</th>
							<th><h4>Trang thái</h4></th>
							<th><h4>Thanh toán</h4></th>
							<th><h4>Tổng tiền</h4></th>
							<th><h4>Chi tiết</h4></th>
							<th><h4>Thao tác</h4></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${list }">
							<tr class="fw-bold mt-3">
								<td>${item.id }</td>
								<td>${item.moTa }</td>
								<td><c:choose>
										<c:when test="${item.status == 0 }">Chờ xác nhận</c:when>
										<c:when test="${item.status == 1 }">Đang giao hàng</c:when>
										<c:when test="${item.status == 2 }">Đã hủy</c:when>
										<c:when test="${item.status == 3 }">Đã giao hàng</c:when>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${item.thanhToan == 0 }">Sau khi nhận hàng</c:when>
										<c:when test="${item.thanhToan == 1 }">Thanh toán Online</c:when>

									</c:choose></td>
								<td>${item.total }</td>
								<td><a href="/QLBH/users/chitiet?id=${item.id }">Xem
										chi tiết</a></td>
								<td><c:if test="${item.status == 0 }">
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal" data-bs-target="#exampleModal${item.id }">
											Hủy đơn hàng</button>
										<div class="modal fade" id="exampleModal${item.id }"
											tabindex="-1" aria-labelledby="exampleModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal" aria-label="Close"></button>
													</div>
													<div class="modal-body">Xác nhận hủy đơn hàng</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Đóng</button>
														<a type="button" class="btn btn-primary" href="/QLBH/users/huy?id=${item.id }">
															Xác nhận</a>
													</div>
												</div>
											</div>
										</div>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</section>