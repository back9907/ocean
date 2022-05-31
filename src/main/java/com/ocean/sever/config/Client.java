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
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author back
 */
@Service
public class Client {

    @Autowired
    MessageService messageService;


    private ObjectInputStream[] inputStream = new ObjectInputStream[100];
    private ObjectOutputStream[] outputStream = new ObjectOutputStream[100];
    private Socket[] socket = new Socket[100];

    private String userName;
    private long[] userIds = new long[100];



    public void sendMessage(String message){
        int sourceId = Integer.parseInt(message.split("#")[0]);
        try{
            outputStream[sourceId] .writeObject(message);
        } catch (IOException e){
            System.out.println("Exception writing to server: " + e);
        }
    }

//    private void disconnect() {
//        try {
//            if (inputStream != null) {
//                inputStream[(int)userId] .close();
//            }
//        } catch (Exception e) {
//        }
//
//        try {
//            if (outputStream != null) {
//                outputStream[(int)userId] .close();
//            }
//        } catch (Exception r) {
//        }
//
//        try {
//            if (socket != null) {
//                socket[(int)userId] .close();
//            }
//        } catch (Exception e) {
//        }
//    }

    public void startUp(long userId) {

        this.userIds[(int)userId] = userId;
        try{
            socket[(int)userId] = new Socket("localhost",4242);
        }catch (Exception e){
            System.out.println("Error connection to server: " + e);
        }

        String msg = "Connection accept " +socket[(int)userId] .getInetAddress() + ":" + socket[(int)userId] .getPort();
        System.out.println(msg);

        try {
            outputStream[(int)userId]  = new ObjectOutputStream(socket[(int)userId] .getOutputStream());
            inputStream[(int)userId]  = new ObjectInputStream(socket[(int)userId] .getInputStream());
        }catch (IOException e){
            System.out.println("Exception creating new Input/output Streams: "+ e);
        }

        new ListenFromSever(userId).start();

        try{
            outputStream[(int)userId] .writeObject("test#"+userId);
        }catch (IOException e){
            System.out.println("Exception doing login : " + e);
//            disconnect();
        }
    }

    class ListenFromSever extends Thread{
        long userId;

        public ListenFromSever(long userId){
            this.userId=userId;
        }
        @Override
        public void run(){
            while (true){
                try {
                    String msg = inputStream[(int) userId].readObject().toString();
                    System.out.println("helloWorld");
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