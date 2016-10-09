package com.mercateo.kitchenapp.pages.signin;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Singleton;

@Singleton
public class Md5Hasher {

    private final MessageDigest md;

    public Md5Hasher() {
        try {
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

     String hash(String s) {
        byte[] digest = md.digest(s.getBytes());
        BigInteger bigInt = new BigInteger(1, digest);
        return bigInt.toString(16);
    }

}
