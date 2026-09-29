package com.laboration;

public class Member {
    private final int membershipNumber;
    private final String name;
    private int numberOfBooksBorrowed;

    public Member(int membershipNumber, String name) {
        if (isValidMembershipNumber(membershipNumber))
            this.membershipNumber = membershipNumber;
        else
            throw new IllegalArgumentException();
        this.name = name;
        this.numberOfBooksBorrowed = 0;
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

    public boolean isAllowedToBorrow() {
        return numberOfBooksBorrowed <= 5;
    }

    public boolean isValidMembershipNumber(int membershipNumber) {
        return membershipNumber >= 1 && membershipNumber <= 10000;
    }
}
