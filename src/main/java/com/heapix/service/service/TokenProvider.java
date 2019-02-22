package com.heapix.service.service;

public interface TokenProvider {
    String getToken(String header);
    boolean isHeaderValid(String header);
}
