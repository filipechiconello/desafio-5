package br.com.superatecnologia.managementapi.controllers;


import br.com.superatecnologia.managementapi.facades.OtpFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/otp")
public class OtpController {

    @Autowired
    private OtpFacade otpFacade;

    @GetMapping(value = "/{email}")
    public ResponseEntity<Void> generateOtpCode(@PathVariable("email") String email) {
        otpFacade.generateOtpCode(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}