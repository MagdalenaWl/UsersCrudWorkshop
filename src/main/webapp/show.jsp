<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/header.jsp" %>

<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
        <a href="/user/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i> Generate Users List</a>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">User details</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-hover" id="dataTable" width="100%" cellspacing="0">
                    <tr>
                        <th> ID</th>
                        <td> ${user.id}</td>
                    </tr>
                    <tr>
                        <th> Username </th>
                        <td> ${user.userName}</td>
                    </tr>
                    <tr>
                        <th> Email </th>
                        <td> ${user.email}</td>
                    </tr>



                </table>
            </div>
        </div>
    </div>
</div>


<!-- /.container-fluid -->
<!-- Page level plugins -->
<script src="theme/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="theme/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="theme/js/demo/datatables-demo.js"></script>

<%@ include file="/footer.jsp" %>

