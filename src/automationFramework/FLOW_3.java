package automationFramework;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;*/
//import org.testng.asserts.SoftAssertion
public class FLOW_3 extends Customer3 implements BD_IPR3, BD_Release3, CM3, GF3, RE3, Admin {

	public static final String EmployeeURL = "https://invcss.invecas.com/css/#/login/employee";
	public static final String CustomerURL = "https://invcss.invecas.com/css/#/login/customer";
	public static List<String> menulist = Arrays.asList("Home", "Catalog", "My Requests", "Downloads", "Support",
			"Contact Us");
	static int nda_button = 1, captcha = 1;
	int gf_Process = 1;// 0 -> GF will not execute 1 -> GF will Execute
	WebDriver driver, driver3;
	String mail, Customer, Company , bdMail = "invcss.testbd1@yopmail.com",
			bdPassword = "Welcome@123", Admin_Mail = "invcss.testadmin@yopmail.com",adminPassword = "Welcome@123";
	String password;
	boolean isQa=false;
	int technology = 1;// 14LPP,22FDX,7LP
	int IPProduct = 1;// (or) IP Type->Foundation,Analog and
	int dropdownNum = 1;// standardcells,GPIO,etc....
	int check = 1;// 1.8v,7.5T,etc.....
	int releaseType=1;//NEW,UPDATE,PATCH
	int today_Date = 21;
	int ipView=1;
	int qaId=0;
	String tarballName="FOUNDATION_14LPP_STDLIB_7P5T_FE_NEW5",releaseVersion="REL_VER_012",releasePdk="PDK_VERSION_012",customerDir="FOUNDATION_IP/14LPP/PDKV-1.3/TEST",bugzillaId="T123456";
	Random random = new Random();
	int n = random.nextInt(50) + 1;
	final String name="downloadfile"+n;
	String IPNumber;
	Customer3 cust;
	BD_IPR3 bd;
	BD_Release3 bd1;
	int ap = 0, cmStatus = 0;
	CM3 cm;
	static String PR_Number;
	
	String Release_Directory_Path = "RE/22FDX/FOUNDATION_IP/PDK_VERSION_1.2_0.0/MEMORY/BASE/SMPV/FE/IN22FDX_MEMSMPV_COMPILER_FE_GFQAV02R60",
			Readme_File_Path = "RE/22FDX/FOUNDATION_IP/PDK_VERSION_1.2_0.0/MEMORY/BASE/SMPV/FE/IN22FDX_MEMSMPV_COMPILER_FE_GFQAV02R60/IN22FDX_MEMSMPV_COMPILER_FE_RELV02R60.md5sum";

	public void Login(WebDriver driver, String URL, String email, String password)
			throws AWTException, InterruptedException {
		String first_tab = driver.getWindowHandle();
		driver.get(URL);
		WebElement img = driver
				.findElement(By.xpath("/html/body/invecas-css/login/pre-sign-in-wrap/div/div[1]/form/div[1]/span/img"));
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
		driver.findElement(By.id("username")).sendKeys(email);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		if (FLOW_3.captcha == 1) {

			decrypted_Captcha = new FLOW_3().get_Captcha(driver, encrypt, first_tab);
			driver.switchTo().window(first_tab);
			// Thread.sleep(10000);
		}
		driver.findElement(By.id("txtinput")).sendKeys(decrypted_Captcha);
		driver.findElement(By.cssSelector("button.btn.disIB")).click();
		Thread.sleep(3000);
	}

	public static void waitFunc(WebDriver driver,By path) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(path));
	}
	public String get_Captcha(WebDriver driver, String encrypt, String first_tab)
			throws AWTException, InterruptedException {
		String decrypted_Captcha1 = "";
		((JavascriptExecutor) driver).executeScript("window.open('https://invcss.invecas.com/');");
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String next_tab = i1.next();
			if (!first_tab.equalsIgnoreCase(next_tab)) {
				WebDriver driver1 = driver.switchTo().window(next_tab);
				driver.get(
						"https://invcss.invecas.com/ims-web/register/decript/capthca/cssValidUser?isValid=true&captcha="
								+ encrypt);
				Thread.sleep(1000);
				decrypted_Captcha1 = driver.findElement(By.xpath("/html/body")).getText();
				System.out.println(decrypted_Captcha1);
				driver1.close();

			}
		}
		driver.switchTo().window(first_tab);
		// https://invcss.invecas.com/ims-web/register/decript/capthca/cssValidUser?isValid=true&captcha=
		return decrypted_Captcha1;
	}

	@Override
	public void openPrivateWindow(WebDriver driver) throws AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_N);
		// Switch focus to new tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
	/*
	 * @Test(priority=2) public void support_Menu() throws InterruptedException {
	 * Support support=new Support(); support.menu(driver);
	 * support.newCaseFile(driver); support.IPName.getText(); }
	 * 
	 * @Test(priority=3) public void check_Case_Details() throws
	 * InterruptedException { Support support1=new Support();
	 * support1.caseDetails(driver,"NEW"); }
	 * 
	 * @Test(priority=4) public void check_CaseDetailsAtBD() throws AWTException,
	 * InterruptedException { WebDriver driver6=new ChromeDriver(); new
	 * FLOW_3().Login(driver6, EmployeeURL, bdMail, bdPassword); Thread.sleep(2000);
	 * new Support().caseDetailsAtBD(driver6, "NEW"); driver6.close();
	 * driver.close(); }
	 */
	/*
	 * @Test(priority = 2) public void IPRequest() throws InterruptedException {
	 * IPNumber = cust.IPRequest(driver, technology, dropdownNum, check, IPProduct,
	 * today_Date);// driver,technology,dopdownNum,IPProduct(1-F,2-A,3-I),date }
	 * 
	 * @Test(priority = 3) public void BD_Login_And_Ip_Approval() throws
	 * AWTException, InterruptedException { bd = new FLOW_3();
	 * bd.openPrivateWindow(driver); bd.BD_loginPlusIPApproval(driver, bdMail,
	 * bdPassword, IPNumber, today_Date, Company, new FLOW_3().gf_Process); }
	 * 
	 * @Test(priority = 4) public void Check_License_History() throws
	 * InterruptedException { bd = new FLOW_3(); bd.check_LicenceHistory(driver,
	 * Company, IPNumber); }
	 * 
	 * @Test(priority = 5) public void ChangeRoleToLicensed() throws
	 * InterruptedException { bd1 = new FLOW_3(); Thread.sleep(1000);
	 * bd1.changeRoleToLicensed(driver, Company, Customer); Thread.sleep(1000); }
	 * 
	 * 
	 * @Test(priority=6) public void Admin_Login_Datasheet_Upload() throws
	 * InterruptedException, AWTException { Admin admin=new FLOW_3(); WebDriver
	 * driver5=new ChromeDriver(); admin.Admin_login_Upload_Datasheet(driver5,
	 * Admin_Mail, "Welcome@123456",IPProduct,technology,dropdownNum,check);
	 * driver5.close(); }
	 * 
	 * @Test(priority=7) public void BD_Approve_Datasheet() { bd1=new FLOW_3();
	 * bd1.gotoPendingDatasheet(driver, IPProduct, technology, dropdownNum);
	 * bd1.gotoDatasheet(driver, Company, IPProduct, technology, dropdownNum); }
	 * 
	 * @Test(priority=8) public void Datasheet_Download_By_Customer() throws
	 * InterruptedException, AWTException { WebDriver driver6=new ChromeDriver();
	 * new Customer3().Customer_login(driver6, mail, password); new
	 * Customer3().download_Datasheet(driver6); driver6.close(); }
	 * 
	 * @Test(priority = 9) public void RE_Release_Upload() throws
	 * InterruptedException, AWTException { FLOW_3 f = new FLOW_3();
	 * Upload_Release(f.technology, f.dropdownNum, f.check, f.IPProduct); }
	 * 
	 * @Test(priority = 10) public void approveIP() throws InterruptedException,
	 * AWTException { ap = bd1.approveRelease(driver, Company, Customer, today_Date,
	 * IPProduct, technology, IPProduct, dropdownNum, check);//
	 * dri,comp,cust,date,IPType,Technology if (ap == 0) {
	 * System.out.println("No Releases Available"); // Assert.assertTrue(false); } }
	 * 
	 * @Test(priority = 11) public void CMLoginAndReleaseApproval() throws
	 * InterruptedException, AWTException { if (ap == 1) { driver.quit(); cm = new
	 * FLOW_3(); driver3 = new ChromeDriver();// Driver2 cm.cm_login(driver3);
	 * cm.cm_Approval(driver3, IPNumber, Company); cmStatus = 1; } else {
	 * System.out.println("No Releases Available for CM Approve");
	 * Assert.assertTrue(false); driver.quit(); } }
	 * 
	 * @Test(priority = 12) public void CustomerDownloads() throws AWTException,
	 * InterruptedException { if (cmStatus != 0) { cm.openPrivateWindow(driver3);
	 * cust.Customer_login(driver3, mail, password); cust.check_Downloads(driver3,
	 * IPNumber); } else { Assert.assertTrue(false); } }
	 */
}
