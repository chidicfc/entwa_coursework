/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chidi.entwa.ent;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author chidi
 */
@Embeddable
public class ContactPerson implements Serializable {
    private String contactName;
    private String telephoneNumber;
    private String emailAddress;
    
}
