package checkpoint.wifi.reputation.models;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class network {
    private String _id;
    private AuthType _authType;
    private float _throughputSum = 0;
    private AtomicLong _throughputReports = new AtomicLong();
    ArrayList<device> _devices;

    public network(String id, AuthType authType)
    {
        _id = id;
        _authType = authType;
        _devices = new ArrayList<device>();
    }

    public void addDevice(String id)
    {
        device dev = new device(id);
        if (!_devices.contains(dev))
        {
            _devices.add(dev);
        }
    }

    public void reportThroughput(float throughput)
    {
        _throughputSum+=throughput;
        _throughputReports.incrementAndGet();
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
        return avg;
    }

    public ArrayList<device> getDevices() {
        return _devices;
    }
}
