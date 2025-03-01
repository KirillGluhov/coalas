package com.testhello.gateway.enums;

public enum Role
{
    EMPLOYEE,
    CLIENT;

    @Override
    public String toString()
    {
        return this.name().toLowerCase();
    }
}
