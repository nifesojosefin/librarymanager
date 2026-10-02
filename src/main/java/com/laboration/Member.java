package com.laboration;

public class Member {
    private final int membershipNumber;
    private final String name;
    private int numberOfBooksBorrowed;

    public Member(int membershipNumber, String name) {
        if (isNotValidMembershipNumber(membershipNumber) || isNotValidInput(name))
            throw new IllegalArgumentException();
        else {
            this.membershipNumber = membershipNumber;
            this.name = name;
            this.numberOfBooksBorrowed = 0;
        }
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public String getName() {
        return name;
    }

    public void addNumberOfBooksBorrowed() {
        this.numberOfBooksBorrowed = this.numberOfBooksBorrowed + 1;
    }

    public void decreaseNumberOfBooksBorrowed() {
        this.numberOfBooksBorrowed = this.numberOfBooksBorrowed - 1;
    }

    public boolean isAllowedToBorrow() {
        return numberOfBooksBorrowed < 5;
    }

    public boolean isNotValidMembershipNumber(int membershipNumber) {
        return membershipNumber <= 0 || membershipNumber > 100000;
    }

    private boolean isNotValidInput(String input) {
        return input.isBlank();
    }
}
