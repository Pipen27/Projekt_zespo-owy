package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity(name = "CARTS")
public class Cart {

    private java.lang.Long id;
    private BigDecimal cartSum;
    private boolean isCartClosed;
    private List<Product> products = new ArrayList<>();

    public Cart(BigDecimal cartSum, boolean isCartClosed) {
        this.cartSum = cartSum;
        this.isCartClosed = isCartClosed;
    }

    public Cart(java.lang.Long id, BigDecimal cartSum, boolean isCartClosed) {
        this.id = id;
        this.cartSum = cartSum;
        this.isCartClosed = isCartClosed;
    }

    public Cart(java.lang.Long id, BigDecimal cartSum, boolean isCartClosed, List<Product> products) {
        this.id = id;
        this.cartSum = cartSum;
        this.isCartClosed = isCartClosed;
        this.products = products;
    }


    public Cart() {
    }

    public Cart(long id, BigDecimal cartSum, boolean cartClosed, List<Product> products, Optional<Product> byId) {
    }

    @Id
    @Column(name = "CART_ID", unique = true)
    @GeneratedValue
    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    @Column(name = "CART_SUM")
    public BigDecimal getCartSum() {
        return cartSum;
    }

    public void setCartSum(BigDecimal cartSum) {
        this.cartSum = cartSum;
    }

    @Column(name = "IS_CART_CLOSED")
    public boolean isCartClosed() {
        return isCartClosed;
    }

    public void setCartClosed(boolean cartClosed) {
        isCartClosed = cartClosed;
    }

    @ManyToMany
    @JoinTable(
            name = "PRODUCTS_IN_CARTS",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    public List<Product> getProducts() {
        return products;
    }


    public void setProducts(List<Product> products) {
        this.products = products;
    }

}