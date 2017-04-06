package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String id;
  private final String lname;
  private final String name;
  private final String address;
  private final String phone;
  private final String email;

  public ContactData(String name, String lname, String address, String phone, String email) {
    this.id = null;
    this.name = name;
    this.lname = lname;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }
  public ContactData(String id, String name, String lname, String address, String phone, String email) {
    this.id = id;
    this.name = name;
    this.lname = lname;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (lname != null ? !lname.equals(that.lname) : that.lname != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
    return email != null ? email.equals(that.email) : that.email == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (lname != null ? lname.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", lname='" + lname + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLname() {
    return lname;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

}