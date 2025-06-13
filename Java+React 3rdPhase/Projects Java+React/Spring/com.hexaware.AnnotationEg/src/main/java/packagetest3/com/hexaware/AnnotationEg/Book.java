package packagetest3.com.hexaware.AnnotationEg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Book implements InitializingBean, DisposableBean {
	int code;
	String name;
	int price;
	List <String> cityList;
	Map<String, Integer> sales = new HashMap<String, Integer>();
	
	Book() {
		
	}

	public Book(int code, String name, int price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public List<String> getCityList() {
		return cityList;
	}

	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}

	public Map<String, Integer> getSales() {
		return sales;
	}

	public void setSales(Map<String, Integer> sales) {
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "Book [code=" + code + ", name=" + name + ", price=" + price + ", cityList=" + cityList + ", sales="
				+ sales + "]";
	}

	void init() {
		System.out.println("Starting ....");
	}
	
	public void destroy() throws Exception {
		System.out.println("Ending ......");
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting ....");
	}
}

