package com.zhongyu.test;

import com.zhongyu.business.BaiduBusiness;
import com.zhongyu.code.DriverBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by YuChan on 02/26/2017.
 */
public class BaiduTest {

    private DriverBase driver;
    private BaiduBusiness baiduBusiness;

    @BeforeClass
    public void setUp() {
        this.driver = new DriverBase();
        baiduBusiness = new BaiduBusiness(driver);
    }

    @Test
    public void translate() {
        driver.get("http://fanyi.baidu.com/#zh/en");
        baiduBusiness.translate("yellow");
    }

    @Test
    public void searchTest() {
        driver.get("https://www.baidu.com");
        baiduBusiness.search("zhongyu.biz");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        try {
            Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
        } catch (Exception e) {
            throw new RuntimeException("杀死ChromeDriver失败");
        }
    }

}