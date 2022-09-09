package com.shpofystore.yourshpofystore.Model;

public class Users {
    private int UID;
    private String FirstName,LastName,CNIC,Email,Password;

    public static final String TABLE="USERS";
    public static final String KEY_USER_ID="UID";
    public static final String KEY_FIRSTNAME="FIRSTNAME";
    public static final String KEY_LASTNAME="lastName";
    public static final String KEY_CNIC="CNIC";
    public static final String KEY_EMAIL="EMAIL";
    public static final String KEY_PASSWORD="PASSWORD";


    public static final String CREATE_TABLE=String.format("CREATE TABLE IF NOT EXISTS " + " %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s TEXT)",
            TABLE,KEY_USER_ID,KEY_FIRSTNAME,KEY_LASTNAME,KEY_CNIC,KEY_EMAIL,KEY_PASSWORD);
    public static final String SELECT_ALL_USERS="SELECT * FROM " + TABLE;
    public static final String GetProfileData = String.format("SELECT * FROM %s WHERE %s =?",TABLE,KEY_EMAIL);
    public static final String CHECKACC_BY_EMAIL = String.format("SELECT * FROM %s WHERE %s =?",TABLE,KEY_EMAIL);
    public static final String LoginByEmailPass = String.format("SELECT * FROM %s WHERE %s =? AND %s =?",TABLE,KEY_EMAIL,KEY_PASSWORD);





    public Users(String email, String password) {
        Email = email;
        Password = password;
    }

    public Users(String firstName, String lastName, String CNIC, String email, String password) {
        FirstName = firstName;
        LastName = lastName;
        this.CNIC = CNIC;
        Email = email;
        Password = password;
    }

    public Users(int UID, String firstName, String lastName, String CNIC, String email, String password) {
        this.UID = UID;
        FirstName = firstName;
        LastName = lastName;
        this.CNIC = CNIC;
        Email = email;
        Password = password;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
