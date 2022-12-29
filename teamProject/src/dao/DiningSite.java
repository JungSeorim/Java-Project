package dao;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DiningSite {

	WebDriver webDriver;
	public static final String WEB_DRIVER_PATH = "C:/chromedriver.exe";
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";

	public void diningmethod(String address) {
		Scanner sc = new Scanner(System.in);
		DiningSite dining = new DiningSite();
		WebDriver driver = dining.webDriver;
		driver = new ChromeDriver();
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//		����ϰ� �ϱ� ���� ��ȣ�� �ڿ� ��ȣ �����ϴ� �޼ҵ� �־��
		Method key = new Method();
		
//		�ϰ� ó�� �κ�
		String url = "https://www.diningcode.com/search?";
		List<WebElement> storesList = null, list = null;
		WebElement rist1to5 = null, searchInput = null;
		String location = "", menu = "", name = "", locationAndMenu = "",score = "", diningInfo = "";
		int number = 0;
		
//		����Ʈ ����
		driver.get(url);// ���̴� ����Ʈ �ּ�
		try {Thread.sleep(3000);} catch (InterruptedException e) {;}
		
//		�˻�â�� ķ���� �ּ� �˻�
		searchInput = driver.findElement(By.className("searchInput"));
		searchInput.click();
		searchInput.sendKeys(address);
		searchInput.sendKeys(Keys.RETURN);
		try {Thread.sleep(2000);} catch (InterruptedException e) {;}
		
//		��õ ������ ��ϰ�������
		rist1to5 = driver.findElement(By.className("RList"));
		storesList = rist1to5.findElements(By.className("RHeader"));
		
		for (WebElement store : storesList) {
//			�Ĵ� �̸�
			name = store.findElement(By.className("InfoHeader")).getText();
			
//			�Ĵ� ��ġ�� �޴�
			list = store.findElements(By.cssSelector("p.Category"));
			locationAndMenu += list.get(0).getText();

//			�Ĵ� ��ġ �и�
			location = locationAndMenu.substring(0, locationAndMenu.indexOf("\n"));
			
//			�޴� �и�
			menu = locationAndMenu.substring(locationAndMenu.indexOf("\n")).replace("\n", "").replace("/", "");

//			���� ������
			score = store.findElement(By.className("Score")).getText();
			
//			��� �ϰ� ó��
			diningInfo = ++number + ". ��ȣ�� : " + key.removeNumber(name) + "\n��ġ : " + location + "\n�޴� : " + menu + "\n���� : " + score + "\n----------------------------------------";
			
//			�Ĵ��� ������ ����ִ� ��� ���
			System.out.println(diningInfo);
		}

	}

}