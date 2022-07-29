package com.springsecurity.authServerSpringSecurity.security.keys;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import com.nimbusds.jose.jwk.RSAKey;

public class JwKeys {

    public static RSAKey generateRSAKey(){

        KeyPairGenerator g = null;
        try {
            g = KeyPairGenerator.getInstance("RSA");
            g.initialize(2048);
            KeyPair keyPair = g.generateKeyPair();

            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            return new RSAKey.Builder(rsaPublicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem generating the keys");
        }
    }
}
