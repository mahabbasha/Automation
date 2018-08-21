package automationFramework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Root extends FLOW_3 {
	@BeforeSuite
	public void preRequisites() {
		driver = new ChromeDriver();// Driver1(Browser-1)
		driver.manage().window().maximize();
		cust = new Customer3();
	}

	@Test(priority = 1)
	public void Customer_RegistrationApproval() throws AWTException, InterruptedException {
		WebDriver driver1 = new ChromeDriver();
		new Regi2().BD_Registration_Approval(driver1);
		driver1.quit();
	}

	@Test(priority = 2)
	public void CustomerLogin() throws InterruptedException, AWTException {
		mail = Regi2.customer_Email;
		Customer = Regi2.customer_Username;
		Company = Regi2.customer_Company;
		password = Regi2.customer_Password;
		cust.Customer_login(driver, mail, password);
	}

	@Test(priority = 3)
	public void IPRequest() throws InterruptedException {
		IPNumber = cust.IPRequest(driver, technology, dropdownNum, check, IPProduct, today_Date);// driver,technology,dopdownNum,IPProduct(1-F,2-A,3-I),date
																									// }
	}

	@Test(priority = 4)
	public void BD_Login_And_Ip_Approval() throws AWTException, InterruptedException {
		bd = new FLOW_3();
		bd.openPrivateWindow(driver);
		bd.BD_loginPlusIPApproval(driver, bdMail, bdPassword, IPNumber, today_Date, Company, new FLOW_3().gf_Process);
		//driver.quit();
	}

	@Test(priority = 5)
	public void Check_License_History() throws InterruptedException {
		//driver = new ChromeDriver();
		bd = new FLOW_3();
	/*	try {
			new FLOW_3().Login(driver, EmployeeURL, bdMail, bdPassword);
		} catch (AWTException e) {
			e.printStackTrace();
		}*/
		bd.check_LicenceHistory(driver, Company, IPNumber);
	}

	@Test(priority = 6)
	public void ChangeRoleToLicensed() throws InterruptedException, AWTException {
		bd1 = new FLOW_3();
		Thread.sleep(1000);
		//new FLOW_3().Login(driver, EmployeeURL, bdMail, bdPassword);
		bd1.changeRoleToLicensed(driver, Company, Customer);
		Thread.sleep(1000);
	}

	@Test(priority = 7)
	public void Admin_Login_Datasheet_Upload() throws InterruptedException, AWTException {
		Admin admin = new FLOW_3();
		WebDriver driver5 = new ChromeDriver();
		admin.Admin_login_Upload_Datasheet(driver5, Admin_Mail, adminPassword, IPProduct, technology, dropdownNum,
				check,name);
		driver5.close();
	}

	@Test(priority = 8)
	public void BD_Approve_Datasheet() throws AWTException, InterruptedException {
		bd1 = new FLOW_3();
		//new FLOW_3().Login(driver, EmployeeURL, bdMail, bdPassword);
		bd1.gotoPendingDatasheet(driver, IPProduct, technology, dropdownNum,name);
		bd1.gotoDatasheet(driver, Company, IPProduct, technology, dropdownNum,name);
//		bd1.gotoDatasheet(driver, "sdhfus", IPProduct, technology, dropdownNum,name);
	}

	@Test(priority = 9)
	public void Datasheet_Download_By_Customer() throws InterruptedException, AWTException {
		WebDriver driver6 = new ChromeDriver();
		//new Customer3().Customer_login(driver6, "akhila.adorable5@gmail.com","Welcome@1234");
		new Customer3().Customer_login(driver6, mail,password);
		new Customer3().datasheetDownload(driver6, IPProduct, technology, dropdownNum,name);
		driver6.close();
	}

	@Test(priority = 9)
	public void Admin_Login_ProductBrief_Upload() throws InterruptedException, AWTException {
		Admin admin = new FLOW_3();
		WebDriver driver5 = new ChromeDriver();
		new FLOW_3().Login(driver5, FLOW_3.EmployeeURL, Admin_Mail, adminPassword);
		admin.admin_upload_productbrief(driver5, IPProduct, technology, dropdownNum,name);
		driver5.close();
		bd1 = new FLOW_3();
		WebDriver driver6 = new ChromeDriver();
		new FLOW_3().Login(driver6, EmployeeURL, bdMail, bdPassword);
		bd1.gotoPendingProductBrief(driver6, IPProduct, technology, dropdownNum, name);
		driver6.close();
	}
	
	@Test(priority = 10)
	public void ProductBrief_Download_By_Customer() throws InterruptedException, AWTException {
		WebDriver driver6 = new ChromeDriver();
		new Customer3().Customer_login(driver6, mail, password);
		new Customer3().productBriefDownload(driver6, IPProduct, technology, dropdownNum,name);
		driver6.close();
	}


	@Test(priority = 11)
	public void RE_Release_Upload() throws InterruptedException, AWTException {
		FLOW_3 f = new FLOW_3();
		//Upload_Release(int releaseType, int ipType, int technology, int ip, int ipFlavor, String tarballName, String releaseVersion, String releasePdk, String releaseDir, String customerDir,String readmeDir, String bugzillaId, boolean isQa, int qaId, String ipView)
		Upload_Release(f.releaseType, f.IPProduct, f.technology, f.dropdownNum, f.check, f.tarballName, f.releaseVersion, f.releasePdk, f.Release_Directory_Path, 
				f.customerDir,f.Readme_File_Path, f.bugzillaId, f.isQa,f.qaId,f.ipView);
	}

	@Test(priority = 12)
	public void approveIP() throws InterruptedException, AWTException {
		ap = bd1.approveRelease(driver, Company, Customer, today_Date, IPProduct, technology, IPProduct, dropdownNum,
				check);// dri,comp,cust,date,IPType,Technology
		if (ap == 0) {
			System.out.println("No Releases Available");
			// Assert.assertTrue(false);
		}
	}

	@Test(priority = 13)
	public void CMLoginAndReleaseApproval() throws InterruptedException, AWTException {
		if (ap == 1) {
			driver.quit();
			cm = new FLOW_3();
			driver3 = new ChromeDriver();// Driver2
			cm.cm_login(driver3);
			cm.cm_Approval(driver3, IPNumber, Company);
			cmStatus = 1;
		} else {
			System.out.println("No Releases Available for CM Approve");
			AssertJUnit.assertTrue(false);
		}
	}
	@Test(priority = 14)
	public void CustomerDownloads() throws AWTException, InterruptedException {
		if (cmStatus != 0) {
			cm.openPrivateWindow(driver3);
			cust.Customer_login(driver3, mail, password);
			cust.check_Downloads(driver3, IPNumber);
		} else {
			AssertJUnit.assertTrue(false);
		}
		driver3.quit();
	}
}
