<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container mt-5 mb-5">
	<div class="row">
		<div class="col-5">
			<img class="card-img-top" src="/QLBH/image/${item.img }"
				alt="..." />
		</div>
		<div class="col-7">
			<p class="bg-success text-white fw-bold text-center"
				style="width: 50px;">New</p>
			<h2>${item.ten }</h2>
			<p>Product Code: ${item.id }</p>
			<i class="bi-star-fill" style="color: rgb(153, 238, 26);"></i> <i
				class="bi-star-fill" style="color: rgb(144, 230, 16);"></i> <i
				class="bi-star-fill" style="color: rgb(153, 238, 26);"></i> <i
				class="bi-star-fill" style="color: rgb(153, 238, 26);"></i> <i
				class="bi-star-fill" style="color: rgb(153, 238, 26);"></i>
			<p class="fw-bold text-warning"
				style="font-size: 26px; padding-top: 20px;">
				Giá :
				<fmt:formatNumber value="${item.donGia }" />
				VNĐ
			</p>
			<p>
				<strong>Màu sắc</strong> : ${item.mauSac }
			</p>
			<p>
				<strong>Size</strong> : ${item.kichThuoc }
			</p>
			<form action="/QLBH/users/cart" method="post">
				<p>
					<input type="hidden" name="id" value="${item.id }"> <strong>Số
						lượng</strong> :<input type="number" class="fw-bold" name="quantity" id=""
						style="width: 50px; text-align: center;" min="1"
						max="${item.soLuong }" value="1"> <i class="text-muted">${item.soLuong }
						sản phẩm có sẳn</i>
				</p>
				<button class="btn btn-outline-danger mb-5 mt-4">Thêm vào
					giỏ hàng</button>
			</form>
			
		</div>
	</div>
	<div class="row mt-5 mb-5">
		<h4 class="fw-bold text-danger">Sản phẩm liên quan</h4>
		<hr>
		<c:forEach var="item" items="${splienquan }">
			<div class="col-3">
				<div class="card h-100">
					<!-- Sale badge-->
					<!-- Product image-->
					<img class="card-img-top" src="/QLBH/image/${item.img }"
						alt="..." />
					<!-- Product details-->
					<div class="card-body p-4">
						<div class="text-center">
							<!-- Product name-->
							<h5 class="fw-bolder">${item.ten }</h5>
							<!-- Product price-->

							<p>${item.donGia }</p>

						</div>
					</div>
					<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
						<div class="text-center">
							<a class="btn btn-outline-dark mt-auto"
								href="/QLBH/users/productDetail?id=${item.id }"> View
								options</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

	</div>
	<hr>
	<div class="row">
		<div class="container mt-5">
			<div class="row d-flex ">
				<div class="col-md-12">
					<div
						class="headings d-flex justify-content-between align-items-center mb-3">
						<h3>Nhận xét sản phẩm</h3>
						
						
						</div>
					<c:if test="${!empty sessionScope.taikhoan  }">
						<form action="/QLBH/users/comment?productID=${item.id }" method="post">
						<div class="row mb-4">
						<div class="col-8"><input type="text" class="form-control" name="noiDung" placeholder="Comment"> </div>
						<div class="col-4"> <button class="btn btn-primary">Đăng</button> </div>
					</div>
						</form>
					</c:if>
				<c:forEach var="cmt" items="${comments }"> 
				<div class="card p-3">
						<div class="d-flex justify-content-between align-items-center">
							<div class="user d-flex flex-row align-items-center">
								<img src="https://i.imgur.com/hczKIze.jpg" width="30"
									class="user-img rounded-circle mr-2"> <span><small
									class="font-weight-bold text-primary">${cmt.user.hoTen }</small> <small
									class="font-weight-bold">${cmt.noiDung }</small></span>
							</div>
							<small><fmt:formatDate value="${cmt.date }" pattern="dd-MM-yyyy"/> </small>
						</div>
						<div
							class="action d-flex justify-content-between mt-2 align-items-center">
							<div class="reply px-4">
								<small>Remove</small> <span class="dots"></span> <small>Reply</small>
								<span class="dots"></span> <small>Translate</small>
							</div>
							<div class="icons align-items-center">
								<i class="fa fa-star text-warning"></i> <i
									class="fa fa-check-circle-o check-icon"></i>
							</div>
						</div>
					</div>
				</c:forEach>
				</div>
			</div>
		</div>
		</div>
	</div>