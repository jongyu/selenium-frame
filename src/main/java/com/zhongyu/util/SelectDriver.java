package com.zhongyu.util;

import org.arquillian.spacelift.Spacelift;
import org.arquillian.spacelift.task.archive.UnzipTool;
import org.arquillian.spacelift.task.net.DownloadTool;
import org.arquillian.spacelift.task.os.CommandTool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by YuChan on 02/26/2017.
 */
public class SelectDriver {

    private final String DIR = System.getProperty("user.dir") + "/target";
    private final String CHROME = "chrome";
    private final String CHROME_DRIVER_FILE = DIR + "/driver/chromedriver.exe";
    private final String EDGE = "edge";
    private final String EDGE_DRIVER_FILE = DIR + "/driver/MicrosoftWebDriver.exe";
    private final String browser = YamlUtil.getInstance("config").get("browser");
    private final String chrome_url = YamlUtil.getInstance("config").get("webdriver", "chrome");
    private final String edge_url = YamlUtil.getInstance("config").get("webdriver", "edge");

    /**
     * 获取浏览器驱动
     *
     * @return driver
     */
    public WebDriver getDriver() {
        if (browser.equalsIgnoreCase(EDGE)) {
            if (!existDriver(EDGE)) {
                Spacelift.task(DownloadTool.class).from(edge_url).to(EDGE_DRIVER_FILE).execute().await();
            }
            setEnv(EDGE);
            return new EdgeDriver();
        } else {
            if (!existDriver(CHROME)) {
                Spacelift.task(DownloadTool.class).from(chrome_url).to(DIR + "/driver/chrome.zip").then(UnzipTool.class).toDir(DIR + "/driver/").execute().await();
            }
            setEnv(CHROME);
            ChromeDriverService service = new ChromeDriverService.Builder().usingAnyFreePort().build();
            try {
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("about:cache");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-popup-blocking");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            return new RemoteWebDriver(service.getUrl(), capabilities);
        }
    }

    /**
     * 设置浏览器环境
     *
     * @param browser 浏览器名称
     */
    public void setEnv(String browser) {
        if (browser.equalsIgnoreCase(EDGE)) {
            System.setProperty("webdriver.edge.driver", EDGE_DRIVER_FILE);
        } else if (browser.equalsIgnoreCase(CHROME)) {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE);
        }
    }

    /**
     * 判断浏览器驱动是否存在
     *
     * @param driver 驱动名称
     * @return 是否存在
     */
    public boolean existDriver(String driver) {
        File folderName = new File(DIR + "/driver");
        File driverFile;
        if (!folderName.isDirectory()) folderName.mkdir();
        if (driver.equalsIgnoreCase("edge")) {
            driverFile = new File(EDGE_DRIVER_FILE);
        } else {
            driverFile = new File(CHROME_DRIVER_FILE);
        }
        return driverFile.exists();
    }

    public static void main(String[] args) {
        WebDriver driver = new SelectDriver().getDriver();
        driver.get("https://baidu.com");
        driver.close();
    }

}