package com.nphase.service;

import java.math.BigDecimal;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

import com.nphase.entity.Category;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

public class ShoppingCartService {

	public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
		return shoppingCart.getProducts().stream()
				.map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	//Task 2
	public BigDecimal calculateTotalWithDiscountAndProductName(ShoppingCart shoppingCart) {
		Map<String, List<Product>> products = shoppingCart.getProducts()
														  .stream()
														  .collect(Collectors.groupingBy(Product::getName));
		return products.values()
				.stream()
				.map(this::productTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	private BigDecimal productTotal(List<Product> products) {
		BigDecimal sum = products.stream()
				.map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);

		int quantity = products
				.stream()
				.mapToInt(Product::getQuantity).sum();

		if (quantity > 3) {
			sum = sum.multiply(new BigDecimal("0.9"));
		}

		return sum;
	}

	// Task 3
	public BigDecimal calculateTotalWithDiscountAndCategory(ShoppingCart shoppingCart) {
		Map<Category, List<Product>> products = shoppingCart.getProducts().stream()
				.collect(Collectors.groupingBy(Product::getCategory));
		return products.values()
				.stream()
				.map(this::categoryTotal)
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}

	private BigDecimal categoryTotal(List<Product> products) {
		BigDecimal sum = products.stream()
				.map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

		int quantity = products.stream().mapToInt(Product::getQuantity).sum();

		if (quantity > 3) {
			sum = sum.multiply(new BigDecimal("0.9"));
		}

		return sum;
	}

	// Task 4
	public BigDecimal calculateTotalWithItemAmountAndDiscountConfigurable(ShoppingCart cart, BigDecimal amount,
			BigDecimal discount) {
		
		
		BigDecimal sum = cart.getProducts().stream()
				.map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
				.reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
		if (sum.compareTo(amount) >= 0) {
			sum = sum.multiply(discount);
		}

		return sum;
	}

}
