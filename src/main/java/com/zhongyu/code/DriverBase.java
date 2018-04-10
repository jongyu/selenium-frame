package com.zhongyu.code;

import com.zhongyu.util.SelectDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by YuChan on 02/26/2017.
 */
public class DriverBase {

    private Logger logger = LoggerFactory.getLogger(DriverBase.class);
    private WebDriver driver;

    public DriverBase() {
        this.driver = new SelectDriver().getDriver();
    }

    /**
     * 获取Driver
     *
     * @return driver
     */
    public WebDriver getDriver() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        return driver;
    }

    public void get(String url) {
        driver.get(url);
    }

    /**
     * 退出驱动
     */
    public void quit() {
        logger.info("stop driver");
        driver.quit();
    }

    /**
     * 退出驱动
     */
    public void close() {
        logger.info("close driver");
        driver.quit();
    }

    /**
     * 封装Element方法
     *
     * @param by
     * @return
     */
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    /**
     * 封装Elements方法
     *
     * @param by
     * @return
     */
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    /**
     * 返回上一页
     */
    public void back() {
        driver.navigate().back();
    }

    /**
     * 点击
     *
     * @param element
     */
    public void click(WebElement element) {
        logger.info("点击" + element.getText());
        element.click();
    }

    /**
     * 获取当前URL
     *
     * @return
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * 获取当前标题
     *
     * @return
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * 获取Window
     *
     * @return
     */
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * 获取一组Window
     *
     * @return
     */
    public List<String> getWindowHandles() {
        return new ArrayList<>(driver.getWindowHandles());
    }

    /**
     * 切换窗口
     *
     * @param name
     */
    public void switchWindows(String name) {
        driver.switchTo().window(name);
    }

    /**
     * 切换Alert
     */
    public void switchAlert() {
        driver.switchTo().alert();
    }

    /**
     * 模态框切换
     */
    public void switchToMode() {
        driver.switchTo().activeElement();
    }

    /**
     * 移动
     *
     * @param element
     * @see Actions,WebElement
     */
    public void action(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    /**
     * 获取Cookie
     *
     * @return
     */
    public Set<Cookie> getCookie() {
        return driver.manage().getCookies();
    }

    /**
     * 删除Cookie
     */
    public void deleteCookie() {
        driver.manage().deleteAllCookies();
    }

    /**
     * 设置Cookie
     *
     * @param cookie
     */
    public void setCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    /**
     * 自动截图
     */
    public void takeScreenShot() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String dateStr = format.format(date);
        String path = this.getClass().getSimpleName() + "_" + dateStr + ".png";
        takeScreenShot((TakesScreenshot) this.getDriver(), path);
    }

    /**
     * 出入参数截图
     *
     * @param driverName
     * @param path
     */
    public void takeScreenShot(TakesScreenshot driverName, String path) {
        String currentPath = System.getProperty("user.dir");
        File scrFile = driverName.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(currentPath + "\\" + path));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            logger.info("截图成功");
        }
    }

}