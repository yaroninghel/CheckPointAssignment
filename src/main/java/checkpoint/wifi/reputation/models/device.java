package checkpoint.wifi.reputation.models;

import java.util.ArrayList;

public class device {
    private String id;

    public device(String str)
    {
        id = str;
    }

    public String getId()
    {
        return id;
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof device){
            device ptr = (device) v;
            retVal = ptr.id.compareTo(this.id) == 0;
        }

        return retVal;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
