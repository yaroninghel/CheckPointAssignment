package checkpoint.wifi.reputation.models;


import checkpoint.wifi.reputation.enums.AuthType;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicLong;

public class Network {
    private String _id;
    private AuthType _authType;
    private float _throughputSum = 0;
    private AtomicLong _throughputReports = new AtomicLong();
    ArrayList<Device> _devices;

    public Network(String id, AuthType authType)
    {
        _id = id;
        _authType = authType;
        _devices = new ArrayList<Device>();
    }

    public void addDevice(String id)
    {
        Device dev = new Device(id);
        if (!_devices.contains(dev))
        {
            _devices.add(dev);
        }
    }

    public void reportThroughput(String deviceID, float throughput)
    {
        _throughputSum+=throughput;
        _throughputReports.incrementAndGet();
        Device currentDevice;

        addDevice(deviceID);
        int ind = _devices.indexOf(new Device(deviceID));
        currentDevice = _devices.get(ind);

        currentDevice.recordThrouput(throughput);
    }

    public String getId()
    {
        return _id;
    }
    public AuthType getAuth()
    {
        return _authType;
    }

    public float getAvg_throughput()
    {
        float avg = _throughputSum/_throughputReports.intValue();
        if (Float.isNaN(avg))
            avg = 0;
        return avg;
    }

    public ArrayList<Device> getDevices() {
        return _devices;
    }
}
