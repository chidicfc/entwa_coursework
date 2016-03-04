/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ctrl;

import chidi.entwa.ent.Organisation;
import chidi.entwa.pers.OrganisationFacade;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author chidi
 */
@FacesConverter(forClass = Organisation.class)
public class OrganisationConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            throw new Error("Selected organisation is empty or null!");
        }
        String id = null;
        Organisation organisation;
        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(value);
        while (m.find()) {
            id = m.group(1);
        }
        if (id != null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            OrganisationBean organisationCtrl = (OrganisationBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "organisationBean");
            OrganisationFacade organisationFacade = organisationCtrl.getFacade();
            Long idLong = Long.decode(id);
            organisation = organisationFacade.find(idLong);
            return organisation;
        } else {
            throw new ConverterException(new FacesMessage("Can't find organisation"));
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Organisation) {
            return value.toString();
        } else {
            throw new Error("object is not of type Organisation");
        }
    }

}
