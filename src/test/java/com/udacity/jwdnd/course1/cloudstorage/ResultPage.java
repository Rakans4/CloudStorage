package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id = "success-home-link")
    private WebElement homeLink;

    public ResultPage (WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void returnHome() {
        homeLink.click();
    }
}
