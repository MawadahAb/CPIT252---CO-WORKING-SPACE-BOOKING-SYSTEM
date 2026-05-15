public class Customer {

    private String fullName;
    private String phoneNumber;
    private String email;
    private int numberOfPeople;

    public Customer(String fullName, String phoneNumber, String email, int numberOfPeople) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.numberOfPeople = numberOfPeople;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getContactInfo() {
        return fullName + " | " + phoneNumber + " | " + email;
    }
}