package iaf.ofek.hadracha.base_course.web_server.EjectedPilotRescue;

import iaf.ofek.hadracha.base_course.web_server.Data.CrudDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjectedPilotRescueProvider {

    AirplanesAllocationManager airplanesAllocationManager;
    CrudDataBase dataBase;

    public EjectedPilotRescueProvider(@Autowired AirplanesAllocationManager airplanesAllocationManager,
                                      @Autowired CrudDataBase dataBase) {
        this.airplanesAllocationManager = airplanesAllocationManager;
        this.dataBase = dataBase;
    }

    public List<EjectedPilotInfo> getEjections() {
        return dataBase.getAllOfType(EjectedPilotInfo.class);
    }

    public void chooseClientInCharge(int ejectionId, String clientId) {
        EjectedPilotInfo ejectedPilotInfo = dataBase.getByID(ejectionId, EjectedPilotInfo.class);
        if (ejectedPilotInfo.getRescuedBy() == null) {
            ejectedPilotInfo.setRescuedBy(clientId);
            dataBase.update(ejectedPilotInfo);
        }
    }

    public void allocateAirplanesForRescue(int ejectionId, String rescuerId) {
        EjectedPilotInfo ejectedPilotInfo = dataBase.getByID(ejectionId, EjectedPilotInfo.class);
        if (ejectedPilotInfo.getRescuedBy() == null) {
            airplanesAllocationManager.allocateAirplanesForEjection(ejectedPilotInfo, rescuerId);
        }
    }
}
