<%@ page language="java" session="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main>

	<div class="container-fluid">
		<div class="row">
			<h1>Danh sách sản phẩm</h1>
			<div class="col-12" style="background-color: rgb(233, 233, 232);">
				<h5 class="mt-3 mb-3">Trang chủ / Quản lý Sản phẩm</h5>
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
				<form action="/QLBH/QLSP/index" method="get">
					<div class="row">
						<div class="col-8">
							<input type="text" class="form-control" name="name" id=""
								placeholder="Tên Sản Phẩm">
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
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Thêm sản phẩm</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<form action="/QLBH/QLSP/store" method="post"
							enctype="multipart/form-data">
							<div class="modal-body mt-4">

								<div class="row">
									<div class="col-6 form-input-group">
										<label for="">Tên Sản phẩm</label> <input type="text"
											class="form-control" name="ten">
									</div>
									<div class="col-6 form-input-group ">
										<label for="">Thể loại</label> <select name="categoryID"
											class="form-select">
											<c:forEach var="c" items="${categories }">
												<option value="${c.id }">${c.ten }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="row">
									<div class="col-6 form-input-group mt-4">
										<label for="">Số lượng</label> <input type="number"
											class="form-control" name="soLuong">
									</div>
									<div class="col-6 form-input-group mt-4">
										<label for="">Đơn giá</label> <input type="number"
											class="form-control" name="donGia">
									</div>
								</div>
								<div class="row">
									<div class="col-6 form-input-group mt-4 mb-3">
										<label for="">Màu sắc</label> <input type="text"
											class="form-control" name="mauSac">
									</div>
									<div class="col-6 form-input-group mt-4 mb-3">
										<label for="">Kích thước</label> <input type="text"
											class="form-control" name="kichThuoc">
									</div>
								</div>
								<div class="form-input-group mt-4 mb-3">
									<label for="">Hình ảnh</label> <input type="file"
										class="form-control" name="image">
								</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>
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
						<th>Tên Sản phẩm</th>
						<th>Thể loại</th>
						<th>Số lượng</th>
						<th>Đơn giá</th>
						<th>Màu sắc</th>
						<th>Kích thước</th>
						<th>Hình ảnh</th>
						<th>Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${items }">
						<tr>
							<td>${p.id }</td>
							<td>${p.ten }</td>
							<td>${p.category.ten }</td>
							<td>${p.soLuong }</td>
							<td>${p.donGia }</td>
							<td>${p.mauSac }</td>
							<td>${p.kichThuoc }</td>
							<td><img alt="" class="img-fluid"
								src="/QLBH/image/${p.img }" height="200px" width="200px">
							</td>
							<td>
								<button class="btn btn-warning" data-bs-toggle="modal"
									data-bs-target="#edit${p.id }">Edit</button>
								<div class="modal fade" id="edit${p.id }" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog modal-lg">
										<div class="modal-content ">
											<div class="modal-header">
												<h5 class="modal-title" id="edit${p.id }">Cập nhật sản
													phẩm</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<form action="/QLBH/QLSP/update?id=${p.id }" method="post"
												enctype="multipart/form-data">
												<div class="modal-body mt-4">
													<div class="row">
														<div class="col-5">
															<img alt="" class="img-fluid" src="/QLBH/image/${p.img }">
														</div>
														<div class="col-7">
															<div class=" form-input-group">
																<label for="">Tên Sản phẩm</label> <input type="text"
																	class="form-control" name="ten" value="${p.ten }">
															</div>
															<div class="form-input-group ">
																<label for="">Thể loại</label> <select name="categoryID"
																	class="form-select">
																	<c:forEach var="c" items="${categories }">
																		<option value="${c.id }"
																			${c.id == p.category.id ? 'selected':'' }>${c.ten }</option>
																	</c:forEach>
																</select>
															</div>
															<div class=" form-input-group mt-4">
																<label for="">Số lượng</label> <input type="number"
																	class="form-control" name="soLuong"
																	value="${p.soLuong }">
															</div>
															<div class="form-input-group mt-4">
																<label for="">Đơn giá</label> <input type="number"
																	class="form-control" name="donGia" value="${p.donGia }">
															</div>


															<div class="form-input-group mt-4 mb-3">
																<label for="">Màu sắc</label> <input type="text"
																	class="form-control" name="mauSac" value="${p.mauSac }">
															</div>
															<div class="form-input-group mt-4 mb-3">
																<label for="">Kích thước</label> <input type="text"
																	class="form-control" name="kichThuoc"
																	value="${p.kichThuoc }">
															</div>

															<div class="form-input-group mt-4 mb-3">
																<label for="">Hình ảnh</label> <input type="file"
																	class="form-control" name="img" value="${p.img }">
															</div>
														</div>
													</div>

												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Close</button>
													<button class="btn btn-primary">Lưu</button>
												</div>
											</form>
										</div>
									</div>
								</div>
							</td>
							<td>
								<button class="btn btn-danger" data-bs-toggle="modal"
									data-bs-target="#delete${p.id }">Delete</button>
									<div class="modal fade" id="delete${p.id }" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="delete${p.id }">Xác nhận</h5>
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
													href="/QLBH/QLSP/delete?id=${p.id }">Xác nhận</a>
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
							<li class="page-item"><a class="page-link"
								href="/QLBH/QLSP/index?name=${name }&page=1"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
							<c:forEach var="p" begin="1" end="${pagecount }">
								<li class="page-item"><a class="page-link"
									href="/QLBH/QLSP/index?name=${name }&page=${p}">${p }</a></li>
							</c:forEach>

							<li class="page-item"><a class="page-link"
								href="/QLBH/QLSP/index?name=${name }&page=${pagecount}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>

</main>