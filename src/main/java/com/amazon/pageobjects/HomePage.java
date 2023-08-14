package com.amazon.pageobjects;

import com.amazon.commonfunctions.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends Common {
    WebDriver driver;
    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        // Giving life for the driver using inside PageFactory commands
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "searchDropdownBox")
    WebElement categoryDropDown;
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBox;
    @FindBy(id = "nav-search-submit-button")
    WebElement searchButton;

    By logo = By.id("nav-logo-sprites");

    public void selectCategory(String cat){
        // Select "Books" from drop down
        waitForElementToAppear(logo);
        Select category = new Select(categoryDropDown);
        category.selectByVisibleText(cat);
    }
    public void search(String searchTerm){
        // Search by "Automation
        searchBox.sendKeys(searchTerm);
        searchButton.click();
    }
}
