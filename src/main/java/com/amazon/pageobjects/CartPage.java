package com.amazon.pageobjects;

import com.amazon.commonfunctions.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CartPage extends Common {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        // Giving life for the driver using inside PageFactory commands
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "sc-subtotal-label-activecart")
    WebElement quantity;
    @FindBy(id = "sc-subtotal-amount-activecart")
    WebElement qty;
    @FindBy(css = "input[value='Delete']")
    WebElement clearButton;
    @FindBy(id = "sc-subtotal-label-activecart")
    WebElement emptyStringlabel;
    By emptyText = By.cssSelector("h1[class='a-spacing-mini a-spacing-top-base']");

    public String getQuantity(){
        // Quantity for assertions
        String quantityInCart = quantity.getText();
        System.out.println(quantityInCart);
        return quantityInCart;
    }
    public double gettotal(){
        // Sub-total for assertions
        String qtyInString = qty.getText();
        String qtyOnlyInString = qtyInString.replace(" $", "");
        double qtyInDouble = Double.parseDouble(qtyOnlyInString);
        System.out.println(qtyInDouble);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return qtyInDouble;
    }
    public void clearCart(){
        // Clear Cart
        clearButton.click();
        waitForElementToAppear(emptyText);
    }
    public String getEmptyString(){
        // Empty string for assertions
        String emptyString = emptyStringlabel.getText();
        System.out.println(emptyString);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return emptyString;
    }
}
