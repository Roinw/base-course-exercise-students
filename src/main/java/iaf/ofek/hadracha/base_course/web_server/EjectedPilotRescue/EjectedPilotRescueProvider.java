package iaf.ofek.hadracha.base_course.web_server.EjectedPilotRescue;

import java.util.List;

public interface EjectedPilotRescueProvider {
        List<EjectedPilotInfo> getEjections();

        void chooseClientInCharge(int ejectionId, String clientId);

        void allocateAirplanesForRescue(int ejectionId, String rescuerId);
}
