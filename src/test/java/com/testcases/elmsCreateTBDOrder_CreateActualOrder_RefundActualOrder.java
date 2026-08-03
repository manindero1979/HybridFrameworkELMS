package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.models.Credentials;
import com.pages.login_page;
import com.pages.logout_page;
import com.pages.order_backendorder_page;
import com.utility.ExcelUtils;
import com.utility.PropertyUtils;

//engp-609
public class elmsCreateTBDOrder_CreateActualOrder_RefundActualOrder extends BaseClass {
	login_page lp = null;
	order_backendorder_page beo = null;
	logout_page logpage = null;

	// @BeforeClass//(alwaysRun = true)
	@Test(priority = 1)
	public void setup() throws Exception {
		intialization();
		System.out.println("webdriver intialized");
		lp = new login_page(driver);
		log.info("************** Opening URL *****************");
	}

	@Test(priority = 2)
	public void login() throws Exception {

	    log.info("************** Verifying Login Test *****************");

	    Credentials admin = ExcelUtils.getCredentials("elms", "admin");

	    lp.loginToelms(admin.getUsername(), admin.getPassword());

	    Assert.assertEquals(driver.getTitle(), "Dashboard / Magento Admin");

	}


	@Test(priority = 3)
	public void CreateTBDOrder() throws Exception {
		beo = new order_backendorder_page(driver);
		log.info("----------------TBD order creation will start-------------");
		Thread.sleep(2000);// added on 31st jan 23 for preprod ins, after this it worked fine
		beo.clickOnOrdersMenu();
		Thread.sleep(1000);
		
		beo.selectCreateTBDOrderLink();
		Thread.sleep(2000);
		beo.generateInvoiceId();
		beo.createTBDOrder();
		Thread.sleep(2000);
		
	}
	@Test(priority = 4)
	public void CreateTBDActualOrder() throws Exception {
		log.info("----------------Actual order creation will start-------------");
		beo.clickCreateActualOrderbtn();
		beo.generateCustomerWorkEmail();
		beo.createNewCustomer();
		Thread.sleep(5000);
		beo.searchCustomerEmail();
		Thread.sleep(3000);// ---
		beo.selectSearchedCustomer();
		Thread.sleep(5000);// ----
		beo.selectProductFromDrpdown();
		Thread.sleep(5000);// ----
		beo.searchEventProduct();
		Thread.sleep(5000);// ---------
		beo.clickOnProductResult();
		Thread.sleep(2000);
		beo.addProductQty("1");
		Thread.sleep(1000);
		beo.clickOKButton();
		Thread.sleep(5000);// ---
		beo.clickAddSelectedProductToOrderBtn();
		
		Thread.sleep(10000);
		beo.clicOnAddRegistrantsBtn();
		Thread.sleep(3000);
		beo.generateNewRegistrantsEmail();
		beo.createNewRegistrant();
		Thread.sleep(1000);

		beo.saveRegistrant();
		Thread.sleep(3000);
		beo.setFreeShipping();
		Thread.sleep(5000);
		beo.setInternalPaymentoption();//this is set when we want to refund order
		
		Thread.sleep(5000);
		beo.order_submit();
	
	}
	@Test(priority = 5)
	public void CheckTBDActualOrder() throws Exception {
		log.info("----------------Verification of TBD order-Actual Order will start-------------");
		beo.clickOnOrdersMenu();
		Thread.sleep(1000);
		beo.clickTbdOrdersLink();
		Thread.sleep(2000);
		
		beo.clearAllTBDOrderFilterLink();
		Thread.sleep(2000);
		beo.clickOnTBDOrderFiltersBtn();
		Thread.sleep(1000);
		beo.setInvoiceIdTBDOrderFilter();
		beo.clickTBDOrderApplyfilterBtn();
		Thread.sleep(3000);
		beo.openFilteredTBDOrder();
		Thread.sleep(5000);
		//beo.sortandOpenTbdOrder();
		beo.verifyOrderinGrid();
	}
	@Test(priority = 6)
	public void openActualOrderAndRefundit() throws Exception {
		log.info("----------------REfund of Actual order will start-------------");
		beo.openActualOrderDetailspage();
		Thread.sleep(3000);	
		beo.clickCreditMemoLink();
		Thread.sleep(3000);
		beo.setItemsToRefund();
		Thread.sleep(3000);
		beo.setOrderDetails();
		Thread.sleep(2000);
		beo.clickRefundOfflineButton();
		Thread.sleep(10000);
		beo.confirmOrderRefund();
		Thread.sleep(2000);
		beo.backToOriginalWindow();
		beo.refreshTBDOrder();	
		Thread.sleep(2000);
		beo.checkQtyOfRefundActualOrderAndtakeScreenshot();
	}
	
	
	@Test(priority = 10)
	public void logout() throws Exception {
		log.info("----------------ELMS Logout will start-------------");
		logpage = new logout_page(driver);
		logpage.elmsLogoutApplication();
	
	}
	
}

