package com.codegym.interfaces;

import com.codegym.Product;

public interface AdminActions {
      void addProduct();
      void removeProduct();
      void updateProduct();
      void showUser();
      Product findProductById(int id);

  }
