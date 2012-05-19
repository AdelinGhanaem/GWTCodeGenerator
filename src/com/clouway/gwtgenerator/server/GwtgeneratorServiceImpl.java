package com.clouway.gwtgenerator.server;

import com.clouway.gwtgenerator.client.GwtgeneratorService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GwtgeneratorServiceImpl extends RemoteServiceServlet implements GwtgeneratorService {
  // Implementation of sample interface method
  public String getMessage(String msg) {
    return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
  }
}