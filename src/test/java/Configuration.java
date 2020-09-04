import org.testng.annotations.BeforeSuite;

import java.io.File;

import static io.restassured.path.json.JsonPath.from;

public class Configuration {
    private String name;
    private String position;
    private String secondPosition;
    private String baseURL;

    public Configuration() {
        try {
            File fJson = new File("config.json");
            name = from(fJson).getString("name");
            position = from(fJson).getString("position");
            secondPosition = from(fJson).getString("secondPosition");
            baseURL = from(fJson).getString("baseURL");
            System.out.println("Successful configuration from a file config.json!");
        } catch (Throwable e){
            System.out.println("Error reading the file config.json!");
            name = "TestUser";
            position = "QA Automation Engineer";
            secondPosition = "Senior QA Automation Engineer";
            baseURL = "https://reqres.in";
        }
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getSecondPosition() {
        return secondPosition;
    }

    public String getBaseURL() {
        return baseURL;
    }
}
