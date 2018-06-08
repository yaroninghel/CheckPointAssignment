package checkpoint.wifi.reputation.controller;

import checkpoint.wifi.reputation.models.AuthType;
import checkpoint.wifi.reputation.models.connectingUser;
import checkpoint.wifi.reputation.models.network;
import checkpoint.wifi.reputation.models.reportThroughput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
public class networkController {

    public HashMap<String, network> networksRepo = new HashMap<String, network>();

    @RequestMapping(value = "/network", method = RequestMethod.GET)
    public ResponseEntity<network> network(@RequestParam(value = "id")String id) {
        if (networksRepo.containsKey(id)) {
            network net = networksRepo.get(id);
            return new ResponseEntity<>(net, HttpStatus.OK);

        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    //		ii. connect device to network (if no such network exists, create new one)
    //		PUT http://my-service/api/network/connect
    @RequestMapping(value = "/network/connect", method = RequestMethod.PUT, consumes = "application/json")
    public  ResponseEntity connect(@RequestBody connectingUser user) {
        if (!networksRepo.containsKey(user.network_id))
        {
            network net = new network(user.network_id, user.auth);
            networksRepo.put(user.network_id, net);
        }
        networksRepo.get(user.network_id).addDevice(user.device_id);

        return new ResponseEntity(HttpStatus.OK);
    }

    //		iii. report network throughput Request:
    //		POST http://my-service/api/network/report
    @RequestMapping(value = "/network/report", method = RequestMethod.POST, consumes = "application/json")
    public  ResponseEntity report(@RequestBody reportThroughput report) {
        if (networksRepo.containsKey(report.network_id)) {
            network currentNetwork = networksRepo.get(report.network_id);
            currentNetwork.reportThroughput(report.throughput);
            return new ResponseEntity(HttpStatus.OK);

        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
