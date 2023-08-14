package com.amazon.automation.tests;

import com.amazon.automation.commontests.BaseTest;
import com.amazon.pageobjects.CartPage;
import com.amazon.pageobjects.HomePage;
import com.amazon.pageobjects.ProductListPage;
import com.amazon.pageobjects.ProductPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCart extends BaseTest {
    @Test
    public void addBookToCart(){
        // Creating objects of page objects
        HomePage homePage = new HomePage(driver);
        ProductListPage productList = new ProductListPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.selectCategory("Books");
        homePage.search("Automation");
        productList.selectRating("4 Stars & Up");
        productList.selectLanguage("English");

        // Book name for assertions
        String bookName = productList.getBookName();
        productList.selectBook();

        // Unit price for assertions
        double unitPrice = productPage.getUnitPrice();
        productPage.selectQuantity("2");
        productPage.addToCart();

        // Quantity for assertions
        String quantity = cartPage.getQuantity();

        // Sub-total for assertions
        double totalPrice = cartPage.gettotal();

        // Assertions
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String actualBookName = driver.findElement(By.cssSelector(".a-truncate-cut")).getText();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertEquals(bookName,actualBookName);
        double actualUnitPrice = Double.parseDouble(driver.findElement(By.cssSelector(".sc-product-price")).getText().replace("$", ""));
        Assert.assertEquals(unitPrice,actualUnitPrice);
        Assert.assertEquals(quantity, "Subtotal (2 items):");
        Assert.assertEquals(totalPrice,unitPrice*2);

        // Clear Cart
        cartPage.clearCart();

        // Empty string for assertions
        String emptyString = cartPage.getEmptyString();

        // Assertions
        Assert.assertEquals(emptyString,"Subtotal (0 items):");
    }
}
