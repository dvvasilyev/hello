package com.dolvesa.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Denis Vasilyev (dv@dolvesa.com) on 19.01.2017 at 12:32.
 * <p>
 * Description is absent
 */
public final class RandomStringGenerator {
    private static Logger log = LoggerFactory.getLogger(RandomStringGenerator.class);

    private SecureRandom random = new SecureRandom();

    public String randomString(byte resultLength) {
        String tmp = new BigInteger(130, random).toString(32);
        return tmp.substring(1, resultLength + 1);
    }

}
