package com.incubateur.carpoolconnect.utilities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Data
@NoArgsConstructor
public class KeyGen {

    public static final String CIPHER = "DESede";

    public static Key getKeyFromKeyGenerator(String cipher, int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(cipher);
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }
}
