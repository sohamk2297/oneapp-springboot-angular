<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <title>EcommAppLogin</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css"
        integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
    <style>
        .hr {
            color: gray;
        }
    </style>
</head>

<body>
    <div class="container-fluid">
        <div class="row d-flex align-items-center min-vh-100">
            <div class="col-sm-4 offset-sm-1 border shadow rounded p-4">
                <center>
                    <h4>Login</h4>
                </center>
                <hr>

                <div class="container">
                    <form id="f1" action="verifylogin" method="post">
                        <div class="row">
                         
                            <div class="form-row">
                                <div class="col-sm-12">
                                    <div class="form-group form-inline">
                                        <label for="email">Username</label>
                                        <input type="text" class="form-control" name="username" id="email"
                                            aria-describedby="emailHelpId" placeholder="email">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-sm-12">
                                    <div class="form-group form-inline">
                                        <label for="pass">Password</label>
                                        <input type="password" class="form-control" name="password" id="pass"
                                            aria-describedby="emailHelpId" placeholder="password" />
                                        <i class="fas fa-eye" id="eye1"></i>
                                    </div>
                                </div>
                            </div>
                          


                        </div>
                        <div class="row">
                            <div class="form-row">
                                <div class="col">
                                    <button type="submit" class="btn btn-primary">Sign Up</button>
                                </div>
                                <button type="reset" id="clear" class="btn btn-outline-primary ml-3">Clear</button>
                            </div>
                        </div>
                        <small id = "wrong" style="color: red;">Username or password is wrong</small>

                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function () {
            $("#pdm").hide();
            $("#wrong").hide();
            $("#eye1").click(function () {
                // console.log($('#pass').attr('type'));
                if ($('#pass').attr('type') == 'password') {
                    $('#pass').attr('type', 'text');
                }
                else if ($('#pass').attr('type') == 'text') {
                    $('#pass').attr('type', 'password');
                }
                if ($('#eye1').hasClass('fa-eye')) {
                    $('#eye1').removeClass('fa-eye').addClass('fa-eye-slash');
                }
                else if ($('#eye1').hasClass('fa-eye-slash')) {
                    $('#eye1').removeClass('fa-eye-slash').addClass('fa-eye');
                }
            });
            $("#eye2").click(function () {
                if ($('#confpass').attr('type') == 'password') {
                    $('#confpass').attr('type', 'text');
                }
                else if ($('#confpass').attr('type') == 'text') {
                    $('#confpass').attr('type', 'password');
                }
                if ($('#eye2').hasClass('fa-eye')) {
                    $('#eye2').removeClass('fa-eye').addClass('fa-eye-slash');
                }
                else if ($('#eye2').hasClass('fa-eye-slash')) {
                    $('#eye2').removeClass('fa-eye-slash').addClass('fa-eye');
                }

            });

            $('#confpass').blur(function () {
                var cp = $('#confpass').val();
                var p = $("#pass").val();
                if (cp !== p) {
                    $("#pdm").show("slow");
                }
                if (cp === p) {
                    $("#pdm").hide("slow");
                }

            });
            $("#clear").click(function () {
                $("#pdm").hide("slow");
            });
             $("#f1").submit(function () {
                 if(${status} === "wrong")
                 {
                     $("#wrong").show("slow");
                     return false;
                 }
                 else
                 {
                     return true;
                 }

             });
            
            
        });
    </script>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>