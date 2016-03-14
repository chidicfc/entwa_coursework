public class SessionService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public ExternalContext getExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    public String getCurrentUser(){
        return getExternalContext().getRemoteUser();
    }
}
