package com.amazon.pageobjects;

import com.amazon.commonfunctions.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class ProductPage extends Common {
    WebDriver driver;
    public ProductPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        // Giving life for the driver using inside PageFactory commands
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "price")
    WebElement price;
    @FindBy(id = "quantity")
    WebElement qtyList;
    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;
    @FindBy(css = "#sw-gtc > span > a")
    WebElement goToCartButton;
    By cartTitle = By.cssSelector("#sc-active-cart > div > div > div > h1");

    public double getUnitPrice(){
        // Unit price for assertions
        String uPriceInString = price.getText();
        System.out.println(uPriceInString);
        String uPriceOnlyNumbers = uPriceInString.replace("$", "");
        double uPrice = Double.parseDouble(uPriceOnlyNumbers);
        System.out.println(uPrice);
        return uPrice;
    }
    public void selectQuantity(String quantity){
        // Select "2" from quantity
        Select qty = new Select(qtyList);
        qty.selectByVisibleText(quantity);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void addToCart(){
        // Adding to cart
        addToCartButton.click();
        goToCartButton.click();
        waitForElementToAppear(cartTitle);
    }
}
