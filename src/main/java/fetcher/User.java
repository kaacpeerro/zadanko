package fetcher;

import java.time.LocalDate;

class User {

    private final String firstName;
    private final String lastName;
    private final LocalDate birthday;
    private final String phoneNumber;

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthday = builder.birthDay;
        this.phoneNumber = builder.phoneNumber;
    }

    static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {

        private String firstName;
        private String lastName;
        private LocalDate birthDay;
        private String phoneNumber;

        UserBuilder() { }

        UserBuilder firstName(String firstName) {
            this.firstName = lastName;
            return this;
        }

        UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        UserBuilder birthday(LocalDate birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        UserBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        User build() {
            return new User(this);
        }

    }
}
