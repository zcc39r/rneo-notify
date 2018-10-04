package com.ols.ruslan.neo.notify;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jcr.observation.Event;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.ols.ruslan.neo.RuslanHelper;
import com.ols.ruslan.neo.jcr.JcrHelper;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = RuslanHelper.CREATE_UPDATE_RECORD_TOPIC),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "Notifier"),
        @ActivationConfigProperty(propertyName = "clientID", propertyValue = "Notifier"),
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "subject = 'record'") })
public class Notifier implements MessageListener {
    private static final Logger log = Logger
            .getLogger(Notifier.class.getName());

    @Resource
    private MessageDrivenContext mdc;

    @Resource
    private String ruslanInstanceUrl;

    @PostConstruct
    private void startup() {
    }

    @Override
    public void onMessage(Message m) {
        try {
            String path = m.getStringProperty("path");
            String pathComponents[] = path.split("/");
            String databaseName = pathComponents[2];
            switch (m.getIntProperty("type")) {
            case Event.NODE_MOVED:
            case Event.PROPERTY_ADDED:
            case Event.PROPERTY_CHANGED:
                log.info(getRecordUrl(path));
                break;
            }
        } catch (JMSException e) {
            log.warning("Unable to process message: " + e.getMessage());
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Exception e) {
            log.severe("Unable to process data: " + e.getMessage());
            e.printStackTrace();
            mdc.setRollbackOnly();
        }
    }

    private String getRecordUrl(String path)
            throws UnsupportedEncodingException {
        String recordPath = path;
        if (recordPath.endsWith("jcr:data")) {
            recordPath = recordPath.substring(0, recordPath.lastIndexOf("/"));
            recordPath = recordPath.substring(0, recordPath.lastIndexOf("/"));
        }
        String recordPathComponents[] = JcrHelper.storagePathToPath(recordPath)
                .split("/");

        return ruslanInstanceUrl + JcrHelper.DB_PATH + "/"
                + URLEncoder.encode(recordPathComponents[2], "UTF-8") + "/"
                + URLEncoder.encode(recordPathComponents[3], "UTF-8");
    }

}
