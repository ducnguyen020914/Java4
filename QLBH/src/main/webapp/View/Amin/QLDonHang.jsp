<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>
	<div class="container-fluid">
		<div class="row">
			<h1>Danh sách sản phẩm</h1>
			<div class="col-12" style="background-color: rgb(233, 233, 232);">
				<h5 class="mt-3 mb-3">Trang chủ / Quản lý đơn hàng</h5>
			</div>
			<div class="col-5 mt-3">
				<form action="" method="get">
					<div class="row">
						<div class="col-8">
							<input type="text" class="form-control" name="" id=""
								placeholder="Tìm kiếm theo mã đơn hàng">
						</div>
						<div class="col-4">

							<button class="btn btn-outline-secondary">Tìm kiếm</button>
						</div>
					</div>
				</form>
			</div>
			<table class="table table-light  mt-3">
				<thead class="thead-light">
					<tr>
						<th>ID</th>
						<th>Tên Khác hàng</th>
						<th>SĐT</th>
						<th>Địa chỉ</th>
						<th>Phương thức</th>
						<th>Mô tat</th>
						<th>Tổng giá</th>
						<th>Tình trạng</th>
						<th>Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list }">
						<tr>
							<td>${item.id }</td>
							<td>${item.user.hoTen }</td>
							<td>${item.user.sdt }</td>
							<td>${item.user.diaChi }</td>
							<td><c:choose>
									<c:when test="${item.status == 0 }">Chờ xác nhận</c:when>
									<c:when test="${item.status == 1 }">Đang giao hàng</c:when>
									<c:when test="${item.status == 2 }">Đã hủy</c:when>
									<c:when test="${item.status == 3 }">Đã giao hàng</c:when>
								</c:choose></td>
							<td>${item.moTa }</td>
							<td>${item.total }</td>
							<td><c:choose>
									<c:when test="${item.thanhToan == 0 }">Sau khi nhận hàng</c:when>
									<c:when test="${item.thanhToan == 1 }">Thanh toán Online</c:when>
								</c:choose></td>
							<td><a class="btn btn-warning" href="/QLBH/admin/QLHD/chitiet?id=${item.id }">Cập nhật</a>
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</main>