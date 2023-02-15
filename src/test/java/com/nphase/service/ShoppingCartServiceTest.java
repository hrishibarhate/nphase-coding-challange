package com.nphase.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.nphase.entity.Category;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

public class ShoppingCartServiceTest {
	private final ShoppingCartService service = new ShoppingCartService();

	@Test
	public void calculatesPrice() {
		ShoppingCart cart = new ShoppingCart(Arrays.asList(new Product("Tea", BigDecimal.valueOf(5.0), 2, null),
				new Product("Coffee", BigDecimal.valueOf(6.5), 1, null)));

		BigDecimal result = service.calculateTotalPrice(cart);

		Assertions.assertEquals(result, BigDecimal.valueOf(16.5));
	}
	
// 	  Task 2 
//    Given 2 products:
//    	 -> name: tea, pricePerUnit: 5, quantity: 5
//    	 -> name: coffee, pricePerUnit: 3.5, quantity: 3
//
//    	Expected total is: 22.5 (for tea) + 10.5 (for coffee) = 33.0
	@Test
	public void calculateTotalWithDiscountAndProductTest_WithDiscount() {
		ShoppingCart cart = new ShoppingCart(Arrays.asList(				
				new Product("Tea", BigDecimal.valueOf(5.0), 5, null),
				new Product("Coffee", BigDecimal.valueOf(3.5), 3, null)));

		BigDecimal result = service.calculateTotalWithDiscountAndProductName(cart);
		
		Assertions.assertTrue(result.compareTo(BigDecimal.valueOf(33.00)) == 0);

	}
	
	@Test
	public void calculateTotalWithDiscountAndProductTest_WithOutDiscount() {
		ShoppingCart cart = new ShoppingCart(Arrays.asList(
				new Product("Tea", BigDecimal.valueOf(5.0), 2, null),
				new Product("Coffee", BigDecimal.valueOf(3.5), 2, null)));

		BigDecimal result = service.calculateTotalWithDiscountAndProductName(cart);
		Assertions.assertTrue(result.compareTo(BigDecimal.valueOf(17.00)) == 0);

	}
	
//	  Task 3 
//    Given 3 products:
//    	 -> name: tea, pricePerUnit: 5.3, quantity: 2, category: drinks
//    	 -> name: coffee, pricePerUnit: 3.5, quantity: 2, category: drinks
//    	 -> name: cheese, pricePerUnit: 8, quantity: 2, category: food
//    Expected total is: 9.54 (for tea) + 6.30 (for coffee) + 16 (for cheese) = 31.84
	@Test
	public void calculateTotalWithDiscountAndCategoryTest_WithCategory() {
		ShoppingCart cart = new ShoppingCart(
				Arrays.asList(new Product("Tea", BigDecimal.valueOf(5.3), 2, Category.DRINKS),
						new Product("Coffee", BigDecimal.valueOf(3.5), 2, Category.DRINKS),
						new Product("Cheese", BigDecimal.valueOf(8), 2, Category.FOOD)));

		BigDecimal result = service.calculateTotalWithDiscountAndCategory(cart);
		Assertions.assertTrue(result.compareTo(BigDecimal.valueOf(31.84)) == 0);

	}
	
	@Test
	public void calculateTotalWithDiscountAndCategoryTest_WithOutCategory() {
		ShoppingCart cart = new ShoppingCart(
				Arrays.asList(new Product("Tea", BigDecimal.valueOf(5.3), 2, Category.DRINKS),
						new Product("Cheese", BigDecimal.valueOf(8), 2, Category.FOOD)));

		BigDecimal result = service.calculateTotalWithDiscountAndCategory(cart);

		Assertions.assertTrue(result.compareTo(BigDecimal.valueOf(26.6)) == 0);

	}
	
//	  Task 4 
//	Requirement:
//		We would like to make amount of items and discount percentage configurable.
	@Test
	public void calculateTotalWithItemAmountAndDiscountConfigurableTest_WithDiscount() {
		ShoppingCart cart = new ShoppingCart(
				Arrays.asList(new Product("Tea", BigDecimal.valueOf(5.3), 2, Category.DRINKS),
						new Product("Coffee", BigDecimal.valueOf(3.5), 2, Category.DRINKS),
						new Product("Cheese", BigDecimal.valueOf(8), 2, Category.FOOD)));

		BigDecimal result = service.calculateTotalWithItemAmountAndDiscountConfigurable(cart, BigDecimal.valueOf(30),
				BigDecimal.valueOf(0.9));

		Assertions.assertTrue(result.compareTo(BigDecimal.valueOf(30.24)) == 0);

	} 
	
	@Test
	public void calculateTotalWithItemAmountAndDiscountConfigurableTest_WithoutDiscount() {
		ShoppingCart cart = new ShoppingCart(
				Arrays.asList(new Product("Tea", BigDecimal.valueOf(5.3), 2, Category.DRINKS),
						new Product("Coffee", BigDecimal.valueOf(3.5), 2, Category.DRINKS),
						new Product("Cheese", BigDecimal.valueOf(8), 2, Category.FOOD)));

		BigDecimal result = service.calculateTotalWithItemAmountAndDiscountConfigurable(cart, BigDecimal.valueOf(35),
				BigDecimal.valueOf(0.9));
		
		Assertions.assertTrue(result.compareTo(BigDecimal.valueOf(33.60)) == 0);

	}

}