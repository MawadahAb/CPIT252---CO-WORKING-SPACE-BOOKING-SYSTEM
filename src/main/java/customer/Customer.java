package customer;

public class Customer {

    private String fullName;
    private String phoneNumber;
    private int numberOfPeople;

    public Customer(String fullName, String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.numberOfPeople = numberOfPeople;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getContactInfo() {
        return fullName + " | " + phoneNumber;
    }
}
