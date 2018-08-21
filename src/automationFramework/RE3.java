package automationFramework;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

interface RE3 {
	public default void Upload_Release(int releaseType, int ipType, int technology, int ip, int ipFlavor, String tarballName, String releaseVersion, String releasePdk, String releaseDir, String customerDir,String readmeDir, String bugzillaId, boolean isQa, int qaId, int ipView)
			throws InterruptedException, AWTException {
		WebDriver driver4 = new ChromeDriver();
		new FLOW_3().Login(driver4, FLOW_3.EmployeeURL, "invcss.testre@yopmail.com", "Welcome@123");
		driver4.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[2]/a")).click();
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/tabs/a[1]")).click();
		Thread.sleep(500);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/tabs/a[1]/ul/li[1]/a")).click();
		List<WebElement> list = driver4.findElements(By.tagName("tr"));
		String firstID = list.get(0).getText();
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[2]/a")).click();
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/tabs/a[2]")).click();
		Thread.sleep(1000);
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[1]/inv-select/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(500);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[1]/inv-select/div/div[1]/div[2]/select-option-list/select-option["+releaseType+"]")).click();
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[1]/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(500);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[1]/div/div[1]/div[2]/select-option-list/select-option["+ipType+"]")).click();
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[2]/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(500);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[2]/div/div[1]/div[2]/select-option-list/select-option["+technology+"]")).click();
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[3]/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(500);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[3]/div/div[1]/div[2]/select-option-list/select-option["+ip+"]")).click();
		Thread.sleep(1000);				
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[4]/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(500);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[4]/div/div[1]/div[2]/select-option-list/select-option["+ipFlavor+"]")).click();
		Thread.sleep(1000);
		driver4.findElement(By.xpath("//*[@id='tarballname']")).sendKeys(tarballName);
		Thread.sleep(1000);
		driver4.findElement(By.xpath("//*[@id='rversion']")).sendKeys(releaseVersion);
		Thread.sleep(1000);				
		driver4.findElement(By.xpath("//*[@id='releasepdk']")).sendKeys(releasePdk);
		Thread.sleep(1500);
		driver4.findElement(By.xpath("//*[@id='ipReleaseDirPath']")).sendKeys(releaseDir);
		Thread.sleep(1500);
		driver4.findElement(By.xpath("//*[@id='ipCustomerDirPath']")).sendKeys(customerDir);
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[5]/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(500);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/inv-select[5]/div/div[1]/div[2]/select-option-list/select-option["+ipView+"]")).click();
		Thread.sleep(1000);			
		driver4.findElement(By.xpath("//*[@id='readmeFilePath']")).sendKeys(readmeDir);
		Thread.sleep(1000);	
		driver4.findElement(By.xpath("//*[@id='bugzillaId']")).sendKeys(bugzillaId);
		Thread.sleep(1000);				
		driver4.findElement(By.xpath("//*[@id='releaseInfo']")).sendKeys("given releaseInfo");
		if(isQa) {
			driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/div[1]/inv-checkbox/div/label/label")).click();
		}
		else if(qaId!=0) {
				driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/div[1]/inv-select/div/div[1]/div[1]/div[2]")).click();
				driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/div[1]/inv-select/div/div[1]/div[2]/select-option-list/select-option["+qaId+"]")).click();
		}
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/div/form/div[2]/div[3]/div/div[2]/button")).click();
		Thread.sleep(1000);
		driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/new-release/modal-popup/div/div/div[3]/div/div[2]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver4, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre")));
		Thread.sleep(500);
		if (driver4.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre")).getText()
				.contains("Release has been added successfully")) {
			driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/tabs/a[1]")).click();
			Thread.sleep(500);
			driver4.findElement(By.xpath("/html/body/invecas-css/home/releases/div/div/div/tabs/a[1]/ul/li[1]/a"))
					.click();
			List<WebElement> list1 = driver4.findElements(By.tagName("tr"));
			if (firstID.equalsIgnoreCase(list1.get(0).getText())) {
				Assert.assertTrue(false);
				// System.out.println("Release Already uploaded");
			} else {
				new FLOW_3();
				FLOW_3.PR_Number = list1.get(0).getText().substring(0, 9);
				new FLOW_3();
				System.out.println("Release Uploaded Successfully with PR_Number=" + FLOW_3.PR_Number);
			}
		} else {
			System.out.println("Release already uploaded previously.");
		}
		driver4.close();
	}
}