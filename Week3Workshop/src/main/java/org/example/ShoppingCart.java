package org.example;

import java.util.ArrayList;
import java.util.List;
public class ShoppingCart {
        private List<Product> cartItems;

        public ShoppingCart() {
            this.cartItems = new ArrayList<>();
        }

        public void addProductToCart(Product product) {
            cartItems.add(product);
        }

        public void removeProduct(String sku) {
            Product productToRemove = null;
            for (Product p : cartItems) {
                if (p.getSku().equalsIgnoreCase(sku)) {
                    productToRemove = p;
                    break;
                }
            }
            if (productToRemove != null) {
                cartItems.remove(productToRemove);

            }
        }

        //TODO get cart total method
        public double getCartTotal() {
            double total = 0.0;
            for (Product product : cartItems) {
                total += product.getPrice();
            }
            return total;
        }
        public List<Product> getCartItems() {
            return cartItems;

                }
            }

