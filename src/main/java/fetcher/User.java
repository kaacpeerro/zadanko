package fetcher;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, birthday, phoneNumber);
    }

    public static class UserBuilder {

        private String firstName;
        private String lastName;
        private LocalDate birthDay;
        private String phoneNumber;

        UserBuilder() {
        }

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
