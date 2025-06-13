package packagetest3.com.hexa.hquery;

import Service.StudentService;

public class App 
{
    public static void main(String[] args)
    {
        StudentService service = new StudentService();

//        service.saveData();
//        service.removeData();
//        service.updateData();
//        service.searchData();
//        service.searchByName();
        service.showData();
    }
}
