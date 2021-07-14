package com.max.shop.bo.security;

import lombok.CustomLog;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.core.io.FileSystemResource;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;

@CustomLog
public class KeyUtils {

    /**
     * Возвращает публичный ключ сертификата
     *
     * @param certificatePath путь к сертификату
     * @return {@link RSAPublicKey}
     */
    @SneakyThrows
    public static RSAPublicKey readPublicKey(String certificatePath) {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate cert = cf.generateCertificate(new FileSystemResource(certificatePath).getInputStream());
        return (RSAPublicKey) cert.getPublicKey();
    }

    /**
     * Возвращает пару приватный/публичный ключ сертификата
     *
     * @param certificatePath путь к сертификату
     * @param password        пароль от сертификата
     * @param alias           алиас сертификата
     * @return {@link KeyPair}
     */
    @SneakyThrows
    public static KeyPair readKeyPair(String certificatePath, String password, String alias) {
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(new FileSystemResource(certificatePath).getInputStream(), password.toCharArray());

        val key = keyStore.getKey(alias, password.toCharArray());
        if (key instanceof PrivateKey) {
            val cert = keyStore.getCertificate(alias);
            val publicKey = cert.getPublicKey();

            return new KeyPair(publicKey, (PrivateKey) key);
        }

        return null;
    }
}
