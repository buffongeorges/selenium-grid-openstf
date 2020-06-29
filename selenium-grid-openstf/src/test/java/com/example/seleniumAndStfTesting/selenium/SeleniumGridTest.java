package com.example.seleniumAndStfTesting.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SeleniumGridTest {
	

	WebDriver driver;
	AndroidDriver<AndroidElement> androidDriver;
	String nodeUrl = "http://localhost:4444/wd/hub";
	
    /*@Test(priority = 0)
    public void executeChrome() throws MalformedURLException {
        this.execute(DesiredCapabilities.chrome());
    }
    
	@Test(priority = 1)
    public void executeFirefoxDriver() throws MalformedURLException {
        this.execute(DesiredCapabilities.firefox());
    }
	
	@Test(priority = 2)
    public void executeIE() throws MalformedURLException {
        this.execute(DesiredCapabilities.internetExplorer());
    }*/
    
    @Test
    public void androidOpenStfTest() throws MalformedURLException
    {
    	DesiredCapabilities capabilities = DesiredCapabilities.android();
        //capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformName", "ANDROID");
        //capabilities.setCapability("platformVersion", "10");
        //capabilities.setCapability("deviceName", "RF8N111C7ZM");
        
        capabilities.setCapability("platformVersion", "8");
        capabilities.setCapability("deviceName", "FRTBF80903007481");
        capabilities.setCapability("launchTimeout", 120000);
        URL url = new URL("http://localhost:4729"); //IP Address of Appium Server
        
        androidDriver = new AndroidDriver<AndroidElement>(url, capabilities) ; //si ça fonctionne pas 
        // faut remettre new AndroidDriver<>(...)
        androidDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        androidDriver.findElementByAccessibilityId("Agenda").click();

        
    }
	@Test//(priority = 3)
	public void executeAndroid() throws MalformedURLException {
		DesiredCapabilities capabilities = DesiredCapabilities.android();
        //capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("platformName", "ANDROID");
        //capabilities.setCapability("platformVersion", "10");
        //capabilities.setCapability("deviceName", "RF8N111C7ZM");
        
        capabilities.setCapability("platformVersion", "8");
        capabilities.setCapability("deviceName", "FRTBF80903007481");
        capabilities.setCapability("launchTimeout", 120000);
 
        //URL url = new URL("http://localhost:4729/wd/hub"); //IP Address of Appium Server
        URL url = new URL("http://localhost:4729/wd/hub"); //IP Address of Selenium Hub
        
        
        
        androidDriver = new AndroidDriver<AndroidElement>(url, capabilities) ;
        androidDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        androidDriver.findElementByAccessibilityId("Agenda").click(); // il faut que l'app soit sur la page

        // androidDriver.findElementByAccessibilityId("Boutique Amazon").click(); // il faut que l'app soit sur la page
        /*WebElement textBox = androidDriver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text"));
        textBox.clear();
        textBox.sendKeys("Harry Potter et le Prince de Sang Mêlé");
        androidDriver.pressKey(new KeyEvent(AndroidKey.ENTER));
        //androidDriver.findElement(By.id("com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text")).click();

        System.out.println("passed");*/
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
        
        //on libere la session
        androidDriver.quit();
	}
 
	private void execute(final DesiredCapabilities capability) throws MalformedURLException 
	{	
		WebDriver driver = new RemoteWebDriver(new URL(nodeUrl), capability);

		driver.get("http://www.amazon.fr/");

		// aller au panier
		//driver.findElement(By.id("nav-cart")).click();

		// effectuer une recherche pour un livre Harry Potter
		WebElement textBox = driver.findElement(By.id("twotabsearchtextbox"));
		textBox.sendKeys("Harry Potter et le Prince de Sang Mêlé");
		//driver.findElement(By.className("nav-input")).click();
		driver.findElement(By.className("nav-input")).sendKeys(Keys.ENTER);
		//driver.findElement(By.id("nav-search-submit-text")).click();
		
		//driver.findElement(By.className("nav-search-submit nav-sprite")).click();
		//driver.findElement(By.xpath("//span[@id='nav-search-submit-text']")).click();

		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// on libère la session
		//driver.quit();
	}
	


	
	  /*@Test 
	  public void executeChrome() throws MalformedURLException 
	  {
	  //this.execute(DesiredCapabilities.chrome());
	  System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
	  
	  this.nodeUrl = "http://localhost:4444/wd/hub"; 
	  WebDriver driver = new RemoteWebDriver(
			  new URL(nodeUrl), DesiredCapabilities.chrome());
	  
	  driver.get("http://www.amazon.fr/");
	  
	  //aller au panier //driver.findElement(By.id("nav-cart")).click();
	  
	  //effectuer une recherche pour un livre Harry Potter 
	  WebElement textBox = driver.findElement(By.id("twotabsearchtextbox"));
	  textBox.sendKeys("Harry Potter et le Prince de Sang Mêlé");
	  driver.findElement(By.className("nav-input")).click();
	  
	  //on libère la session 
	  driver.quit(); 
	  
	  
	  }*/
	 

	/*@Test
	public void executeFirefox() throws MalformedURLException {
		this.nodeUrl = "http://localhost:4444/wd/hub"; 
		  WebDriver driver = new RemoteWebDriver(
				  new URL(nodeUrl), DesiredCapabilities.firefox());
		  
		  driver.get("http://www.amazon.fr/");
		  
		  //aller au panier //driver.findElement(By.id("nav-cart")).click();
		  
		  //effectuer une recherche pour un livre Harry Potter 
		  WebElement textBox = driver.findElement(By.id("twotabsearchtextbox"));
		  textBox.sendKeys("Harry Potter et le Prince de Sang Mêlé");
		  driver.findElement(By.className("nav-input")).click();
		  
		  //on libère la session 
		  driver.quit(); 
		
	}*/
	
	/*
	@Test
	public void executeIE() throws MalformedURLException {
		this.nodeUrl = "http://localhost:4444/wd/hub"; 

          this.driver = new RemoteWebDriver(new URL(nodeUrl), DesiredCapabilities.internetExplorer());
          
          driver.get("http://www.amazon.fr/");
          
        //effectuer une recherche pour un livre Harry Potter 
		  WebElement textBox = driver.findElement(By.id("twotabsearchtextbox"));
		  textBox.sendKeys("Harry Potter et le Prince de Sang Mêlé");
		  
		  driver.findElement(By.className("nav-search-submit nav-sprite")).click();
		  
		  driver.findElement(By.className("nav-input")).click();
		  
		  //avec la ligne suivante ne fonctionne pas sur IE, car 
		  //certains clicks natifs ne fonctionnent pas sur IE 
		  
		  //on libère la session 
		  driver.quit(); 
		
	}*/
	

	

}
