package com.example.m_news.manager;

import ohos.rpc.IRemoteBroker;
import ohos.rpc.RemoteException;

/**
 * News demo
 */
public interface INewsDemoIDL extends IRemoteBroker {
    void tranShare(
            String deviceId,
            String shareUrl,
            String shareTitle,
            String shareAbstract,
            String shareImg) throws RemoteException;
}

