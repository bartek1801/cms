package pl.com.bottega.cms.domain.commands;

public class CreateCinemaCommand implements Command{
    private String name, city;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
