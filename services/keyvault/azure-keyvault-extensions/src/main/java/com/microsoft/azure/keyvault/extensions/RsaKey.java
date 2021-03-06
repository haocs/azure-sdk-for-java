/**
 *
 * Copyright (c) Microsoft and contributors.  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.microsoft.azure.keyvault.extensions;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import com.microsoft.azure.keyvault.core.IKey;
import com.microsoft.azure.keyvault.extensions.cryptography.Algorithm;
import com.microsoft.azure.keyvault.extensions.cryptography.AlgorithmResolver;
import com.microsoft.azure.keyvault.extensions.cryptography.AsymmetricEncryptionAlgorithm;
import com.microsoft.azure.keyvault.extensions.cryptography.ICryptoTransform;
import com.microsoft.azure.keyvault.webkey.JsonWebKey;
import com.microsoft.azure.keyvault.webkey.JsonWebKeyEncryptionAlgorithm;
import com.microsoft.azure.keyvault.webkey.JsonWebKeySignatureAlgorithm;

public class RsaKey implements IKey {

    class FutureDecrypt extends FutureBase<byte[]> {

        private final byte[]           _data;
        private final ICryptoTransform _transform;

        FutureDecrypt(ICryptoTransform transform, byte[] data) {
            _data = data;
            _transform = transform;
        }

        @Override
        public byte[] get() throws InterruptedException, ExecutionException {
            try {
                return _transform.doFinal(_data);
            } catch (Exception e) {
                throw new ExecutionException(e);
            }
        }

        @Override
        public byte[] get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            try {
                return _transform.doFinal(_data);
            } catch (Exception e) {
                throw new ExecutionException(e);
            }
        }
    }

    class FutureEncrypt extends FutureBase<Triple<byte[], byte[], String>> {

        private final String           _algorithm;
        private final byte[]           _data;
        private final ICryptoTransform _transform;

        FutureEncrypt(String algorithm, byte[] data, ICryptoTransform transform) {
            _algorithm = algorithm;
            _data = data;
            _transform = transform;
        }

        @Override
        public Triple<byte[], byte[], String> get() throws InterruptedException, ExecutionException {
            try {
                return Triple.of(_transform.doFinal(_data), (byte[]) null, _algorithm);
            } catch (Exception e) {
                throw new ExecutionException(e);
            }
        }

        @Override
        public Triple<byte[], byte[], String> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            try {
                return Triple.of(_transform.doFinal(_data), (byte[]) null, _algorithm);
            } catch (Exception e) {
                throw new ExecutionException(e);
            }
        }
    }

    class FutureWrap extends FutureBase<Pair<byte[], String>> {

        private final String           _algorithm;
        private final byte[]           _data;
        private final ICryptoTransform _transform;

        FutureWrap(String algorithm, byte[] data, ICryptoTransform transform) {
            _algorithm = algorithm;
            _data = data;
            _transform = transform;
        }

        @Override
        public Pair<byte[], String> get() throws InterruptedException, ExecutionException {
            try {
                return Pair.of(_transform.doFinal(_data), _algorithm);
            } catch (Exception e) {
                throw new ExecutionException(e);
            }
        }

        @Override
        public Pair<byte[], String> get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            try {
                return Pair.of(_transform.doFinal(_data), _algorithm);
            } catch (Exception e) {
                throw new ExecutionException(e);
            }
        }
    }

    public static int KeySize1024 = 1024;
    public static int KeySize2048 = 2048;

    public static int getDefaultKeySize() {
        return RsaKey.KeySize2048;
    }

    private final String  _kid;
    private final KeyPair _keyPair;

    public RsaKey(String kid) throws NoSuchAlgorithmException {
        this(kid, getDefaultKeySize());
    }

    public RsaKey(String kid, int keySize) throws NoSuchAlgorithmException {

        if (Strings.isNullOrWhiteSpace(kid)) {
            throw new IllegalArgumentException("kid");
        }

        final KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

        generator.initialize(keySize);

        _keyPair = generator.generateKeyPair();
        _kid = kid;
    }

    public RsaKey(String kid, KeyPair keyPair) {

        if (Strings.isNullOrWhiteSpace(kid)) {
            throw new IllegalArgumentException("kid");
        }

        if (keyPair == null) {
            throw new IllegalArgumentException("kid");
        }

        if (keyPair.getPublic() == null || !(keyPair.getPublic() instanceof RSAPublicKey)) {
            throw new IllegalArgumentException("keyPair");
        }

        _keyPair = keyPair;
        _kid = kid;
    }

    public RsaKey(JsonWebKey key) {
        _keyPair = key.toRSA(true);
        _kid = key.getKid();
    }

    @Override
    public String getDefaultEncryptionAlgorithm() {
        return JsonWebKeyEncryptionAlgorithm.RSAOAEP;
    }

    @Override
    public String getDefaultKeyWrapAlgorithm() {
        return JsonWebKeyEncryptionAlgorithm.RSAOAEP;
    }

    @Override
    public String getDefaultSignatureAlgorithm() {
        return JsonWebKeySignatureAlgorithm.RS256;
    }

    @Override
    public String getKid() {
        return _kid;
    }

    @Override
    public Future<byte[]> decryptAsync(final byte[] ciphertext, final byte[] iv, final byte[] authenticationData, final byte[] authenticationTag, final String algorithm) throws NoSuchAlgorithmException {

        if (ciphertext == null) {
            throw new IllegalArgumentException("ciphertext");
        }

        // Interpret the requested algorithm
        if (Strings.isNullOrWhiteSpace(algorithm)) {
            throw new IllegalArgumentException("algorithm");
        }

        Algorithm baseAlgorithm = AlgorithmResolver.Default.get(algorithm);
        
        if (baseAlgorithm == null || !(baseAlgorithm instanceof AsymmetricEncryptionAlgorithm)) {
            throw new NoSuchAlgorithmException(algorithm);
        }
        
        AsymmetricEncryptionAlgorithm algo = (AsymmetricEncryptionAlgorithm)baseAlgorithm;

        ICryptoTransform transform;
        Future<byte[]> result;

        try {
            transform = algo.CreateDecryptor(_keyPair);
            result = new FutureDecrypt(transform, ciphertext);
        } catch (Exception e) {
            result = new FutureExecutionException<byte[]>(e);
        }

        return result;
    }

    @Override
    public Future<Triple<byte[], byte[], String>> encryptAsync(final byte[] plaintext, final byte[] iv, final byte[] authenticationData, final String algorithm) throws NoSuchAlgorithmException {

        if (plaintext == null) {
            throw new IllegalArgumentException("plaintext");
        }

        // Interpret the requested algorithm
        String    algorithmName = (Strings.isNullOrWhiteSpace(algorithm) ? getDefaultEncryptionAlgorithm() : algorithm);
        Algorithm baseAlgorithm = AlgorithmResolver.Default.get(algorithmName);
        
        if (baseAlgorithm == null || !(baseAlgorithm instanceof AsymmetricEncryptionAlgorithm)) {
            throw new NoSuchAlgorithmException(algorithmName);
        }
        
        AsymmetricEncryptionAlgorithm algo = (AsymmetricEncryptionAlgorithm)baseAlgorithm;

        ICryptoTransform transform;
        Future<Triple<byte[], byte[], String>> result;

        try {
            transform = algo.CreateEncryptor(_keyPair);
            result = new FutureEncrypt(algorithmName, plaintext, transform);
        } catch (Exception e) {
            result = new FutureExecutionException<Triple<byte[], byte[], String>>(e);
        }

        return result;
    }

    @Override
    public Future<Pair<byte[], String>> wrapKeyAsync(final byte[] key, final String algorithm) throws NoSuchAlgorithmException {

        if (key == null) {
            throw new IllegalArgumentException("key");
        }

        // Interpret the requested algorithm
        String    algorithmName = (Strings.isNullOrWhiteSpace(algorithm) ? getDefaultKeyWrapAlgorithm() : algorithm);
        Algorithm baseAlgorithm = AlgorithmResolver.Default.get(algorithmName);
        
        if (baseAlgorithm == null || !(baseAlgorithm instanceof AsymmetricEncryptionAlgorithm)) {
            throw new NoSuchAlgorithmException(algorithmName);
        }
        
        AsymmetricEncryptionAlgorithm algo = (AsymmetricEncryptionAlgorithm)baseAlgorithm;

        ICryptoTransform transform;
        Future<Pair<byte[], String>> result;

        try {
            transform = algo.CreateEncryptor(_keyPair);
            result = new FutureWrap(algorithmName, key, transform);
        } catch (Exception e) {
            result = new FutureExecutionException<Pair<byte[], String>>(e);
        }

        return result;
    }

    @Override
    public Future<byte[]> unwrapKeyAsync(final byte[] encryptedKey, final String algorithm) throws NoSuchAlgorithmException {

        if (encryptedKey == null) {
            throw new IllegalArgumentException("encryptedKey ");
        }

        // Interpret the requested algorithm
        if (Strings.isNullOrWhiteSpace(algorithm)) {
            throw new IllegalArgumentException("algorithm");
        }

        // Interpret the requested algorithm
        Algorithm baseAlgorithm = AlgorithmResolver.Default.get(algorithm);
        
        if (baseAlgorithm == null || !(baseAlgorithm instanceof AsymmetricEncryptionAlgorithm)) {
            throw new NoSuchAlgorithmException(algorithm);
        }
        
        AsymmetricEncryptionAlgorithm algo = (AsymmetricEncryptionAlgorithm)baseAlgorithm;

        ICryptoTransform transform;
        Future<byte[]> result;

        try {
            transform = algo.CreateDecryptor(_keyPair);
            result = new FutureDecrypt(transform, encryptedKey);
        } catch (Exception e) {
            result = new FutureExecutionException<byte[]>(e);
        }

        return result;
    }

    @Override
    public Future<Pair<byte[], String>> signAsync(final byte[] digest, final String algorithm) {
        return null;
    }

    @Override
    public Future<Boolean> verifyAsync(final byte[] digest, final byte[] signature, final String algorithm) {
        return null;
    }

    @Override
    public void close() throws IOException {
        // Intentionally empty
    }

}
