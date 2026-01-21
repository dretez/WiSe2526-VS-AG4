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
    private final String name;

    public Client(List<Socket> sockets, String name) {
        this.stores = new ArrayList<>(sockets.stream().map(DataStoreInterface::new).toList());
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public void write(int index, String data) {
        for (var ds : stores)
            try {
                ds.write(index, data);
            } catch (WriteException e) {
                Logger.error(e.getMessage());
            } catch (ServerCommunicationException _) {
                stores.remove(ds);
            }
    }

    @Override
    public String read(int index) throws NoSuchElementException, ServerCommunicationException {
        stores.addLast(stores.removeFirst());
        try {
            return stores.getFirst().read(index);
        } catch (ServerCommunicationException _) {
            stores.remove(stores.getFirst());
            if (!stores.isEmpty())
                return read(index);
            else
                throw new ServerCommunicationException("Could not reach any server, try to login again");
        }
    }
}

