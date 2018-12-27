package com.backlog.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

public class MaskingTest {
    //private static final Logger logger = LogManager.getLogger(MaskingTest.class);
    private static final org.slf4j.Logger logger=LoggerFactory.getLogger(MaskingTest.class);
    public static void myTest(){

        logger.info("this is my info message");
        logger.debug("This is debug message");
        logger.debug("Passed to server::0084USER:17603,IP:0:0:0:0:0:0:0:1,3425,Credit Card 1:1000002367844224,3425,Credit Card2:1000002367844224 , CVV:234,SSN:123456789");

    }
    public static void main(String[] args) {
        myTest();
    }
}