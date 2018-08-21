package automationFramework;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

interface GF3 {
	public default void gf_Approval(WebDriver driver3, String IPNumber) throws InterruptedException, AWTException {
		new FLOW_3().Login(driver3, FLOW_3.EmployeeURL, "invcss.testgf@yopmail.com", "Welcome@123");
		driver3.findElement(By.cssSelector(
				"body > invecas-css > home > dashboard > div > div.col-md-9 > div > div > dash-box > div > div.content > div.tar.more > btn > a"))
				.click();
		Thread.sleep(1000);
		int num = 1;
		String IPNumber1 = driver3.findElement(By.xpath(
				"/html/body/invecas-css/home/gf/div/div/div/gfreqlist/div/div/div/table/tbody/tr[" + num + "]/td[1]"))
				.getText();
		if (!IPNumber.equalsIgnoreCase(IPNumber1)) {
			for (int i = 1; i <= 10; i++) {
				IPNumber1 = driver3.findElement(
						By.xpath("/html/body/invecas-css/home/gf/div/div/div/gfreqlist/div/div/div/table/tbody/tr[" + i
								+ "]/td[1]"))
						.getText();
				Thread.sleep(500);
				System.out.println("IPNumber1=" + IPNumber1);
				if (IPNumber.equalsIgnoreCase(IPNumber1)) {
					Thread.sleep(1000);
					num = i;
					break;
				}
			}
		}
		if (IPNumber.equalsIgnoreCase(IPNumber1)) {
			Thread.sleep(1000);
			System.out.println("k=" + Customer3.k);
			driver3.findElement(
					By.xpath("/html/body/invecas-css/home/gf/div/div/div/gfreqlist/div/div/div/table/tbody/tr[" + num
							+ "]/td[1]"))
					.click();
			Thread.sleep(2000);
			driver3.findElement(
					By.xpath("/html/body/invecas-css/home/gf/div/div/div/gfreqdetails/div[1]/div[2]/div[1]/a")).click();
			Thread.sleep(1000);
			driver3.findElement(By.cssSelector(
					"body > invecas-css > home > gf > div > div > div > gfreqdetails > modal-popup > div > div > div.foot > div > button"))
					.click();
			Thread.sleep(2000);
			if (driver3.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[1]/div"))
					.isDisplayed()) {
				System.out.println("GF Approved Successfully");
			} else {
				System.out.println("NO GF APPROVE MESSAGE");
			}
		} else {
			System.out.println("Something Went Wrong in GF");
		}
	}
}