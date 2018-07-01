package com.zhongyu.handle;

import com.zhongyu.code.DriverBase;
import com.zhongyu.page.BaiduPage;

/**
 * Created by YuChan on 02/26/2017.
 */
public class BaiduHandle {

    private BaiduPage baiduPage;

    public BaiduHandle(DriverBase driver) {
        baiduPage = new BaiduPage(driver);
    }

    public void sendKeysSearchInput(String key) {
        baiduPage.sendKeys(baiduPage.getSearchInput(), key);
    }

    public void sendKeysContent(String row) {
        baiduPage.sendKeys(baiduPage.getInputElement(), row);
    }

    public void clickTranslate() {
        baiduPage.click(baiduPage.getTranslateElement());
    }

    public String getResult() {
        return baiduPage.getText(baiduPage.getResultElement());
    }

}