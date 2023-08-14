package com.amazon.pageobjects;

import com.amazon.commonfunctions.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class ProductListPage extends Common {
    WebDriver driver;
    public ProductListPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        // Giving life for the driver using inside PageFactory commands
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "div[id='reviewsRefinements']>ul[data-csa-c-content-id='1250219011']>span[data-csa-c-type='element']>li>span>a")
    List<WebElement> ratings;
    @FindBy(css = "ul[data-csa-c-content-id='3291435011']>span>li")
    List<WebElement> languages;
    @FindBy(css = ".s-card-border")
    List<WebElement> books;
    By ratingBy = By.cssSelector("section");
    By languageBy = By.cssSelector("span>a");
    By bookNameBy = By.cssSelector("div>div>div:nth-child(2)>div>div>div>h2>a>span");
    By bookBy = By.cssSelector("div>div>div:nth-child(2)>div>div>div>h2>a");
    By bookTypeBy = By.cssSelector("div>div>div:nth-child(2)>div>div>div:nth-child(3)>div>div>div>div:nth-child(1)>a");
    By productTitle = By.id("productTitle");
    public void selectRating(String expectedRating){
        // Select rating
        WebElement rating = ratings.stream().filter(rat -> rat.findElement(ratingBy).getAttribute("aria-label").equals(expectedRating)).findFirst().orElse(null);
        rating.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void selectLanguage(String expectedLanguage){
        // Select Language
        WebElement language = languages.stream().filter(lan -> lan.getAttribute("aria-label").equals(expectedLanguage)).findFirst().orElse(null);
        language.findElement(languageBy).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public String getBookName(){
        // Book name for assertions
        String bookName = null;
        if(!Objects.equals(books.get(1).findElement(bookTypeBy).getText(), "Audible Audiobook")){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            bookName = books.get(1).findElement(bookNameBy).getText();
        }else if (!Objects.equals(books.get(2).findElement(bookTypeBy).getText(), "Audible Audiobook")){
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            bookName = books.get(2).findElement(bookNameBy).getText();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println(bookName);
        return bookName;
    }
    public void selectBook(){
        // Select Book
        if(!Objects.equals(books.get(1).findElement(bookTypeBy).getText(), "Audible Audiobook")){
            books.get(1).findElement(bookBy).click();
        }else if (!Objects.equals(books.get(2).findElement(bookTypeBy).getText(), "Audible Audiobook")){
            books.get(2).findElement(bookBy).click();
        }
        waitForElementToAppear(productTitle);
    }
}
