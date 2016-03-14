public class LogOutBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public LogOutBean() {
    }

    public String logout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);

        if (session != null) {
            session.invalidate();
        }

        return "/index.xhtml?faces-redirect=true";
    }

}
