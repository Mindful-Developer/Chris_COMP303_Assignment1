package com.spring.chris_comp303_assignment1.models;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Order {
    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Contact area code is required")
    @Pattern(regexp = "^\\d{3}$", message = "Please provide a valid area code (e.g., 123)")
    private String contactAreaCode;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\d{3}-\\d{4}$", message = "Please provide a valid phone number (e.g., 123-4567)")
    private String contactNumber;

    @NotBlank(message = "Street address is required")
    private String streetAddress;

    private String streetAddressLine2;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State/Province is required")
    private String stateProvince;

    @NotBlank(message = "Postal/Zip code is required")
    private String postalCode;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Product number is required")
    private String productNumber;

    @NotBlank(message = "Phone brand is required")
    private String phoneBrand;

    @NotBlank(message = "Phone model is required")
    private String phoneModel;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private String phoneCost = "$0.00";


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactAreaCode() {
        return contactAreaCode;
    }

    public void setContactAreaCode(String contactAreaCode) {
        this.contactAreaCode = contactAreaCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddressLine2() {
        return streetAddressLine2;
    }

    public void setStreetAddressLine2(String streetAddressLine2) {
        this.streetAddressLine2 = streetAddressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPhoneCost() {
        return this.phoneCost;
    }

    public void setPhoneCost(String phoneCost) {
        this.phoneCost = phoneCost;
    }

    public String getFullName() {
        return firstName + (middleName != null && !middleName.isEmpty() ? " " + middleName : "") + " " + lastName;
    }

    public String getSubtotal() {
        return formatCurrency(parseCurrency(this.phoneCost) * this.getQuantity());
    }

    public String getTaxes() {
        return formatCurrency(parseCurrency(this.phoneCost) * this.getQuantity() * 0.13);
    }

    public String getTotalPrice() {
        return formatCurrency(parseCurrency(this.phoneCost) * this.getQuantity() * 1.13);
    }

    private String formatCurrency(Double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setCurrency(Currency.getInstance("CAD"));
        return formatter.format(value);
    }

    private double parseCurrency(String value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        formatter.setCurrency(Currency.getInstance("CAD"));
        try {
            return formatter.parse(value).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}