package com.example.m_news.manager;

import ohos.rpc.IRemoteObject;
import ohos.rpc.MessageOption;
import ohos.rpc.MessageParcel;
import ohos.rpc.RemoteException;

/**
 * News demo
 */
public class NewsDemoIDLProxy implements INewsDemoIDL {
    private static final String DESCRIPTOR = "com.example.m_news.INewsDemoIDL";

    private static final int COMMAND_TRAN_SHARE = IRemoteObject.MIN_TRANSACTION_ID;

    private final IRemoteObject remote;

    NewsDemoIDLProxy(IRemoteObject remote) {
        this.remote = remote;
    }

    @Override
    public IRemoteObject asObject() {
        return remote;
    }

    @Override
    public void tranShare(
            String deviceId,
            String shareUrl,
            String shareTitle,
            String shareAbstract,
            String shareImg) throws RemoteException {
        MessageParcel data = MessageParcel.obtain();
        MessageParcel reply = MessageParcel.obtain();
        MessageOption option = new MessageOption(MessageOption.TF_SYNC);

        data.writeInterfaceToken(DESCRIPTOR);
        data.writeString(deviceId);
        data.writeString(shareUrl);
        data.writeString(shareTitle);
        data.writeString(shareAbstract);
        data.writeString(shareImg);

        try {
            remote.sendRequest(COMMAND_TRAN_SHARE, data, reply, option);
            reply.readException();
        } finally {
            data.reclaim();
            reply.reclaim();
        }
    }
}

