package br.com.localizaja.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesMessageUtil {

    public static void addMenssagem(Severity severity, String message) {
        FacesMessage facesMessage = new FacesMessage(severity, "Mensagem", message);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage("messages", facesMessage);
    }

    public static void addMensagemInfo(String message) {
        addMenssagem(FacesMessage.SEVERITY_INFO, message);
    }

    public static void addMensagemError(String message) {
        addMenssagem(FacesMessage.SEVERITY_ERROR, message);
    }

    public static void addMensagemWarn(String message) {
        addMenssagem(FacesMessage.SEVERITY_WARN, message);
    }

}
