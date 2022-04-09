<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>
	<div class="container-fluid">
		<div class="row">
			<h1>QUẢN LÝ ĐƠN HÀNG</h1>
			<div class="col-12" style="background-color: rgb(233, 233, 232);">
				<h5 class="mt-3 mb-3">Trang chủ / Quản lý đơn hàng/Chi tiết đơn
					hàng</h5>
			</div>
			<div class="col-1"></div>
			<div class="col-9">
				<form action="/QLBH/admin/QLHD/update?id=${hoadon.id }" method="post">
					<div class="form-input-group">
					<div class="row">
					<div class="col-6">
					<label class="fw-bold mt-4">Tên khách hàng:</label> <input
							type="text" class="form-control" value="${hoadon.user.hoTen }"
							disabled>
					</div>
					<div class="col-6">
					 <label class="fw-bold mt-4">Số
							Điện Thoại: </label> <input type="text" class="form-control"
							value=" ${hoadon.user.sdt }" disabled>
					</div>
					</div>
						<div class="row">
						<div class="col-6">
						  <label
							class="fw-bold mt-4">Địa chỉ : </label> <input type="text"
							class="form-control" disabled value="${hoadon.user.diaChi }">
						</div>
						<div class="col-6"> <label class="fw-bold mt-4">Phương thức : </label> <input
							type="text" class="form-control" disabled
							value="${hoadon.thanhToan==0?'Thanh toán sau khi trả':'Thanh toán online' }">
							</div>
						</div>
				<label
							class="fw-bold mt-4">Mô tả: </label> <input type="text"
							class="form-control" disabled value="${hoadon.moTa }"> <br>
						<label class="fw-bold mt-4">Tình trạng </label> 
						<select name="status"
							class="form-select">
							<option value="0" ${hoadon.status == 0 ?'selected':'' }>Chờ
								xác nhận</option>
							<option value="1" ${hoadon.status == 1 ?'selected':'' }>Đang
								giao</option>
							<option value="2" ${hoadon.status == 2 ?'selected':'' }>Đã
								hủy</option>
							<option value="3" ${hoadon.status == 3 ?'selected':'' }>Đã
								giao</option>
						</select>
					</div>
					<div class="form-input-group mt-4">
						<button class="btn btn-primary">Cập nhật</button>
					</div>
				</form>
			</div>
		</div>
		<div class="row">
			<table id="shoppingCart"
				class="table table-condensed table-responsive">
				<thead>
					<tr class="fw-bold">
						<th>
							<h4>Mã sản phẩm</h4>
						</th>
						<th>
							<h4>Ảnh</h4>
						</th>
						<th>
							<h4>Tên sản phẩm</h4>
						</th>
						<th><h4>Số lượng</h4></th>
						<th><h4>Đơn giá</h4></th>
						<th><h4>Thành tiền</h4></th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list }">
						<tr class="fw-bold mt-3">
							<td>${item.product.id }</td>
							<td><img alt="" width="200px" height="200px"
								class="img-fluid d-none d-md-block rounded mb-2 shadow "
								src="/QLBH/image/${item.product.img }"></td>
							<td>${item.product.ten }</td>
							<td>${item.quantity }</td>
							<td>${item.price }</td>
							<td>${item.quantity * item.price }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</main>