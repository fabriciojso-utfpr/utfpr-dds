/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dds.views;

import com.dds.DAO.CommunicationUnitDAO;
import com.dds.model.CommunicationUnit;
import com.dds.model.TypeChannel;

/**
 *
 * @author fabriciojso
 */
public class Teste {


    public static void main(String[] args) {
          CommunicationUnit communicationUnit = new CommunicationUnit(TypeChannel.CHANNEL);
        new CommunicationUnitDAO().save(communicationUnit);
    }
    
    
}
