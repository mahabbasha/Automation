package automationFramework;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

interface CM3 {
	public abstract void openPrivateWindow(WebDriver driver) throws AWTException;

	public default void cm_login(WebDriver driver1) throws InterruptedException, AWTException {

		new FLOW_3().Login(driver1, FLOW_3.EmployeeURL, "invcss.testcm@yopmail.com", "Welcome@123");
	}

	public default void cm_Approval(WebDriver driver1, String IPNumber, String company) throws InterruptedException {
		Thread.sleep(1500);
		driver1.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver1.findElement(By.xpath(
				"/html/body/invecas-css/home/bd-approved-releases/div/div/div/pending-release-list/div[1]/form/div[1]/div[1]/inv-select/div/div[1]/div[1]/div[2]"))
				.click();
		Thread.sleep(2000);
		List<WebElement> l = driver1.findElements(By.tagName("select-option"));
		int size = l.size();
		for (int j = 1; j < size; j++) {
			WebElement com = driver1.findElement(By.xpath(
					"/html/body/invecas-css/home/bd-approved-releases/div/div/div/pending-release-list/div[1]/form/div[1]/div[1]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ j + "]"));

			String s = com.getText();
			if (s.equalsIgnoreCase(company)) {
				com.click();
				break;
			}
		}
		Thread.sleep(1000);
		driver1.findElement(By.xpath("/html/body/invecas-css/home/bd-approved-releases/div/div/div/pending-release-list/div[1]/form/div[4]/div[1]/button")).click();
		Thread.sleep(1000);
		try {
			driver1.findElement(By.xpath(
					"/html/body/invecas-css/home/bd-approved-releases/div/div/div/pending-release-list/div[1]/div/div/table/tbody/tr/td[1]/inv-checkbox/div/label/label"))
					.click();
			Thread.sleep(1000);
			driver1.findElement(By.cssSelector(
					"body > invecas-css > home > bd-approved-releases > div > div > div > pending-release-list > div.container.max1200.ng-trigger.ng-trigger-routerAnimation > div > div > div > a:nth-child(1)"))
					.click();
			Thread.sleep(1000);
			driver1.findElement(By.xpath(
					"/html/body/invecas-css/home/bd-approved-releases/div/div/div/pending-release-list/modal-popup/div/div/div[3]/div/button"))
					.click();
			Thread.sleep(6000);
			try {
				if (driver1.findElement(By.cssSelector("body > invecas-css > status-message > div > div.tac.fs15.p10"))
						.isDisplayed()) {
					System.out.println("Approved By CM");
					Thread.sleep(1000);
				} else {
					System.out.println("Some issue in CM Approval");
				}
			} catch (Exception e) {
				System.out.println("OOPS");
			}
		} catch (Exception e) {
			System.out.println("There are no Ip releases available for the selected Company i.e," + company);
		}
	}
}
