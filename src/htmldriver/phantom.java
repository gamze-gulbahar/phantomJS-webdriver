package htmldriver;	
import java.io.File;
import java.util.List;
import java.lang.Thread;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class phantom {		
		static String fileWithPath = "screenshots";
		public static void getKargoPrice() throws Exception {
			File file = new File("/Users/gamze/Downloads/phantomjs-2.1.1-macosx/bin/phantomjs");				
            System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
            WebDriver driver = new PhantomJSDriver();
            driver.get("https://www.mediamarkt.com.tr/tr/product/_81ut00d8tx-%C4%B1deapad-s145-ryzen3-3200u-8gb-ram-256gb-ssd-vega3-graphics-15-6-hd-w10-laptop-silver-1209294.html");         
            WebElement e = driver.findElement(By.xpath("//*[@id=\"pdp-add-to-cart\"]/span"));
            takeSnapShot(driver,fileWithPath+ "/mediamarkt");
            e.click();
            Thread.sleep(5000);
            WebElement t = driver.findElement(By.xpath("//*[@id=\"basket-flyout-cart\"]/span"));
            takeSnapShot(driver,fileWithPath+ "/sepet");
            //System.out.println(t.getText());
            t.click();    
            Thread.sleep(5000);
           // System.out.println(driver.getPageSource());
            List <WebElement> kargo = driver.findElements(By.cssSelector(".cototals-label.ng-binding"));
            List <WebElement> value = driver.findElements(By.cssSelector(".cototal-value.ng-binding"));
            
            takeSnapShot(driver,fileWithPath+ "/approvedsepet");
           
            for(WebElement k:kargo) {
            	if(k.getText().equals("Kargo Ücreti")) {
            		System.out.println(k.getText() + ": " + value.get(1).getText() );
            	}
            }
            
            driver.quit();
            
		}
		
		public static void getIP() throws Exception {
			
			File file = new File("/Users/gamze/Downloads/phantomjs-2.1.1-macosx/bin/phantomjs");				
            System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
            WebDriver driver = new PhantomJSDriver();	
            driver.get("http://httpbin.org/get");       
            WebElement jsonEl = driver.findElement(By.tagName("pre"));
            String json = jsonEl.getText();
            JSONObject obj = new JSONObject(json);
            String origin = obj.getString("origin");
            
            takeSnapShot(driver,fileWithPath + "/json");
            System.out.println(origin);
			
		}
		
		public static void takeSnapShot(WebDriver driver,String fileWithPath) throws Exception{

	        TakesScreenshot scrShot =((TakesScreenshot)driver);
	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	        File DestFile=new File(fileWithPath);
	        FileUtils.copyFile(SrcFile, DestFile);

	    }
		public static void main(String[] args) throws Exception {
        	        
			getKargoPrice();
            getIP();
                                 			
           }	
}