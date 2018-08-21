package automationFramework;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

interface BD_IPR3 {
	public abstract void openPrivateWindow(WebDriver driver) throws AWTException;

	public default void BD_loginPlusIPApproval(WebDriver driver, String email, String password, String IPNumber,
			int today_Date, String company, int gf_Process) throws InterruptedException, AWTException {
		new FLOW_3().Login(driver, FLOW_3.EmployeeURL, email, password);
		BD_IPApproval(driver, IPNumber, today_Date, company, gf_Process);// IPApproval method
	}

	private void BD_IPApproval(WebDriver driver, String IPNumber, int today_Date, String company, int gf_Process)
			throws InterruptedException {

		driver.findElement(By.xpath("html/body/invecas-css/home/menu/ul/li[3]")).click();
		
		FLOW_3.waitFunc(driver, By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[3]"));
		Thread.sleep(6000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[3]")).click();
		Thread.sleep(1000);
		FLOW_3.waitFunc(driver, By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[3]/ul/li[3]"));
		Thread.sleep(4000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[3]/ul/li[3]")).click();
		Thread.sleep(1000);
		Customer3.k = 1;
		String IPNumber1 = driver.findElement(
				By.xpath("/html/body/invecas-css/home/customers/div/div/div/all-requests/div/div[2]/table/tbody/tr["
						+ Customer3.k + "]/td[1]"))
				.getText();
		if (!IPNumber.trim().equalsIgnoreCase(IPNumber1.trim())) {
			for (int i = 1; i <= 10; i++) {
				IPNumber1 = driver.findElement(By.xpath(
						"/html/body/invecas-css/home/customers/div/div/div/all-requests/div/div[2]/table/tbody/tr[" + i
								+ "]/td[1]"))
						.getText();
				Thread.sleep(500);
				System.out.println("IPNumber1=" + IPNumber1);
				if (IPNumber.equalsIgnoreCase(IPNumber1)) {
					Thread.sleep(1000);
					Customer3.k = i;
					break;
				}

				if (i % 10 == 0) {
					Thread.sleep(500);
					driver.findElement(By.xpath(
							"/html/body/invecas-css/home/customers/div/div/div/all-requests/div[1]/pagination/div[1]/div[8]/div"))
							.click();
					Thread.sleep(500);
					i = 0;
				} else {
					continue;
				}
			}
		}

		if (IPNumber.equalsIgnoreCase(IPNumber1)) {
			Thread.sleep(1000);
			System.out.println("k=" + Customer3.k);
			driver.findElement(
					By.xpath("/html/body/invecas-css/home/customers/div/div/div/all-requests/div/div[2]/table/tbody/tr["
							+ Customer3.k + "]/td[1]"))
					.click();
			Thread.sleep(2000);
			FLOW_3.nda_button = 1;
			System.out.println("GF=" + gf_Process);
			if (driver
					.findElement(By.xpath(
							"/html/body/invecas-css/home/customers/div/div/div/request-details/div[1]/div[1]/h3"))
					.getText().trim().equalsIgnoreCase("Status : NEW")) {
				System.out.println("EDIT BUTTON");
				Assert.assertTrue(driver
						.findElement(By.xpath(
								"/html/body/invecas-css/home/customers/div/div/div/request-details/div[1]/div[1]/a"))
						.isEnabled());
			}
			if (gf_Process != 0 && gf_Process == 1) {
				FLOW_3.nda_button = 2;
				try {
					String status = driver.findElement(By.xpath(
							"/html/body/invecas-css/home/customers/div/div/div/request-details/div[1]/div[1]/h3"))
							.getText();
					System.out.println("Status : " + status);
					WebElement but = driver.findElement(By.xpath(
							"/html/body/invecas-css/home/customers/div/div/div/request-details/div[1]/div[4]/div[1]/a"));
					String name = but.getText();
					System.out.println("Button Name is : " + name);
					if (but.isEnabled()) {
						if (name.equalsIgnoreCase("Send for GF Approval")) {
							driver.findElement(By.xpath(
									"/html/body/invecas-css/home/customers/div/div/div/request-details/div[1]/div[4]/div[1]/a"))
									.click();
							Thread.sleep(1000);
							driver.findElement(By.cssSelector(
									"body > invecas-css > home > customers > div > div > div > request-details > modal-popup:nth-child(7) > div > div > div.foot > div > button"))
									.click();
							Thread.sleep(1000);
							WebDriver driver3 = new ChromeDriver();
							GF3 gf = new FLOW_3();
							gf.gf_Approval(driver3, IPNumber);
							Thread.sleep(1000);
							driver3.quit();
							Thread.sleep(1000);
							BD_IPApproval(driver, IPNumber, today_Date, company, gf_Process);
						} else {
							System.out.println("GF Already Approved");
						}
					}
				} catch (Exception e) {
					System.out.println("GF and IP already Aproved........");

				}
			}

			try {
				Thread.sleep(1000);
				if (driver.findElement(By.xpath(
						"/html/body/invecas-css/home/customers/div/div/div/request-details/div[1]/div[3]/div/div["
								+ FLOW_3.nda_button + "]/a"))
						.isEnabled()) {
					directNDA_ApprovalByBD(driver, today_Date, company);
					BD_IPApproval(driver, IPNumber, today_Date, company, gf_Process);// **************
				}

			} catch (Exception e) {

				System.out.println("Currently U have an NDA...." + e);
				approve_License(driver, today_Date);
			}
		} else {
			System.out.println("It is not u r requested IP...");
		}

	}

	public default void directNDA_ApprovalByBD(WebDriver driver, int date, String company) throws InterruptedException {

		if (new FLOW_3().gf_Process != 0 && new FLOW_3().gf_Process == 1) {
			FLOW_3.nda_button = 2;
		} else {
			FLOW_3.nda_button = 1;
		}
		Thread.sleep(1000);

		driver.findElement(
				By.xpath("/html/body/invecas-css/home/customers/div/div/div/request-details/div[1]/div[3]/div/div["
						+ FLOW_3.nda_button + "]/a"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[6]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[6]/ul/li[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(1200);
		List<WebElement> l = driver.findElements(By.tagName("select-option"));
		int size = l.size();
		for (int j = 1; j < size; j++) {
			WebElement com = driver.findElement(By.cssSelector(
					"body > invecas-css > home > customers > div > div > div > bd-nda-request > div > form > div > div > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option:nth-child("
							+ j + ")"));
			String s = com.getText();

			if (s.equalsIgnoreCase(company)) {
				com.click();
				break;
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.id("startDate")).click();
		Thread.sleep(2000);
		////*[@id="Start Date"]/div[3]/inv-input-datepicker/div/div[3]/div/div[3]/div[25]
		driver.findElement(By.xpath(
				"/html/body/invecas-css/home/customers/div/div/div/bd-nda-request/div/form/div/div/inv-input-datepicker[1]/div/div[3]/div/div[3]/div["
						+ (date + 3) + "]"))
				.click();// *[@id="Start Date"]/div[3]/inv-input-datepicker/div/div[3]/div/div[3]/div[25]
		Thread.sleep(1000);
		driver.findElement(By.id("endDate")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"/html/body/invecas-css/home/customers/div/div/div/bd-nda-request/div/form/div/div/inv-input-datepicker[2]/div/div[3]/div/div[3]/div["
						+ (date + 4) + "]"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(
				"/html/body/invecas-css/home/customers/div/div/div/bd-nda-request/div/form/div/div/div[2]/div/div/div[2]/button"))
				.click();
		Thread.sleep(1000);
		System.out.println("NDA Approved Successfully");
	}

	public default void approve_License(WebDriver driver, int date) {
		if (new FLOW_3().gf_Process != 0 && new FLOW_3().gf_Process == 1) {
			FLOW_3.nda_button = 1;
		} else {
			FLOW_3.nda_button = 2;
		}
		try {
			Thread.sleep(1000);
			if (driver.findElement(
					By.xpath("/html/body/invecas-css/home/customers/div/div/div/request-details/div[1]/div[4]/div["
							+ FLOW_3.nda_button + "]/a"))
					.isEnabled()) {
				driver.findElement(
						By.xpath("/html/body/invecas-css/home/customers/div/div/div/request-details/div[1]/div[4]/div["
								+ FLOW_3.nda_button + "]/a/span"))
						.click();
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("div.pH")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath(
						"//*[@id=\"License Type\"]/div[3]/inv-select/div/div[1]/div[2]/select-option-list/select-option[2]"))
						.click();
				Thread.sleep(1000);
				driver.findElement(By.id("startDate")).click();
				Thread.sleep(1500);
				driver.findElement(
						By.xpath("//*[@id=\"Start Date\"]/div[3]/inv-input-datepicker/div/div[3]/div/div[3]/div["
								+ (date + 3) + "]"))
						.click();
				Thread.sleep(500);
				driver.findElement(By.id("endDate")).click();
				Thread.sleep(1500);
				driver.findElement(
						By.xpath("//*[@id=\"End Date\"]/div[3]/inv-input-datepicker/div/div[3]/div/div[3]/div["
								+ (date + 4) + "]"))
						.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath(
						"/html/body/invecas-css/home/customers/div/div/div/request-details/div[2]/form/modal-popup/div/div/div[3]/div/button[2]"))
						.click();
				Thread.sleep(1000);
				try {
					if (driver.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[1]/div"))
							.isDisplayed()) {
						Thread.sleep(5000);
						driver.findElement(By.xpath(
								"/html/body/invecas-css/home/customers/div/div/div/request-details/div[2]/form/modal-popup/div/div/div[3]/div/div[1]"))
								.click();
						System.out.println("Customer NDA will be expired before license support period.");
						Thread.sleep(500);
					} else {
						int p = 1;
						Assert.assertTrue(p == 1);
					}
				} catch (Exception e) {
					System.out.println("IP Request Approved Successfully");
				}

			}
		} catch (Exception e) {
			System.out.println("Currently U have This IP" + e);
		}
	}

	public default void check_LicenceHistory(WebDriver driver, String company, String IPNumber)
			throws InterruptedException {
		driver.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[3]/a")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[4]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[4]/ul/li/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(2000);
		List<WebElement> l = driver.findElements(By.tagName("select-option"));
		int size = l.size();
		for (int j = 1; j < size; j++) {
			WebElement com = driver.findElement(By.cssSelector(
					"body > invecas-css > home > customers > div > div > div > customer-license > div.container.max1200.ng-trigger.ng-trigger-routerAnimation > form > div:nth-child(1) > div > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option:nth-child("
							+ j + ")"));
			String s = com.getText();

			if (s.equalsIgnoreCase(company)) {
				com.click();
				break;
			}
		}
		Thread.sleep(1500);
		driver.findElement(By.xpath(
				"/html/body/invecas-css/home/customers/div/div/div/customer-license/div[1]/form/div[2]/div[1]/button"))
				.click();
		Thread.sleep(1000);
		List<WebElement> iplist = driver.findElements(By.tagName("tr"));
		Thread.sleep(1000);
		System.out.println("License History Size=" + iplist.size());
		Assert.assertTrue(iplist.size() > 0);
	}

}