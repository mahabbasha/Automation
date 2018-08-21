package automationFramework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.awt.AWTException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Regi2 {
	static String customer_Email,customer_Username,customer_Password,customer_Company;
	WebDriver driver;
	@DataProvider(name = "addMethodDataProvider")
	public Object[][] dataProvider() {
		Object data1[][];
		data1 = new Object[][] { { "firstname", "lastname", "jenkinsautomate02", "Welcome@123", "jenkinsautomate02@yopmail.com", "4234234",
				"jenkinscompany02", "title09", "address", "abcd.com", "guntur", "23323", "1", "2" } };
		return data1;
	}
	@Test(dataProvider = "addMethodDataProvider")
	public void regi(String[] ar) throws InterruptedException, AWTException {
		driver = new ChromeDriver();
		driver.get("https://invcss.invecas.com/css/#/login/customer");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		String first_tab = driver.getWindowHandle();
		WebElement img = driver.findElement(By.xpath(
				"/html/body/invecas-css/registration/pre-sign-in-wrap/div/div[1]/form/div[1]/div/div[19]/span/img"));
		String src = img.getAttribute("src");
		System.out.println(src);
		String regex1 = "captchaString=(.*)";
		Matcher matcher1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE).matcher(src);
		String encrypt = "";
		String decrypted_Captcha = "";
		while (matcher1.find()) {
			encrypt = matcher1.group(1);
			System.out.println(matcher1.group(1));
		}
		driver.findElement(By.id("firstName")).sendKeys(ar[0]);
		driver.findElement(By.id("lastName")).sendKeys(ar[1]);
		driver.findElement(By.id("userName")).sendKeys(ar[2]);
		customer_Username=ar[2];
		driver.findElement(By.id("password")).sendKeys(ar[3]);
		customer_Password=ar[3];
		driver.findElement(By.id("confirmPassword")).sendKeys(ar[3]);
		driver.findElement(By.id("email")).sendKeys(ar[4]);
		customer_Email = ar[4];
		driver.findElement(By.id("confirmEmail")).sendKeys(ar[4]);
		driver.findElement(By.id("organization")).sendKeys(ar[6]);
		customer_Company=ar[6];
		driver.findElement(By.id("title")).sendKeys(ar[7]);
		driver.findElement(By.id("address1")).sendKeys(ar[8]);
		driver.findElement(By.id("address2")).sendKeys(ar[9]);
		driver.findElement(By.id("city")).sendKeys(ar[10]);
		driver.findElement(By.id("zip")).sendKeys(ar[11]);
		driver.findElement(By.cssSelector("div.pH")).click();
		String c = ar[12];
		Thread.sleep(1000);
		driver.findElement(By.xpath("*//select-option[" + c + "]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("phone")).sendKeys(ar[5]);
		driver.findElement(By.cssSelector("div.pH")).click();
		String c1 = ar[13];
		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"/html/body/invecas-css/registration/pre-sign-in-wrap/div/div[1]/form/div[1]/div/div[14]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
						+ c1 + "]"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("label.btn-cb.fs11 > label")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"div.reg-modal > modal-popup > div.overlay > div.content > div.foot > div.tar > div.btn.small"))
				.click();
		// Thread.sleep(10000);
		decrypted_Captcha = new FLOW_3().get_Captcha(driver, encrypt, first_tab);
		driver.switchTo().window(first_tab);
		// Thread.sleep(10000);
		driver.findElement(By.id("txtinput")).sendKeys(decrypted_Captcha);
		// driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		driver.findElement(By.cssSelector("button.btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("html/body/invecas-css/registration/modal-popup/div/div/div[3]/div/div[2]"))
				.click();
		Thread.sleep(2000);
		WebElement we = driver
				.findElement(By.xpath("html/body/invecas-css/registration-status/pre-sign-in-wrap/div/div[1]/p[1]"));
		Thread.sleep(1000);
		AssertJUnit.assertTrue(we.isDisplayed());
		driver.quit();
		Thread.sleep(2000);
	}
	
	public void BD_Registration_Approval(WebDriver driver) throws AWTException, InterruptedException {
		FLOW_3 f=new FLOW_3();
		f.Login(driver, FLOW_3.EmployeeURL, f.bdMail, f.bdPassword);
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[3]/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/div/a[5]")).click();Thread.sleep(1500);
		WebElement username=driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/pending-emails/div[1]/div[2]/div/div/table/tbody/tr[1]/td[2]"));
		if(username.getText().trim().equalsIgnoreCase(customer_Username)) {
			driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/pending-emails/div[1]/div[2]/div/div/table/tbody/tr[1]/td[1]/inv-checkbox/div/label/label")).click();
			Thread.sleep(500);
			driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/pending-emails/div[1]/div[1]/div/a")).click();Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/pending-emails/div[3]/modal-popup/div/div/div[3]/div/button")).click();
			Thread.sleep(1000);
			AssertJUnit.assertEquals(driver.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre")).getText().trim(), "User Email has been confirmed successfully");
			Thread.sleep(5000);
			driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/div/a[2]")).click();
			//WebDriverWait wait=new WebDriverWait(driver,20);
			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/users/div[1]/div[2]/div[2]/div/table"))));
			Thread.sleep(5000);
			username=driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/users/div[1]/div[2]/div[2]/div/table/tbody/tr[1]/td[2]/a"));
			if(username.getText().trim().equalsIgnoreCase(customer_Username)) {
				driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/users/div[1]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/inv-checkbox/div/label/label")).click();
				Thread.sleep(500);
				driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/users/div[1]/div[2]/div[1]/div[1]/a")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/users/div[3]/modal-popup/div/div/div[3]/div/button")).click();
				Thread.sleep(1000);
				AssertJUnit.assertEquals(driver.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre")).getText().trim(), "User has been approved successfully");
				driver.quit();
			}
		}
	}
}