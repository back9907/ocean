/**
 * @(#)SshConfiguration.java, 5月 20, 2022.
 * <p>
 * Copyright 2022 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ocean.sever.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author back
 */
@Component
public class SshConfiguration implements ServletContextInitializer {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    public SshConfiguration(){
        try {
            Properties p =new Properties();
            p.load(getClass().getResourceAsStream("/application.properties"));
            if(p.getProperty("ssh.forward.enabled")!=null){
                LOG.info("ssh forward is opened.");
                LOG.info("ssh init ......");
                Session session = new JSch().getSession(p.getProperty("ssh.forward.username"),
                        p.getProperty("ssh.forward.host"),Integer.valueOf(p.getProperty("ssh.forward.port")));
                session.setConfig("StrictHostKeyChecking","no");
                session.setPassword(p.getProperty("ssh.forward.password"));
                session.connect();
                session.setPortForwardingL(p.getProperty("ssh.forward.from_host"),
                        Integer.valueOf(p.getProperty("ssh.forward.from_port")),
                        p.getProperty("ssh.forward.to_host"),Integer.valueOf(p.getProperty("ssh.forward.to_port")));
            }
            else {
                LOG.info("ssh forward is closed.");
            }
        } catch (IOException e) {
            LOG.error("ssh IOException failed.", e);
        } catch (JSchException e){
            LOG.error("ssh JSchException failed.", e);
        }
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }

}