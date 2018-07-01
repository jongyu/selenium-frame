package com.zhongyu.page;

import com.zhongyu.code.BasePage;
import com.zhongyu.code.DriverBase;
import org.openqa.selenium.WebElement;

/**
 * Created by YuChan on 02/26/2017.
 */
public class BaiduPage extends BasePage {

    public BaiduPage(DriverBase driver) {
        super(driver);
    }

    public WebElement getSearchInput() {
        return element(getLocator("home","search_input"));
    }

    public WebElement getInputElement() {
        return element(getLocator("fanyi", "row_input"));
    }

    public WebElement getTranslateElement() {
        return element(getLocator("fanyi", "translate"));
    }

    public WebElement getResultElement() {
        return element(getLocator("fanyi", "output"));
    }

}