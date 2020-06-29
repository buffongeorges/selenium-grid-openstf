package com.example.seleniumAndStfTesting.stf;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.seleniumAndStfTesting.models.DeviceApi;
import com.example.seleniumAndStfTesting.models.STFService;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AndroidTest {
    private static final String STF_SERVICE_URL = "http://localhost:7100";  // Change this URL
    private static final String ACCESS_TOKEN = "e334457w444ue2342b6e5b8e5e0e723423488487b5cfe3";  // Change this access token
    

    private AndroidDriver androidDriver;
    private String deviceSerial = "FRTBF80903007481";
    private AppiumDriverLocalService service;
    private DeviceApi deviceApi;

    private void createAppiumService()
    {
    	
    	//Set Capabilities
    	DesiredCapabilities cap = new DesiredCapabilities();
    	cap.setCapability("noReset", "false");
    	
    	//Build the Appium service
    	AppiumServiceBuilder builder = new AppiumServiceBuilder();
    	builder.withIPAddress("127.0.0.1");
    	builder.usingPort(4723);
    	builder.withCapabilities(cap);
    	builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
    	builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
    	
    	//Start the server with the builder
    	service = AppiumDriverLocalService.buildService(builder);
    	service.start();
    	
    }

    private void connectToStfDevice() throws MalformedURLException, URISyntaxException 
    {
        STFService stfService = new STFService(STF_SERVICE_URL,
                ACCESS_TOKEN);
        this.deviceApi = new DeviceApi(stfService);
        this.deviceApi.connectDevice(this.deviceSerial);
    }

    @BeforeClass
    public void setup() throws MalformedURLException, URISyntaxException 
    {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "ANDROID");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        desiredCapabilities.setCapability("udid", this.deviceSerial);

        connectToStfDevice();
        createAppiumService();
        androidDriver = new AndroidDriver(this.service.getUrl(), desiredCapabilities);
    }

    @Test
    public void scrollingToSubElement() {
        androidDriver.findElementByAccessibilityId("Agenda").click();
    }

    @AfterClass
    public void tearDown() {
        if (androidDriver != null) {
            androidDriver.quit();
        }

        if (this.service.isRunning()) {
            service.stop();
            this.deviceApi.releaseDevice(this.deviceSerial);
        }
    }
}
