package com.tuti.util

import com.sun.jersey.core.util.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

object IPINBlockGenerator {
    fun getIPINBlock(
        ipin: String,
        publicKey: String?, uuid: String
    ): String {
        // clear ipin = uuid +  IPIN
        var ipin = ipin
        var publicKey = publicKey
        val cleraIpin = uuid + ipin

        // prepare public key, get public key from its String representation as
        // base64
        if (publicKey == null || publicKey.isEmpty()) {
            publicKey =
                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANx4gKYSMv3CrWWsxdPfxDxFvl+Is/0kc1dvMI1yNWDXI3AgdI4127KMUOv7gmwZ6SnRsHX/KAM0IPRe0+Sa0vMCAwEAAQ=="
        }
        val keyByte: ByteArray = Base64.decode(publicKey)
        // generate public key
        val s = X509EncodedKeySpec(keyByte)
        var factory: KeyFactory? = null
        try {
            factory = KeyFactory.getInstance("RSA")
        } catch (e: NoSuchAlgorithmException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        var pubKey: Key? = null
        try {
            pubKey = factory!!.generatePublic(s)
        } catch (e: InvalidKeySpecException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        try {
            // construct Cipher with encryption algrithm:RSA, cipher mode:ECB and padding:PKCS1Padding
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, pubKey)
            // calculate ipin, encryption then encoding to base64
            ipin = String(
                Base64.encode(
                    cipher.doFinal(
                        cleraIpin
                            .toByteArray()
                    )
                )
            )
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        }
        return ipin
    }
}
