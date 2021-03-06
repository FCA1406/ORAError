package br.com.decade.oraerror.mobile;

import br.com.decade.oraerror.database.ConnectionFactory;
import br.com.decade.oraerror.server.ORAErrorBO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;

public class ORAErrorDC {
    private ORAErrorBO[] ORAErrorVO;

    private ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public ORAErrorDC() {
        super();

        ORAErrorVO = getORAErrorDB(new String(""));
    }

    public void setORAErrorVO(ORAErrorBO[] ORAErrorVO) {
        this.ORAErrorVO = ORAErrorVO;
        providerChangeSupport.fireProviderRefresh("ORAErrorVO");
    }

    public ORAErrorBO[] getORAErrorVO() {
        return ORAErrorVO;
    }

    private ORAErrorBO[] getORAErrorDB(String findString) {
        List listError = new ArrayList();

        String doDML;

        if (findString.equals("")) {
            doDML = "SELECT code, erro, cause, action, favorite FROM ora_error WHERE favorite = 'Y' ORDER BY code;";
        } else {
            doDML = "SELECT code, erro, cause, action, favorite FROM ora_error WHERE code LIKE '%" + findString + "%' OR erro LIKE '%" + findString + "%' ORDER BY code;";
        }

        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            Statement stORAError = conn.createStatement();
            ResultSet rsORAError = stORAError.executeQuery(doDML);

            while (rsORAError.next()) {
                ORAErrorBO oraError = new ORAErrorBO();

                oraError.setCode(rsORAError.getString("code"));
                oraError.setErro(rsORAError.getString("erro"));
                oraError.setCause(rsORAError.getString("cause"));
                oraError.setAction(rsORAError.getString("action"));
                oraError.setFavorite(rsORAError.getString("favorite"));

                listError.add(oraError);
            }
        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());

            AdfmfContainerUtilities.invokeContainerJavaScriptFunction("main"
                                                                     ,"navigator.notification.alert"
                                                                     ,new Object[] { "DB: IS DOWN OR DEAD", "null", "Warning", "OK" });
        }

        return (ORAErrorBO[]) listError.toArray(new ORAErrorBO[listError.size()]);
    }

    public void findError (String oraError) {
        setORAErrorVO(getORAErrorDB(oraError));
    }

    public void makeFavorite (String code, String favorite) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement psDML = conn.prepareStatement("UPDATE ora_error SET favorite = ? WHERE code = ?;");

            psDML.setString(1, favorite);
            psDML.setString(2, code);

            psDML.execute();

            conn.commit();
        } catch (Exception ex) {
            Utility.ApplicationLogger.severe(ex.getMessage());

            AdfmfContainerUtilities.invokeContainerJavaScriptFunction("main"
                                                                     ,"navigator.notification.alert"
                                                                     ,new Object[] { "DB: IS DOWN OR DEAD", "null", "Warning", "OK" });
        }
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
}
