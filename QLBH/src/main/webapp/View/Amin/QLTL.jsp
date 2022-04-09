<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>
	<div class="container-fluid">
		<div class="row">
			<h1>Danh sách Thể loại</h1>
			<div class="col-12" style="background-color: rgb(233, 233, 232);">
				<h5 class="mt-3 mb-3">Trang chủ / Quản lý Thể loại</h5>
			</div>
			<div class="col-2">
				<form action="" method="post">
					<label class="label" for="">Sắp xếp</label> <select
						class="form-select" id="">
						<option value="hoTen"></option>
						<option value="hoTen">Họ tên</option>
					</select>
				</form>
			</div>
			<div class="col-6 text-center">
			 <c:if test="${!empty sessionScope.message }">
			     <label class="alert alert-success">${ sessionScope.message }</label>
			     <c:remove var="message" scope="session"/>
			 </c:if>
			  <c:if test="${!empty sessionScope.error }">
			     <label class="alert alert-danger">${ sessionScope.error }</label>
			     <c:remove var="error" scope="session"/>
			 </c:if>
			</div>
			<div class="col-4 mt-3">
				<form action="/QLBH/QLTL/index" method="get">
					<div class="row">
						<div class="col-8">
							<input type="text" class="form-control" name="name" id=""
								placeholder="Họ tên">
						</div>
						<div class="col-4">
							<button class="btn btn-outline-secondary">Tìm kiếm</button>
						</div>
					</div>
				</form>
			</div>
			<div class="modal fade" id="exampleModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content ">
						<form action="/QLBH/QLTL/store" method="post">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Thêm mới</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body mt-4">
								<div class="form-input-group">
									<label for="">Tên thể loại</label> <input type="text"
										class="form-control" name="ten">
								</div>	
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Đóng</button>
								<button class="btn btn-primary">Thêm mới</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt-5">
			<div class="col-2">
				<button type="button" class="btn btn-success" data-bs-toggle="modal"
					data-bs-target="#exampleModal">
					<i class="bi bi-phone"></i>
				</button>
			</div>
			<table class="table table-light">
				<thead class="thead-light">
					<tr>
						<th>ID</th>
						<th>Tên Thể loại</th>
					
						<th colspan="2">Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ca" items="${categories }">
						<tr>
							<td>${ca.id }</td>
							<td>${ca.ten }</td>
							<td><button class="btn btn-warning" data-bs-toggle="modal"
					data-bs-target="#edit${ca.id }" >Edit</button>
								<div class="modal fade" id="edit${ca.id }" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content ">
											<form action="/QLBH/QLTL/update?id=${ca.id }" method="post">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Thêm	mới</h5>
													<button type="button" class="btn-close"  data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body mt-4">
													<div class="form-input-group">
														<label for="">Tên Sản phẩm</label> <input type="text"
															class="form-control" name="ten" value="${ca.ten }">
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Đóng</button>
													<button class="btn btn-primary">Lưu</button>
												</div>
											</form>
										</div>
									</div>
								</div></td>
							<td>
								<button class="btn btn-danger" data-bs-toggle="modal"
									data-bs-target="#exampleModal${ca.id }">Delete</button> <!-- Button trigger modal -->
								<!-- Modal -->
								<div class="modal fade" id="exampleModal${ca.id }"
									tabindex="-1" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<h5>Bạn có muốn xóa ?</h5>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Đóng</button>
												<a type="button" class="btn btn-primary"
													href="/QLBH/QLTL/delete?id=${ca.id }">Xác nhận</a>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
</div>
	</div>
</main>