package com.imperial.academia.use_case.generator;

import java.util.concurrent.TimeUnit;

public interface TokenGenerator {
    String generateToken();
    long getTokenExpiryTime(int duration, TimeUnit unit);
}
