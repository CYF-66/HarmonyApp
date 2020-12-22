package com.example.m_news.manager;

import ohos.rpc.IRemoteBroker;
import ohos.rpc.IRemoteObject;
import ohos.rpc.MessageParcel;
import ohos.rpc.RemoteObject;
import ohos.rpc.MessageOption;
import ohos.rpc.RemoteException;

/**
 * News demo
 */
public abstract class NewsDemoIDLStub extends RemoteObject implements INewsDemoIDL {
    private static final String DESCRIPTOR = "com.example.m_news.INewsDemoIDL";

    private static final int COMMAND_TRAN_SHARE = IRemoteObject.MIN_TRANSACTION_ID;

    public NewsDemoIDLStub(String descriptor) {
        super(descriptor);
    }

    /**
     * INewsDemoIDL
     *
     * @param object IRemoteObject
     * @return INewsDemoIDL
     */
    public static INewsDemoIDL asInterface(IRemoteObject object) {
        INewsDemoIDL result = null;
        if (object != null) {
            IRemoteBroker broker = object.queryLocalInterface(DESCRIPTOR);
            if (broker != null) {
                if (broker instanceof INewsDemoIDL) {
                    result = (INewsDemoIDL) broker;
                }
            } else {
                result = new NewsDemoIDLProxy(object);
            }
        }
        return result;
    }

    @Override
    public IRemoteObject asObject() {
        return this;
    }

    @Override
    public boolean onRemoteRequest(
            int code,
            MessageParcel data,
            MessageParcel reply,
            MessageOption option) throws RemoteException {
        String token = data.readInterfaceToken();
        if (!DESCRIPTOR.equals(token)) {
            return false;
        }
        if (code == COMMAND_TRAN_SHARE) {
            String deviceId = data.readString();
            String shareUrl = data.readString();
            String shareTitle = data.readString();
            String shareAbstract = data.readString();
            String shareImg = data.readString();
            tranShare(deviceId, shareUrl, shareTitle, shareAbstract, shareImg);
            reply.writeNoException();
            return true;
        }
        return super.onRemoteRequest(code, data, reply, option);
    }
}

