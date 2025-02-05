/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.campus.PetSociety.web.exceptions;

/**
 *
 * @author camid
 */

public class NotFoundException extends RuntimeException {
    
    public NotFoundException(String message){
        super(message);
    }
}