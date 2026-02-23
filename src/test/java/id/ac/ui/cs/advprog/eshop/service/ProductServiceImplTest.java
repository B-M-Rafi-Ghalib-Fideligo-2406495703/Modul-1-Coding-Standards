package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("test-id");
        product.setProductName("Test Product");
        product.setProductQuantity(100);
    }

    @Test
    void testCreateProduct() {
        Mockito.when(productRepository.create(product)).thenReturn(product);
        Product savedProduct = productService.create(product);
        assertNotNull(savedProduct);
        assertEquals(product.getProductId(), savedProduct.getProductId());
        Mockito.verify(productRepository).create(product);
    }

    @Test
    void testFindAll() {
        Product product2 = new Product();
        product2.setProductId("test-id-2");
        List<Product> productList = Arrays.asList(product, product2);
        Iterator<Product> iterator = productList.iterator();

        Mockito.when(productRepository.findAll()).thenReturn(iterator);

        List<Product> allProducts = productService.findAll();
        assertEquals(2, allProducts.size());
        assertEquals("test-id", allProducts.get(0).getProductId());
        Mockito.verify(productRepository).findAll();
    }

    @Test
    void testGetProductById() {
        Mockito.when(productRepository.findById("test-id")).thenReturn(product);
        Product foundProduct = productService.getProductById("test-id");
        assertNotNull(foundProduct);
        assertEquals("test-id", foundProduct.getProductId());
        Mockito.verify(productRepository).findById("test-id");
    }

    @Test
    void testEditProduct() {
        Mockito.when(productRepository.edit(product)).thenReturn(product);
        Product editedProduct = productService.edit(product);
        assertNotNull(editedProduct);
        assertEquals(product.getProductId(), editedProduct.getProductId());
        Mockito.verify(productRepository).edit(product);
    }

    @Test
    void testDeleteProduct() {
        productService.delete("test-id");
        Mockito.verify(productRepository).delete("test-id");
    }
}
