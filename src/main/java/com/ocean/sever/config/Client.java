/**
 * @(#)Client.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.config;

import com.ocean.sever.service.spi.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author back
 */
@Service
public class Client {

    @Autowired
    MessageService messageService;


    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket socket;

    private String userName;
    private long userId;


    public void sendMessage(String message){
        try{
            outputStream.writeObject(message);
        } catch (IOException e){
            System.out.println("Exception writing to server: " + e);
        }
    }

    private void disconnect() {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Exception e) {
        }

        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception r) {
        }

        try {
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
        }
    }

    public void startUp(long userId) {

        this.userId = userId;
        try{
            socket = new Socket("localhost",4242);
        }catch (Exception e){
            System.out.println("Error connection to server: " + e);
        }

        String msg = "Connection accept " +socket.getInetAddress() + ":" + socket.getPort();
        System.out.println(msg);

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }catch (IOException e){
            System.out.println("Exception creating new Input/output Streams: "+ e);
        }

        new ListenFromSever().start();

        try{
            outputStream.writeObject("test#"+userId);
        }catch (IOException e){
            System.out.println("Exception doing login : " + e);
            disconnect();
        }
    }

    class ListenFromSever extends Thread{

        @Override
        public void run(){
            while (true){
                try {
                    String msg = inputStream.readObject().toString();
                    String[] message = msg.split("#");
                    System.out.println(message[3]);
                    messageService.createMessage(Long.parseLong(message[1]),Long.parseLong(message[2]),message[3]);
                }catch (IOException e){
                    System.out.println("Sever has close the connection: " + e);
                    break;
                }catch (ClassNotFoundException e){

                }
            }
        }
    }

}