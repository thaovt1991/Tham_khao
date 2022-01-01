package com.cg.service.product;


import com.cg.model.Product;
import com.cg.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Boolean existsBySlugEquals(String slug) {
        return productRepository.existsBySlugEquals(slug);
    }

    @Override
    public Boolean existsBySlugEqualsAndIdIsNot(String slug, long id) {
        return productRepository.existsBySlugEqualsAndIdIsNot(slug, id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {

    }
}
