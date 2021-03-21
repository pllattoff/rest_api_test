package com.platov.rest_api_test.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import com.platov.rest_api_test.entity.FioRequest;
import com.platov.rest_api_test.entity.FioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

@Service
public class FioService {

    private static final Logger log = Logger.getLogger(FioService.class.getName());

    public FioResponse getFioById(FioRequest fioRequest) {

        if(fioRequest.getId() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required param: id");
        }

        FioResponse fioResponse = new FioResponse();

        if(fioRequest.getId()==1) {
            fioResponse.setFio("Test Testov");
        }

        Gson gson = new GsonBuilder().create();
        String jsonFioRequest = gson.toJson(fioRequest);
        String jsonFioResponse = gson.toJson(fioResponse);

        final String password = "Encryption Password";
        String salt = KeyGenerators.string().generateKey();
        TextEncryptor encryptor = Encryptors.text(password, salt);

        String encryptedJsonFioRequest = encryptor.encrypt(jsonFioRequest);
        String decryptedJsonFioRequest = encryptor.decrypt(encryptedJsonFioRequest);

        String encryptedJsonFioResponse = encryptor.encrypt(jsonFioResponse);
        String decryptedJsonFioResponse = encryptor.decrypt(encryptedJsonFioResponse);

        log.info("=============================================");
        log.info("REQUEST:               " + jsonFioRequest);
        log.info("REQUEST (encrypted)):  " + encryptedJsonFioRequest);
        log.info("REQUEST (decrypted)):  " + decryptedJsonFioRequest);
        log.info("RESPONSE:              " + jsonFioResponse);
        log.info("RESPONSE (encrypted)): " + encryptedJsonFioResponse);
        log.info("RESPONSE (decrypted)): " + decryptedJsonFioResponse);

        return fioResponse;
    }
}
