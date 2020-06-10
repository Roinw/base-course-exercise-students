package iaf.ofek.hadracha.base_course.web_server.EjectedPilotRescue;

import iaf.ofek.hadracha.base_course.web_server.Data.CrudDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulativeEjectedPilotRescueProvider implements EjectedPilotRescueProvider {

    CrudDataBase dataBase;

    public SimulativeEjectedPilotRescueProvider(@Autowired CrudDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public List<EjectedPilotInfo> getEjections() {
        return dataBase.getAllOfType(EjectedPilotInfo.class);
    }

    @Override
    public void setRescuer(int ejectionId, String clientId) {
        EjectedPilotInfo ejectedPilotInfo = dataBase.getByID(ejectionId, EjectedPilotInfo.class);
        if (ejectedPilotInfo.rescuedBy == null) {
            ejectedPilotInfo.rescuedBy = clientId;
            dataBase.update(ejectedPilotInfo);
        }
    }
}
