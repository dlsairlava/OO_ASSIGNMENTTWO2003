package edu.curtin.app.event;

import java.util.logging.Logger;

public class ConsoleLogger implements Observer
{
    // Console Logger is where simulation logs events to the console 
    private static final Logger log = Logger.getLogger(ConsoleLogger.class.getName());

    @Override
    public void update(String message) 
    {   
            log.info(message);
    }

}

