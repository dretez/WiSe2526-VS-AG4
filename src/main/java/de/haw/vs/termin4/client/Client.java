package de.haw.vs.termin4.client;

import de.haw.vs.termin4.client.exceptions.ServerCommunicationException;
import de.haw.vs.termin4.client.exceptions.WriteException;
import de.haw.vs.termin4.common.DataStore;
import de.haw.vs.termin4.common.Logger;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Client implements DataStore {
    private final List<DataStoreInterface> stores;

    public Client(List<Socket> sockets) {
        this.stores = new ArrayList<>(sockets.stream().map(DataStoreInterface::new).toList());
    }

    @Override
    public void write(int index, String data) throws ServerCommunicationException {
        List<DataStoreInterface> stale = new ArrayList<>();
        for (var ds : stores)
            try {
                ds.write(index, data);
            } catch (WriteException e) {
                Logger.error(e.getMessage());
            } catch (ServerCommunicationException _) {
                stale.add(ds);
            }
        stores.removeAll(stale);
        if (stores.isEmpty())
            throw new ServerCommunicationException("Could not reach any server, try to login again");
    }

    @Override
    public String read(int index) throws NoSuchElementException, ServerCommunicationException {
        if (stores.isEmpty())
            throw new ServerCommunicationException("Could not reach any server, try to login again");
        try {
            String data = stores.getFirst().read(index);
            stores.addLast(stores.removeFirst());
            return data;
        } catch (ServerCommunicationException _) {
            stores.remove(stores.getFirst());
            if (!stores.isEmpty())
                return read(index);
            else
                throw new ServerCommunicationException("Could not reach any server, try to login again");
        }
    }
}

