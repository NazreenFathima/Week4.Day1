package week4.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandling {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		 //1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		 //2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		 
		 //3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		 
		 //4. Click on CRM/SFA Link
		driver.findElement(By.xpath("//a[contains(text(),'CRM')]")).click();
		
		 //5. Click on contacts Button
		driver.findElement(By.xpath("//a[contains(text(),'Contact')]")).click();
		
		//6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[contains(text(),'Merge Contacts')]")).click();
		
		//7. Click on Widget of From Contact
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[1]")).click();
		
		//8. Click on First Resulting Contact
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		
		driver.findElement(By.xpath("(//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first ']//a)[1]")).click();
		
		Thread.sleep(2000);
		driver.switchTo().window(windowHandlesList.get(0));
		
		//9. Click on Widget of To Contact
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click();
		
		//Click on Second Resulting Contact
		
		Set<String> windowHandlesSet1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandlesSet1);
		driver.switchTo().window(windowHandlesList1.get(1));
		
		driver.findElement(By.xpath("(//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first ']//a)[2]")).click();
		Thread.sleep(2000);
		driver.switchTo().window(windowHandlesList.get(0));
		
		
		//11. Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("(//a[contains(text(),'Merge')])[2]")).click();
		
		//12. Accept the Alert
		driver.switchTo().alert().accept();
		
		//13. Verify the title of the page
		String title = driver.getTitle();
		String verifyTitle = "View Contact | opentaps CRM";
		//System.out.println(title);
		if(verifyTitle.contains(title))
			System.out.println("Landed in correct page");
		else
			System.out.println("Landed in incorrect page");
		
		
		
	}

}
