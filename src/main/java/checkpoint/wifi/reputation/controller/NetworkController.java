package checkpoint.wifi.reputation.controller;

import checkpoint.wifi.reputation.ApiModels.ConnectingUser;
import checkpoint.wifi.reputation.Services.NetworksService;
import checkpoint.wifi.reputation.models.Network;
import checkpoint.wifi.reputation.ApiModels.ReportThroughput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.HashMap;


@RestController
public class NetworkController {

    @Autowired
    private NetworksService networkService;

    @RequestMapping(value = "/network", method = RequestMethod.GET)
    public ResponseEntity<Network> getNetwork(@RequestParam(value = "id")String id) {
        return networkService.getNetworkResponse(id);
    }

    //		ii. connect device to network (if no such network exists, create new one)
    //		PUT http://my-service/api/network/connect
    @RequestMapping(value = "/network/connect", method = RequestMethod.PUT, consumes = "application/json")
    public  ResponseEntity connect(@RequestBody ConnectingUser user) {
        return networkService.connectToNetwork(user);
    }

    //		iii. report network throughput Request:
    //		POST http://my-service/api/network/report
    @RequestMapping(value = "/network/report", method = RequestMethod.POST, consumes = "application/json")
    public  ResponseEntity report(@RequestBody ReportThroughput report) {
        return networkService.reportThroughput(report);
    }
}
