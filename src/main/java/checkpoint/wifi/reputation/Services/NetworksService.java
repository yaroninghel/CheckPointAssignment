package checkpoint.wifi.reputation.Services;

import checkpoint.wifi.reputation.ApiModels.ConnectingUser;
import checkpoint.wifi.reputation.ApiModels.ReportThroughput;
import checkpoint.wifi.reputation.models.Network;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Service
public class NetworksService {
    public HashMap<String, Network> networksRepo = new HashMap<String, Network>(); // don't need to be here.. need a service

    public ResponseEntity<Network> getNetworkResponse(String id)
    {
        if (networksRepo.containsKey(id)) {
            Network net = networksRepo.get(id);
            return new ResponseEntity<>(net, HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public  ResponseEntity connectToNetwork(@RequestBody ConnectingUser user) {
        if (!networksRepo.containsKey(user.network_id)) {
            Network net = new Network(user.network_id, user.auth);
            networksRepo.put(user.network_id, net);
        }
        networksRepo.get(user.network_id).addDevice(user.device_id);

        return new ResponseEntity(HttpStatus.OK);
    }


    public  ResponseEntity reportThroughput(@RequestBody ReportThroughput report) {
        if (networksRepo.containsKey(report.network_id)) {
            Network currentNetwork = networksRepo.get(report.network_id);
            currentNetwork.reportThroughput(report.device_id, report.throughput);
            return new ResponseEntity(HttpStatus.OK);

        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
