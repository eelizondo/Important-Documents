package com.codefactorycr.tests;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class AppTest {

    //The 'browser' itself
    private WebDriver driver;

    @BeforeTest
    public void setupSelenium(){
        //Start the browser (firefox for now)
        driver = new FirefoxDriver();

        //This adds implicit timeouts to the driver (instead of clickAndWait())
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void testSearch(){
        driver.navigate().to("http://www.codefactorycr.com");

        String searchTerm = "Java";

        //Clear the search box and ype searchTerm
        driver.findElement(By.cssSelector("#s")).clear();
        driver.findElement(By.cssSelector("#s")).sendKeys(searchTerm);

        //Click on the magnifying glass
        driver.findElement(By.cssSelector("#searchsubmit")).click();

        //Get search term for search results, to check if matches the given search
        String termInTitle =
                driver.findElement(By.cssSelector("#archive-title>strong")).getText();

        assertEquals(termInTitle, searchTerm,
                "Search term not found in search results");

    }


    @AfterTest
    public void closeSelenium(){
        //Shutdown the browser
        driver.quit();
    }


}
