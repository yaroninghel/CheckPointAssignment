package checkpoint.wifi.reputation.Models;

import java.util.ArrayList;

public class Device {
    private String id;
    ArrayList<Float> throughputs;

    public Device(String str)
    {
        id = str;
        throughputs = new ArrayList<Float>();
    }

    public String getId()
    {
        return id;
    }

    public void recordThrouput(float throuput)
    {
        throughputs.add(throuput);
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof Device){
            Device ptr = (Device) v;
            retVal = ptr.id.compareTo(this.id) == 0;
        }
        else if (v instanceof String)
        {

            String ptr = (String) v;
            retVal = ptr.compareTo(this.id) == 0;
        }

        return retVal;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
