package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowFrames {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		//Step1: Load ServiceNow application URL given above
		driver.get("https://dev113545.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Step2: Enter username as admin
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.switchTo().defaultContent();
		
		//Step3: Enter password as w6hnF2FRhwLC
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_password")).sendKeys("w6hnF2FRhwLC");
		driver.switchTo().defaultContent();
		
		//Step4: Click Login
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sysverb_login")).click();
		
		Thread.sleep(1000);
		
		//Step5: Search “incident “ Filter Navigator
		driver.findElement(By.id("filter")).sendKeys("incident", Keys.ENTER);
		
		//Step6: Click “All”
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		
		//Step7: Click New button
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sysverb_new")).click();
		
		
		//Step8: Select a value for Caller and Enter value for short_description
		driver.findElement(By.xpath("(//span[@class='icon icon-search'])[1]")).click();
		
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		driver.switchTo().window(windowHandlesList.get(0));
		
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys("Short Description");
		
		//Step9: Read the incident number and save it a variable
		String incidentNum = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println(incidentNum);
		
		//Step10: Click on Submit button
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		driver.switchTo().defaultContent();
		
		//Step 11: Search the same incident number in the next search screen as below
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(incidentNum, Keys.ENTER);
		
		//Step12: Verify the incident is created successful and take snapshot of the created incident.
		String verifyIncident = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		
		if (incidentNum.equals(verifyIncident))
		System.out.println("Incident created successfully");
		else
			System.out.println("Incident creation unsuccessful");
		
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/Screenshot1.png");
		FileUtils.copyFile(src, dst);
	
	}

}
