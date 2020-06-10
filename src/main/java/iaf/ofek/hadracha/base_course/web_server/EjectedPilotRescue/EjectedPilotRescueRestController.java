package iaf.ofek.hadracha.base_course.web_server.EjectedPilotRescue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ejectedPilotRescue")
public class EjectedPilotRescueRestController {
    SimulativeEjectedPilotRescueProvider simulativeEjectedPilotRescueProvider;

    public EjectedPilotRescueRestController(@Autowired SimulativeEjectedPilotRescueProvider simulativeEjectedPilotRescueProvider) {
        this.simulativeEjectedPilotRescueProvider = simulativeEjectedPilotRescueProvider;
    }

    @GetMapping("/infos")
    public List<EjectedPilotInfo> sendEjectionsInfo() {
        return simulativeEjectedPilotRescueProvider.getEjections();
    }

    @GetMapping("/takeResponsibility")
    public List<EjectedPilotInfo> takeResponsibility(int ejectionId, @CookieValue(value = "client-id", defaultValue = "") String clientId) {
        this.simulativeEjectedPilotRescueProvider.setRescuer(ejectionId, clientId);
        // TODO: Send closest aircraft to EjectedPilot

        return simulativeEjectedPilotRescueProvider.getEjections();
    }
}
