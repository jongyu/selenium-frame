package com.zhongyu.code;

import com.zhongyu.util.GetByLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by YuChan on 02/26/2017.
 */
public class BasePage {

    private Logger logger = LoggerFactory.getLogger(BasePage.class);

    public DriverBase driver;

    public BasePage(DriverBase driver) {
        this.driver = driver;
    }

    /**
     * 使用YAML定位
     *
     * @param node 节点
     * @param key  键
     * @return
     */
    public By getLocator(String node, String key) {
        return GetByLocator.getLocator(node, key);
    }

    /**
     * 关闭驱动
     */
    public void close() {
        driver.close();
    }

    /**
     * 推出驱动
     */
    public void quit() {
        driver.quit();
    }

    /**
     * 定位Element
     *
     * @param by 定位方式
     * @return element
     */
    public WebElement element(By by) {
        return driver.findElement(by);
    }

    /**
     * 层级定位，通过父节点定位到子节点
     * 需要传入父节点和子节点的By
     *
     * @param by   定位方式
     * @param node 父节点
     * @return
     */
    public WebElement nodeElement(By by, By node) {
        WebElement element = this.element(by);
        return element.findElement(node);
    }

    /**
     * 层级定位出入Element，已经子节点的By
     *
     * @param element
     * @param by
     * @return
     */
    public WebElement nodeElement(WebElement element, By by) {
        return element.findElement(by);
    }

    /**
     * 定位一组Element
     *
     * @param by
     * @return
     */
    public List<WebElement> elements(By by) {
        return driver.findElements(by);
    }

    /**
     * 通过父节点定位一组elements
     *
     * @param element
     * @param by
     * @return
     */
    public List<WebElement> elements(WebElement element, By by) {
        return element.findElements(by);
    }

    /**
     * 封装点击
     *
     * @param element
     */
    public void click(WebElement element) {
        if (null != element) element.click();
        else {
            logger.info("元素未定位到, 点击失败!");
        }
    }

    /**
     * 封装输入
     *
     * @param element
     * @param value
     */
    public void sendKeys(WebElement element, String value) {
        if (null != element) element.sendKeys(value);
        else {
            logger.info("元素未定位到, 输入 " + value + " 输入失败!");
        }
    }

    /**
     * 元素是否显示
     *
     * @param element
     * @return
     */
    public boolean existbElement(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * 获取文本信息
     *
     * @param element
     * @return
     */
    public String getText(WebElement element) {
        return element.getText();
    }

    /**
     * 获取标题
     *
     * @return
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * 移动操作
     *
     * @param element
     */
    public void action(WebElement element) {
        driver.action(element);
    }

    /**
     * 获取当前窗口所有的Windows
     *
     * @return
     */
    public List<String> getWindowsHandles() {
        return driver.getWindowHandles();
    }

    /**
     * 根据Title切换到新窗口
     *
     * @param titleName
     * @return
     */
    public boolean switchToWindowTitle(String titleName) {
        boolean flag = false;
        try {
            String currentHandle = driver.getWindowHandle();
            List<String> handles = driver.getWindowHandles();
            for (String handle : handles)
                if (handle.equals(currentHandle)) continue;
                else {
                    driver.switchWindows(handle);
                    if (driver.getTitle().contains(titleName)) {
                        flag = true;
                        logger.info("切换Window成功: " + titleName);
                        break;
                    } else {
                        continue;
                    }
                }
        } catch (NoSuchWindowException e) {
            logger.info("Window: " + titleName + " 没找到!!!" + e.fillInStackTrace());
            flag = false;
        }
        return flag;
    }

}