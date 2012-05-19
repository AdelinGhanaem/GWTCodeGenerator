package com.clouway.gwtgenerator.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("gwtgeneratorService")
public interface GwtgeneratorService extends RemoteService {
  // Sample interface method of remote interface
  String getMessage(String msg);

  /**
   * Utility/Convenience class.
   * Use GwtgeneratorService.App.getInstance() to access static instance of gwtgeneratorServiceAsync
   */
  public static class App {
    private static GwtgeneratorServiceAsync ourInstance = GWT.create(GwtgeneratorService.class);

    public static synchronized GwtgeneratorServiceAsync getInstance() {
      return ourInstance;
    }
  }
}
