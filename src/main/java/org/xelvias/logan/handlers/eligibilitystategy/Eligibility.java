package org.xelvias.logan.handlers.eligibilitystategy;

public interface Eligibility<T> {
    boolean isEligible(T data);

}
