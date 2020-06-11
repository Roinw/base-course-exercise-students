package iaf.ofek.hadracha.base_course.web_server.EjectedPilotRescue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejectedPilotRescue")
public class EjectedPilotRescueRestController {

    EjectedPilotRescueProvider ejectedPilotRescueProvider;

    public EjectedPilotRescueRestController(@Autowired EjectedPilotRescueProvider ejectedPilotRescueProvider) {
        this.ejectedPilotRescueProvider = ejectedPilotRescueProvider;
    }

    @GetMapping("/infos")
    public List<EjectedPilotInfo> sendEjectionsInfo() {
        return ejectedPilotRescueProvider.getEjections();
    }

    @GetMapping("/takeResponsibility")
    public List<EjectedPilotInfo> takeResponsibility(
            @RequestParam("ejectionId") int ejectionId,
            @CookieValue(value = "client-id", defaultValue = "") String clientId) {
        this.ejectedPilotRescueProvider.allocateAirplanesForRescue(ejectionId, clientId);
        this.ejectedPilotRescueProvider.chooseClientInCharge(ejectionId, clientId);

        return ejectedPilotRescueProvider.getEjections();
    }
}
