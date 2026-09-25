package com.laboration;

public class Member {
    private final int membershipNumber;
    private final String name;
    private int numberOfBooksBorrowed;

    public Member(int membershipNumber, String name) {
        this.membershipNumber = membershipNumber;
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
}
