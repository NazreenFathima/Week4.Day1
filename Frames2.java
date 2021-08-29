package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//1.Take the the screenshot of the click me button of first frame
		WebElement frame = driver.findElement(By.xpath("//iframe[@src='default.html']"));
		driver.switchTo().frame(frame);
		WebElement clickMe = driver.findElement(By.id("Click"));
		File src = clickMe.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/ClickMe.png");
		FileUtils.copyFile(src, dst);
		driver.switchTo().defaultContent();
		
		//2.Find the number of frames
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println("Count of the frames visible to the main page is : " + frames.size());

	}

}
