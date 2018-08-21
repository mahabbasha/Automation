package automationFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

interface BD_Release3 extends BD_IPR3 {
	String technology = null;

	public default void changeRoleToLicensed(WebDriver driver, String company, String customer)
			throws InterruptedException {
		driver.findElement(By.xpath("html/body/invecas-css/home/menu/ul/li[3]")).click();// customers
		Thread.sleep(6000);
		// driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/manage-users/div/div/a[1]")).click();
		// Thread.sleep(1000);
		// driver.findElement(By.linkText("Manage Users")).click();
		// Thread.sleep(1000 driver.get);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(2000);
		List<WebElement> l = driver.findElements(By.tagName("select-option"));
		int size = l.size();
		for (int j = 1; j < size; j++) {
			WebElement com = driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/manage-users/div/users/div[1]/form/div[1]/div[1]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ j + "]"));
			String s = com.getText();

			if (s.trim().equalsIgnoreCase(company.trim())) {
				com.click();
				break;
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.row.foot > div.submit.col-md-2.col-sm-2.mT10 > button")).click();// CheckBox
		Thread.sleep(1000);
		List<WebElement> l1 = driver.findElements(By.tagName("tr"));
		int size1 = l1.size();
		System.out.println("No.of users=" + size1);
		for (int j = 1; j <= size1; j++) {
			WebElement user = driver.findElement(
					By.cssSelector("div.table-wrapper > table > tbody > tr:nth-child(" + j + ") > td:nth-child(2)"));
			String s = user.getText();
			System.out.println("User" + j + "=" + s);
			if (s.equalsIgnoreCase(customer)) {
				WebElement role = driver.findElement(By
						.cssSelector("div.table-wrapper > table > tbody > tr:nth-child(" + j + ") > td:nth-child(5)"));
				String s1 = role.getText();
				System.out.println(s + " is a " + s1);
				if (s1.equalsIgnoreCase("Licensed Customer")) {
					System.out.println(
							"Selected Customer is already Licensed Customer. \n Role of Licensed Customer is not able to Modify");
					break;
				} else {
					driver.findElement(
							By.cssSelector("tr:nth-child(" + j + ") >td.check > inv-checkbox > div > label > label"))
							.click();
					driver.findElement(By.cssSelector("div.bg3.p10 > a:nth-child(3)")).click();// ChangeRole button
					Thread.sleep(1000);
					driver.findElement(By.xpath("//div[@id='Role Name']/div[3]/inv-select/div/div/div/div[2]")).click();
					Thread.sleep(6000);
					driver.findElement(By.cssSelector(
							"#Role\\20 Name > div.card-content.fs14 > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option:nth-child(3)"))
							.click();
					Thread.sleep(2500);
					driver.findElement(
							By.xpath("//*[@id=\"Select Category\"]/div[3]/inv-select/div/div[1]/div[1]/div[2]"))
							.click();// Select Category
					Thread.sleep(1000);
					driver.findElement(By.cssSelector(
							"#Select\\20 Category > div.card-content.fs14 > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option"))
							.click();
					Thread.sleep(1000);
					driver.findElement(By.cssSelector("div.sel-wrap.multiple > div.select > div.pH")).click();
					Thread.sleep(1000);
					driver.findElement(By.cssSelector(
							"#Select\\20 jira\\20 projects > div.card-content.fs14 > inv-select > div > div.sel-wrap.multiple.open > div.optionsWrap > select-option-list > select-option:nth-child(1)"))
							.click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//div[2]/div/modal-popup/div/div/div[3]/div/div[2]")).click();
					Thread.sleep(10000);
					System.out.println("Role changed to Licensed successfully");
					break;
				}
			}

		}
	}

	public default void gotoPendingDatasheet(WebDriver driver, int IPProduct, int technology, int dropdownNum,
			String name) {
		driver.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[3]/a")).click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//customers/div/div/div/tabs/a[7]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[7]/ul/li[3]/a")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[1]/form/div/div[1]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[1]/form/div/div[1]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ IPProduct + "]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[1]/form/div/div[2]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[1]/form/div/div[2]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ technology + "]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[1]/form/div/div[3]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[1]/form/div/div[3]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ dropdownNum + "]"))
					.click();

			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[1]/form/div/div[5]/div/div[2]/button"))
					.click();
			Thread.sleep(1000);

			List<WebElement> sheet = driver.findElements(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[2]/div[2]/table/tbody/*/td[1]/inv-checkbox/div/label/label"));
			List<WebElement> displaynames = driver.findElements(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[2]/div[2]/table/tbody/*/td[4]"));
			// driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/div[1]/table/tbody/tr[1]/td[1]/inv-checkbox/div/label/label")).click();
			System.out.println("-----------" + sheet.size() + "----------");

			for (int i = 0; i < sheet.size(); i++) {
				System.out.println(i + "  " + sheet.get(i).getText());// datasheetPath.size()-1

				if (displaynames.get(i).getText().contains(name)) {
					sheet.get(i).click();
					break;
				}
			}

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[1]/div[2]/div[1]/div/a"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-datasheets-list/div[2]/modal-popup/div/div/div[3]/div/button"))
					.click();

			Thread.sleep(2000);
			String st = driver.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre"))
					.getText();

			System.out.println(st);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public default void gotoDatasheet(WebDriver driver, String company, int ipType, int technology, int ipName,
			String name) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[3]/a")).click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//customers/div/div/div/tabs/a[7]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//customers/div/div/div/tabs/a[7]/ul/li/a")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/form/div/div[1]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/form/div/div[1]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ ipType + "]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/form/div/div[2]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/form/div/div[2]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ technology + "]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/form/div/div[3]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/form/div/div[3]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ ipName + "]"))
					.click();

			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/form/div/div[5]/div/div[2]/button"))
					.click();

			Thread.sleep(1000);

			List<WebElement> sheet = driver.findElements(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/div[1]/table/tbody/*/td[1]/inv-checkbox/div/label/label"));
			List<WebElement> displaynames = driver.findElements(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div[1]/div[1]/table/tbody/*/td[4]"));
			// driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/div[1]/table/tbody/tr[1]/td[1]/inv-checkbox/div/label/label")).click();
			System.out.println("-----------" + sheet.size() + "----------");

			for (int i = 0; i < sheet.size(); i++) {
				System.out.println(i + "  " + sheet.get(i).getText());// datasheetPath.size()-1

				if (displaynames.get(i).getText().contains(name)) {
					sheet.get(i).click();
					break;
				}
			}

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/div[2]/form/div[1]/div[1]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);

			List<WebElement> comElement = driver.findElements((By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/div[2]/form/div[1]/div[1]/inv-select/div/div[1]/div[2]/select-option-list/*")));
			// SAVE ALL THE OPTIONS

			for (int i = 0; i < comElement.size(); i++) {

				// companyForNda.add(comElement.get(i).getText());
				// System.out.println(i+" "+comElement.get(i).getText());
				if (comElement.get(i).getText().equals(company)) {
					// System.out.println("===================="+comElement.get(i).getText());
					comElement.get(i).click();
					break;
				}
				// PRINT ALL THE OPTIONS
			}
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/div[2]/form/div[1]/div[2]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/div[2]/form/div[1]/div[2]/inv-select/div/div[1]/div[2]/select-option-list/select-option[1]"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/div[2]/form/div[2]/div[2]/button"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/modal-popup/div/div/div[3]/div/div[2]"))
					.click();
			Thread.sleep(2000);
			String st = driver.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre"))
					.getText();

			System.out.println(st);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public default int approveRelease(WebDriver driver, String company, String customer, int today_Date, int IPType,
			int technology, int IPProduct, int dropdownNum, int check) throws InterruptedException {
		driver.findElement(By.xpath("html/body/invecas-css/home/menu/ul/li[3]")).click();// customers
		Thread.sleep(6000);
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[5]")).click();// Releases
		Thread.sleep(1000);
		driver.findElement(By.linkText("Approve a Release")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(2000);
		List<WebElement> l = driver.findElements(By.tagName("select-option"));
		int size = l.size();
		for (int j = 1; j < size; j++) {
			WebElement com = driver.findElement(By.cssSelector(
					"body > invecas-css > home > customers > div > div > div > approve-release > div.container.max1200 > form > div:nth-child(1) > div:nth-child(1) > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option:nth-child("
							+ j + ")"));
			String s = com.getText();
			if (s.equalsIgnoreCase(company)) {
				com.click();
				break;
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(2000);
		List<WebElement> l1 = driver.findElements(By.tagName("select-option"));
		int size1 = l1.size();
		for (int j = 1; j < size1; j++) {
			WebElement com = driver.findElement(By.cssSelector(
					"body > invecas-css > home > customers > div > div > div > approve-release > div.container.max1200 > form > div:nth-child(1) > div:nth-child(2) > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option:nth-child("
							+ j + ")"));
			String s = com.getText();
			if (s.equalsIgnoreCase(customer)) {
				com.click();
				break;
			}
		}
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(
				"body > invecas-css > home > customers > div > div > div > approve-release > div.container.max1200 > form > div:nth-child(2) > div:nth-child(1) > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option:nth-child("
						+ IPType + ")"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(
				"body > invecas-css > home > customers > div > div > div > approve-release > div.container.max1200 > form > div:nth-child(2) > div:nth-child(2) > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option:nth-child("
						+ technology + ")"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(
				"body > invecas-css > home > customers > div > div > div > approve-release > div.container.max1200 > form > div:nth-child(2) > div:nth-child(3) > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option:nth-child("
						+ dropdownNum + ")"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div.pH")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(
				"body > invecas-css > home > customers > div > div > div > approve-release > div.container.max1200 > form > div:nth-child(3) > div:nth-child(1) > inv-select > div > div.sel-wrap.open > div.optionsWrap > select-option-list > select-option:nth-child("
						+ check + ")"))
				.click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(
				"body > invecas-css > home > customers > div > div > div > approve-release > div.container.max1200 > form > div:nth-child(4) > div.col-md-2.mB20 > button"))
				.click();
		Thread.sleep(2000);
		try {
			if (driver.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[1]/div"))
					.isDisplayed()) {
				System.out.println("There is no active NDA for selected customer");
				BD_IPR3 bdipr = new FLOW_3();
				bdipr.directNDA_ApprovalByBD(driver, today_Date, company);
				System.out.println("NDA Approved Successfully");
				approveRelease(driver, company, customer, today_Date, IPType, technology, IPProduct, dropdownNum,
						check);
			}
		} catch (Exception e) {
			System.out.println("Already have an NDA");
		}
		try {
			if (driver
					.findElement(
							By.xpath("/html/body/invecas-css/home/customers/div/div/div/approve-release/div[1]/div"))
					.isDisplayed()) {
				// String
				// IP=driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/approve-release/div[1]/div/table/tbody/tr[1]/td[2]")).getText();/////////////////////////
				driver.findElement(By.xpath(
						"/html/body/invecas-css/home/customers/div/div/div/approve-release/div[1]/div/table/tbody/tr[2]/td[1]/inv-checkbox/div/label/label"))
						.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/approve-release/div[1]/div/div/a")).click();
				Thread.sleep(2000);
				if (driver.findElement(By.cssSelector("body > invecas-css > home > header")).isDisplayed()) {
					System.out.println("IP Release is Approved by BD Just Wait for CM Approval");
				} else {
					// driver.findElement(By.cssSelector(("div.container.max1200 > div > table >
					// tbody > tr > td.tac > inv-checkbox > div > label > label")).click();
					System.out.println("Something Went Wrong");
				}
			}
		} catch (Exception e) {
			System.out.println("There are no releases to list.");// It is better to stop after this step execution
																	// instead of continue.
			return 0;
		}
		System.out.println("Releases are available for the requested IP");
		return 1;
	}

	public default void gotoPendingProductBrief(WebDriver driver, int ipType, int technology, int ipName, String name) {
		driver.findElement(By.xpath("/html/body/invecas-css/home/menu/ul/li[3]/a")).click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//customers/div/div/div/tabs/a[7]")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/tabs/a[7]/ul/li[4]/a")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/form/div/div[1]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/form/div/div[1]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ ipType + "]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/form/div/div[2]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/form/div/div[2]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ technology + "]"))
					.click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/form/div/div[3]/inv-select/div/div[1]/div[1]/div[2]"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/form/div/div[3]/inv-select/div/div[1]/div[2]/select-option-list/select-option["
							+ ipName + "]"))
					.click();

			Thread.sleep(1000);
			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/form/div/div[5]/div/div[2]/button"))
					.click();

			Thread.sleep(1000);

			List<WebElement> sheet = driver.findElements(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/div[2]/table/tbody/*/td[1]/inv-checkbox/div/label/label"));
			List<WebElement> displaynames = driver.findElements(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/div[2]/table/tbody/*/td[4]"));
			// driver.findElement(By.xpath("/html/body/invecas-css/home/customers/div/div/div/app-enable-datasheets/div/div[1]/table/tbody/tr[1]/td[1]/inv-checkbox/div/label/label")).click();
			System.out.println("-----------" + sheet.size() + "----------");

			for (int i = 0; i < sheet.size(); i++) {
				System.out.println(i + "  " + sheet.get(i).getText());// datasheetPath.size()-1

				if (displaynames.get(i).getText().contains(name)) {
					sheet.get(i).click();
					break;
				}
			}

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[1]/div[1]/div/a/span"))
					.click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(
					"/html/body/invecas-css/home/customers/div/div/div/app-pending-product-briefs-list/div[2]/modal-popup/div/div/div[3]/div/button"))
					.click();

			Thread.sleep(2000);
			String st = driver.findElement(By.xpath("/html/body/invecas-css/status-message/div/div[2]/div[2]/div/pre"))
					.getText();

			System.out.println(st);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}