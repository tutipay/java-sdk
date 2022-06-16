package com.tuti.model;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.sun.jersey.core.util.Base64;

public class IPIN {

    public static String getIPINBlock(String encryptedIPIN,
                                      String publicKey, String uuid) {
        // clear ipin = uuid +  IPIN
        String clearIPIN = uuid + encryptedIPIN;

        // prepare public key, get public key from its String representation as
        // base64
        byte[] keyRawBytes = Base64.decode(publicKey);
        // generate public key
        X509EncodedKeySpec encodeKeySpecs = new X509EncodedKeySpec(keyRawBytes);
        KeyFactory rsaKeyFactory = null;
        try {
            rsaKeyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Key pubKey = null;
        try {
            pubKey = rsaKeyFactory.generatePublic(encodeKeySpecs);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            // construct Cipher with encryption algrithm:RSA, cipher mode:ECB and padding:PKCS1Padding
            Cipher rsaCipherInstance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            rsaCipherInstance.init(Cipher.ENCRYPT_MODE, pubKey);
            // calculate ipin, encryption then encoding to base64
            encryptedIPIN = (new String(Base64.encode(rsaCipherInstance.doFinal(clearIPIN
                    .getBytes()))));
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptedIPIN;
    }

//    public static void main(String[] args) {
    //remember to move to a unit test
//        System.out.println(new IPINBlockGenerator().getIPINBlock("0000","MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJ4HwthfqXiK09AgShnnLqAqMyT5VUV0hvSdG+ySMx+a54Ui5EStkmO8iOdVG9DlWv55eLBoodjSfd0XRxN7an0CAwEAAQ==", UUID.randomUUID().toString()));
//    }

}
