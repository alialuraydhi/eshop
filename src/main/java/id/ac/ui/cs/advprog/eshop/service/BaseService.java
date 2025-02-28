<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit Product</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Bootstrap Icons (untuk ikon) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body class="bg-gray-50 flex justify-center items-start min-h-screen font-sans p-5">
<!-- Container dengan margin-top (mt-10) -->
<div class="w-full max-w-2xl mt-10 transition-all duration-300 hover:-translate-y-2 hover:shadow-2xl">
    <div class="bg-gradient-to-r from-[#2d2d2d] to-[#121212] text-white px-6 py-4 rounded-t-lg">
        <h2 class="text-2xl font-semibold">Create New Product</h2>
    </div>
    <div class="bg-white shadow-lg rounded-b-lg p-6">
        <form th:action="@{/product/create}" th:object="${product}" method="post">
            <input type="hidden" th:field="*{productId}" />

            <!-- Product Name Field -->
            <div class="mb-4">
                <label for="productName" class="block text-sm font-medium text-gray-700">Product Name:</label>
                <input type="text" id="productName" th:field="*{productName}" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" required />
            </div>

            <!-- Product Quantity Field -->
            <div class="mb-6">
                <label for="productQuantity" class="block text-sm font-medium text-gray-700">Product Quantity:</label>
                <input type="number" id="productQuantity" th:field="*{productQuantity}" class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" required />
            </div>

            <!-- Submit Button -->
            <div class="flex justify-end">
                <button type="submit" class="bg-[#2d2d2d] text-white px-4 py-2 rounded-md inline-flex items-center hover:bg-[#b6b6b6] hover:text-[#2d2d2d] transition-colors">
                    <i class="bi bi-save mr-2"></i> Create Product
                </button>
            </div>
        </form>

        <!-- Back to Product List Link -->
        <a th:href="@{/product/list}" class="mt-4 inline-flex items-center text-[#2d2d2d] hover:text-[#121212] transition-colors">
            <i class="bi bi-arrow-left mr-2"></i> Back to Product List
        </a>
    </div>
</div>
</body>
</html>