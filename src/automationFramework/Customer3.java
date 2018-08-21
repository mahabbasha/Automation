package automationFramework;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class Customer3 {
	String email;
	String password;
	public static int k = 1;

	public void Customer_login(WebDriver driver, String email, String password)
			throws InterruptedException, AWTException {
		this.email = email;
		this.password = password;

		new FLOW_3();
		new FLOW_3().Login(driver, FLOW_3.CustomerURL, email, password);
	}

	public String IPRequest(WebDriver driver, int technology, int dropdownNum, int check, int IPProduct, int date)
			throws InterruptedException {
		driver.findElement(By.xpath("html/body/invecas-css/home/menu/ul/li[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("html/body/invecas-css/home/my-requests/div/div/tabs/a[2]")).click();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("*//select-option[" + technology + "]")).click();// 22FDX
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[2]/inv-select/div/div/div[2]/select-option-list/select-option")).click();// 55LP
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[3]/inv-select/div/div/div[2]/select-option-list/select-option")).click();// IP
																													// License
																													// Only
		Thread.sleep(1000);
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("/html/body/invecas-css/home/my-requests/div/div/new-request/div/form/block[2]/div[3]/h4["
						+ IPProduct + "]/div/div[1]"))
				.click();
		Thread.sleep(1000);
		driver.findElement(
				By.xpath("/html/body/invecas-css/home/my-requests/div/div/new-request/div/form/block[2]/div[3]/div["
						+ (IPProduct * 2 + 1) + "]/div/div[" + dropdownNum + "]/inv-select/div/div[1]/div[1]/div[2]"))
				.click();// Low Power Memories
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[3]/div[" + (IPProduct * 2 + 1) + "]/div/div[" + dropdownNum
				+ "]/inv-select/div/div[1]/div[2]/select-option-list/select-option[" + check + "]")).click();
		driver.findElement(By.cssSelector("label.btn-cb.fs11 > label")).click();
		Thread.sleep(1000);
		List<WebElement> a = driver.findElements(By.xpath("//*[@id=\'endDate_0\']"));
		a.get(0).click();
		driver.findElement(By.xpath(
				"/html/body/invecas-css/home/my-requests/div/div/new-request/div/form/block[3]/div[3]/div[3]/div/div/div[2]/inv-input-datepicker/div/div[3]/div/div[3]/div["
						+ (date + 3) + "]"))
				.click();

		driver.findElement(By.cssSelector(
				"body > invecas-css > home > my-requests > div > div > new-request > div > form > div.tac > div > button"))
				.click();
		Thread.sleep(2000);
		try {
			if (driver.findElement(By.xpath("/html/body/invecas-css/home/my-requests/div/div/request-list/div/h2"))
					.isDisplayed())
				;
			{
				Thread.sleep(2000);
				System.out.println("IP Requested successfully");
			}
		} catch (Exception e) {
			System.out.println("U already requested this IP....");
			Thread.sleep(5000);
		}
		driver.findElement(By.xpath("/html/body/invecas-css/home/my-requests/div/div/tabs/a[1]")).click();
		Thread.sleep(2000);
		String IPNumber = driver.findElement(By.cssSelector(
				"body > invecas-css > home > my-requests > div > div > request-list > div > div.table-wrapper > table > tbody > tr:nth-child(1) > td:nth-child(1) > a"))
				.getText();
		System.out.println("IPNumber=" + IPNumber);
		Thread.sleep(2000);
		return IPNumber;
	}

	public void check_Downloads(WebDriver driver1, String IPNumber) throws InterruptedException {
		driver1.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[4]")).click();
		Thread.sleep(1000);

		try {
			WebElement download = driver1.findElement(By.cssSelector(
					"body > invecas-css > home > downloads > div > div > available-releases > div > div.table-wrapper > a"));
			if (download.isEnabled()) {

				List<WebElement> l = driver1.findElements(By.tagName("tr"));
				int size = l.size();
				System.out.println("size is: " + size);
				for (int i = 0; i < size; i++) {
					String IP = driver1.findElement(By.cssSelector(
							"body > invecas-css > home > downloads > div > div > available-releases > div > div.table-wrapper > table > tbody > tr:nth-child("
									+ (i + 1) + ") > td:nth-child(1)"))
							.getText();
					if (IP.equalsIgnoreCase(IPNumber)) {
						String Path = driver1.findElement(By.cssSelector(
								"body > invecas-css > home > downloads > div > div > available-releases > div > div.table-wrapper > table > tbody > tr:nth-child("
										+ (i + 1) + ") > td:nth-child(5)"))
								.getText();
						System.out.println("Download is Available for your IP " + IP + " with download path: " + Path);
						download.click();
						Thread.sleep(2000);
						System.out.println("ASPERA is opened");
						driver1.quit();
						break;
					} else {
						System.out.println(IP);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("No Download is Available");
		}

	}

	public void datasheetDownload(WebDriver driver,int ipType,int technology,int ipName,String name) throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[2]/a")).click();
		Thread.sleep(3000);
		List<WebElement> datasheetPath=driver.findElements(By.xpath("/html/body/invecas-css/home/catalog/div/div/products/div[1]/div["+technology+"]/block/div[3]/table["+ipType+"]/tbody/tr["+ipName+"]/td[4]/ul/*"));
		
		for(int i=0;i<datasheetPath.size();i++) {
		System.out.println(i+"  "+datasheetPath.get(i).getText());//datasheetPath.size()-1
		
		if(datasheetPath.get(i).getText().contains(name)) {
			datasheetPath.get(i).click();
			break;
			}
		}
		Thread.sleep(8000);
	}
	
	public void productBriefDownload(WebDriver driver,int ipType,int technology,int ipName,String name) throws InterruptedException
	{
		driver.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[2]/a")).click();
		Thread.sleep(3000);
		List<WebElement> productBriefPath=driver.findElements(By.xpath("/html/body/invecas-css/home/catalog/div/div/products/div[1]/div["+technology+"]/block/div[3]/table["+ipType+"]/tbody/tr["+ipName+"]/td[3]/ul/*"));
		for(int i=0;i<productBriefPath.size();i++) {
			System.out.println(productBriefPath.get(i).getText());
			if(productBriefPath.get(i).getText().contains(name)) {
				productBriefPath.get(i).click();
				break;
			}
		}
		Thread.sleep(8000);
	}
	
}
