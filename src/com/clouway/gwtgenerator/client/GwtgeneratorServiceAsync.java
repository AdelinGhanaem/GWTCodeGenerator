package com.clouway.gwtgenerator.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwtgeneratorServiceAsync {
  void getMessage(String msg, AsyncCallback<String> async);
}
