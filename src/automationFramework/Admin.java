package automationFramework;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

interface Admin {
	public abstract void openPrivateWindow(WebDriver driver) throws AWTException;

	public default void Admin_login_Upload_Datasheet(WebDriver driver5, String email, String password, int iPProduct,
			int technology, int dropdownNum, int check,String name) throws InterruptedException, AWTException {
		new FLOW_3().Login(driver5, FLOW_3.EmployeeURL, email, password);
		admin_upload_datasheet(driver5, iPProduct, technology, dropdownNum, check,name);
	}

	public default void admin_upload_datasheet(WebDriver driver1, int ipType, int technology, int ipName, int ipFlavor,String name)
			throws InterruptedException {

		driver1.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[4]/a")).click();
		Thread.sleep(3000);
		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/documents/div/div/add-new-datasheet/div/form/div/div[1]/inv-select/div/div[1]/div[1]/div[2]"))
				.click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/documents/div/div/add-new-datasheet/div/form/div/div[1]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
						+ ipType + "]"))
				.click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/documents/div/div/add-new-datasheet/div/form/div/div[2]/inv-select/div/div[1]/div[1]/div[2]"))
				.click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/documents/div/div/add-new-datasheet/div/form/div/div[2]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
						+ technology + "]"))
				.click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/documents/div/div/add-new-datasheet/div/form/div/div[3]/inv-select/div/div[1]/div[1]/div[2]"))
				.click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/documents/div/div/add-new-datasheet/div/form/div/div[3]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
						+ ipName + "]"))
				.click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/documents/div/div/add-new-datasheet/div/form/div/div[4]/inv-select/div/div[1]/div[1]/div[2]"))
				.click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/documents/div/div/add-new-datasheet/div/form/div/div[4]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
						+ ipFlavor + "]"))
				.click();
		Thread.sleep(1500);
		driver1.findElement(By.id("displayName")).sendKeys(name);
//		System.out.println("Given display name " + n);
		driver1.findElement(By.id("docInfo")).sendKeys(name);

//		driver1.findElement(By.id("attachment")).sendKeys("/home/mshaik/Music/sample.pdf");
		driver1.findElement(By.id("attachment")).sendKeys("/home/mshaik/Music/sample.pdf");

		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/documents/div/div/add-new-datasheet/div/form/div/div[9]/div/div[2]/button"))
				.click();
		WebDriverWait wait = new WebDriverWait(driver1, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre")));

		String msg = driver1.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre"))
				.getText();
		System.out.println(msg);

	}

	public default void admin_upload_productbrief(WebDriver driver1,int ipType,int technology,int ipName,String name) throws InterruptedException
	{
		
		driver1.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[4]/a")).click();
		Thread.sleep(3000);
		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/tabs/a[2]")).click();
		Thread.sleep(2000);
		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/app-add-new-product-brief/div/form/div/div[1]/inv-select/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/app-add-new-product-brief/div/form/div/div[1]/inv-select/div/div[1]/div[2]/select-option-list/select-option["+ipType+"]")).click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/app-add-new-product-brief/div/form/div/div[2]/inv-select/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/app-add-new-product-brief/div/form/div/div[2]/inv-select/div/div[1]/div[2]/select-option-list/select-option["+technology+"]")).click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/app-add-new-product-brief/div/form/div/div[3]/inv-select/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(1500);
		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/app-add-new-product-brief/div/form/div/div[3]/inv-select/div/div[1]/div[2]/select-option-list/select-option["+ipName+"]")).click();
		Thread.sleep(1500);	
//		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/app-add-new-product-brief/div/form/div/div[4]/inv-select/div/div[1]/div[1]/div[2]")).click();
//		Thread.sleep(1500);
//		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/app-add-new-product-brief/div/form/div/div[4]/inv-select/div/div[1]/div[2]/select-option-list/select-option[1]")).click();
//		Thread.sleep(1500);		

		driver1.findElement(By.id("displayName")).sendKeys(name);
		driver1.findElement(By.id("docInfo")).sendKeys(name);
													
		driver1.findElement(By.id("attachment")).sendKeys("/home/mshaik/Music/sample.pdf");
		driver1.findElement(By.xpath("/html/body/invecas-css/home/documents/div/div/app-add-new-product-brief/div/form/div/div[8]/div/div[2]/button")).click();
		WebDriverWait wait = new WebDriverWait(driver1, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre")));
	
	String msg=driver1.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre")).getText();
	System.out.println(msg);
	
	}

}
