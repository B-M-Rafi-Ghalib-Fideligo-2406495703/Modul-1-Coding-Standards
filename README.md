# eShop - Advanced Programming

## Reflection 1

### Coding Standards & Clean Code Principles
In the development of the "Edit Product" and "Delete Product" features, I have applied several Clean Code principles and Secure Coding practices:

1.  **Meaningful Names**: I used clear and descriptive variable and method names. For example, `editProductPage`, `deleteProduct`, and `productId` clearly convey their purpose without ambiguity.
2.  **Single Responsibility Principle (SRP)**: I maintained a strict separation of concerns by dividing the application into Controller, Service, and Repository layers.
    *   `ProductController` handles HTTP requests and routing.
    *   `ProductService` encapsulates business logic.
    *   `ProductRepository` manages data storage and retrieval.
    This makes the code modular, easier to test, and maintainable.
3.  **Consistency**: I ensured consistent URL patterns (`/product/{id}/edit` and `/product/{id}/delete`) and coding style (using `@Autowired` for dependency injection and Lombok for model boilerplate).

### Secure Coding Practices
1.  **UUID for IDs**: Instead of sequential integers, I implemented UUID generation for product IDs (`Category 4: Insecure Direct Object References`). This makes IDs unpredictable and harder for attackers to enumerate or guess other valid product IDs.
2.  **Product Not Found Handling**: The `findById` and `update` methods in the repository handle cases where a product isn't found (returning `null`), preventing potential NullPointerExceptions or data corruption.
3.  **Secure HTTP Methods**: I used `POST` (via `_method="DELETE"` hidden field) for the delete feature instead of `GET`. This adheres to RESTful principles and protects against CSRF attacks, ensuring that state-changing operations cannot be triggered by simple link clicks or malicious image tags.

### Areas for Improvement
1.  **Input Validation**: While the current implementation works, there is minimal validation on the product name and quantity. Adding basic validation (e.g., ensuring quantity is positive, name is not empty) would robustify the application against invalid data.
