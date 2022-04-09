<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>
	<div class="container-fluid">
		<div class="row">
			<h1>Danh sách người dùng</h1>
			<div class="col-12" style="background-color: rgb(233, 233, 232);">
				<h5 class="mt-3 mb-3">Trang chủ / Quản lý User</h5>
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
			<div>
				<c:if test="${!empty sessionScope.message }"> 
				<label class="alert alert-success">${sessionScope.message }</label>
					 <c:remove var="message" scope="session"/>
				 </c:if>
			
			</div>
			<div>
			<c:if test="${!empty sessionScope.error }"> 
				<label class="alert alert-danger">${sessionScope.error }</label>
				 <c:remove var="error" scope="session"/>
				 </c:if>
				 	
			</div>
				
			</div>
			<div class="col-4 mt-3">
				<form action="/QLBH/QLUS/index" method="get">
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
			<!-- Button trigger modal -->
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content ">
						<form action="/QLBH/QLUS/store" method="post">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Thêm mới</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body mt-4">
								<div class="form-input-group">
									<label for="">Họ Tên</label> <input type="text"
										class="form-control" name="hoTen">
								</div>
								<div class="row">
									<div class="col-6 form-input-group mt-4">
										<label for="">Email</label> <input type="email"
											class="form-control" name="email">
									</div>
									<div class="col-6 form-input-group mt-4">
										<label for="">Password</label> <input type="password"
											class="form-control" name="password" required="required">
									</div>
								</div>
								<div class=" form-input-group mt-4">
									<label for="">Địa chỉ</label> <input type="text"
										class="form-control" name="diaChi">
								</div>
								<div class="form-input-group mt-4 mb-3">
									<label for="">SĐT</label> <input type="text"
										class="form-control" name="sdt">
								</div>
								<div class="form-input-group mt-4 mb-3">
									<label class="me-4">Phần quyền</label> <input type="radio"
										class="form-check-input" name="role" value="true"> <label>Admin</label>
									<input type="radio" class="form-check-input" name="role"
										value="false" checked> <label>User</label>
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
						<th>Họ tên</th>
						<th>SDT</th>
						<th>Email</th>
						<th>Địa chỉ</th>
						<th>Phân quyền</th>
						<th colspan="2">Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${users }">
						<tr>
							<td>${user.id }</td>
							<td>${user.hoTen }</td>
							<td>${user.sdt }</td>

							<td>${user.email }</td>
							<td>${user.diaChi }</td>
							<td>${user.role }</td>
							<td><button class="btn btn-warning" data-bs-toggle="modal"
					data-bs-target="#edit${user.id }" >Edit</button>
								<div class="modal fade" id="edit${user.id }" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content ">
											<form action="/QLBH/QLUS/update?id=${user.id }" method="post">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Thêm	mới</h5>
													<button type="button" class="btn-close"  data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body mt-4">
													<div class="form-input-group">
														<label for="">Họ Tên</label> <input type="text"
															class="form-control" name="hoTen" value="${user.hoTen }">
													</div>
														<div class="form-input-group mt-4">
															<label for="">Email</label> <input type="email"
																class="form-control" name="email"  value="${user.email }">
														</div>
													<div class=" form-input-group mt-4">
														<label for="">Địa chỉ</label> <input type="text"
															class="form-control" name="diaChi"  value="${user.diaChi }">
													</div>
													<div class="form-input-group mt-4 mb-3">
														<label for="">SĐT</label> <input type="text"
															class="form-control" name="sdt"  value="${user.sdt }">
													</div>
													<div class="form-input-group mt-4 mb-3">
														<label class="me-4">Phần quyền</label> <input type="radio"
															class="form-check-input" name="role" value="true" ${user.role? 'checked':'' }>
														<label>Admin</label> <input type="radio"
															class="form-check-input" name="role" value="false" ${!user.role? 'checked':''}
															> <label>User</label>
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
									data-bs-target="#exampleModal${user.id }">Delete</button> <!-- Button trigger modal -->
								<!-- Modal -->
								<div class="modal fade" id="exampleModal${user.id }"
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
													href="/QLBH/QLUS/delete?id=${user.id }">Xác nhận</a>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="row justify-content-center">
				<div class="col-3"></div>
				<div class="col-6">
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<li class="page-item"><a class="page-link" href="/QLBH/QLUS/index?name=${name }&page=1"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
							<c:forEach var="p" begin="1" end="${pagecount }">
								<li class="page-item"><a class="page-link"
									href="/QLBH/QLUS/index?name=${name }&page=${p}">${p }</a></li>
							</c:forEach>
							<li class="page-item"><a class="page-link" href="/QLBH/QLUS/index?name=${name }&page=${pagecount}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>

	</div>
</main>