<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Custom Styling -->
    <style>
        /* Background styling */
        body {
            background: url('https://img.freepik.com/free-photo/still-life-books-versus-technology_23-2150062979.jpg?size=626&ext=jpg&ga=GA1.1.464910780.1720724193&semt=ais_hybrid') no-repeat center center fixed;
            background-size: cover;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
            backdrop-filter: blur(5px); /* Apply blur to the background */
        }
        
        /* Blurred header background */
        .header {
            width: 100%;
            background: rgba(255, 192, 203, 0.5); /* Soft pink with 50% transparency */
            color: white;
            text-align: center;
            padding: 20px 0;
            font-size: 1.5rem;
            font-weight: bold;
            backdrop-filter: blur(10px); /* Blur the background behind the header */
            position: fixed;
            top: 0;
            z-index: 1;
        }

        /* Semi-transparent card styling */
        .card {
            background: rgba(255, 192, 203, 0.5); /* Soft pink with 50% transparency */
            color: white;
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.3);
            backdrop-filter: blur(10px); /* Extra blur for card */
        }

        /* Title styling with blur effect */
        .card h2 {
            color: #fff;
            font-size: 2.5rem;
            font-weight: bold;
            text-shadow: 0px 4px 10px rgba(0, 0, 0, 0.5); /* Shadow for blurred effect */
        }

        /* Button styling */
        .btn-custom {
            border-radius: 25px;
            font-size: 1.2rem;
            width: 100%;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .btn-outline-primary:hover {
            transform: scale(1.05); /* Slightly enlarge button on hover */
            box-shadow: 0px 4px 10px rgba(0, 123, 255, 0.5); /* Add shadow on hover */
        }

        .btn-outline-success:hover {
            transform: scale(1.05);
            box-shadow: 0px 4px 10px rgba(40, 167, 69, 0.5);
        }
    </style>
</head>
<body>

	 <!-- Header with blurred background -->
    <div class="header">
        Book Management
    </div>

    <!-- Main Container -->
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card text-center">
                    <h2 class="mb-4">Book Management</h2>
                    
                    <a href="book/showBook" class="btn btn-outline-primary btn-lg btn-custom mb-3">Show Book List</a>
                    <a href="/addBook" class="btn btn-outline-success btn-lg btn-custom">Add Book</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.11.6/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.2/js/bootstrap.min.js"></script>
</body>
</html>
